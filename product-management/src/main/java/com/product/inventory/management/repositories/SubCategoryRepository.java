package com.product.inventory.management.repositories;

import com.product.inventory.management.entities.SubCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Set;

@Repository
public interface SubCategoryRepository extends JpaRepository<SubCategory, Long> {
    Set<SubCategory> findByIdIn(Set<Long> subCategoryIds);
}
