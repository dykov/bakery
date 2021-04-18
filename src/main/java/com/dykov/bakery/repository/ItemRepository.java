package com.dykov.bakery.repository;

import java.util.UUID;
import com.dykov.bakery.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepository extends JpaRepository<Item, UUID> {
    boolean existsByName(String name);
}
