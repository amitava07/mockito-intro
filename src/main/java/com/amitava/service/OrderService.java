package com.amitava.service;

import com.amitava.exception.OrderProcessingException;
import com.amitava.model.Order;
import com.amitava.repository.OrderRepository;

public interface OrderService {
    boolean placeOrder(Order order) throws OrderProcessingException;
    boolean cancelOrder(int orderId) throws OrderProcessingException;
    boolean deleteOrder(int orderId) throws OrderProcessingException;
    OrderRepository getOrderRepository();
}
