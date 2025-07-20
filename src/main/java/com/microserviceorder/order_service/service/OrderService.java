package com.microserviceorder.order_service.service;

import com.microserviceorder.order_service.dto.OrderDto;
import com.microserviceorder.order_service.dto.OrderRequest;
import com.microserviceorder.order_service.dto.OrderResponse;
import com.microserviceorder.order_service.exception.OrderNotFoundException;

public interface OrderService {
    OrderResponse createOrder(OrderRequest orderRequest);
    OrderDto getOrderById(Long id) throws OrderNotFoundException;
}
