package com.product.inventory.management.controllers;

import com.product.inventory.management.config.PathController;
import com.product.inventory.management.dtos.create.CreateCategoryDto;
import com.product.inventory.management.dtos.create.CreateSubCategoryDto;
import com.product.inventory.management.dtos.update.UpdateCategoryDto;
import com.product.inventory.management.dtos.update.UpdateSubCategoryDto;
import com.product.inventory.management.entities.Category;
import com.product.inventory.management.entities.SubCategory;
import com.product.inventory.management.services.interfaces.ICategoryService;
import com.product.inventory.management.services.interfaces.ISubCategoryService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping(PathController.SUBCATEGORY)
@Tag(name = "SubCategories (sub-categorias)")
public class SubCategoryController {

    @Autowired
    private ISubCategoryService service;

    @GetMapping
    public ResponseEntity<Collection<SubCategory>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<SubCategory> search(@PathVariable Long id) {
        return ResponseEntity.ok(service.findOne(id));
    }

    @PostMapping
    public ResponseEntity<SubCategory> create(@Valid @RequestBody CreateSubCategoryDto requestDto,
                                              @RequestParam(value = "categoryId", required = true) Long categoryId) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(requestDto,categoryId));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<SubCategory> patch(@PathVariable Long id,
                                         @RequestBody @Validated(CreateSubCategoryDto.ValidateUpdate.class) UpdateSubCategoryDto requestDto)
             {
        return ResponseEntity.ok(service.update(requestDto,id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.ok(String.format("SubCategory with id '%s' deleted successfully", id));
    }




}
