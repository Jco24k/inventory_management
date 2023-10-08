package com.purchase.management.dtos.create;

import com.purchase.management.dtos.base.BasePurchaseOrderHeaderDto;
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
@GroupSequence({ CreatePurchaseOrderHeaderDto.class, CreatePurchaseOrderHeaderDto.ValidateUpdate.class })
public class CreatePurchaseOrderHeaderDto extends BasePurchaseOrderHeaderDto {

    @Min(1)
    @NotNull(message = "userId must not be null")
    private Long userId;

    @Min(1)
    @NotNull(message = "productId must not be null")
    private Long providerId;

    @NotNull()
    @DateTimeFormat(pattern = "yyyy-MM-dd", iso = DateTimeFormat.ISO.DATE)
    private String date_order;

    @Nullable()
    private List<CreatePurchaseOrderDetailDto> purchaseDetailDtos = new ArrayList<>();

    public interface ValidateUpdate {
    }
}
