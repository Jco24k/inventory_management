package com.product.inventory.management.repositories;

import com.product.inventory.management.entities.composite.ProductProvider;
import com.product.inventory.management.entities.composite.ProductProviderPk;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;


@Repository
public interface ProductProviderRepository extends JpaRepository<ProductProvider, ProductProviderPk> {

    Set<ProductProvider> findByProduct_Id(Long productId);
}
