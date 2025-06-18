package org.example.tlias.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Component
public class CustomAccessDeniedHandler implements AccessDeniedHandler {

    private final ObjectMapper objectMapper;

    // 通过构造函数注入 ObjectMapper
    public CustomAccessDeniedHandler(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    public void handle(jakarta.servlet.http.HttpServletRequest request,
                       jakarta.servlet.http.HttpServletResponse response,
                       AccessDeniedException accessDeniedException) throws IOException, jakarta.servlet.ServletException {

        // 构建返回的 JSON 数据
        Map<String, Object> responseData = new HashMap<>();
        responseData.put("status", HttpStatus.FORBIDDEN.value());
        responseData.put("message", "Access Denied: " + accessDeniedException.getMessage());

        // 设置响应类型为 JSON
        response.setContentType("application/json;charset=UTF-8");

        // 将 responseData 转换为 JSON 并写入响应
        response.getWriter().write(objectMapper.writeValueAsString(responseData));
    }
}
