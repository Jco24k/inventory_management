package com.purchase.management.dtos.update;

import com.purchase.management.dtos.create.CreateInventoryIncomeDetailDto;
import com.purchase.management.dtos.create.CreateInventoryIncomeHeaderDto;
import jakarta.annotation.Nullable;
import jakarta.validation.GroupSequence;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class UpdateInventoryIncomeHeaderDto extends CreateInventoryIncomeHeaderDto {

    @Nullable
    @Min(value = 1,groups = ValidateUpdate.class)
    private Long userId;

    @Nullable
    @Min(value = 1,groups = ValidateUpdate.class)
    private Long providerId;

    @Nullable
    @Min(value = 1,groups = ValidateUpdate.class)
    private Long purchaseOrderId;

    @Nullable
    @Min(value = 1,groups = ValidateUpdate.class)
    private BigDecimal cost_amount;

    @Nullable
    @Min(value = 1,groups = ValidateUpdate.class)
    private BigDecimal quantity;

    @Nullable
    @DateTimeFormat(pattern = "yyyy-MM-dd", iso = DateTimeFormat.ISO.DATE)
    private String date_income;

    @Nullable
    private Boolean changeStock;

    @Nullable()
    private List<CreateInventoryIncomeDetailDto> inventoryIncomeDetailDtos = new ArrayList<>();

    public interface ValidateUpdate {
    }
}
