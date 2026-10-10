package com.aman.CreatorStore.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name="products")
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Product name is required")
    @Column(nullable = false)
    private String name;

    private String description ;

    private String category;

    @NotNull(message = "Product price required")
    @Column(nullable = false)
    @DecimalMin(value = "0.0",inclusive = false,message = "Price must be ")
    private BigDecimal price;

    @NotNull(message = "Stock quantity required")
    @Min(value = 0,message = "Stock can not be less than 0")
    @Column(name="stock_quantity",nullable = false)
    private Integer stockQuantity;

    @JsonIgnore
    @OneToMany(mappedBy = "product")
    private List<OrderItem> orderItems;
}
