package com.microserviceorder.order_service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderRequest {
    private PaymentDto paymentDto;
    private OrderDto orderDto;
}
