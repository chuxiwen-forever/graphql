package com.liu.fetcher;

import com.liu.param.UserInput;
import com.liu.service.UserService;
import com.liu.vo.UserVO;
import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsMutation;
import com.netflix.graphql.dgs.InputArgument;
import org.springframework.beans.factory.annotation.Autowired;

@DgsComponent
public class UserFetcher {

    @Autowired
    private UserService userService;

    @DgsMutation
    public UserVO createUser(@InputArgument(name = "userInput") UserInput userInput) {
        ensureUserNotExists(userInput);
        return null;
    }

    private void ensureUserNotExists(UserInput userInput) {
        boolean ifExistUser = userService.checkIfExistUser(userInput);
        if (ifExistUser) {
            throw new RuntimeException("该用户已经注册过");
        }
    }
}
