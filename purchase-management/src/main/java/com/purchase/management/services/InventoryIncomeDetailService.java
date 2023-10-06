package com.purchase.management.services;

import com.purchase.management.dtos.create.CreateInventoryIncomeDetailDto;
import com.purchase.management.entities.InventoryIncomeHeader;
import com.purchase.management.entities.composite.InventoryIncomeDetail;
import com.purchase.management.entities.composite.InventoryIncomeDetailPk;
import com.purchase.management.exception.ResourceNotFoundException;
import com.purchase.management.feignclients.ProductManagementFeignClient;
import com.purchase.management.mappers.MapperNotNull;
import com.purchase.management.models.ProductModel;
import com.purchase.management.repositories.InventoryIncomeDetailRepository;
import com.purchase.management.services.interfaces.IInventoryIncomeDetailService;
import com.purchase.management.services.interfaces.IInventoryIncomeHeaderService;
import com.purchase.management.services.interfaces.IPurchaseOrderHeaderService;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Collection;

@Service
public class InventoryIncomeDetailService implements IInventoryIncomeDetailService {

    private final InventoryIncomeDetailRepository repository;
    private final ProductManagementFeignClient productManagementService;
    private final IInventoryIncomeHeaderService inventoryIncomeHeaderService;

    public InventoryIncomeDetailService(InventoryIncomeDetailRepository repository,
                                         @Lazy ProductManagementFeignClient productManagementService,
                                         @Lazy IInventoryIncomeHeaderService inventoryIncomeHeaderService
                                       ){
        this.repository = repository;
        this.productManagementService = productManagementService;
        this.inventoryIncomeHeaderService = inventoryIncomeHeaderService;
    }

    @Override
    @Transactional(readOnly = true)
    public Collection<InventoryIncomeDetail> findAll() {
        return repository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public InventoryIncomeDetail findOne(Long inventoryIncomeHeaderId, Long productId ) {
        InventoryIncomeDetailPk newId = new InventoryIncomeDetailPk(inventoryIncomeHeaderId,productId);
        return repository.findById(newId).orElseThrow(
                () -> new ResourceNotFoundException("PurchaseOrderDetail not found with id: " + newId)
        );
    }

    @Override
    @Transactional()
    public     InventoryIncomeDetail create(CreateInventoryIncomeDetailDto requestDto, Long inventoryIncomeHeaderId)  {
        InventoryIncomeDetail newDetail = new InventoryIncomeDetail();
        getAndVerifyDto(requestDto,newDetail,null);
        if(inventoryIncomeHeaderId != null){
            newDetail.setInventoryIncomeHeader(inventoryIncomeHeaderService.findOne(inventoryIncomeHeaderId));
        }
        return repository.save(newDetail);
    }

    public InventoryIncomeDetail getAndVerifyDto(CreateInventoryIncomeDetailDto requestDto, InventoryIncomeDetail entity, InventoryIncomeHeader inventoryIncomeHeader){
        MapperNotNull.notNullMapper().map(requestDto, entity);
        if(requestDto.getProductId()!=null){
            ProductModel productModel = productManagementService.findOneProduct(requestDto.getProductId());
            entity.setProduct(productModel.getId());
        }
        if(inventoryIncomeHeader!=null) entity.setInventoryIncomeHeader(inventoryIncomeHeader);
        return entity;
    }





}