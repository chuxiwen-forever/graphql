package com.liu.repository.impl;

import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.liu.DO.UserDO;
import com.liu.converter.UserConverter;
import com.liu.entity.User;
import com.liu.mapper.UserMapper;
import com.liu.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Repository
@Slf4j
public class UserRepositoryImpl implements UserRepository {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserConverter userConverter;

    @Override
    public User getUserByEmail(String email) {
        if (StrUtil.isBlankIfStr(email)) {
            throw new RuntimeException("UserRepository.getUserByEmail => email is empty");
        }
        LambdaQueryWrapper<UserDO> eq = new LambdaQueryWrapper<>();
        eq.eq(UserDO::getEmail, email);
        UserDO userDO = userMapper.selectOne(eq);
        if (ObjectUtil.isEmpty(userDO)) {
            log.info("UserRepository.getUserByEmail => Don' t have this user");
            return null;
        }
        return userConverter.toUser(userDO);
    }

    @Override
    public User addOneUser(User user) {
        if (ObjectUtil.isEmpty(user)) {
            throw new RuntimeException("UserRepository.addOneUser => user is empty");
        }
        UserDO userDO = userConverter.toUserDO(user);
        userMapper.insert(userDO);
        return userConverter.toUser(userDO);
    }

    @Override
    public User getUserById(int id) {
        UserDO userDO = userMapper.selectById(id);
        if (ObjectUtil.isEmpty(userDO)) {
            log.info("UserRepository.getUserById => userDO is empty");
            return null;
        }
        return userConverter.toUser(userDO);
    }

    @Override
    public Map<Integer, User> selectUsersByIds(Collection<Integer> collection) {
        if (ArrayUtil.isEmpty(collection)) {
            log.info("UserRepository.selectUsersByIds => collection is empty");
            return Collections.emptyMap();
        }
        List<UserDO> userDOList = userMapper.selectBatchIds(collection);
        if (ArrayUtil.isEmpty(userDOList)) {
            log.info("UserRepository.selectUsersByIds => userDOList is empty");
            return Collections.emptyMap();
        }
        return userDOList.stream()
                .collect(Collectors.toMap(UserDO::getId, userConverter::toUser));
    }
}
