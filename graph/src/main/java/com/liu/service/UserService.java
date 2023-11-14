package com.liu.service;

import com.liu.param.UserInput;
import com.liu.vo.UserVO;

public interface UserService {

    /**
     * 根据input查询用户是否存在
     *
     * @param userInput 前端输入参数
     * @return 是否存在该用户
     */
    boolean checkIfExistUser(UserInput userInput);

    /**
     * 添加一名用户
     *
     * @param userInput 前端输入
     * @return 用户vo
     */
    UserVO addUser(UserInput userInput);

    /**
     * 根据用户主键id查询userVO
     * @param creatorId 主键
     * @return 用户vo数据
     */
    UserVO getUserById(int creatorId);
}
