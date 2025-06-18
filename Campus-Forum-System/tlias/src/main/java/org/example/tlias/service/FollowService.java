package org.example.tlias.service;

import org.example.tlias.pojo.Follow;
import org.example.tlias.pojo.Pagebean;

import java.time.LocalDateTime;
import java.util.List;

public interface FollowService {
    boolean insertFollow(Integer userId,Integer beFollowedId);

    boolean isFollowed(Integer userId, Integer beFollowedId);
    List<Integer> getFollowsByuserId(Integer userId);
    Pagebean getFollowers(Integer userId, Integer page, Integer pageSize);
    public Integer getFollowersCountByuserId(Integer userId,Integer dayAgo);
    Integer getFollowersCountByuserId(Integer userId);
}
