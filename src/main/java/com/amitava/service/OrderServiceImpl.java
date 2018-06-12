package com.amitava.service;

import com.amitava.exception.OrderProcessingException;
import com.amitava.model.Order;
import com.amitava.repository.OrderRepository;

import java.sql.SQLException;

public class OrderServiceImpl implements OrderService {
    private OrderRepository orderRepository;

    public OrderServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public boolean placeOrder(Order order) throws OrderProcessingException {
        try {
            int result = orderRepository.createOrder(order);

            if (result == 0) {
                return false;
            }
        } catch (SQLException e) {
            throw new OrderProcessingException(e);
        }

        return true;
    }

    @Override
    public boolean cancelOrder(int orderId) throws OrderProcessingException {
        try {
            Order order = orderRepository.readOrder(orderId);
            order.setStatus("cancelled");
            int result = orderRepository.updateOrder(order);
            if (result == 0) {
                return false;
            }
        } catch (SQLException e) {
            throw new OrderProcessingException(e);
        }
        return true;
    }

    @Override
    public boolean deleteOrder(int orderId) throws OrderProcessingException {
        try {
            int result = orderRepository.deleteOrder(orderId);
            if (result == 0) {
                return false;
            }
        } catch (SQLException e) {
            throw new OrderProcessingException(e);
        }

        return true;
    }

    @Override
    public OrderRepository getOrderRepository() {
        return orderRepository;
    }
}
