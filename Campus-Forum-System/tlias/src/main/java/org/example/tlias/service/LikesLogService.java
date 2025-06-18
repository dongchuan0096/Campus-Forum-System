package org.example.tlias.service;

import org.example.tlias.pojo.LikesLog;

import java.time.LocalDateTime;

public interface LikesLogService {
    Integer like(Integer userId,Integer Id,Integer at);
    boolean isLiked(Integer userId, Integer Id ,Integer at);
    Integer getLikes (Integer likedAtId,Integer Dayago);
}
