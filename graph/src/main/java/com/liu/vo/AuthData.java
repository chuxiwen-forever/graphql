package com.liu.vo;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AuthData {
    private int userId;
    private String token;
    private int tokenExpiration;
}
