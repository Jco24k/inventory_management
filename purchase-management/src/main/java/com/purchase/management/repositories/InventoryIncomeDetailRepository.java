package com.purchase.management.repositories;

import com.purchase.management.entities.composite.InventoryIncomeDetail;
import com.purchase.management.entities.composite.InventoryIncomeDetailPk;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InventoryIncomeDetailRepository extends JpaRepository<InventoryIncomeDetail, InventoryIncomeDetailPk> {
}
