package com.product.inventory.management.controllers;

import com.product.inventory.management.config.PathController;
import com.product.inventory.management.dtos.create.CreateCategoryDto;
import com.product.inventory.management.dtos.create.CreateProductDto;
import com.product.inventory.management.dtos.update.UpdateCategoryDto;
import com.product.inventory.management.dtos.update.UpdateProductDto;
import com.product.inventory.management.entities.Category;
import com.product.inventory.management.entities.Product;
import com.product.inventory.management.services.interfaces.ICategoryService;
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
@RequestMapping(PathController.CATEGORY)
@Tag(name = "Categories (categorias)")
public class CategoryController {

    @Autowired
    private ICategoryService service;

    @GetMapping
    public ResponseEntity<Collection<Category>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Category> search(@PathVariable Long id) {
        return ResponseEntity.ok(service.findOne(id));
    }

    @PostMapping
    public ResponseEntity<Category> save(@Valid @RequestBody CreateCategoryDto requestDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(requestDto));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Category> patch(@PathVariable Long id,
                                         @RequestBody @Valid UpdateCategoryDto requestDto)
             {
        return ResponseEntity.ok(service.update(requestDto,id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.ok(String.format("Category with id '%s' deleted successfully", id));
    }




}
