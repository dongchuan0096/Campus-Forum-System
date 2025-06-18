package org.example.tlias.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Follow {
    Integer followId;
    Integer userId;
    Integer beFollowedId;
    LocalDateTime followAtTime;
    User user;
}
