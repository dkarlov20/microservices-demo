package com.dkarlov.order.service;

import com.dkarlov.order.model.Order;

import java.util.Optional;

public interface OrderService {
    Order createOrder(Order order);

    Optional<Order> findOrderById(Long id);
}
