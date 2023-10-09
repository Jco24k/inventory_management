package com.purchase.management.services;

import com.purchase.management.dtos.create.CreateInventoryIncomeDetailDto;
import com.purchase.management.dtos.update.UpdateInventoryIncomeDetailDto;
import com.purchase.management.entities.InventoryIncomeHeader;
import com.purchase.management.entities.Warehouse;
import com.purchase.management.entities.composite.InventoryIncomeDetail;
import com.purchase.management.entities.composite.InventoryIncomeDetailPk;
import com.purchase.management.entities.composite.ProductWarehouseStockDetail;
import com.purchase.management.exception.ResourceNotFoundException;
import com.purchase.management.feignclients.ProductManagementFeignClient;
import com.purchase.management.mappers.MapperNotNull;
import com.purchase.management.models.ProductModel;
import com.purchase.management.repositories.InventoryIncomeDetailRepository;
import com.purchase.management.services.interfaces.IInventoryIncomeDetailService;
import com.purchase.management.services.interfaces.IInventoryIncomeHeaderService;
import com.purchase.management.services.interfaces.IProductWarehouseStockDetailService;
import com.purchase.management.services.interfaces.IWarehouseService;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Collection;

@Service
public class InventoryIncomeDetailService implements IInventoryIncomeDetailService {

    private final InventoryIncomeDetailRepository repository;
    private final ProductManagementFeignClient productManagementService;
    private final IInventoryIncomeHeaderService inventoryIncomeHeaderService;
    private final IProductWarehouseStockDetailService productWarehouseStockDetailService;

    public InventoryIncomeDetailService(InventoryIncomeDetailRepository repository,
                                        IProductWarehouseStockDetailService productWarehouseStockDetailService,
                                         @Lazy ProductManagementFeignClient productManagementService,
                                         @Lazy IInventoryIncomeHeaderService inventoryIncomeHeaderService
                                       ){
        this.repository = repository;
        this.productWarehouseStockDetailService = productWarehouseStockDetailService;
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
        if(inventoryIncomeHeaderId != null){
            newDetail.setInventoryIncomeHeader(inventoryIncomeHeaderService.findOne(inventoryIncomeHeaderId));
        }
        getAndVerifyDto(requestDto,newDetail,null);
        return repository.save(newDetail);
    }

    @Override
    @Transactional()
    public InventoryIncomeDetail update(UpdateInventoryIncomeDetailDto requestDto, Long inventoryIncomeHeaderId, Long productId) {
        InventoryIncomeDetail newDetail = findOne(inventoryIncomeHeaderId,productId);
        BigDecimal oldQuantity = newDetail.getQuantity();
        MapperNotNull.notNullMapper().map(requestDto,newDetail);
        updatedStock(newDetail,oldQuantity,newDetail.getQuantity());
        return repository.save(newDetail);
    }

    public InventoryIncomeDetail getAndVerifyDto(CreateInventoryIncomeDetailDto requestDto, InventoryIncomeDetail entity, InventoryIncomeHeader inventoryIncomeHeader){
        MapperNotNull.notNullMapper().map(requestDto, entity);
        if(requestDto.getProductId()!=null){
            ProductModel productModel = productManagementService.findOneProduct(requestDto.getProductId());
            entity.setProduct(productModel.getId());
        }
        if(inventoryIncomeHeader!=null) entity.setInventoryIncomeHeader(inventoryIncomeHeader);
        updatedStock(entity,BigDecimal.ZERO,entity.getQuantity());
        return entity;
    }


    public void updatedStock( InventoryIncomeDetail entity,BigDecimal oldQuantity,
                              BigDecimal newQuantity){
        ProductWarehouseStockDetail stockFound = productWarehouseStockDetailService
                .findOne(
                        entity.getProduct(),
                        entity.getInventoryIncomeHeader().getWarehouse().getId(),
                        false
                );
        BigDecimal currentQuantity = BigDecimal.ZERO;
        if(stockFound == null){
            stockFound = new ProductWarehouseStockDetail(entity.getProduct(),
                    entity.getInventoryIncomeHeader().getWarehouse(), BigDecimal.ZERO);
            entity.getInventoryIncomeHeader().getWarehouse().getProductWarehouseStockDetails().add(stockFound);
        }else{
            currentQuantity = stockFound.getStock();
        }
        BigDecimal difference = newQuantity.subtract(oldQuantity);
        stockFound.setStock(currentQuantity.add(difference));
        if(stockFound.getStock().compareTo(BigDecimal.ZERO) < 0){
            throw  new IllegalArgumentException("Stock must not be less than 0");
        }
    }




}