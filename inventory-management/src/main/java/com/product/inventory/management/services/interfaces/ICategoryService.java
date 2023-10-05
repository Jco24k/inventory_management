package com.product.inventory.management.services.interfaces;

import com.product.inventory.management.dtos.create.CreateCategoryDto;
import com.product.inventory.management.dtos.update.UpdateCategoryDto;
import com.product.inventory.management.entities.Category;
import java.util.Collection;
import java.util.Set;

public interface ICategoryService {
    Collection<Category> findAll();
    Category findOne(Long id);
    Category create(CreateCategoryDto categoryDto) ;
    Category update(UpdateCategoryDto categoryDto, Long id) ;
    void delete(Long id) ;
    Set<Category> getCategories(Set<Long> listIds);
}
