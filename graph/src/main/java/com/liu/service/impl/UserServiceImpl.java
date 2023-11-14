package com.liu.service.impl;

import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.util.ObjectUtil;
import com.liu.entity.User;
import com.liu.param.UserInput;
import com.liu.repository.UserRepository;
import com.liu.resolver.UserResolver;
import com.liu.service.UserService;
import com.liu.vo.UserVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
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

    @Override
    public UserVO getUserById(int creatorId) {
        User userById = userRepository.getUserById(creatorId);
        if (ObjectUtil.isEmpty(userById)) {
            log.info("UserService.getUserById ==> userById is empty");
            return new UserVO();
        }
        return userResolver.toUserVO(userById);
    }

    @Override
    public List<UserVO> getUserList() {
        List<User> userList = userRepository.getUserList();
        if (ArrayUtil.isEmpty(userList)) {
            log.info("UserService.getUserList ==> userList is empty");
            return Collections.emptyList();
        }
        return userList.stream().map(userResolver::toUserVO).collect(Collectors.toList());
    }
}
