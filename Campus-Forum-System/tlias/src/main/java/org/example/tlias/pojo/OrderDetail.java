package org.example.tlias.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDetail {
    private Integer orderDetailId;
    private Integer orderId;
    private Integer productId;
    private Integer quantity;
    private Double pricePerUnit;


}
