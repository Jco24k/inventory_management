package com.product.inventory.management.repositories;

import com.product.inventory.management.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    Set<Category> findByIdIn(Set<Long> categoryIds);
}
