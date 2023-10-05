package com.product.inventory.management.services.interfaces;

import com.product.inventory.management.dtos.create.CreateSubCategoryDto;
import com.product.inventory.management.dtos.update.UpdateSubCategoryDto;
import com.product.inventory.management.entities.SubCategory;

import java.util.Collection;
import java.util.Set;

public interface ISubCategoryService {
    Collection<SubCategory> findAll();
    SubCategory findOne(Long id);
    SubCategory create(CreateSubCategoryDto categoryDto, Long categoryId) ;
    SubCategory update(UpdateSubCategoryDto categoryDto, Long id) ;
    void delete(Long id) ;

    Set<SubCategory> getSubCategories(Set<Long> listIds);
}
