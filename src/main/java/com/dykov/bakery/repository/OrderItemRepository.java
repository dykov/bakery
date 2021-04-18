package com.dykov.bakery.repository;

import com.dykov.bakery.entity.OrderItem;
import com.dykov.bakery.entity.OrderItemId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem, OrderItemId> {
}
