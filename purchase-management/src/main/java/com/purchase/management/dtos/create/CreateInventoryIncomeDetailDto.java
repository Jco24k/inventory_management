package com.purchase.management.dtos.create;

import jakarta.validation.GroupSequence;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@GroupSequence({ CreateInventoryIncomeDetailDto.class, CreateInventoryIncomeDetailDto.ValidateUpdate.class })
public class CreateInventoryIncomeDetailDto {

    @Min(1)
    @NotNull(message = "productId must not be null")
    private Long productId;

    @NotNull(message = "cost_amount must not be null")
    @Min(1)
    private BigDecimal cost_amount;

    @NotNull(message = "quantity must not be null")
    @Min(1)
    private BigDecimal quantity;
    public interface ValidateUpdate {
    }
}
