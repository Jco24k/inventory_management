package com.purchase.management.dtos.update;

import com.purchase.management.dtos.create.CreatePurchaseOrderDetailDto;
import com.purchase.management.dtos.create.CreatePurchaseOrderHeaderDto;
import jakarta.annotation.Nullable;
import jakarta.validation.constraints.Min;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class UpdatePurchaseOrderHeaderDto extends CreatePurchaseOrderHeaderDto {

    @Min(value = 1, groups = ValidateUpdate.class)
    @Nullable
    private Long userId;

    @Min(value = 1, groups = ValidateUpdate.class)
    @Nullable
    private Long providerId;

    @Min(value = 1, groups = ValidateUpdate.class)
    @Nullable
    private BigDecimal cost_amount;

    @Min(value = 1, groups = ValidateUpdate.class)
    @Nullable
    private BigDecimal quantity;

    @Nullable
    @DateTimeFormat(pattern = "yyyy-MM-dd", iso = DateTimeFormat.ISO.DATE)
    private String date_order;

    @Nullable()
    private List<CreatePurchaseOrderDetailDto> purchaseDetailDtos = new ArrayList<>();

    public interface ValidateUpdate {
    }
}
