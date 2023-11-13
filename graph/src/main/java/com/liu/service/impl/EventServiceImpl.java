package com.liu.service.impl;

import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.util.ObjectUtil;
import com.liu.entity.Event;
import com.liu.param.EventInput;
import com.liu.repository.EventRepository;
import com.liu.resolver.EventResolver;
import com.liu.service.EventService;
import com.liu.vo.EventVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class EventServiceImpl implements EventService {

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private EventResolver eventResolver;

    @Override
    public List<EventVO> getEventList() {
        List<Event> eventList = eventRepository.getEventList();
        if (ArrayUtil.isEmpty(eventList)) {
            log.info("EventService.getEventList => eventList is empty");
            return Collections.emptyList();
        }
        return eventList.stream().map((item) -> eventResolver.toEventVO(item)).collect(Collectors.toList());
    }

    @Override
    public EventVO addEvent(EventInput eventInput) {
        if (ObjectUtil.isEmpty(eventInput)) {
            throw new RuntimeException("EventService.addEvent => eventInput is empty");
        }
        Event event = eventResolver.toEvent(eventInput);
        Event insertEvent = eventRepository.insertEvent(event);
        return eventResolver.toEventVO(insertEvent);
    }
}
