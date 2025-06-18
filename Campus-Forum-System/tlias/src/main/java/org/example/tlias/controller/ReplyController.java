package org.example.tlias.controller;

import lombok.extern.slf4j.Slf4j;
import org.example.tlias.pojo.Reply;
import org.example.tlias.service.ReplyService;
import org.example.tlias.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
@Slf4j
@RestController
@RequestMapping("/user")
public class ReplyController {
    @Autowired
    ReplyService replyService;
    @PreAuthorize("hasAuthority('CAN_POST')")
    @PostMapping("/reply")
    Result insert(@RequestBody Reply reply){
        return new Result(1,"回复插入成功",replyService.insertReply(reply));
    }
    @GetMapping("/reply")
    Result getRepliesByCommentId(@RequestParam Integer commentId){
        return new Result(1,"该评论下的回复",replyService.getRepliesByCommentId(commentId));
    }
/*    @PostMapping("/reply/likes")
    Result replyLikes(@RequestParam Integer replyId){
        if(replyService.replyLikes(replyId)==1){
            return Result.success();
        }
        else
            return Result.error("点赞失败！");

    }*/
}
