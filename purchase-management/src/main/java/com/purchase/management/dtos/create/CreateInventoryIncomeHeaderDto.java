package com.purchase.management.dtos.create;

import com.purchase.management.entities.enums.EIncomeType;
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
@GroupSequence({ CreateInventoryIncomeHeaderDto.class, CreateInventoryIncomeHeaderDto.ValidateUpdate.class })
public class CreateInventoryIncomeHeaderDto {

    @NotNull(message = "type must not be null")
    private EIncomeType type;

    @Min(1)
    @NotNull(message = "userId must not be null")
    private Long userId;

    @Min(1)
    @NotNull(message = "productId must not be null")
    private Long providerId;

    @Min(1)
    @NotNull(message = "purchaseOrderId must not be null")
    private Long purchaseOrderId;

    @NotNull()
    @DateTimeFormat(pattern = "yyyy-MM-dd", iso = DateTimeFormat.ISO.DATE)
    private String date_income;

    @Nullable()
    private List<CreateInventoryIncomeDetailDto> inventoryIncomeDetailDtos = new ArrayList<>();

    public interface ValidateUpdate {
    }
}
