package org.example.tlias.controller;

import lombok.extern.slf4j.Slf4j;
import org.example.tlias.service.FollowService;
import org.example.tlias.service.LikesLogService;
import org.example.tlias.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/user")
public class FollowController {
    @Autowired
    FollowService followService;
    @Autowired
    LikesLogService likesLogService;
    @PostMapping("/follow")
    Result insertFollow(Integer userId,Integer beFollowedId){
        if(followService.insertFollow(userId, beFollowedId)){
            return Result.success();
        }
        else{
            return Result.error("关注失败");
        }
    }
    @PostMapping("/isfollowed")
    Result isFollowed(@RequestParam Integer userId,@RequestParam Integer beFollowedId){
        if(followService.isFollowed(userId, beFollowedId)){
            return Result.success();
        }
        else{
            return Result.error("关注失败");
        }
    }
    /*获取userId的关注列表*/
    @PostMapping("/getfollows")
    Result getFollows(@RequestParam Integer userId){
            return new Result(1,"关注列表",followService.getFollowsByuserId(userId));
    }
    /*获取userId的粉丝列表*/
    @PostMapping("/getfollowers")
    Result getFollowers(@RequestParam Integer userId,@RequestParam Integer page,@RequestParam Integer pageSize){
           return new Result(1,"粉丝列表",followService.getFollowers(userId,page,pageSize));
    }
    @PostMapping("/getfollowedcount")
    Result  getFollowersCountByuserId(Integer userId,Integer dayAgo){
        return new Result(1,"粉丝数",followService.getFollowersCountByuserId(userId,dayAgo));
    }
    @PostMapping("/getfollowscount")
    Result  getFollowersCountByuserId(Integer userId){
        return new Result(1,"关注数",followService.getFollowersCountByuserId(userId));
    }
    @PostMapping("/getuserlikescount")
    Result  getlikes(Integer userId,Integer dayAgo){
        return new Result(1,"点赞数",likesLogService.getLikes(userId,dayAgo));
    }
}
