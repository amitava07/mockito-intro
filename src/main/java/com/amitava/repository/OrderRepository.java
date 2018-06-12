package com.amitava.repository;

import com.amitava.model.Order;

import java.sql.SQLException;

public interface OrderRepository {
    int createOrder(Order order) throws SQLException;
    Order readOrder(int orderId) throws SQLException;
    int updateOrder(Order order) throws SQLException;
    int deleteOrder(int orderId) throws SQLException;
}
