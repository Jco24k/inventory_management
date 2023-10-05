package com.product.inventory.management.services.interfaces;

import com.product.inventory.management.dtos.create.CreateProductDto;
import com.product.inventory.management.dtos.update.UpdateProductDto;
import com.product.inventory.management.entities.Product;
import java.util.Collection;

public interface IProductService {
    Collection<Product> findAll();
    Product findOne(Long id);
    Product create(CreateProductDto requestDto) ;
    Product update(UpdateProductDto requestDto, Long id) ;
    void delete(Long id) ;
}
