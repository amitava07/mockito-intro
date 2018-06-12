package com.amitava.repository;

import com.amitava.model.Order;

import java.sql.SQLException;

public class OrderRepositoryImpl implements OrderRepository {
    @Override
    public int createOrder(Order order) throws SQLException {
        return 0;
    }

    @Override
    public Order readOrder(int orderId) throws SQLException {
        return null;
    }

    @Override
    public int updateOrder(Order order) throws SQLException {
        return 0;
    }

    @Override
    public int deleteOrder(int orderId) throws SQLException {
        return 0;
    }
}
