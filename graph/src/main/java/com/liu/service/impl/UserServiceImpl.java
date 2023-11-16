package com.liu.service.impl;

import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.util.ObjectUtil;
import com.liu.constants.AppConstants;
import com.liu.entity.User;
import com.liu.param.LoginInput;
import com.liu.param.UserInput;
import com.liu.repository.UserRepository;
import com.liu.resolver.UserResolver;
import com.liu.service.UserService;
import com.liu.util.TokenUtil;
import com.liu.vo.AuthData;
import com.liu.vo.UserVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collection;
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
    public UserVO getUserById(int id) {
        User userById = userRepository.getUserById(id);
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

    @Override
    public AuthData loginByEmail(LoginInput loginInput) {
        User user = userRepository.getUserByEmail(loginInput.getEmail());
        if (ObjectUtil.isEmpty(user)) {
            throw new RuntimeException("不存在该email注册用户");
        }
        boolean matches = passwordEncoder.matches(loginInput.getPassword(), user.getPassword());
        if (!matches) {
            throw new RuntimeException("用户名或者用户密码错误");
        }
        int userId = Integer.parseInt(user.getId());
        String token = TokenUtil.signToken(userId, AppConstants.TOKEN_VALID_HOUR);
        return AuthData.builder()
                .token(token)
                .tokenExpiration(AppConstants.TOKEN_VALID_HOUR)
                .userId(userId)
                .build();
    }

    @Override
    public List<UserVO> getAllUserVOListByIds(Collection<Integer> list) {
        if (ArrayUtil.isEmpty(list)) {
            log.info("UserService.getAllUserVOListByIds ==> list is empty");
            return Collections.emptyList();
        }
        List<User> userList = userRepository.selectUsersByIds(list);
        if (ArrayUtil.isEmpty(userList)) {
            log.info("UserService.getAllUserVOListByIds ==> userList is empty");
            return Collections.emptyList();
        }
        return userList.stream().map(userResolver::toUserVO).collect(Collectors.toList());
    }
}
