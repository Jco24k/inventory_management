package com.purchase.management.dtos.update;

import com.purchase.management.dtos.base.BaseInventoryIncomeHeaderDto;
import com.purchase.management.entities.enums.EIncomeType;
import jakarta.annotation.Nullable;
import jakarta.validation.constraints.Min;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

@Getter
@Setter
public class UpdateInventoryIncomeHeaderDto extends BaseInventoryIncomeHeaderDto {

    @Nullable
    private EIncomeType type;

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
    private Long warehouseId;

    @Nullable
    @DateTimeFormat(pattern = "yyyy-MM-dd", iso = DateTimeFormat.ISO.DATE)
    private String date_income;

    @Nullable
    private Boolean isActive;

}
