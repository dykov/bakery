package com.dykov.bakery.repository;

import java.util.Optional;
import java.util.UUID;
import com.dykov.bakery.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends JpaRepository<Client, UUID> {
    Optional<Client> findByPhone(String phone);

    boolean existsByPhone(String phone);
}
