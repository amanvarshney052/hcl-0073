package com.aman.CreatorStore.repositories;

import com.aman.CreatorStore.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order,Long> {
}
