package com.product.inventory.management.mappers;

import com.product.inventory.management.dtos.create.CreateSubCategoryDto;
import com.product.inventory.management.entities.SubCategory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
@Slf4j
public class SubCategoryDtoMapper implements Function<CreateSubCategoryDto, SubCategory> {

    @Override
    public SubCategory apply(CreateSubCategoryDto subCategoryDto){
        return new SubCategory(subCategoryDto.getName(),subCategoryDto.getDescription());
    }
}
