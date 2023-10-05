package com.product.inventory.management.dtos.update;

import com.product.inventory.management.dtos.create.CreateProviderDto;
import jakarta.annotation.Nullable;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateProviderDto extends CreateProviderDto {

    @Size(max = 11,min = 11,groups = ValidateUpdate.class)
    private String ruc;

    @Nullable
    private Boolean isActive;

}
