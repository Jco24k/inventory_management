package com.purchase.management.repositories;

import com.purchase.management.entities.InventoryIncomeHeader;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InventoryIncomeHeaderRepository extends JpaRepository<InventoryIncomeHeader, Long> {
}
