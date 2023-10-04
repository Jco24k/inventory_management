package com.product.inventory.management.dtos.create;

import jakarta.validation.GroupSequence;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@GroupSequence({ CreateSubCategoryDto.class, CreateSubCategoryDto.ValidateUpdate.class })
public class CreateSubCategoryDto {

    @Size(min = 1,max = 30)
    @NotBlank(message = "name must not be null")
    private String name;

    @Size(max = 120)
    @NotBlank(message = "description must not be null")
    private String description;
    public interface ValidateUpdate {
    }
}
