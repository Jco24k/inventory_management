package com.purchase.management.services.interfaces;

import com.purchase.management.dtos.create.CreateInventoryIncomeDetailDto;
import com.purchase.management.dtos.create.CreateInventoryIncomeHeaderDto;
import com.purchase.management.entities.InventoryIncomeHeader;
import com.purchase.management.entities.composite.InventoryIncomeDetail;
import java.util.Collection;

public interface IInventoryIncomeDetailService {
    Collection<InventoryIncomeDetail> findAll();
    InventoryIncomeDetail findOne(Long inventoryIncomeHeaderId, Long productId);
    InventoryIncomeDetail create(CreateInventoryIncomeDetailDto requestDto, Long inventoryIncomeHeaderId) ;
    InventoryIncomeDetail getAndVerifyDto(CreateInventoryIncomeDetailDto requestDto, InventoryIncomeDetail entity, InventoryIncomeHeader inventoryIncomeHeader);
}
