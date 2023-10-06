package com.purchase.management.dtos.update;

import com.purchase.management.dtos.create.CreatePurchaseOrderDetailDto;
import jakarta.annotation.Nullable;
import jakarta.validation.constraints.Min;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class UpdatePurchaseOrderDetailDto {

    @Nullable
    @Min(value = 1)
    private BigDecimal cost_amount;

    @Nullable
    @Min(value = 1)
    private BigDecimal quantity;

    @Nullable
    @Min(value = 1)
    private BigDecimal suggested_price;

}
