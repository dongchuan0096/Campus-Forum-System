package org.example.tlias.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.example.tlias.mapper.CommentMapper;
import org.example.tlias.mapper.PostMapper;
import org.example.tlias.mapper.ReplyMapper;
import org.example.tlias.mapper.UserMapper;
import org.example.tlias.pojo.Comment;
import org.example.tlias.pojo.Pagebean;
import org.example.tlias.pojo.Reply;
import org.example.tlias.pojo.User;
import org.example.tlias.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MessageServiceImpl implements MessageService {
    @Autowired
    CommentMapper commentMapper;
    @Autowired
    PostMapper postMapper;
    @Autowired
    ReplyMapper replyMapper;
    @Autowired
    UserMapper userMapper;

    @Override
    public Pagebean getMsg(Integer userId, Integer page, Integer pageSize) {
        List<Integer> postsList = postMapper.getWorksId(userId);
        List<Comment> filteredComments = new ArrayList<>();
        // 遍历作品ID列表，获取每个作品的评论，并过滤掉userId为自己的评论
        for (Integer postId : postsList) {
            List<Comment> commentsForPost = commentMapper.getMsg(postId);
            for (Comment comment : commentsForPost) {
                if (!comment.getUserId().equals(userId)) {
                   User user = userMapper.getByID(comment.getUserId());
                    comment.setUser(new User());
                    comment.setUser(user);
                    filteredComments.add(comment);
                }
            }
        }
        List<Comment> paginatedComments = filteredComments.stream()
                .skip((long) (page - 1) * pageSize)
                .limit(pageSize)
                .collect(Collectors.toList());
        long total = filteredComments.size();
        Pagebean pagebean = new Pagebean(total, paginatedComments);
        System.out.println(total + "  " + paginatedComments.size());
        return pagebean;
    }

    public Pagebean getReply(Integer userId, Integer page, Integer pageSize) {
        PageHelper.startPage(page, pageSize);
        List<Reply> list = replyMapper.getReply(userId);
        PageInfo<Reply> p = new PageInfo<>(list);
        Pagebean pagebean = new Pagebean(p.getTotal(), p.getList());
        System.out.println(p.getTotal() + "  " + p.getList().size());
        return pagebean;
    }
}
