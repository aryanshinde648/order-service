package com.microserviceorder.order_service.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.microserviceorder.order_service.dto.OrderDto;
import com.microserviceorder.order_service.dto.OrderRequest;
import com.microserviceorder.order_service.dto.OrderResponse;
import com.microserviceorder.order_service.dto.PaymentDto;
import com.microserviceorder.order_service.exception.OrderNotFoundException;
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

    @Override
    @Cacheable(value = "orders",unless = "#result == null")
    public OrderDto getOrderById(Long id) throws OrderNotFoundException {
        Optional<Order> orderOptional = orderRepository.findById(id);
        if (orderOptional.isEmpty()) {
            throw new OrderNotFoundException("Order not found with id = "+id);
        }
        else{
            OrderDto orderDto = orderConversion.toDto(orderOptional.get());
            return orderDto;
        }
    }
    
    @Scheduled(cron = "0 0/1 * * * ?") // Every minute
    @CacheEvict(value = "orders", allEntries = true)
    public void clearOrderCache() {
        System.out.println("Clearing order cache...");
    }
    
}
