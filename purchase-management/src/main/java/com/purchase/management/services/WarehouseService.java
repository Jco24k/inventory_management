package com.purchase.management.services;

import com.purchase.management.dtos.create.CreateWarehouseDto;
import com.purchase.management.dtos.update.UpdateWarehouseDto;
import com.purchase.management.entities.Warehouse;
import com.purchase.management.exception.ResourceNotFoundException;
import com.purchase.management.mappers.MapperNotNull;
import com.purchase.management.repositories.WarehouseRepository;
import com.purchase.management.services.interfaces.IWarehouseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Collection;

@Service
@RequiredArgsConstructor
public class WarehouseService implements IWarehouseService {

    private final WarehouseRepository repository;

    @Override
    @Transactional(readOnly = true)
    public Collection<Warehouse> findAll() {
        return repository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Warehouse findOne(Long id) {
        return repository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Warehouse not found with id: " + id)
        );
    }

    @Override
    @Transactional()
    public Warehouse create(CreateWarehouseDto requestDto) {
        Warehouse warehouse = new Warehouse();
        MapperNotNull.notNullMapper().map(requestDto, warehouse);
        return repository.save(warehouse);
    }

    @Override
    @Transactional()
    public Warehouse update(UpdateWarehouseDto requestDto, Long id) {
        Warehouse dataFound = findOne(id);
        MapperNotNull.notNullMapper().map(requestDto, dataFound);
        return repository.save(dataFound);
    }

    @Override
    public void delete(Long id) {
        Warehouse dataFound = findOne(id);
        dataFound.setIsActive(false);
        repository.save(dataFound);
    }

}