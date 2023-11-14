package com.liu.custom;

import com.liu.entity.User;
import lombok.Data;

@Data
public class AuthContext {
    private User user;
    private boolean tokenInvalid;
}
