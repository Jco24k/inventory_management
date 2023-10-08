package com.purchase.management.dtos.create;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import java.math.BigDecimal;

@Getter
@Setter
public class CreateProductWarehouseStockDetailDto {

    @NotNull(message = "warehouseId must not be null")
    @Min(1)
    private Long warehouseId;

    @NotNull(message = "stock must not be null")
    @Min(1)
    private BigDecimal stock;

}
