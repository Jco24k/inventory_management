package com.purchase.management.controllers;

import com.purchase.management.config.PathController;
import com.purchase.management.dtos.create.CreateWarehouseDto;
import com.purchase.management.dtos.update.UpdateWarehouseDto;
import com.purchase.management.entities.Warehouse;
import com.purchase.management.services.interfaces.IWarehouseService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import java.util.Collection;

@RestController
@RequestMapping(PathController.WAREHOUSE)
@Tag(name = "Warehouse (almacen)")
public class WarehouseController {

    @Autowired
    private IWarehouseService service;

    @GetMapping
    public ResponseEntity<Collection<Warehouse>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Warehouse> search(@PathVariable Long id) {
        return ResponseEntity.ok(service.findOne(id));
    }

    @PostMapping
    public ResponseEntity<Warehouse> save(@Valid @RequestBody CreateWarehouseDto requestDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(requestDto));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Warehouse> patch(@PathVariable Long id,
                                         @RequestBody @Validated(CreateWarehouseDto.ValidateUpdate.class) UpdateWarehouseDto requestDto)
             {
        return ResponseEntity.ok(service.update(requestDto,id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.ok(String.format("Warehouse with id '%s' deleted successfully", id));
    }


}
