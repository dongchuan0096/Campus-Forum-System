package org.example.tlias.exception;

import org.example.tlias.utils.Result;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHander {
    @ExceptionHandler(AccessDeniedException.class)
    public Result ex1(Exception e){
        e.printStackTrace();
        return  new Result(-2,"没有权限",null);
    }
    @ExceptionHandler(MissingServletRequestParameterException.class)
    public Result ex2(Exception e){
        e.printStackTrace();
        return  new Result(0,"缺少参数",null);
    }

}
