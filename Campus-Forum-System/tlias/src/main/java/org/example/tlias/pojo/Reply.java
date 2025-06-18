package org.example.tlias.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Reply {
    private Integer replyId;
    private Integer commentId;
    private Integer postId;
    private Integer replyToId;
    private Integer userId;
    private String content;
    private Integer likesCount;
    private LocalDateTime createdAt;
    private String replyToUsername;
    private User user;
}
