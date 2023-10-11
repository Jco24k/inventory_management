package com.product.inventory.management.dtos.create;

import com.product.inventory.management.dtos.decorators.interfaces.NoDuplicates;
import jakarta.annotation.Nullable;
import jakarta.validation.GroupSequence;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@GroupSequence({ CreateProductDto.class, CreateProductDto.ValidateUpdate.class })
public class CreateProductDto {

    @Size(min = 1,max = 30)
    @NotBlank(message = "name must not be null")
    private String name;

    @Nullable()
    @Size(max = 120)
    private String description;

    @NotNull(message = "price must not be null")
    @Min(1)
    private BigDecimal price;

    @Nullable
    private Boolean hasIgv;

    @Nullable()
    @NoDuplicates()
    private List<Long> subCategoryIds = new ArrayList<>();

    @Nullable()
    private List<CreateProductProviderDto> productProviderDtos = new ArrayList<>();

    public interface ValidateUpdate {
    }
}
