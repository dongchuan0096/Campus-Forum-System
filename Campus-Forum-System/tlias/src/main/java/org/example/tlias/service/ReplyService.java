package org.example.tlias.service;

import org.example.tlias.pojo.Reply;

import java.util.List;

public interface ReplyService {
    Integer insertReply(Reply reply);
    List<Reply> getRepliesByCommentId(Integer commentId);

 /*   Integer replyLikes(Integer replyId);*/
}
