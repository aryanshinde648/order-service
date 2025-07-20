package com.microserviceorder.order_service.exception;

public class OrderNotFoundException extends Exception{
    public OrderNotFoundException(String msg) {
        super(msg);
    }
    public OrderNotFoundException(String msg,Throwable cause) {
        super(msg,cause);
    }
}
