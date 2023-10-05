package com.product.inventory.management.services;

import com.product.inventory.management.dtos.create.CreateProductProviderDto;
import com.product.inventory.management.entities.Product;
import com.product.inventory.management.entities.composite.ProductProvider;
import com.product.inventory.management.entities.composite.ProductProviderPk;
import com.product.inventory.management.exception.ResourceNotFoundException;
import com.product.inventory.management.mappers.MapperNotNull;
import com.product.inventory.management.repositories.ProductProviderRepository;
import com.product.inventory.management.services.interfaces.IProductProviderService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Collection;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class ProductProviderService implements IProductProviderService {

    private final ProductProviderRepository repository;
    private final ProviderService providerService;
    private final ProductService productService;

    @Override
    @Transactional(readOnly = true)
    public Collection<ProductProvider> findAll() {
        return repository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public ProductProvider findOne(Long productId, Long providerId) {

        ProductProviderPk productProviderPk = new ProductProviderPk(productId,providerId);
        return repository.findById(productProviderPk).orElseThrow(
                ()-> new ResourceNotFoundException("ProductProvider not found with id: " + productProviderPk)
        );

    }

    @Override
    @Transactional(readOnly = true)
    public Set<ProductProvider> findOneProduct(Long productId) {
        return repository.findByProduct_Id(productId);

    }

    @Override
    @Transactional()
    public ProductProvider create(CreateProductProviderDto requestDto, Long productId) {
        ProductProvider newCategory = new ProductProvider();
        getAndVerifyDto(requestDto, newCategory,null);
        if(productId != null){
            newCategory.setProduct(productService.findOne(productId));
        }
        return repository.save(newCategory);
    }

    private ModelMapper modelMapperWithoutFks() {
        ModelMapper modelMapper = MapperNotNull.notNullMapper();
        PropertyMap<CreateProductProviderDto, ProductProvider> propertyMap = new
                PropertyMap<>() {
                    protected void configure() {
                        skip().setProvider(null);
                        skip().setProduct(null);
                    }
                };
        modelMapper.addMappings(propertyMap);
        return modelMapper;
    }

    public ProductProvider getAndVerifyDto(CreateProductProviderDto requestDto, ProductProvider entity, Product product){
        modelMapperWithoutFks().map(requestDto, entity);
        if(requestDto.getProviderId()!=null){
            entity.setProvider(providerService.findOne(requestDto.getProviderId()));
        }
        if(product!=null) entity.setProduct(product);
        return entity;
    }
}