package com.liu.repository;

import com.liu.entity.User;

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
}
