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
public class UpdateProductDto {

    @Nullable
    @Size(min = 1,max = 30)
    private String name;

    @Nullable
    @Size(min = 1,max = 120)
    private String description;

    @Nullable
    @Positive()
    private BigDecimal price;

    @Nullable
    private Boolean hasIgv;
}
