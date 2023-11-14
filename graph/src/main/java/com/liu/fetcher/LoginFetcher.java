package com.liu.fetcher;

import com.liu.param.LoginInput;
import com.liu.service.UserService;
import com.liu.vo.AuthData;
import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsQuery;
import com.netflix.graphql.dgs.InputArgument;
import org.springframework.beans.factory.annotation.Autowired;

@DgsComponent
public class LoginFetcher {

    @Autowired
    private UserService userService;

    @DgsQuery
    public AuthData login(@InputArgument(name = "loginInput") LoginInput loginInput) {
        return userService.loginByEmail(loginInput);
    }
}
