package org.example.tlias.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Result {
    private Integer code;//响应码
    /*
    * 0,"失败"
    *
    *
    *
    *
    * 10,"验证码正确"
    * 11,"验证码错误"
    * 12,"验证码过期"
    * 13,"验证码不存在"
    * 14，"验证码已发送"
    *
    *
    * 20,"登录成功"
    * 21,"用户不存在，账号或密码错误"
    * 22,"令牌错误"
    *
    * 30,"注册成功，自动登录"
    *
    *
    *
    * 40，“帖子添加成功”
    *
    *
    * 50,"信息获取成功"
    *
    * */
    private String msg;  //响应信息 描述字符串
    private Object data; //返回的数据

    //增删改 成功响应
    public static Result success(){
        return new Result(1,"success",null);
    }
    //查询 成功响应
    public static Result success(Object data){
        return new Result(1,"success",data);
    }
    //失败响应
    public static Result error(String msg){
        return new Result(0,msg,null);
    }
    public static Result error(Integer code){ return new Result(code,"error",null);}
}
