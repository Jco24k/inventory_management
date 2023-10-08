package com.purchase.management.services.interfaces;

import com.purchase.management.dtos.create.CreateProductWarehouseStockDetailDto;
import com.purchase.management.dtos.update.UpdateProductWarehouseStockDetailDto;
import com.purchase.management.entities.composite.ProductWarehouseStockDetail;
import java.util.Collection;

public interface IProductWarehouseStockDetailService {
    Collection<ProductWarehouseStockDetail> findAll();
    ProductWarehouseStockDetail findOne(Long productId, Long warehouseId );
    ProductWarehouseStockDetail create(CreateProductWarehouseStockDetailDto requestDto, Long productId) ;
    ProductWarehouseStockDetail update(UpdateProductWarehouseStockDetailDto requestDto,Long productId, Long warehouseId );
}
