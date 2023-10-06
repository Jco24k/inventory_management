package com.purchase.management.repositories;

import com.purchase.management.entities.composite.PurchaseOrderDetail;
import com.purchase.management.entities.composite.PurchaseOrderDetailPk;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PurchaseOrderDetailRepository extends JpaRepository<PurchaseOrderDetail, PurchaseOrderDetailPk> {
}
