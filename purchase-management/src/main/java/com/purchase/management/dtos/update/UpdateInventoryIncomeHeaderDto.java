package com.purchase.management.dtos.update;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.Min;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;
import java.math.BigDecimal;

@Getter
@Setter
public class UpdateInventoryIncomeHeaderDto {

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
    @DateTimeFormat(pattern = "yyyy-MM-dd", iso = DateTimeFormat.ISO.DATE)
    private String date_income;

    @Nullable
    private Boolean isActive;

}
