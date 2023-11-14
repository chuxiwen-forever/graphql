package com.liu.custom;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.liu.constants.AppConstants;
import com.liu.entity.User;
import com.liu.repository.UserRepository;
import com.liu.service.UserService;
import com.liu.util.TokenUtil;
import com.liu.vo.UserVO;
import com.netflix.graphql.dgs.context.DgsCustomContextBuilderWithRequest;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.WebRequest;

import java.util.Map;

@Component
@Slf4j
public class AuthContextBuilder implements DgsCustomContextBuilderWithRequest<AuthContext> {

    @Autowired
    private UserRepository userRepository;

    @Override
    public AuthContext build(@Nullable Map<String, ?> map, @Nullable HttpHeaders httpHeaders, @Nullable WebRequest webRequest) {
        log.info("Building Auth Context...");
        AuthContext authContext = new AuthContext();
        if (!httpHeaders.containsKey(AppConstants.AUTHORIZATION_HEADER)) {
            log.info("用户未认证...");
            return authContext;
        }

        String token = httpHeaders.getFirst(AppConstants.AUTHORIZATION_HEADER);

        if (token != null) {
            token = token.replace(AppConstants.TOKEN_PREFIX, "");
        }

        Integer userId = null;
        try {
            userId = TokenUtil.verifyToken(token);
        } catch (Exception e) {
            authContext.setTokenInvalid(true);
            return authContext;
        }
        User userById = userRepository.getUserById(userId);
        if (ObjectUtil.isEmpty(userById)) {
            authContext.setTokenInvalid(true);
            return authContext;
        }
        log.info("用户校验成功, userId = {}", userId);
        authContext.setUser(userById);
        return authContext;
    }
}
