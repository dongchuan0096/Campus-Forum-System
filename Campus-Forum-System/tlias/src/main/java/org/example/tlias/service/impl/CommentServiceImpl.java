package org.example.tlias.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.example.tlias.mapper.CommentMapper;
import org.example.tlias.mapper.PostMapper;
import org.example.tlias.mapper.UserMapper;
import org.example.tlias.pojo.*;
import org.example.tlias.service.CommentService;
import org.example.tlias.service.LikesLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    CommentMapper commentMapper;
    @Autowired
    LikesLogService likesLogService;
    @Autowired
    UserMapper userMapper;
    @Autowired
    PostMapper postMapper;
    @Override
    @Transactional
    public Integer insertComment(Comment comment) {
        commentMapper.insertComment(comment);
        postMapper.commentsCount(comment.getPostId());
        return comment.getCommentId();
    }

    @Transactional
    public Pagebean getCommentsByPostId(Integer postId,Integer page,Integer pageSize) {
        PageHelper.startPage(page, pageSize);
        List<Comment> list = commentMapper.getCommnetsByPostId(postId);
        for (Comment comment : list) {
            User user = userMapper.getByID(comment.getUserId());
            comment.setUser(user);
            if( comment.getUser().getIntroduction()==null){
                comment.getUser().setIntroduction("暂时还没有简介");
            }
        }
        PageInfo<Comment> p = new PageInfo<>(list);
        Pagebean pagebean = new Pagebean(p.getTotal(), p.getList());
/*        for (Post post : list) {
            List<String> urls = postMapper.getMediaByPostId(post.getPostId());
            post.setUrl(urls);
        }*/
        return pagebean;
    }

    @Override
    @Transactional
    public Integer commentLike(Integer commentId, Integer userId) {
        if(likesLogService.isLiked(commentId,userId,1)){
            return 0;
        }

           return likesLogService.like(userId,commentId,1);
    }

    @Override
    public boolean iscommentLiked(Integer commentId, Integer userId) {
        if(likesLogService.isLiked(userId,commentId,1)){
            return true;
        }
        else {
            return false;
        }
    }



}

