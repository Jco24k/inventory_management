package com.purchase.management.dtos.update;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.Min;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;
import java.math.BigDecimal;

@Getter
@Setter
public class UpdatePurchaseOrderHeaderDto  {

    @Min(value = 1)
    @Nullable
    private Long userId;

    @Min(value = 1)
    @Nullable
    private Long providerId;

    @Nullable
    @DateTimeFormat(pattern = "yyyy-MM-dd", iso = DateTimeFormat.ISO.DATE)
    private String date_order;

    @Nullable
    private Boolean isActive;

}
