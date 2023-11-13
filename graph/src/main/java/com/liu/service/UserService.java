package com.liu.service;

import com.liu.param.UserInput;

public interface UserService {

    /**
     * 根据input查询用户是否存在
     * @param userInput 前端输入参数
     * @return 是否存在该用户
     */
    boolean checkIfExistUser(UserInput userInput);
}
