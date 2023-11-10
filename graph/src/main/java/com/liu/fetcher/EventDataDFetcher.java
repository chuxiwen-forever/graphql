package com.liu.fetcher;

import com.liu.entity.EventEntity;
import com.liu.mapper.EventEntityMapper;
import com.liu.type.Event;
import com.liu.type.EventInput;
import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsMutation;
import com.netflix.graphql.dgs.DgsQuery;
import com.netflix.graphql.dgs.InputArgument;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;

@DgsComponent
public class EventDataDFetcher {

    @Autowired
    private EventEntityMapper eventEntityMapper;

    @DgsQuery
    public List<Event> events() {
        List<EventEntity> eventEntities = eventEntityMapper.selectList(null);
        return eventEntities.stream()
                .map(Event::fromEntity).collect(Collectors.toList());
    }

    @DgsMutation
    public Event createEvent(@InputArgument(name = "eventInput") EventInput input) {
        EventEntity eventEntity = EventEntity.fromEventInput(input);
        eventEntityMapper.insert(eventEntity);
        return Event.fromEntity(eventEntity);
    }
}
