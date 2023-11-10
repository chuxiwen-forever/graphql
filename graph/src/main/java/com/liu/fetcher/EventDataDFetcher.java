package com.liu.fetcher;

import com.liu.type.Event;
import com.liu.type.EventInput;
import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsMutation;
import com.netflix.graphql.dgs.DgsQuery;
import com.netflix.graphql.dgs.InputArgument;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@DgsComponent
public class EventDataDFetcher {

    private final List<Event> events = new ArrayList<>();

    @DgsQuery
    public List<Event> events() {
        return events;
    }

    @DgsMutation
    public Event createEvent(@InputArgument(name = "eventInput") EventInput input) {
        Event newEvent = new Event();
        newEvent.setId(UUID.randomUUID().toString());
        BeanUtils.copyProperties(input, newEvent);
        events.add(newEvent);
        return newEvent;
    }
}
