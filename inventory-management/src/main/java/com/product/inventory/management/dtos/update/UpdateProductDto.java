package com.product.inventory.management.dtos.update;

import com.product.inventory.management.dtos.create.CreateProductDto;
import com.product.inventory.management.dtos.decorators.interfaces.NoDuplicates;
import jakarta.annotation.Nullable;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class UpdateProductDto extends CreateProductDto {

    @Nullable
    @Size(min = 1,max = 30, groups = ValidateUpdate.class)
    private String name;

    @Nullable
    @Size(min = 1,max = 120, groups = ValidateUpdate.class)
    private String description;

    @Nullable
    @Positive()
    private BigDecimal price;

    @Nullable
    private Boolean hasIgv;

    @Nullable
    @NoDuplicates(groups = ValidateUpdate.class)
    private List<Long> categoryIds = new ArrayList<>();
}
