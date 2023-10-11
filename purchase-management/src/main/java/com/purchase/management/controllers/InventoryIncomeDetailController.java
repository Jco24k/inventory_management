package com.purchase.management.controllers;

import com.purchase.management.config.PathController;
import com.purchase.management.dtos.create.CreateInventoryIncomeDetailDto;
import com.purchase.management.dtos.update.UpdateInventoryIncomeDetailDto;
import com.purchase.management.entities.composite.InventoryIncomeDetail;
import com.purchase.management.services.interfaces.IInventoryIncomeDetailService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping(PathController.INVENTORY_INCOME_DETAIL)
@Tag(name = "InventoryIncomeDetail (Ingreso de Inventario - Detalle)")
public class InventoryIncomeDetailController {

    @Autowired
    private IInventoryIncomeDetailService service;

    @GetMapping
    public ResponseEntity<Collection<InventoryIncomeDetail>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{inventoryIncomeHeaderId}/{productId}")
    public ResponseEntity<InventoryIncomeDetail> search(@PathVariable Long inventoryIncomeHeaderId,
                                                      @PathVariable Long productId) {
        return ResponseEntity.ok(service.findOne(inventoryIncomeHeaderId,productId));
    }

    @CircuitBreaker(name = "productCB", fallbackMethod = "fallBackSaveDetail")
    @PostMapping
    public ResponseEntity<InventoryIncomeDetail> save(@Valid @RequestBody
                                                          CreateInventoryIncomeDetailDto requestDto,
     @RequestParam(value = "inventoryIncomeHeaderId", required = true) Long inventoryIncomeHeaderId) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(requestDto,inventoryIncomeHeaderId));
    }

    @PatchMapping("/{inventoryIncomeHeaderId}/{productId}")
    public ResponseEntity<InventoryIncomeDetail> patch(@PathVariable Long inventoryIncomeHeaderId,
                                                       @PathVariable Long productId,
                                                       @RequestBody @Valid UpdateInventoryIncomeDetailDto requestDto)
    {
        return ResponseEntity.ok(service.update(requestDto,inventoryIncomeHeaderId,productId));
    }


    private ResponseEntity<InventoryIncomeDetail> fallBackSaveDetail(@Valid @RequestBody
                                                                     CreateInventoryIncomeDetailDto requestDto,
                                                                     @RequestParam(value = "inventoryIncomeHeaderId", required = true) Long inventoryIncomeHeaderId, RuntimeException e) {
        return new ResponseEntity("Service failed - ProductManagement", HttpStatus.OK);
    }


}
