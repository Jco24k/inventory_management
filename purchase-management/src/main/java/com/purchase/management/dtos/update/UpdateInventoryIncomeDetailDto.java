package com.purchase.management.dtos.update;

import com.purchase.management.dtos.create.CreateInventoryIncomeDetailDto;
import jakarta.validation.constraints.Min;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class UpdateInventoryIncomeDetailDto  extends CreateInventoryIncomeDetailDto {

    @Min(value = 1,groups = ValidateUpdate.class)
    private Long productId;

    @Min(value = 1 ,groups = ValidateUpdate.class)
    private BigDecimal cost;

    @Min(value = 1 ,groups = ValidateUpdate.class)
    private BigDecimal quantity;
    public interface ValidateUpdate {
    }
}
