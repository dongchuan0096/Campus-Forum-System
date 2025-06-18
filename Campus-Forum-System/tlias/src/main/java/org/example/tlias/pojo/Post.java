package org.example.tlias.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Post {
    private Integer postId;
    private Integer userId;
    private String title;
    private String content;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime publishAt;
    private Integer likesCount;
    private Integer commentsCount;
    private Integer audi;
    private List<String> url;
}
