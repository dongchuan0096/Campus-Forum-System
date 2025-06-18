package org.example.tlias.controller;

import lombok.extern.slf4j.Slf4j;
import org.example.tlias.service.AccountService;
import org.example.tlias.service.UserService;
import org.example.tlias.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.example.tlias.pojo.User;

import java.time.LocalDateTime;

@Slf4j
@RestController
@CrossOrigin
public class UserController {
    @Autowired
    UserService userService;
    @Autowired
    AccountService accountService;
    @GetMapping("/user/user")
    Result getById(@RequestParam Integer userId){
        User user = userService.getByID(userId);
        System.out.println(user);
        return new Result(50,"信息获取成功",user);
    }
    @PostMapping("/admin/user")
    Result getById1(@RequestParam Integer userId){
        User user = userService.getByID(userId);
        System.out.println(user);
        return new Result(50,"信息获取成功",user);
    }
    @PostMapping("/user/update")
    Result updateUser(@RequestBody User user){
        if(userService.updateUser(user)==1){
            return new Result(1,"信息更新成功",user);
        }
        else{
            return new Result(0,"信息更新失败",user);
        }

    }
    @PostMapping("/user/user/avatar")
    Result insertUserAvater(@RequestParam Integer userId,@RequestParam String avatarUrl){
        if(userService.insertUserAvater(userId,avatarUrl)==0){
            return new Result(0,"不能在7天内连续上传图片",avatarUrl);
        }
        else {
            return new Result(1,"上传图片成功",null);
        }
    }


    @PostMapping("/admin/users")
    Result getUsers( Integer userId, String username,  String email,  LocalDateTime createdAt, LocalDateTime updatedAt, @RequestParam Integer page, @RequestParam Integer pageSize){
        User user = new User();
        user.setUserId(userId);
        user.setUsername(username);
        user.setEmail(email);
        user.setCreatedAt(createdAt);
        user.setUpdatedAt(updatedAt);
        return Result.success(userService.getUsers(user,page,pageSize));
    }
    @PreAuthorize("hasAuthority('SUPER')")
    @PostMapping("/admin/usersadmin")
    Result getUsersAdmin( Integer userId, String username,  String email,  LocalDateTime createdAt, LocalDateTime updatedAt, @RequestParam Integer page, @RequestParam Integer pageSize,String role){
        User user = new User();
        user.setUserId(userId);
        user.setUsername(username);
        user.setEmail(email);
        user.setCreatedAt(createdAt);
        user.setUpdatedAt(updatedAt);
        return Result.success(userService.getUsersAdmin(user,page,pageSize,role));
    }
    @PostMapping("/admin/banedusers")
    Result getBanedUsers( Integer userId, String username,  String email,  LocalDateTime createdAt, LocalDateTime updatedAt, @RequestParam Integer page, @RequestParam Integer pageSize){
        User user = new User();
        user.setUserId(userId);
        user.setUsername(username);
        user.setEmail(email);
        user.setCreatedAt(createdAt);
        user.setUpdatedAt(updatedAt);
        return Result.success(userService.getBanedUsers(user,page,pageSize));
    }
    @PostMapping("/admin/mute")
    Result muteUserById(@RequestParam Integer userId){
        return Result.success(accountService.setUserRole4(userId));
    }
    @PostMapping("/admin/ban")
    Result banUserById(@RequestParam Integer userId){
        return Result.success(accountService.setUserRole5(userId));
    }
    @PostMapping("/admin/un")
    Result unUserById(@RequestParam Integer userId){
        return Result.success(accountService.setUserRole1(userId));
    }
    @PreAuthorize("hasAuthority('SUPER')")
    @PostMapping("/admin/assessor")
    Result assessorUserById(@RequestParam Integer userId){
        return Result.success(accountService.setUserRole3(userId));
    }
    @PreAuthorize("hasAuthority('SUPER')")
    @PostMapping("/admin/unassessor")
    Result unAssessorUserById(@RequestParam Integer userId){
        return Result.success(accountService.setUserRole1(userId));
    }



}
