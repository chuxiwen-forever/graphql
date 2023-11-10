package com.liu.repository.impl;

import com.liu.DO.EventDO;
import com.liu.converter.EventConverter;
import com.liu.entity.Event;
import com.liu.mapper.EventMapper;
import com.liu.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public class EventRepositoryImpl implements EventRepository {

    @Autowired
    private EventMapper eventMapper;

    @Autowired
    private EventConverter eventConverter;

    @Override
    public List<Event> getEventList() {
        List<EventDO> eventDOList = eventMapper.selectList(null);
        return eventDOList.stream().map(item -> eventConverter.toEvent(item)).collect(Collectors.toList());
    }

    @Override
    public Event insertEvent(Event event) {
        EventDO eventDO = eventConverter.toEventDO(event);
        eventMapper.insert(eventDO);
        return eventConverter.toEvent(eventDO);
    }
}
