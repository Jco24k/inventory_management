package com.product.inventory.management.repositories;

import com.product.inventory.management.entities.Provider;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Set;

@Repository
public interface ProviderRepository extends JpaRepository<Provider, Long> {
    Set<Provider> findByIdIn(Set<Long> providerIds);
}
