package com.microserviceorder.order_service.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.microserviceorder.order_service.dto.OrderDto;
import com.microserviceorder.order_service.dto.OrderRequest;
import com.microserviceorder.order_service.dto.OrderResponse;
import com.microserviceorder.order_service.dto.PaymentDto;
import com.microserviceorder.order_service.mapper.OrderConversion;
import com.microserviceorder.order_service.model.Order;
import com.microserviceorder.order_service.repository.OrderRepository;

@Service
public class OrderServiceImpl implements OrderService{

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderConversion orderConversion;

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public OrderResponse createOrder(OrderRequest orderRequest) {
        // Convert DTO to entity
        Order order = orderConversion.toOrder(orderRequest.getOrderDto());

        // Save the order using the repository
        Order savedOrder = orderRepository.save(order);

        // Convert the saved entity back to DTO
        OrderDto orderDto=orderConversion.toDto(savedOrder);

        // Call the payment service to create a payment
        String paymentServiceUrl = "http://PAYMENT-SERVICE/api/payments/create"; // Adjust the URL as needed

        ResponseEntity<PaymentDto> paymentDtoResponseEntity = restTemplate.postForEntity(
                paymentServiceUrl,
                orderRequest.getPaymentDto(),
                PaymentDto.class
        );

        return new OrderResponse(orderDto,paymentDtoResponseEntity.getBody());
    }
    
    
}
