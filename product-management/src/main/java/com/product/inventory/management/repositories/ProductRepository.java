package com.product.inventory.management.repositories;

import com.product.inventory.management.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    Set<Product> findByIdIn(Set<Long> categoryIds);
}
