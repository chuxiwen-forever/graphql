package com.liu.service.impl;

import com.liu.entity.Event;
import com.liu.param.EventInput;
import com.liu.repository.EventRepository;
import com.liu.resolver.EventResolver;
import com.liu.service.EventService;
import com.liu.vo.EventVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EventServiceImpl implements EventService {

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private EventResolver eventResolver;

    @Override
    public List<EventVO> getEventList() {
        List<Event> eventList = eventRepository.getEventList();
        return eventList.stream().map((item) -> eventResolver.toEventVO(item)).collect(Collectors.toList());
    }

    @Override
    public EventVO addEvent(EventInput eventInput) {
        Event event = eventResolver.toEvent(eventInput);
        Event insertEvent = eventRepository.insertEvent(event);
        return eventResolver.toEventVO(insertEvent);
    }
}
