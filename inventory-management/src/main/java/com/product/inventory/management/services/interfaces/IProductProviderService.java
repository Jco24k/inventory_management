package com.product.inventory.management.services.interfaces;

import com.product.inventory.management.dtos.create.CreateProductProviderDto;
import com.product.inventory.management.dtos.update.UpdateProductProviderDto;
import com.product.inventory.management.entities.Product;
import com.product.inventory.management.entities.composite.ProductProvider;
import java.util.Collection;
import java.util.Set;

public interface IProductProviderService {
    Collection<ProductProvider> findAll();

    ProductProvider findOne(Long productId,Long providerId);
    Set<ProductProvider> findOneProduct(Long id);
    ProductProvider create(CreateProductProviderDto requestDto, Long productId);

    ProductProvider getAndVerifyDto(CreateProductProviderDto requestDto, ProductProvider entity, Product product);
}
