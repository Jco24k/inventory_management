package com.product.inventory.management.services;

import com.product.inventory.management.dtos.create.CreateCategoryDto;
import com.product.inventory.management.dtos.update.UpdateCategoryDto;
import com.product.inventory.management.entities.Category;
import com.product.inventory.management.exception.ResourceNotFoundException;
import com.product.inventory.management.mappers.MapperNotNull;
import com.product.inventory.management.mappers.SubCategoryDtoMapper;
import com.product.inventory.management.repositories.CategoryRepository;
import com.product.inventory.management.services.interfaces.ICategoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class CategoryService implements ICategoryService {

    private final CategoryRepository repository;
    private final SubCategoryService subCategoryService;
    private final SubCategoryDtoMapper subCategoryDtoMapper;

    @Override
    @Transactional(readOnly = true)
    public Collection<Category> findAll() {
        return repository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Category findOne(Long id){
        return repository.findById(id).orElseThrow(
                ()-> new ResourceNotFoundException("Category not found with id: " + id)
        );
    }

    @Override
    @Transactional()
    public Category create(CreateCategoryDto requestDto) {
        Category newData = new Category();
        getAndVerifyDto(requestDto,newData);
        return repository.save(newData);
    }

    @Override
    @Transactional()
    public Category update(UpdateCategoryDto requestDto, Long id) {
        Category dataFound = findOne(id);
        modelMapperWithoutFks().map(requestDto,dataFound);
        return repository.save(dataFound);
    }

    @Override
    public void delete(Long id) {
        Category dataFound = findOne(id);
        dataFound.setIsActive(false);
        repository.save(dataFound);
    }

    private ModelMapper modelMapperWithoutFks() {
        ModelMapper modelMapper = MapperNotNull.notNullMapper();
         PropertyMap<CreateCategoryDto, Category> propertyMap = new
         PropertyMap<CreateCategoryDto, Category>() {
         protected void configure() {
            skip().setSubCategories(null);
            }
         };
        modelMapper.addMappings(propertyMap);
        return modelMapper;
    }

    private void getAndVerifyDto(CreateCategoryDto requestDto,Category entity){
        modelMapperWithoutFks().map(requestDto, entity);
        if(!requestDto.getSubCategoryDto().isEmpty()){
            entity.setSubCategories(requestDto.getSubCategoryDto().stream().map(subCategoryDtoMapper) .collect(Collectors.toSet()));
        }
    }

    public Set<Category> getCategories(Set<Long> listIds)  {
        Set<Category> categoriesFound = repository.findByIdIn(listIds);
        if (categoriesFound.size() != listIds.size()) {
            List<Long> notFoundIds = listIds.stream()
                    .filter(category -> categoriesFound.stream().noneMatch(cat -> cat.getId().equals(category)))
                    .toList();
            throw new ResourceNotFoundException("Some Category were not found: "+notFoundIds);
        }
        return categoriesFound;
    }

}
