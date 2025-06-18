package org.example.tlias.controller;

import lombok.extern.slf4j.Slf4j;
import org.example.tlias.pojo.Comment;
import org.example.tlias.service.CommentService;
import org.example.tlias.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@Slf4j
@RestController
@RequestMapping("/user")
public class CommentController {
    @Autowired
    CommentService commentService;
    @PreAuthorize("hasAuthority('CAN_POST')")
    @PostMapping("/comment")
    Result insertComment(@RequestBody Comment comment){
        comment.setCreatedAt(LocalDateTime.now());
        Integer commnetId = commentService.insertComment(comment);
        System.out.println("/////////"+commnetId);
       if(commnetId!=1) {
           return new Result(1,"评论上传成功",commnetId);
       }
        else {
            return new Result(0,"评论插入失败",null);
       }
    }
    @GetMapping("/comment")
    Result getCommnetsByPostId (@RequestParam Integer postId,@RequestParam Integer page,@RequestParam Integer pageSize){
        return new Result(1,"获取评论成功",commentService.getCommentsByPostId(postId,page,pageSize));
    }

    @PostMapping("/commentlike")
    Result commentLike(@RequestParam Integer commentId,@RequestParam Integer userId,@RequestParam Boolean commentLiked){

      if(!commentLiked){
          if(commentService.commentLike(commentId,userId)==1){
              return Result.success();
          }
          return Result.error("点赞失败");
      }
       else
           return Result.error("点赞失败");
    }
    @GetMapping("/iscommentliked")
    Result iscommentLiked(@RequestParam Integer commentId,@RequestParam Integer userId){
        if(commentService.iscommentLiked(commentId,userId)){
            return new Result(60,"点赞过",null);
        }
        else
            return new Result(61,"未点赞",null);
    }
}

