package com.purchase.management.services.interfaces;

import com.purchase.management.dtos.create.CreateInventoryIncomeHeaderDto;
import com.purchase.management.dtos.update.UpdateInventoryIncomeHeaderDto;
import com.purchase.management.entities.InventoryIncomeHeader;

import java.util.Collection;

public interface IInventoryIncomeHeaderService {
    Collection<InventoryIncomeHeader> findAll();
    InventoryIncomeHeader findOne(Long id);
    InventoryIncomeHeader create(CreateInventoryIncomeHeaderDto requestDto) ;
    InventoryIncomeHeader update(UpdateInventoryIncomeHeaderDto requestDto, Long id) ;
    void delete(Long id) ;

    void getAndVerifyDto(CreateInventoryIncomeHeaderDto requestDto,InventoryIncomeHeader entity);
}
