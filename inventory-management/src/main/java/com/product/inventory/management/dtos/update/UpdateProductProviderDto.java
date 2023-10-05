package com.product.inventory.management.dtos.update;

import com.product.inventory.management.dtos.create.CreateProductProviderDto;
import jakarta.annotation.Nullable;
import jakarta.validation.constraints.Min;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class UpdateProductProviderDto extends CreateProductProviderDto {

    @Nullable()
    @Min(value = 1,groups = ValidateUpdate.class)
    private Long providerId;

    @Nullable()
    @Min(value = 1, groups = ValidateUpdate.class)
    private BigDecimal cost_amount;

}
