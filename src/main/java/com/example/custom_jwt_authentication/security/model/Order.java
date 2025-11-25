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

    private Double totalAmount;

    private String status;

    // Relationship to User
    @ManyToOne
    @JoinColumn(name = "owner_id")
    private User owner;

    public String getOwnerUsername() {
        return owner.getUsername();
    }
}

