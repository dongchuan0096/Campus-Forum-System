package org.example.tlias.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Pagebean<T> {
    private Long total; // 总记录数
    private List<T> rows; // 当前页数据列表
} 