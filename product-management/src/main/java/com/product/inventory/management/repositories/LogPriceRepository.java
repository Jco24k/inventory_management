package com.product.inventory.management.repositories;

import com.product.inventory.management.entities.logs.LogPrice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LogPriceRepository extends JpaRepository<LogPrice, Long> {
}
