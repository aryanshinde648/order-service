package com.microserviceorder.order_service.dto;

import io.swagger.v3.oas.annotations.Hidden;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentDto {
    @Hidden
    private Long id;
    private String orderId;
    private Double price;
    private String paymentStatus;
}
