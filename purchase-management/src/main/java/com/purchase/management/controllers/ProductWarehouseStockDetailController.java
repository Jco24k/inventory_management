package com.purchase.management.controllers;

import com.purchase.management.config.PathController;
import com.purchase.management.dtos.create.CreateInventoryIncomeDetailDto;
import com.purchase.management.dtos.create.CreateProductWarehouseStockDetailDto;
import com.purchase.management.dtos.update.UpdateInventoryIncomeDetailDto;
import com.purchase.management.dtos.update.UpdateProductWarehouseStockDetailDto;
import com.purchase.management.entities.composite.InventoryIncomeDetail;
import com.purchase.management.entities.composite.ProductWarehouseStockDetail;
import com.purchase.management.services.interfaces.IInventoryIncomeDetailService;
import com.purchase.management.services.interfaces.IProductWarehouseStockDetailService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping(PathController.PRODUCT_WAREHOUSE_STOCK)
@Tag(name = "ProductWarehouseStock")
public class ProductWarehouseStockDetailController {

    @Autowired
    private IProductWarehouseStockDetailService service;

    @GetMapping
    public ResponseEntity<Collection<ProductWarehouseStockDetail>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{productId}/{warehouseId}")
    public ResponseEntity<ProductWarehouseStockDetail> search(@PathVariable Long productId,
                                                      @PathVariable Long warehouseId) {
        return ResponseEntity.ok(service.findOne(productId,productId,true));
    }

    @PostMapping
    public ResponseEntity<ProductWarehouseStockDetail> save(@Valid @RequestBody
                                                                CreateProductWarehouseStockDetailDto requestDto,
     @RequestParam(value = "productId", required = true) Long productId) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(requestDto,productId));
    }

    @PatchMapping("/{productId}/{warehouseId}")
    public ResponseEntity<ProductWarehouseStockDetail> patch(@PathVariable Long productId,
                                                       @PathVariable Long warehouseId,
                                                       @RequestBody @Valid UpdateProductWarehouseStockDetailDto requestDto)
    {
        return ResponseEntity.ok(service.update(requestDto,productId,warehouseId));
    }



}
