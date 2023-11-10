package com.liu.service;

import com.liu.param.EventInput;
import com.liu.vo.EventVO;

import java.util.List;

public interface EventService {

    List<EventVO> getEventList();

    EventVO addEvent(EventInput eventInput);
}
