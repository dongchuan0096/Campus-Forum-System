package org.example.tlias.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VerificationCode {
    private Integer id; // 主键ID
    private String email; // 邮箱地址
    private String code; // 验证码
    private LocalDateTime expiresAt; // 过期时间
    private Boolean used; // 是否已使用
} 