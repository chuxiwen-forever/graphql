package com.liu.fetcher;

import com.liu.param.EventInput;
import com.liu.service.EventService;
import com.liu.vo.EventVO;
import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsMutation;
import com.netflix.graphql.dgs.DgsQuery;
import com.netflix.graphql.dgs.InputArgument;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@DgsComponent
public class EventDataDFetcher {
    @Autowired
    private EventService eventService;

    @DgsQuery
    public List<EventVO> events() {
        return eventService.getEventList();
    }

    @DgsMutation
    public EventVO createEvent(@InputArgument(name = "eventInput") EventInput input) {
        return eventService.addEvent(input);
    }
}
