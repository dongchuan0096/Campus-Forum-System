package org.example.tlias.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostMedia {
    private Integer mediaId;
    private Integer postId;
    private String url;
    private String type;
    private LocalDateTime createdAt;
}
