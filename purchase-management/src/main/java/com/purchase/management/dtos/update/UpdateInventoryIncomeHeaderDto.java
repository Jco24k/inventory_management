package com.purchase.management.dtos.update;

import com.purchase.management.dtos.base.BaseInventoryIncomeHeaderDto;
import jakarta.annotation.Nullable;
import jakarta.validation.constraints.Min;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;
import java.math.BigDecimal;

@Getter
@Setter
public class UpdateInventoryIncomeHeaderDto extends BaseInventoryIncomeHeaderDto {

    @Nullable
    @Min(value = 1)
    private Long userId;

    @Nullable
    @Min(value = 1)
    private Long providerId;

    @Nullable
    @Min(value = 1)
    private Long purchaseOrderId;

    @Nullable
    @Min(value = 1)
    private BigDecimal cost_amount;

    @Nullable
    @Min(value = 1)
    private Long warehouseId;

    @Nullable
    @DateTimeFormat(pattern = "yyyy-MM-dd", iso = DateTimeFormat.ISO.DATE)
    private String date_income;

    @Nullable
    private Boolean isActive;

}
