package com.product.inventory.management.controllers;

import com.product.inventory.management.config.PathController;
import com.product.inventory.management.dtos.create.CreateProductDto;
import com.product.inventory.management.dtos.update.UpdateProductDto;
import com.product.inventory.management.entities.Product;
import com.product.inventory.management.services.interfaces.IProductService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import java.util.Collection;

@RestController
@RequestMapping(PathController.PRODUCT)
@Tag(name = "Products (productos)")
public class ProductController {

    @Autowired
    private IProductService service;

    @GetMapping
    public ResponseEntity<Collection<Product>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> search(@PathVariable Long id) {
        return ResponseEntity.ok(service.findOne(id));
    }

    @PostMapping
    public ResponseEntity<Product> create(@Valid @RequestBody CreateProductDto roleDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(roleDto));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Product> patch(@PathVariable Long id,
                                         @RequestBody @Valid UpdateProductDto permissionDto)
             {
        return ResponseEntity.ok(service.update(permissionDto,id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.ok(String.format("Product with id '%s' deleted successfully", id));
    }




}
