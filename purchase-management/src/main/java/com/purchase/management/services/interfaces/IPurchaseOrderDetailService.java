package com.purchase.management.services.interfaces;

import com.purchase.management.dtos.create.CreatePurchaseOrderDetailDto;
import com.purchase.management.dtos.update.UpdateInventoryIncomeDetailDto;
import com.purchase.management.dtos.update.UpdatePurchaseOrderDetailDto;
import com.purchase.management.entities.PurchaseOrderHeader;
import com.purchase.management.entities.composite.InventoryIncomeDetail;
import com.purchase.management.entities.composite.PurchaseOrderDetail;

import java.util.Collection;

public interface IPurchaseOrderDetailService {
    Collection<PurchaseOrderDetail> findAll();
    PurchaseOrderDetail findOne(Long purchaseOrderHeaderId, Long productId);
    PurchaseOrderDetail update(UpdatePurchaseOrderDetailDto requestDto, Long purchaseOrderHeaderId, Long productId);

    PurchaseOrderDetail create(CreatePurchaseOrderDetailDto requestDto, Long purchaseOrderHeaderId) ;
    PurchaseOrderDetail getAndVerifyDto(CreatePurchaseOrderDetailDto requestDto, PurchaseOrderDetail entity, PurchaseOrderHeader purchaseOrderHeader);
}
