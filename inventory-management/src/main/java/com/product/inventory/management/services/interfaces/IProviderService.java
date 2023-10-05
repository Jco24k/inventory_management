package com.product.inventory.management.services.interfaces;

import com.product.inventory.management.dtos.create.CreateProviderDto;
import com.product.inventory.management.dtos.update.UpdateProviderDto;
import com.product.inventory.management.entities.Provider;
import java.util.Collection;

public interface IProviderService {
    Collection<Provider> findAll();
    Provider findOne(Long id);
    Provider create(CreateProviderDto requestDto) ;
    Provider update(UpdateProviderDto requestDto, Long id) ;
    void delete(Long id) ;
}
