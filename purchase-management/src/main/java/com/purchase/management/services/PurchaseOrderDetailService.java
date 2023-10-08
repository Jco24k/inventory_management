package com.purchase.management.services;

import com.purchase.management.dtos.create.CreatePurchaseOrderDetailDto;
import com.purchase.management.dtos.update.UpdatePurchaseOrderDetailDto;
import com.purchase.management.entities.PurchaseOrderHeader;
import com.purchase.management.entities.composite.PurchaseOrderDetail;
import com.purchase.management.entities.composite.PurchaseOrderDetailPk;
import com.purchase.management.exception.ResourceNotFoundException;
import com.purchase.management.feignclients.ProductManagementFeignClient;
import com.purchase.management.mappers.MapperNotNull;
import com.purchase.management.models.ProductModel;
import com.purchase.management.repositories.PurchaseOrderDetailRepository;
import com.purchase.management.services.interfaces.IPurchaseOrderDetailService;
import com.purchase.management.services.interfaces.IPurchaseOrderHeaderService;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Collection;

@Service
public class PurchaseOrderDetailService implements IPurchaseOrderDetailService {

    private final PurchaseOrderDetailRepository repository;
    private final ProductManagementFeignClient productManagementService;
    private final IPurchaseOrderHeaderService purchaseOrderHeaderService;

    public PurchaseOrderDetailService(PurchaseOrderDetailRepository repository,
                                       @Lazy ProductManagementFeignClient productManagementService,
                                       @Lazy IPurchaseOrderHeaderService purchaseOrderHeaderService
                                       ){
        this.repository = repository;
        this.productManagementService = productManagementService;
        this.purchaseOrderHeaderService = purchaseOrderHeaderService;
    }

    @Override
    @Transactional(readOnly = true)
    public Collection<PurchaseOrderDetail> findAll() {
        return repository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public PurchaseOrderDetail findOne(Long inventoryIncomeHeaderId, Long productId ) {
        PurchaseOrderDetailPk newId = new PurchaseOrderDetailPk(inventoryIncomeHeaderId,productId);
        return repository.findById(newId).orElseThrow(
                () -> new ResourceNotFoundException("PurchaseOrderDetail not found with id: " + newId)
        );
    }

    @Override
    @Transactional()
    public PurchaseOrderDetail create(CreatePurchaseOrderDetailDto requestDto, Long purchaseOrderHeaderId) {
        PurchaseOrderDetail newDetail = new PurchaseOrderDetail();
        getAndVerifyDto(requestDto,newDetail,null);
        PurchaseOrderHeader purchaseOrderHeader = purchaseOrderHeaderService.findOne(purchaseOrderHeaderId);
        BigDecimal total = updateTotal(BigDecimal.ZERO,requestDto.getQuantity().multiply(requestDto.getCost_amount()),purchaseOrderHeader.getTotal());
        purchaseOrderHeader.setTotal(total);
        newDetail.setPurchaseOrderHeader(purchaseOrderHeader);
        return repository.save(newDetail);
    }

    @Override
    @Transactional()
    public PurchaseOrderDetail update(UpdatePurchaseOrderDetailDto requestDto, Long purchaseOrderHeaderId, Long productId) {
        PurchaseOrderDetail newDetail = findOne(purchaseOrderHeaderId,productId);
        MapperNotNull.notNullMapper().map(requestDto,newDetail);
        BigDecimal total = updateTotal(newDetail.getSubtotal(),newDetail.getQuantity().
                multiply(newDetail.getCost_amount()),
                newDetail.getPurchaseOrderHeader().getTotal());
        newDetail.getPurchaseOrderHeader().setTotal(total);
        return repository.save(newDetail);
    }

    public PurchaseOrderDetail getAndVerifyDto(CreatePurchaseOrderDetailDto requestDto, PurchaseOrderDetail entity, PurchaseOrderHeader purchaseOrderHeader){
        MapperNotNull.notNullMapper().map(requestDto, entity);
        if(requestDto.getProductId()!=null){
            ProductModel productModel = productManagementService.findOneProduct(requestDto.getProductId());
            entity.setProduct(productModel.getId());
        }
        if(purchaseOrderHeader!=null) entity.setPurchaseOrderHeader(purchaseOrderHeader);
        return entity;
    }


    public BigDecimal updateTotal(BigDecimal oldSubtotal , BigDecimal newSubtotal, BigDecimal total){
        BigDecimal difference = newSubtotal.subtract(oldSubtotal);
        return total.add(difference);
    }


}