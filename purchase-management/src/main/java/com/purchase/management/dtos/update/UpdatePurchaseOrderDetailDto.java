package com.purchase.management.dtos.update;

import com.purchase.management.dtos.create.CreatePurchaseOrderDetailDto;
import jakarta.annotation.Nullable;
import jakarta.validation.constraints.Min;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class UpdatePurchaseOrderDetailDto extends CreatePurchaseOrderDetailDto {

    @Min(value = 1, groups = ValidateUpdate.class)
    @Nullable
    private Long productId;

    @Nullable
    @Min(value = 1, groups = ValidateUpdate.class)
    private BigDecimal cost;

    @Nullable
    @Min(value = 1, groups = ValidateUpdate.class)
    private BigDecimal quantity;

    @Nullable
    @Min(value = 1, groups = ValidateUpdate.class)
    private BigDecimal suggested_price;
    public interface ValidateUpdate {
    }
}
