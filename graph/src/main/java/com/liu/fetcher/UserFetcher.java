package com.liu.fetcher;

import com.liu.custom.AuthContext;
import com.liu.param.UserInput;
import com.liu.service.EventService;
import com.liu.service.UserService;
import com.liu.vo.EventVO;
import com.liu.vo.UserVO;
import com.netflix.graphql.dgs.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@DgsComponent
public class UserFetcher {

    @Autowired
    private UserService userService;

    @Autowired
    private EventService eventService;

    @DgsMutation
    public UserVO createUser(@InputArgument(name = "userInput") UserInput userInput) {
        ensureUserNotExists(userInput);
        return userService.addUser(userInput);
    }

    @DgsQuery
    public List<UserVO> users(DgsDataFetchingEnvironment dfe) {
        AuthContext.checkAuthAndReturnContext(dfe);
        return userService.getUserList();
    }

    @DgsData(parentType = "User")
    public List<EventVO> createdEvents(DgsDataFetchingEnvironment dfe) {
        UserVO userVO = dfe.getSource();
        return eventService.getEventListByCreatorId(userVO.getId());
    }

    private void ensureUserNotExists(UserInput userInput) {
        boolean ifExistUser = userService.checkIfExistUser(userInput);
        if (ifExistUser) {
            throw new RuntimeException("该用户已经注册过");
        }
    }
}
