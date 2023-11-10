package com.liu.repository;

import com.liu.entity.Event;

import java.util.List;

public interface EventRepository {

    List<Event> getEventList();

    Event insertEvent(Event event);
}
