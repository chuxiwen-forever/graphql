package com.liu.service.impl;

import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.util.ObjectUtil;
import com.liu.entity.Event;
import com.liu.entity.User;
import com.liu.param.EventInput;
import com.liu.repository.EventRepository;
import com.liu.repository.UserRepository;
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
    private UserRepository userRepository;

    @Autowired
    private EventResolver eventResolver;

    @Override
    public List<EventVO> getEventList() {
        List<Event> eventList = eventRepository.getEventList();
        if (ArrayUtil.isEmpty(eventList)) {
            log.info("EventService.getEventList => eventList is empty");
            return Collections.emptyList();
        }
        return eventList.stream().map(eventResolver::toEventVO).collect(Collectors.toList());
    }

    @Override
    public EventVO addEvent(EventInput eventInput, int userId) {
        if (ObjectUtil.isEmpty(eventInput)) {
            throw new RuntimeException("EventService.addEvent => eventInput is empty");
        }
        User userById = userRepository.getUserById(userId);
        if (ObjectUtil.isEmpty(userById)) {
            throw new RuntimeException("EventService.addEvent => Don't have target user");
        }
        Event event = eventResolver.toEvent(eventInput);
        Event insertEvent = eventRepository.insertEvent(event);
        return eventResolver.toEventVO(insertEvent);
    }

    @Override
    public List<EventVO> getEventListByCreatorId(String creatorId) {
        List<Event> events = eventRepository.getEventListByCreatorId(Integer.parseInt(creatorId));
        if (ArrayUtil.isEmpty(events)) {
            log.info("EventService.getEventListByCreatorId ==> events is empty");
            return Collections.emptyList();
        }
        return events.stream().map(eventResolver::toEventVO).collect(Collectors.toList());
    }
}
