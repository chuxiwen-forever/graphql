package com.liu.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.liu.entity.User;
import com.liu.param.UserInput;
import com.liu.repository.UserRepository;
import com.liu.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public boolean checkIfExistUser(UserInput userInput) {
        if (ObjectUtil.isEmpty(userInput)){
            throw new RuntimeException("UserService.checkIfExistUser => userInput is empty");
        }
        User user = userRepository.getUserByEmail(userInput.getEmail());
        return user == null;
    }
}
