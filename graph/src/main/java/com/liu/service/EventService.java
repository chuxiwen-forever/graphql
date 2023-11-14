package com.liu.service;

import com.liu.param.EventInput;
import com.liu.vo.EventVO;

import java.util.List;

public interface EventService {

    /**
     * 获取事件列表
     *
     * @return eventVo列表
     */
    List<EventVO> getEventList();

    /**
     * 通过前端输入新增事件
     *
     * @param eventInput 前端输入
     * @return eventVo数据
     */
    EventVO addEvent(EventInput eventInput, int userId);

    /**
     * 根据创建用户id创建event类型
     *
     * @param creatorId 创建用户id
     * @return event集合
     */
    List<EventVO> getEventListByCreatorId(String creatorId);
}
