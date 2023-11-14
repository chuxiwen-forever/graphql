package com.liu.repository;

import com.liu.entity.User;

import java.util.Collection;
import java.util.List;
import java.util.Map;

public interface UserRepository {
    /**
     * 通过邮箱查询用户
     * @param email 用户邮箱
     * @return 返回用户信息
     */
    User getUserByEmail(String email);

    /**
     * 根据用户信息新建一个用户
     * @param user 未填充的用户
     * @return 填充后的用户
     */
    User addOneUser(User user);

    /**
     * 通过id查询user信息
     * @param id id
     * @return user信息
     */
    User getUserById(int id);

    /**
     * 根据用户id集合查询用户集合
     * @param collection 用户id集合
     * @return k: 用户id v: 用户信息
     */
    Map<Integer,User> selectUsersByIds(Collection<Integer> collection);

    /**
     * 获取用户列表
     * @return 用户集合
     */
    List<User> getUserList();
}
