package com.example.custom_jwt_authentication.security.model;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "orders")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Example business fields
    private Double totalAmount;

    private String status; // NEW, PROCESSING, COMPLETED

    // Relationship to User
    @ManyToOne
    @JoinColumn(name = "owner_id")
    private User owner;

    // Helper to get username easily
    public String getOwnerUsername() {
        return owner.getUsername();
    }
}

