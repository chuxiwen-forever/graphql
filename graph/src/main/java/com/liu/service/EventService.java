package com.liu.service;

import com.liu.param.EventInput;
import com.liu.vo.EventVO;

import java.util.List;

public interface EventService {

    /**
     * 获取事件列表
     * @return eventVo列表
     */
    List<EventVO> getEventList();

    /**
     * 通过前端输入新增事件
     * @param eventInput 前端输入
     * @return eventVo数据
     */
    EventVO addEvent(EventInput eventInput);
}
