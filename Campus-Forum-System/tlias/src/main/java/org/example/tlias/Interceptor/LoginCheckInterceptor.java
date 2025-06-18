//package org.example.tlias.Interceptor;
//
//import com.alibaba.fastjson.JSONObject;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import lombok.extern.slf4j.Slf4j;
//import org.example.tlias.utils.Result;
//import org.example.tlias.utils.JwtUtils;
//import org.springframework.stereotype.Component;
//import org.springframework.util.StringUtils;
//import org.springframework.web.bind.annotation.CrossOrigin;
//import org.springframework.web.servlet.HandlerInterceptor;
//
//import java.util.Enumeration;
//@CrossOrigin
//@Slf4j
//@Component
//public class LoginCheckInterceptor implements HandlerInterceptor {
//
//    @Override
//    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//        if(request.getMethod().equals("OPTIONS")){
//            System.out.println("????????????????????");
//            return true;
//        }
//
//        /*1 获取请求url。*/
//        String url = request.getRequestURL().toString();
//        log.info("请求路径:{}",url);
//        /*2判断请求url中是否包含login，如果包含，说明是登录操作，放行。*/
//        if(url.contains("login")||url.contains("send-verification-code")||url.contains("verify-code")||url.contains("signup")){
//            log.info("登录或注册操作，放行");
//            return true;
//        }
///*        Enumeration<String> headerNames = request.getHeaderNames();
//        while (headerNames.hasMoreElements()) {
//            String headerName = headerNames.nextElement();
//            log.info("Header Name = {} Value = {}", headerName, request.getHeader(headerName));
//        }*/
//        /*3 获取请求头中的令牌（token）。*/
//        String Jwt = request.getHeader("Token");
//        log.info(Jwt);
//        /*4 判断令牌是否存在，如果不存在，返回错误结果（未登录）。*/
//        if(!(StringUtils.hasLength(Jwt))){
//            log.info("token为空");
//            String notLogin = JSONObject.toJSONString( Result.error(-1));
//            response.getWriter().write(notLogin);
//            return false;
//        }
//        /*5  解析token，如果解析失败，返回错误结果（未登录）。*/
//        try {
//            JwtUtils.parseJWT(Jwt);
//        }
//        catch (Exception e){
//            e.printStackTrace();
//            log.info("令牌错误");
//            String notLogin = JSONObject.toJSONString( new Result(22,"令牌错误",null));
//            response.getWriter().write(notLogin);
//            return false;
//        }
//        /*6 放行。*/
//        log.info("令牌合法");
//        return true;
//    }
//    }
//
