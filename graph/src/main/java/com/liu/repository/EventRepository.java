package com.liu.repository;

import com.liu.entity.Event;

import java.util.List;

public interface EventRepository {

    /**
     * 获得事件列表
     * @return event列表
     */
    List<Event> getEventList();

    /**
     * 插入一个新事件
     * @param event event
     * @return 填充后的事件对象
     */
    Event insertEvent(Event event);
}
