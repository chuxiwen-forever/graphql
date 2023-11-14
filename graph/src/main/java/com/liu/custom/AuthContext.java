package com.liu.custom;

import com.liu.entity.User;
import com.netflix.graphql.dgs.DgsDataFetchingEnvironment;
import com.netflix.graphql.dgs.context.DgsContext;
import lombok.Data;

@Data
public class AuthContext {
    private User user;
    private boolean tokenInvalid;

    public static AuthContext checkAuthAndReturnContext(DgsDataFetchingEnvironment dfe) {
        AuthContext context = DgsContext.getCustomContext(dfe);
        context.ensureAuthenticated();
        return context;
    }

    private void ensureAuthenticated() {
        if (tokenInvalid) throw new RuntimeException("认证信息无效");
        if (user == null) throw new RuntimeException("未登录，请先登录");
    }
}
