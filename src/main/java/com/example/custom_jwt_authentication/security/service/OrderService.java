package com.example.custom_jwt_authentication.security.service;

import com.example.custom_jwt_authentication.security.model.Order;
import com.example.custom_jwt_authentication.security.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {


    private final OrderRepository orderRepository;
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }
}
