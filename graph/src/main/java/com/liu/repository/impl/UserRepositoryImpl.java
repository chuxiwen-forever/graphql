package com.liu.repository.impl;

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
}
