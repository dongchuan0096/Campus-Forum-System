package org.example.tlias.controller;
import org.example.tlias.pojo.User;
import org.example.tlias.service.UserService;
import org.example.tlias.service.impl.VerificationCodeImpl;
import org.example.tlias.utils.Result;
import org.example.tlias.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
/*
* user登录控制器
* */
@CrossOrigin
@RestController
@RequestMapping("/user")
public class LoginController {
    @Autowired
    UserService userService;
    @Autowired
    private VerificationCodeImpl verificationCode;
    @PostMapping("/login")
    Result login(@RequestBody User user){
        User userres= userService.userLogin(user);
          if(userres!=null){
              Map<String, Object> claims =new HashMap<>();
              claims.put("username",user.getUsername());
              String token= JwtUtils.generateJwt(claims);
              Map<String,Object> res=new HashMap<>();
              res.put("token",token);
              res.put("userId",userres.getUserId());
              System.out.println(res);
              return new Result(20,"登录成功",res);
          }
          else
              return new Result(21,"用户不存在，账号或密码错误",null);
    }
    @PostMapping("/signup")
    public Result signUp(@RequestBody User user){
        System.out.println("///////////"+user);
        if(userService.insertUser(user)==1){
            return new Result(30,"注册成功，自动登录",null);
        }
        else
        return new Result(31,"注册失败，用户已经存在",null);
    }
}
