package com.liu.resolver;

import com.liu.entity.User;
import com.liu.param.UserInput;
import com.liu.vo.UserVO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(
        componentModel = "spring"
)
public interface UserResolver {

    @Mapping(target = "password", ignore = true)
    UserVO toUserVO(User user);

    @Mapping(target = "password", ignore = true)
    User toUser(UserInput userInput);
}
