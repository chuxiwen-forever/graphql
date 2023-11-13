package com.liu.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.liu.entity.User;
import com.liu.param.UserInput;
import com.liu.repository.UserRepository;
import com.liu.resolver.UserResolver;
import com.liu.service.UserService;
import com.liu.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserResolver userResolver;

    @Override
    public boolean checkIfExistUser(UserInput userInput) {
        if (ObjectUtil.isEmpty(userInput)) {
            throw new RuntimeException("UserService.checkIfExistUser => userInput is empty");
        }
        User user = userRepository.getUserByEmail(userInput.getEmail());
        return user != null;
    }

    @Override
    public UserVO addUser(UserInput userInput) {
        if (ObjectUtil.isEmpty(userInput)) {
            throw new RuntimeException("UserService.addUser => userInput is empty");
        }
        User user = userResolver.toUser(userInput);
        user.setPassword(passwordEncoder.encode(userInput.getPassword()));
        user = userRepository.addOneUser(user);
        return userResolver.toUserVO(user);
    }
}
