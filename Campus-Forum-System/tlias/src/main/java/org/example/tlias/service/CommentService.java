package org.example.tlias.service;

import org.example.tlias.pojo.Comment;
import org.example.tlias.pojo.Pagebean;

import java.util.List;

public interface CommentService {
    Integer insertComment(Comment comment);
    Pagebean getCommentsByPostId(Integer postId,Integer page,Integer pageSize);

    Integer commentLike(Integer commentId,Integer userId);

    boolean iscommentLiked(Integer commentId,Integer userId);
}
