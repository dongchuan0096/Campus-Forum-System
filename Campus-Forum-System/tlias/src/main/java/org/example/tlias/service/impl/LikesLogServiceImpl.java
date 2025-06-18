package org.example.tlias.service.impl;

import org.example.tlias.mapper.LikesLogMapper;
import org.example.tlias.pojo.LikesLog;
import org.example.tlias.service.LikesLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
@Service
public class LikesLogServiceImpl implements LikesLogService {
    @Autowired
    LikesLogMapper likesLogMapper;
    @Override
    public Integer like(Integer userId,Integer Id,Integer at) {
       LikesLog liksesLog = new LikesLog();
       liksesLog.setUserId(userId);
       liksesLog.setLikedAt(at);
       liksesLog.setLikedAtId(Id);
       liksesLog.setCreatedAt(LocalDateTime.now());
       return likesLogMapper.insertLike(liksesLog);
    }
    public boolean isLiked(Integer userId,Integer Id ,Integer at){
             if( likesLogMapper.isLiked(userId,Id,at)==null){
                 return  false;
             }
             else{
                 return true;
             }
    }

    @Override
    public Integer getLikes(Integer likedAtId, Integer dayAgo) {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime dateTime;
        if(dayAgo==0){
            dateTime = now.minusYears(1);
        }
        else{
            dateTime = now.minusDays(dayAgo);
        }
        return likesLogMapper.getLikes(likedAtId,1,dateTime);
    }
}
