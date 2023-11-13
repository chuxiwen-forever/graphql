package com.liu.converter;

import com.liu.DO.UserDO;
import com.liu.entity.User;
import org.mapstruct.Mapper;

@Mapper(
        componentModel = "spring"
)
public interface UserConverter {

    User toUser(UserDO userDO);

    UserDO toUserDO(User user);
}
