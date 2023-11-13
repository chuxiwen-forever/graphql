package com.liu.DO;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName(value = "tb_user")
public class UserDO {

    @TableId(type = IdType.AUTO)
    private Integer id;
    private String email;
    private String password;
}
