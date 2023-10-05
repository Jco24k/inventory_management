package com.product.inventory.management.dtos.update;

import com.product.inventory.management.dtos.create.CreateProviderDto;
import jakarta.annotation.Nullable;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

@Getter
@Setter
public class UpdateProviderDto extends CreateProviderDto {

    @Length(max = 11, groups = ValidateUpdate.class)
    private String ruc;

    @Nullable
    private Boolean isActive;

}
