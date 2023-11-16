package com.liu.fetcher.dataloader;

import com.liu.service.UserService;
import com.liu.vo.UserVO;
import com.netflix.graphql.dgs.DgsDataLoader;
import org.dataloader.BatchLoader;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

@DgsDataLoader(name = "userVo")
public class UserVODataLoader implements BatchLoader<Integer, UserVO> {

    @Autowired
    private UserService userService;

    @Override
    public CompletionStage<List<UserVO>> load(List<Integer> list) {
        return CompletableFuture.supplyAsync(() -> userService.getAllUserVOListByIds(list));
    }
}
