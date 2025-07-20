package com.microserviceorder.order_service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderResponse {
    private OrderDto orderDto;
    private PaymentDto paymentDto;
}
