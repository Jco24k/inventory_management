package com.purchase.management.controllers;

import com.purchase.management.config.PathController;
import com.purchase.management.dtos.create.CreateInventoryIncomeDetailDto;
import com.purchase.management.dtos.create.CreateInventoryIncomeHeaderDto;
import com.purchase.management.dtos.create.CreatePurchaseOrderHeaderDto;
import com.purchase.management.dtos.update.UpdateInventoryIncomeHeaderDto;
import com.purchase.management.dtos.update.UpdatePurchaseOrderHeaderDto;
import com.purchase.management.entities.InventoryIncomeHeader;
import com.purchase.management.entities.PurchaseOrderHeader;
import com.purchase.management.entities.composite.InventoryIncomeDetail;
import com.purchase.management.services.interfaces.IInventoryIncomeHeaderService;
import com.purchase.management.services.interfaces.IPurchaseOrderHeaderService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping(PathController.INVENTORY_INCOME_HEADER)
@Tag(name = "InventoryIncomeHeader (Ingreso de Inventorio - Cabecera)")
public class InventoryIncomeHeaderController {

    @Autowired
    private IInventoryIncomeHeaderService service;

    @GetMapping
    public ResponseEntity<Collection<InventoryIncomeHeader>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<InventoryIncomeHeader> search(@PathVariable Long id) {
        return ResponseEntity.ok(service.findOne(id));
    }

    @CircuitBreaker(name = "allCB", fallbackMethod = "fallBackSaveHeader")
    @PostMapping
    public ResponseEntity<InventoryIncomeHeader> save(@Valid @RequestBody CreateInventoryIncomeHeaderDto requestDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(requestDto));
    }

    @CircuitBreaker(name = "allCB", fallbackMethod = "fallBackSaveHeader")
    @PatchMapping("/{id}")
    public ResponseEntity<InventoryIncomeHeader> patch(@PathVariable Long id,
                                         @RequestBody @Valid UpdateInventoryIncomeHeaderDto requestDto)
             {
        return ResponseEntity.ok(service.update(requestDto,id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.ok(String.format("InventoryIncomeHeader with id '%s' deleted successfully", id));
    }

    private ResponseEntity<InventoryIncomeHeader> fallBackSaveHeader(@Valid @RequestBody CreateInventoryIncomeHeaderDto requestDto, RuntimeException e) {
        return new ResponseEntity("Service failed - ProductManagement/EmployeeManagement", HttpStatus.SERVICE_UNAVAILABLE);
    }


}
