package com.product.inventory.management.dtos.create;

import com.product.inventory.management.dtos.decorators.interfaces.NoDuplicates;
import jakarta.annotation.Nullable;
import jakarta.validation.GroupSequence;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@GroupSequence({ CreateCategoryDto.class, CreateCategoryDto.ValidateUpdate.class })
public class CreateCategoryDto {

    @Size(min = 1,max = 30)
    @NotBlank(message = "name must not be null")
    private String name;

    @Size(max = 120)
    @NotBlank(message = "description must not be null")
    private String description;

    @Nullable()
    @NoDuplicates()
    private List<@Min(1)Long> subCategoryIds = new ArrayList<>();
    public interface ValidateUpdate {
    }
}
