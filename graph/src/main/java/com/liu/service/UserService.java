package com.liu.service;

import com.liu.param.LoginInput;
import com.liu.param.UserInput;
import com.liu.vo.AuthData;
import com.liu.vo.UserVO;

import java.util.Collection;
import java.util.List;

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
     *
     * @param id 主键
     * @return 用户vo数据
     */
    UserVO getUserById(int id);

    /**
     * 获取用户列表
     *
     * @return 用户集合
     */
    List<UserVO> getUserList();

    /**
     * 根据前端输入传入判断是否可以登录
     *
     * @param loginInput 登录信息
     * @return 验证信息
     */
    AuthData loginByEmail(LoginInput loginInput);


    /**
     * 根据用户id集合查询所有用户数据
     * @param list 主键id
     * @return 用户集合
     */
    List<UserVO> getAllUserVOListByIds(Collection<Integer> list);
}
