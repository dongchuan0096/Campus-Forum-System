package org.example.tlias.service.impl;

import org.example.tlias.mapper.ReplyMapper;
import org.example.tlias.mapper.UserMapper;
import org.example.tlias.pojo.Reply;
import org.example.tlias.pojo.User;
import org.example.tlias.service.ReplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ReplyServiceImpl implements ReplyService {

    @Autowired
    ReplyMapper replyMapper;
    @Autowired
    UserMapper userMapper;
    @Override
    public Integer insertReply(Reply reply) {
        reply.setCreatedAt(LocalDateTime.now());
        return replyMapper.insertReply(reply);
    }

    @Override
    public List<Reply> getRepliesByCommentId(Integer commentId) {
        List<Reply> list = replyMapper.getRepliesByCommentId(commentId);
        for (Reply reply : list) {
            User user = userMapper.getByID(reply.getUserId());
             reply.setUser(user);
            if( reply.getUser().getIntroduction()==null){
               reply.getUser().setIntroduction("暂时还没有简介");
            }
        }
        return list;
    }

/*    @Override
    public Integer replyLikes(Integer replyId) {
        return replyMapper.replyLikes(replyId);
    }*/
}
