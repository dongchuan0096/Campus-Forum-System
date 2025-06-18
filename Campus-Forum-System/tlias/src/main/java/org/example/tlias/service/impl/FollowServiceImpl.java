package org.example.tlias.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.example.tlias.mapper.FollowMapper;
import org.example.tlias.mapper.UserMapper;
import org.example.tlias.pojo.*;
import org.example.tlias.service.FollowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FollowServiceImpl implements FollowService {
    @Autowired
    FollowMapper followMapper;
    @Autowired
    UserMapper userMapper;
    @Override
    public boolean insertFollow(Integer userId, Integer beFollowedId) {
        Follow follow = new Follow();
        follow.setUserId(userId);
        follow.setBeFollowedId(beFollowedId);
        follow.setFollowAtTime(LocalDateTime.now());
        if(followMapper.insertFollow(follow)==1)
            return true;
        else
        return false;
    }

    @Override
    public boolean isFollowed(Integer userId, Integer beFollowedId) {
        if(followMapper.isFollowed(userId, beFollowedId)!=null){
            return true;
        }
        else
        return false;
    }

    @Override
    public List<Integer> getFollowsByuserId(Integer userId) {
        List<Integer> userIdList = followMapper.getFollowsByuserId(userId).stream()
                .map(Follow::getBeFollowedId) // 将每个Follow映射到其userId
                .collect(Collectors.toList()); // 收集到List<Integer>
        return userIdList;
    }

    @Override
    public Pagebean getFollowers(Integer userId,Integer page,Integer pageSize) {
        PageHelper.startPage(page, pageSize);
        List<Follow> followList = followMapper.getFollowers(userId);
        for (Follow follow : followList) {
            User user = userMapper.getByID(follow.getUserId());
            follow.setUser(user);
            if(follow.getUser().getIntroduction()==null){
                follow.getUser().setIntroduction("暂时还没有简介");
            }
        }
        PageInfo<Follow> p = new PageInfo<>(followList);
        Pagebean pagebean = new Pagebean(p.getTotal(), p.getList());
        return pagebean;
    }

    @Override
    public Integer getFollowersCountByuserId(Integer userId,Integer dayAgo) {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime dateTime;
        if(dayAgo==0){
             dateTime = now.minusYears(1);
        }
        else{
            dateTime = now.minusDays(dayAgo);
        }

        return followMapper.getFollowersCountBybeFollowedId(userId,dateTime);
    }

    @Override
    public Integer getFollowersCountByuserId(Integer userId) {
        return followMapper.getFollowersCountByuserId(userId);
    }
}
