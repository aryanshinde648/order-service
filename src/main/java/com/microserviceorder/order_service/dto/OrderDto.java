package com.microserviceorder.order_service.dto;

import io.swagger.v3.oas.annotations.Hidden;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDto {

    @Hidden
    private Long id;
    private String productName;
    private int quantity;
    private Double price;
    private String status;

}
