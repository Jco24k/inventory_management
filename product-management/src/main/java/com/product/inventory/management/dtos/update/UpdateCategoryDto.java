package com.product.inventory.management.dtos.update;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateCategoryDto {

    @Nullable
    @Size(min = 1,max = 30)
    private String name;

    @Nullable
    @Size(min = 1,max = 120)
    private String description;

    @Nullable
    private Boolean isActive;
}
