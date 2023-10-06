package com.purchase.management.services.interfaces;

import com.purchase.management.dtos.create.CreateWarehouseDto;
import com.purchase.management.dtos.update.UpdateWarehouseDto;
import com.purchase.management.entities.Warehouse;
import java.util.Collection;

public interface IWarehouseService {
    Collection<Warehouse> findAll();
    Warehouse findOne(Long id);
    Warehouse create(CreateWarehouseDto requestDto) ;
    Warehouse update(UpdateWarehouseDto requestDto, Long id) ;
    void delete(Long id) ;

}
