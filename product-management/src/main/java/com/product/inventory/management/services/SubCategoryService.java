package com.product.inventory.management.services;

import com.product.inventory.management.dtos.create.CreateSubCategoryDto;
import com.product.inventory.management.dtos.update.UpdateSubCategoryDto;
import com.product.inventory.management.entities.SubCategory;
import com.product.inventory.management.exception.ResourceNotFoundException;
import com.product.inventory.management.mappers.MapperNotNull;
import com.product.inventory.management.repositories.SubCategoryRepository;
import com.product.inventory.management.services.interfaces.ICategoryService;
import com.product.inventory.management.services.interfaces.ISubCategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Collection;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class SubCategoryService implements ISubCategoryService {

    private final SubCategoryRepository repository;
    private final ICategoryService categoryService;

    @Override
    @Transactional(readOnly = true)
    public Collection<SubCategory> findAll() {
        return repository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public SubCategory findOne(Long id) {
        return repository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("SubCategory not found with id: " + id)
        );
    }

    @Override
    @Transactional()
    public SubCategory create(CreateSubCategoryDto requestDto,Long categoryId) {
        SubCategory newCategory = new SubCategory();
        MapperNotNull.notNullMapper().map(requestDto, newCategory);
        if(categoryId != null){
            newCategory.setCategory(categoryService.findOne(categoryId));
        }
        return repository.save(newCategory);
    }

    @Override
    @Transactional()
    public SubCategory update(UpdateSubCategoryDto requestDto, Long id) {
        SubCategory dataFound = findOne(id);
        MapperNotNull.notNullMapper().map(requestDto, dataFound);
        return repository.save(dataFound);
    }

    @Override
    public void delete(Long id) {
        SubCategory dataFound = findOne(id);
        dataFound.setIsActive(false);
        repository.save(dataFound);
    }

    @Override
    public Set<SubCategory> getSubCategories(Set<Long> listIds)  {
        Set<SubCategory> subCategoriesFound = repository.findByIdIn(listIds);
        if (subCategoriesFound.size() != listIds.size()) {
            List<Long> notFoundIds = listIds.stream()
                    .filter(subCategory -> subCategoriesFound.stream().noneMatch(sub -> sub.getId().equals(subCategory)))
                    .toList();
            throw new ResourceNotFoundException("Some SubCategory were not found: "+notFoundIds);
        }
        return subCategoriesFound;
    }

}