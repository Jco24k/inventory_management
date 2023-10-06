package com.purchase.management.controllers;

import com.purchase.management.config.PathController;
import com.purchase.management.dtos.create.CreatePurchaseOrderHeaderDto;
import com.purchase.management.dtos.update.UpdatePurchaseOrderHeaderDto;
import com.purchase.management.entities.PurchaseOrderHeader;
import com.purchase.management.services.interfaces.IPurchaseOrderHeaderService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping(PathController.PURCHASE_ORDER_HEADER)
@Tag(name = "PurchaseOrderHeader (Orden de Compra - Cabecera)")
public class PurchaseOrderHeaderController {

    @Autowired
    private IPurchaseOrderHeaderService service;

    @GetMapping
    public ResponseEntity<Collection<PurchaseOrderHeader>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PurchaseOrderHeader> search(@PathVariable Long id) {
        return ResponseEntity.ok(service.findOne(id));
    }

    @PostMapping
    public ResponseEntity<PurchaseOrderHeader> save(@Valid @RequestBody CreatePurchaseOrderHeaderDto requestDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(requestDto));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<PurchaseOrderHeader> patch(@PathVariable Long id,
                                         @RequestBody @Valid UpdatePurchaseOrderHeaderDto requestDto)
             {
        return ResponseEntity.ok(service.update(requestDto,id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.ok(String.format("PurchaseOrderHeader with id '%s' deleted successfully", id));
    }




}
