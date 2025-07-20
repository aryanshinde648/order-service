package com.microserviceorder.order_service.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import com.microserviceorder.order_service.dto.PaymentDto;

@FeignClient(name = "payment-service", url = "http://PAYMENT-SERVICE/api/payments/create")
public interface PaymentClient {

    @PostMapping("/create")
    public ResponseEntity<PaymentDto> createPayment(@RequestBody PaymentDto paymentDto);

}
