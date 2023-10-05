package com.product.inventory.management.dtos.create;

import jakarta.validation.GroupSequence;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import java.math.BigDecimal;

@Getter
@Setter
@GroupSequence({ CreateProductProviderDto.class, CreateProductProviderDto.ValidateUpdate.class })
public class CreateProductProviderDto {

    @Min(1)
    @NotNull(message = "providerId must not be null")
    private Long providerId;

    @NotNull(message = "cost_amount must not be null")
    @Min(1)
    private BigDecimal cost_amount;

    public interface ValidateUpdate {
    }
}
