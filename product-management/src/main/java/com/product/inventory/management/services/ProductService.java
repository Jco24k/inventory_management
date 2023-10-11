package com.product.inventory.management.services;

import com.product.inventory.management.dtos.create.CreateProductDto;
import com.product.inventory.management.dtos.update.UpdateProductDto;
import com.product.inventory.management.entities.Product;
import com.product.inventory.management.entities.composite.ProductProvider;
import com.product.inventory.management.exception.ResourceNotFoundException;
import com.product.inventory.management.mappers.MapperNotNull;
import com.product.inventory.management.repositories.ProductRepository;
import com.product.inventory.management.services.interfaces.IProductProviderService;
import com.product.inventory.management.services.interfaces.IProductService;
import com.product.inventory.management.services.interfaces.ISubCategoryService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ProductService implements IProductService {

    private final ProductRepository repository;
    private final ISubCategoryService subCategoryService;
    private final IProductProviderService productProviderService;

    public ProductService(ProductRepository repository,
                          ISubCategoryService subCategoryService,
                                  @Lazy IProductProviderService productProviderService) {
        this.repository = repository;
        this.subCategoryService = subCategoryService;
        this.productProviderService = productProviderService;
    }

    @Override
    @Transactional(readOnly = true)
    public Collection<Product> findAll() {
        return repository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Product findOne(Long id){
        return repository.findById(id).orElseThrow(
                ()-> new ResourceNotFoundException("Product not found with id: " + id)
        );
    }

    @Override
    @Transactional()
    public Product create(CreateProductDto requestDto) {
        Product newData = new Product();
        getAndVerifyDto(requestDto,newData);
        return repository.save(newData);
    }

    @Override
    @Transactional()
    public Product update(UpdateProductDto requestDto, Long id) {
        Product dataFound = findOne(id);
        modelMapperWithoutFks().map(requestDto,dataFound);
        return repository.save(dataFound);
    }

    @Override
    public void delete(Long id) {
        Product dataFound = findOne(id);
        dataFound.setIsActive(false);
        repository.save(dataFound);
    }

    private ModelMapper modelMapperWithoutFks() {
        ModelMapper modelMapper = MapperNotNull.notNullMapper();
         PropertyMap<CreateProductDto, Product> propertyMap = new
                 PropertyMap<>() {
                     protected void configure() {
                         skip().setProductProviders(null);
                         skip().setLogPrices(null);
                         skip().setSubCategories(null);
                     }
                 };
        modelMapper.addMappings(propertyMap);
        return modelMapper;
    }

    @Override
    public void getAndVerifyDto(CreateProductDto requestDto,Product entity){
        modelMapperWithoutFks().map(requestDto, entity);
        if(!requestDto.getSubCategoryIds().isEmpty()){
            entity.getSubCategories().clear();
            entity.setSubCategories(subCategoryService.getSubCategories(new HashSet<>(requestDto.getSubCategoryIds())));
        }
        if(!requestDto.getProductProviderDtos().isEmpty()){
            entity.getProductProviders().clear();
            Set<ProductProvider> productProvidersSet =
                    requestDto.getProductProviderDtos().stream().map(
                            productProvider -> productProviderService.getAndVerifyDto(productProvider,
                                    new ProductProvider(),entity)
                    ).collect(Collectors.toSet());
            entity.setProductProviders(productProvidersSet);
        }
    }

    @Override
    public Set<Product> getProducts(Set<Long> listIds)  {
        Set<Product> dataFound = repository.findByIdIn(listIds);
        if (dataFound.size() != listIds.size()) {
            List<Long> notFoundIds = listIds.stream()
                    .filter(product -> dataFound.stream().noneMatch(pro -> pro.getId().equals(product)))
                    .toList();
            throw new ResourceNotFoundException("Some Product were not found: "+notFoundIds);
        }
        return dataFound;
    }

}
