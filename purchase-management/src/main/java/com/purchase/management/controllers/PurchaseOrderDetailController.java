package com.purchase.management.controllers;

import com.purchase.management.config.PathController;
import com.purchase.management.dtos.create.CreatePurchaseOrderDetailDto;
import com.purchase.management.dtos.create.CreatePurchaseOrderHeaderDto;
import com.purchase.management.dtos.update.UpdatePurchaseOrderHeaderDto;
import com.purchase.management.entities.PurchaseOrderHeader;
import com.purchase.management.entities.composite.PurchaseOrderDetail;
import com.purchase.management.services.interfaces.IPurchaseOrderDetailService;
import com.purchase.management.services.interfaces.IPurchaseOrderHeaderService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping(PathController.PURCHASE_ORDER_DETAIL)
@Tag(name = "PurchaseOrderDetail (Orden de Compra - Detalle)")
public class PurchaseOrderDetailController {

    @Autowired
    private IPurchaseOrderDetailService service;

    @GetMapping
    public ResponseEntity<Collection<PurchaseOrderDetail>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{purchaseOrderHeaderId}/{productId}")
    public ResponseEntity<PurchaseOrderDetail> search(@PathVariable Long purchaseOrderHeaderId,
                                                      @PathVariable Long productId) {
        return ResponseEntity.ok(service.findOne(purchaseOrderHeaderId,productId));
    }

    @PostMapping
    public ResponseEntity<PurchaseOrderDetail> save(@Valid @RequestBody
                                                        CreatePurchaseOrderDetailDto requestDto,
     @RequestParam(value = "purchaseOrderHeaderId", required = true) Long purchaseOrderHeaderId) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(requestDto,purchaseOrderHeaderId));
    }





}
