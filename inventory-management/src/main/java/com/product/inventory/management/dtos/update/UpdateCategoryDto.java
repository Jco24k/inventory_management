package com.product.inventory.management.dtos.update;

import com.product.inventory.management.dtos.create.CreateCategoryDto;
import com.product.inventory.management.dtos.create.CreateSubCategoryDto;
import com.product.inventory.management.dtos.decorators.interfaces.NoDuplicates;
import jakarta.annotation.Nullable;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class UpdateCategoryDto extends CreateCategoryDto {

    @Nullable
    @Size(min = 1,max = 30, groups = ValidateUpdate.class)
    private String name;

    @Nullable
    @Size(min = 1,max = 120, groups = ValidateUpdate.class)
    private String description;

    @Nullable
    private Boolean isActive;

    @Nullable
    @NoDuplicates(groups = ValidateUpdate.class)
    private List<Long> subCategoryIds = new ArrayList<>();
}
