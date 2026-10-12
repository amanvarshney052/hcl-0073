package com.aman.CreatorStore.entities;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;


import java.time.ZoneId;

@Entity
@Table(name="orders")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="customer_name",nullable = false)
    @NotNull(message = "Customer Name required")
    private String customerName;

    @Column(name="customer_email",nullable = false)
    private String customerEmail;

    @Column(nullable = false)
    private String status;

    @Column(nullable = false,name="total_price")
    @NotNull(message = "Price is required")
    private BigDecimal totalPrice;

    @Column(name="created_at")
    private LocalDateTime createdAt;

    @PrePersist
    public void prePersist() {
        this.createdAt = LocalDateTime.now(
                ZoneId.of("Asia/Kolkata")
        );
    }
    @OneToMany(mappedBy = "order")
    private List<OrderItem> orderItems;

}



