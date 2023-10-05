package com.product.inventory.management.controllers;

import com.product.inventory.management.config.PathController;
import com.product.inventory.management.dtos.create.CreateProviderDto;
import com.product.inventory.management.dtos.update.UpdateProviderDto;
import com.product.inventory.management.entities.Provider;
import com.product.inventory.management.services.interfaces.IProviderService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Collection;

@RestController
@RequestMapping(PathController.PROVIDER)
@Tag(name = "Providers (proveedores)")
public class ProviderController {

    @Autowired
    private IProviderService service;

    @GetMapping
    public ResponseEntity<Collection<Provider>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Provider> search(@PathVariable Long id) {
        return ResponseEntity.ok(service.findOne(id));
    }

    @PostMapping
    public ResponseEntity<Provider> save(@Valid @RequestBody CreateProviderDto requestDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(requestDto));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Provider> patch(@PathVariable Long id,
                                         @RequestBody @Valid UpdateProviderDto requestDto)
             {
        return ResponseEntity.ok(service.update(requestDto,id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.ok(String.format("Provider with id '%s' deleted successfully", id));
    }




}
