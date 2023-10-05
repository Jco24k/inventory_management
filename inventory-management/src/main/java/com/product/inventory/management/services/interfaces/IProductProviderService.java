package com.product.inventory.management.services.interfaces;

import com.product.inventory.management.dtos.create.CreateCategoryDto;
import com.product.inventory.management.dtos.create.CreateProductProviderDto;
import com.product.inventory.management.dtos.update.UpdateCategoryDto;
import com.product.inventory.management.dtos.update.UpdateProductProviderDto;
import com.product.inventory.management.entities.Category;
import com.product.inventory.management.entities.composite.ProductProvider;

import java.util.Collection;

public interface IProductProviderService {
    Collection<ProductProvider> findAll();
    ProductProvider findOne(Long id);
    ProductProvider create(CreateProductProviderDto requestDto) ;
    ProductProvider update(UpdateProductProviderDto requestDto, Long id) ;
    void delete(Long id) ;
}
