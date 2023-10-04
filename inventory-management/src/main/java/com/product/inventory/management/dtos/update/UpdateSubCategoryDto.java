package com.product.inventory.management.dtos.update;

import com.product.inventory.management.dtos.create.CreateSubCategoryDto;
import jakarta.annotation.Nullable;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateSubCategoryDto extends CreateSubCategoryDto {

    @Nullable
    @Size(min = 1,max = 30, groups = ValidateUpdate.class)
    private String name;


    @Nullable
    @Size(min = 1,max = 120, groups = ValidateUpdate.class)
    private String description;

    @Nullable
    private Boolean isActive;

}
