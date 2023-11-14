package com.liu.repository.impl;

import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.liu.DO.EventDO;
import com.liu.converter.EventConverter;
import com.liu.entity.Event;
import com.liu.mapper.EventMapper;
import com.liu.repository.EventRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Repository
@Slf4j
public class EventRepositoryImpl implements EventRepository {

    @Autowired
    private EventMapper eventMapper;

    @Autowired
    private EventConverter eventConverter;

    @Override
    public List<Event> getEventList() {
        List<EventDO> eventDOList = eventMapper.selectList(null);
        if (ArrayUtil.isEmpty(eventDOList)) {
            log.info("EventRepository.getEventList => eventDOList is empty");
            return Collections.emptyList();
        }
        return eventDOList.stream().map(item -> eventConverter.toEvent(item)).collect(Collectors.toList());
    }

    @Override
    public Event insertEvent(Event event) {
        if (ObjectUtil.isEmpty(event)) {
            throw new RuntimeException("EventRepository.insertEvent => event is empty");
        }
        EventDO eventDO = eventConverter.toEventDO(event);
        eventMapper.insert(eventDO);
        return eventConverter.toEvent(eventDO);
    }

    @Override
    public List<Event> getEventListByCreatorId(int creatorId) {
        LambdaQueryWrapper<EventDO> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(EventDO::getCreatorId, creatorId);
        List<EventDO> eventDOList = eventMapper.selectList(wrapper);
        if (ArrayUtil.isEmpty(eventDOList)) {
            log.info("EventRepository.getEventListByCreatorId ==> eventDOList is empty");
            return Collections.emptyList();
        }
        return eventDOList.stream()
                .map(eventConverter::toEvent)
                .collect(Collectors.toList());
    }

    @Override
    public Event getEventById(int id) {
        if (id == 0) {
            log.info("EventRepository.getEventById ==> id 不合法");
            return null;
        }
        LambdaQueryWrapper<EventDO> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(EventDO::getId, id);
        EventDO eventDO = eventMapper.selectOne(wrapper);
        if (ObjectUtil.isEmpty(eventDO)) {
            log.info("EventRepository.getEventById ==> event 不存在");
            return null;
        }
        return eventConverter.toEvent(eventDO);
    }
}
