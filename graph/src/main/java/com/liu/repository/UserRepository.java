package com.liu.repository;

import com.liu.entity.User;

public interface UserRepository {
    /**
     * 通过邮箱查询用户
     * @param email 用户邮箱
     * @return 返回用户信息
     */
    User getUserByEmail(String email);
}
