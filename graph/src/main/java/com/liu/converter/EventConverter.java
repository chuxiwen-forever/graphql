package com.liu.converter;

import com.liu.DO.EventDO;
import com.liu.entity.Event;
import org.mapstruct.Mapper;

@Mapper(
        componentModel = "spring"
)
public interface EventConverter {

    EventDO toEventDO(Event event);

    Event toEvent(EventDO eventDO);
}
