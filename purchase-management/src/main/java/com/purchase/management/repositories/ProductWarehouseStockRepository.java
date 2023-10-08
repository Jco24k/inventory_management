package com.purchase.management.repositories;

import com.purchase.management.entities.composite.ProductWarehouseStockDetail;
import com.purchase.management.entities.composite.ProductWarehouseStockDetailPk;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductWarehouseStockRepository extends
        JpaRepository<ProductWarehouseStockDetail, ProductWarehouseStockDetailPk> {
}
