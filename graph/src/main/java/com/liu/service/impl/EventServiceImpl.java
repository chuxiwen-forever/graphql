package com.liu.service.impl;

import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.util.ObjectUtil;
import com.liu.entity.Event;
import com.liu.entity.User;
import com.liu.param.EventInput;
import com.liu.repository.EventRepository;
import com.liu.repository.UserRepository;
import com.liu.resolver.EventResolver;
import com.liu.resolver.UserResolver;
import com.liu.service.EventService;
import com.liu.vo.EventVO;
import com.liu.vo.UserVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;
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

    @Autowired
    private UserResolver userResolver;

    @Override
    public List<EventVO> getEventList() {
        List<Event> eventList = eventRepository.getEventList();
        if (ArrayUtil.isEmpty(eventList)) {
            log.info("EventService.getEventList => eventList is empty");
            return Collections.emptyList();
        }

        Set<Integer> collect = eventList.stream().map(Event::getCreatorId)
                .collect(Collectors.toSet());
        Map<Integer, User> userMap = userRepository.selectUsersByIds(collect);

        if (MapUtil.isEmpty(userMap)) {
            throw new RuntimeException("EventService.getEventList => userMap is empty");
        }

        return eventList.stream().map((item) -> {
            EventVO eventVO = eventResolver.toEventVO(item);
            User user = userMap.get(item.getCreatorId());
            eventVO.setCreator(userResolver.toUserVO(user));
            return eventVO;
        }).collect(Collectors.toList());
    }

    @Override
    public EventVO addEvent(EventInput eventInput) {
        if (ObjectUtil.isEmpty(eventInput)) {
            throw new RuntimeException("EventService.addEvent => eventInput is empty");
        }
        User userById = userRepository.getUserById(eventInput.getCreatorId());
        if (ObjectUtil.isEmpty(userById)) {
            throw new RuntimeException("EventService.addEvent => Don't have target user");
        }
        Event event = eventResolver.toEvent(eventInput);
        Event insertEvent = eventRepository.insertEvent(event);
        EventVO eventVO = eventResolver.toEventVO(insertEvent);
        UserVO userVO = userResolver.toUserVO(userById);
        eventVO.setCreator(userVO);
        return eventVO;
    }
}
