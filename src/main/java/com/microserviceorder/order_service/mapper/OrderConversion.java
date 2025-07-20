package com.microserviceorder.order_service.mapper;

import org.mapstruct.Mapper;

import com.microserviceorder.order_service.dto.OrderDto;
import com.microserviceorder.order_service.model.Order;

@Mapper(componentModel = "spring")
public interface OrderConversion {
    Order toOrder(OrderDto orderDto);
    OrderDto toDto(Order order);
}
