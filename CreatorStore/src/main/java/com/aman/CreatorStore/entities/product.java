package com.aman.CreatorStore.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Table(name="products")
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class product {
    private Long id;
    private String name;
    private String description ;
    private String category;
    private BigDecimal price;
    private Integer stockQuantity;
}
