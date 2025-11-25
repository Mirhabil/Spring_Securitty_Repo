package com.example.custom_jwt_authentication.security.repository;

import com.example.custom_jwt_authentication.security.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
}
