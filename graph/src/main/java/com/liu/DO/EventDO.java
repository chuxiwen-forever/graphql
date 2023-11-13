package com.liu.DO;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

@TableName(value = "tb_event")
@Data
public class EventDO {

    @TableId(type = IdType.AUTO)
    private Integer id;

    private String title;

    private String description;

    private Double price;

    private Date date;

    private Integer creatorId;
}
