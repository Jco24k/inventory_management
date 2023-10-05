package com.product.inventory.management.services;

import com.product.inventory.management.dtos.create.CreateProviderDto;
import com.product.inventory.management.dtos.update.UpdateProviderDto;
import com.product.inventory.management.entities.Provider;
import com.product.inventory.management.exception.ResourceNotFoundException;
import com.product.inventory.management.mappers.MapperNotNull;
import com.product.inventory.management.repositories.ProviderRepository;
import com.product.inventory.management.services.interfaces.IProviderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Collection;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class ProviderService implements IProviderService {

    private final ProviderRepository repository;

    @Override
    @Transactional(readOnly = true)
    public Collection<Provider> findAll() {
        return repository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Provider findOne(Long id) {
        return repository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Provider not found with id: " + id)
        );
    }

    @Override
    @Transactional()
    public Provider create(CreateProviderDto requestDto) {
        Provider newProvider = new Provider();
        MapperNotNull.notNullMapper().map(requestDto, newProvider);
        return repository.save(newProvider);
    }

    @Override
    @Transactional()
    public Provider update(UpdateProviderDto requestDto, Long id) {
        Provider dataFound = findOne(id);
        MapperNotNull.notNullMapper().map(requestDto, dataFound);
        return repository.save(dataFound);
    }

    @Override
    public void delete(Long id) {
        Provider dataFound = findOne(id);
        dataFound.setIsActive(false);
        repository.save(dataFound);
    }

    public Set<Provider> getProviders(Set<Long> listIds)  {
        Set<Provider> providerFounds = repository.findByIdIn(listIds);
        if (providerFounds.size() != listIds.size()) {
            List<Long> notFoundIds = listIds.stream()
                    .filter(provider -> providerFounds.stream().noneMatch(prov -> prov.getId().equals(provider)))
                    .toList();
            throw new ResourceNotFoundException("Some Providers were not found: "+notFoundIds);
        }
        return providerFounds;
    }

}