package org.example.tlias.controller;

import lombok.extern.slf4j.Slf4j;
import org.example.tlias.pojo.Comment;
import org.example.tlias.pojo.Pagebean;
import org.example.tlias.service.MessageService;
import org.example.tlias.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/user")
public class MessageController {
  @Autowired
  MessageService messageService;
  @PostMapping("/getMsg")
  Result getMsg(@RequestParam Integer userId,@RequestParam Integer page,@RequestParam Integer pageSize){

    Pagebean comments = messageService.getMsg(userId,page,pageSize);

    return  Result.success(comments);
  }
  @PostMapping("/getReply")
  Result getReply(@RequestParam Integer userId,@RequestParam Integer page,@RequestParam Integer pageSize){

    Pagebean comments = messageService.getReply(userId,page,pageSize);

    return  Result.success(comments);
  }
}
