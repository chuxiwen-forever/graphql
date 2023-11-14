package com.liu.fetcher;

import com.liu.param.EventInput;
import com.liu.service.EventService;
import com.liu.service.UserService;
import com.liu.vo.EventVO;
import com.liu.vo.UserVO;
import com.netflix.graphql.dgs.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@DgsComponent
public class EventFetcher {
    @Autowired
    private EventService eventService;

    @Autowired
    private UserService userService;

    @DgsQuery
    public List<EventVO> events() {
        return eventService.getEventList();
    }

    @DgsMutation
    public EventVO createEvent(@InputArgument(name = "eventInput") EventInput input) {
        return eventService.addEvent(input);
    }

    @DgsData(parentType = "Event")
    public UserVO creator(DgsDataFetchingEnvironment dfe) {
        EventVO source = dfe.getSource();
        return userService.getUserById(source.getCreatorId());
    }
}
