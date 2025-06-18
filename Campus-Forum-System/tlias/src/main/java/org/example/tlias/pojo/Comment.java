package org.example.tlias.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Comment {
    private Integer commentId;
    private Integer postId;
    private Integer userId;
    private String content;
    private LocalDateTime createdAt;
    private Integer likesCount;
    private List<Reply> replies;
    private User user;
}
