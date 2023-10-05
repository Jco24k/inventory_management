package com.product.inventory.management.controllers;

import com.product.inventory.management.config.PathController;
import com.product.inventory.management.dtos.create.CreateProductProviderDto;
import com.product.inventory.management.dtos.create.CreateProviderDto;
import com.product.inventory.management.dtos.update.UpdateProviderDto;
import com.product.inventory.management.entities.Provider;
import com.product.inventory.management.entities.composite.ProductProvider;
import com.product.inventory.management.services.interfaces.IProductProviderService;
import com.product.inventory.management.services.interfaces.IProviderService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping(PathController.PRODUCT_PROVIDER)
@Tag(name = "ProductProviders")
public class ProductProviderController {

    @Autowired
    private IProductProviderService service;

    @GetMapping
    public ResponseEntity<Collection<ProductProvider>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{productId}/{providerId}")
    public ResponseEntity<ProductProvider> search(@PathVariable Long productId, @PathVariable Long providerId) {
        return ResponseEntity.ok(service.findOne(productId,providerId));
    }

    @PostMapping
    public ResponseEntity<ProductProvider> save(@Valid @RequestBody
                                                    CreateProductProviderDto requestDto,
                                                @RequestParam(value = "productId", required = true) Long productId) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(requestDto,productId));
    }


}
