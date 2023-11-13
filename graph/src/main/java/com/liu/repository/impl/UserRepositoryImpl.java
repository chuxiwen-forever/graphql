package com.liu.repository.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.liu.DO.UserDO;
import com.liu.converter.UserConverter;
import com.liu.entity.User;
import com.liu.mapper.UserMapper;
import com.liu.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepositoryImpl implements UserRepository {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserConverter userConverter;

    @Override
    public User getUserByEmail(String email) {
        LambdaQueryWrapper<UserDO> eq = new LambdaQueryWrapper<>();
        eq.eq(UserDO::getEmail, email);
        UserDO userDO = userMapper.selectOne(eq);
        return userConverter.toUser(userDO);
    }
}
