package org.example.tlias.aop;

import com.alibaba.fastjson.JSONObject;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.example.tlias.mapper.OperateLogMapper;
import org.example.tlias.pojo.OperateLog;
import org.example.tlias.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.Array;
import java.time.LocalDateTime;
import java.util.Arrays;

@Slf4j
@Component
@Aspect
public class LogAspect {
    @Autowired
    HttpServletRequest request;
    @Autowired
    OperateLogMapper operateLogMapper;

    @Around("@annotation(org.example.tlias.ano.Log)")
    public Object recordLog(ProceedingJoinPoint joinPoint) throws Throwable {
        Long begin= System.currentTimeMillis();
        String jwt = request.getHeader("token");
        Claims claims = JwtUtils.parseJWT(jwt);
        Integer operateUser = (Integer) claims.get("id"); //操作人ID
        LocalDateTime operateTime = LocalDateTime.now(); //操作时间
        String className = joinPoint.getTarget().getClass().getName(); //操作类名
        String methodName = joinPoint.getSignature().getName(); //操作方法名
        String methodParams = Arrays.toString(joinPoint.getArgs()); //操作方法参数


        Object result = joinPoint.proceed();
        String returnValue = JSONObject.toJSONString(result); //操作方法返回值
        Long end=System.currentTimeMillis();
        Long costTime = end-begin; //操作耗时
        OperateLog operateLog = new OperateLog(null,operateUser,operateTime,className,methodName,methodParams,returnValue,costTime);
        operateLogMapper.insert(operateLog);
        log.info("aop");
        return result;
    }
}
