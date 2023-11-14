package com.liu.DO;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

@Data
@TableName("tb_booking")
public class BookingDO {

    @TableId(type = IdType.AUTO)
    private int id;

    private int userId;

    private int eventId;

    private Date createdAt;

    private Date updatedAt;
}
