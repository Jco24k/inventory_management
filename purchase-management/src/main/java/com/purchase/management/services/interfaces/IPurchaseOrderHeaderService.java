package com.purchase.management.services.interfaces;

import com.purchase.management.dtos.base.BasePurchaseOrderHeaderDto;
import com.purchase.management.dtos.create.CreatePurchaseOrderHeaderDto;
import com.purchase.management.dtos.update.UpdatePurchaseOrderHeaderDto;
import com.purchase.management.entities.PurchaseOrderHeader;
import java.util.Collection;

public interface IPurchaseOrderHeaderService {
    Collection<PurchaseOrderHeader> findAll();
    PurchaseOrderHeader findOne(Long id);
    PurchaseOrderHeader create(CreatePurchaseOrderHeaderDto requestDto) ;
    PurchaseOrderHeader update(UpdatePurchaseOrderHeaderDto requestDto, Long id) ;
    void delete(Long id) ;
    void getAndVerifyDto(BasePurchaseOrderHeaderDto requestDto, PurchaseOrderHeader entity,Boolean option);
}
