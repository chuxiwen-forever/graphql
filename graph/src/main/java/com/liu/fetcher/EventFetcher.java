package com.liu.fetcher;

import com.liu.custom.AuthContext;
import com.liu.param.EventInput;
import com.liu.service.EventService;
import com.liu.service.UserService;
import com.liu.vo.EventVO;
import com.liu.vo.UserVO;
import com.netflix.graphql.dgs.*;
import org.dataloader.DataLoader;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@DgsComponent
public class EventFetcher {
    @Autowired
    private EventService eventService;

    @Autowired
    private UserService userService;

    @DgsQuery
    public List<EventVO> events(DgsDataFetchingEnvironment dfe) {
        AuthContext.checkAuthAndReturnContext(dfe);
        return eventService.getEventList();
    }

    @DgsMutation
    public EventVO createEvent(@InputArgument(name = "eventInput") EventInput input, DgsDataFetchingEnvironment dfe) {
        AuthContext context = AuthContext.checkAuthAndReturnContext(dfe);
        return eventService.addEvent(input, Integer.parseInt(context.getUser().getId()));
    }

    // parentType是schema.graphqls文件中定义的类型名称
    @DgsData(parentType = "Event")
    public CompletableFuture<UserVO> creator(DgsDataFetchingEnvironment dfe) {
        EventVO source = dfe.getSource();
        DataLoader<Integer, UserVO> userVoList = dfe.getDataLoader("userVo");
        return userVoList.load(source.getCreatorId());
    }
}
