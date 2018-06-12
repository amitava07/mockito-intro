package com.amitava.service;

import com.amitava.exception.OrderProcessingException;
import com.amitava.model.Order;
import com.amitava.repository.OrderRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.sql.SQLException;

import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class OrderServiceImplTest {
    @Mock
    private OrderRepository orderRepository;
    private OrderService orderService;
    private Order order;

    @Before
    public void setUp() throws Exception {
        //Initialize objects annotated with Mockito annotations for given test class
        MockitoAnnotations.initMocks(this);
        orderService = new OrderServiceImpl(orderRepository);
        order = new Order();
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void placeOrder_Should_Create_An_Order() throws SQLException, OrderProcessingException {
        //mock out the create method in OrderRepository
        //as per business logic 1 means success
        //when createOrder is called on orderRepo return 1
        when(orderRepository.createOrder(order)).thenReturn(1);

        boolean result = orderService.placeOrder(order);
        assertTrue(result);
        //verify that the createOrder method is called and called only once
        verify(orderRepository).createOrder(order);
    }

    @Test
    public void placeOrder_Should_Not_Create_An_Order() throws OrderProcessingException, SQLException {
        when(orderRepository.createOrder(order)).thenReturn(0);

        boolean result = orderService.placeOrder(order);
        assertFalse(result);

        verify(orderRepository).createOrder(order);
    }

    @Test(expected = OrderProcessingException.class)
    public void placeOrder_Should_Throw_OrderProcessingException() throws SQLException, OrderProcessingException {
        when(orderRepository.createOrder(order)).thenThrow(SQLException.class);
        orderService.placeOrder(order);

        verify(orderRepository).createOrder(order);
    }

    @Test
    public void cancelOrder_Should_Cancel_Order() throws SQLException, OrderProcessingException {
        int orderId = 123;
        order.setId(orderId);
        when(orderRepository.readOrder(orderId)).thenReturn(order);
        when(orderRepository.updateOrder(order)).thenReturn(1);

        boolean result = orderService.cancelOrder(orderId);
        assertTrue(result);
        assertEquals("cancelled", order.getStatus());

        verify(orderRepository).readOrder(orderId);
        verify(orderRepository).updateOrder(order);

    }

    @Test
    public void cancelOrder_Should_Not_Cancel_Order() throws SQLException, OrderProcessingException {
        int orderId = 123;
        order.setId(orderId);
        when(orderRepository.readOrder(orderId)).thenReturn(order);
        when(orderRepository.updateOrder(order)).thenReturn(0);

        boolean result = orderService.cancelOrder(orderId);
        assertFalse(result);
        assertEquals("cancelled", order.getStatus());

        verify(orderRepository).readOrder(orderId);
        verify(orderRepository).updateOrder(order);

    }

    @Test(expected = OrderProcessingException.class)
    public void cancelOrder_Should_Throw_OrderProcessingException_On_readOrder_Invocation()
            throws SQLException, OrderProcessingException {
        int orderId = 123;

        when(orderRepository.readOrder(orderId)).thenThrow(SQLException.class);
        orderService.cancelOrder(orderId);

        verify(orderRepository).readOrder(orderId);
    }

    @Test(expected = OrderProcessingException.class)
    public void cancelOrder_Should_Throw_OrderProcessingException_On_UpdateOrder_Invocation()
            throws SQLException, OrderProcessingException {
        int orderId = 123;
        order.setId(orderId);
        when(orderRepository.readOrder(orderId)).thenReturn(order);
        when(orderRepository.updateOrder(order)).thenThrow(SQLException.class);

        orderService.cancelOrder(orderId);

        verify(orderRepository).readOrder(orderId);
        verify(orderRepository).updateOrder(order);
    }


}