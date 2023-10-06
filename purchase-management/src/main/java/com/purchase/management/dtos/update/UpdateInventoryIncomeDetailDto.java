package com.purchase.management.dtos.update;

import com.purchase.management.dtos.create.CreateInventoryIncomeDetailDto;
import jakarta.validation.constraints.Min;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class UpdateInventoryIncomeDetailDto {

    @Min(value = 1 )
    private BigDecimal cost_amount;

    @Min(value = 1)
    private BigDecimal quantity;

}
