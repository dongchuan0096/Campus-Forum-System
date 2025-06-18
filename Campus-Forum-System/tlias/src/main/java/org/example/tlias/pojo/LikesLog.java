package org.example.tlias.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LikesLog {
    private Integer likeId;
    private Integer userId;
    private Integer likedAt;
    private Integer likedAtId;
    private LocalDateTime createdAt;

}