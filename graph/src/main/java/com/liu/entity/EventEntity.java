package com.liu.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.liu.type.EventInput;
import com.liu.util.DateUtil;
import lombok.Data;

import java.util.Date;

@TableName(value = "event")
@Data
public class EventEntity {

    @TableId(type = IdType.AUTO)
    private Integer id;

    private String title;

    private String description;

    private Double price;

    private Date date;

    public static EventEntity fromEventInput(EventInput input) {
        EventEntity eventEntity = new EventEntity();
        eventEntity.setDate(DateUtil.convertISOStringToDate(input.getDate()));
        eventEntity.setTitle(input.getTitle());
        eventEntity.setPrice(input.getPrice());
        eventEntity.setDescription(input.getDescription());
        return eventEntity;
    }
}
