package com.purchase.management.dtos.update;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.Min;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class UpdateProductWarehouseStockDetailDto {

    @Nullable
    @Min(1)
    private BigDecimal stock;

}
