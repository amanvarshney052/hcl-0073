package com.aman.CreatorStore.repositories;

import com.aman.CreatorStore.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product,Long> {
}
