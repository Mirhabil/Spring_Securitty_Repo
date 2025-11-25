package com.example.custom_jwt_authentication.security.controller;

import com.example.custom_jwt_authentication.security.model.Order;
import com.example.custom_jwt_authentication.security.service.OrderService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller("/api")
@AllArgsConstructor
@NoArgsConstructor
public class OrderController {

    private OrderService orderService;


    @GetMapping("/orders")
    public ResponseEntity<List<Order>> getOrders() {
        List<Order> orderList=orderService.getAllOrders();
        return ResponseEntity.ok(orderList);
    }

}
