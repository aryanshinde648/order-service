package com.microserviceorder.order_service.service;

import com.microserviceorder.order_service.dto.OrderRequest;
import com.microserviceorder.order_service.dto.OrderResponse;

public interface OrderService {
    OrderResponse createOrder(OrderRequest orderRequest);
}
