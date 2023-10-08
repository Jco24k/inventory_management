package com.purchase.management.services;

import com.purchase.management.dtos.create.CreateProductWarehouseStockDetailDto;
import com.purchase.management.dtos.update.UpdateProductWarehouseStockDetailDto;
import com.purchase.management.entities.Warehouse;
import com.purchase.management.entities.composite.ProductWarehouseStockDetail;
import com.purchase.management.entities.composite.ProductWarehouseStockDetailPk;
import com.purchase.management.exception.ResourceNotFoundException;
import com.purchase.management.feignclients.ProductManagementFeignClient;
import com.purchase.management.mappers.MapperNotNull;
import com.purchase.management.models.ProductModel;
import com.purchase.management.repositories.ProductWarehouseStockRepository;
import com.purchase.management.services.interfaces.IProductWarehouseStockDetailService;
import com.purchase.management.services.interfaces.IWarehouseService;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Collection;

@Service
public class ProductWarehouseStockService implements IProductWarehouseStockDetailService {

    private final ProductWarehouseStockRepository repository;
    private final ProductManagementFeignClient productManagementService;
    private final IWarehouseService warehouseService;

    public ProductWarehouseStockService(ProductWarehouseStockRepository repository,
                                        IWarehouseService warehouseService,
                                        @Lazy ProductManagementFeignClient productManagementService
                                       ){
        this.repository = repository;
        this.warehouseService = warehouseService;
        this.productManagementService = productManagementService;
    }

    @Override
    @Transactional(readOnly = true)
    public Collection<ProductWarehouseStockDetail> findAll() {
        return repository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public ProductWarehouseStockDetail findOne(Long productId, Long warehouseId ) {
        ProductWarehouseStockDetailPk newId = new ProductWarehouseStockDetailPk(productId,warehouseId);
        return repository.findById(newId).orElseThrow(
                () -> new ResourceNotFoundException("ProductWarehouseStockDetail not found with id: " + newId)
        );
    }

    @Override
    @Transactional()
    public ProductWarehouseStockDetail create(CreateProductWarehouseStockDetailDto requestDto, Long productId)  {
        ProductWarehouseStockDetail newDetail = new ProductWarehouseStockDetail();
        getAndVerifyDto(requestDto,newDetail);
        if(productId != null){
            ProductModel productModel = productManagementService.findOneProduct(productId);
            newDetail.setProduct(productModel.getId());
        }
        return repository.save(newDetail);
    }

    @Override
    @Transactional()
    public ProductWarehouseStockDetail update(UpdateProductWarehouseStockDetailDto requestDto,Long productId, Long warehouseId) {
        ProductWarehouseStockDetail newDetail = findOne(productId,warehouseId);
        MapperNotNull.notNullMapper().map(requestDto,newDetail);
        return repository.save(newDetail);
    }

    public void getAndVerifyDto(CreateProductWarehouseStockDetailDto requestDto, ProductWarehouseStockDetail entity){
        MapperNotNull.notNullMapper().map(requestDto, entity);
        if(requestDto.getWarehouseId()!=null){
            Warehouse warehouse = warehouseService.findOne(requestDto.getWarehouseId()) ;
            entity.setWarehouse(warehouse);
        }
    }





}