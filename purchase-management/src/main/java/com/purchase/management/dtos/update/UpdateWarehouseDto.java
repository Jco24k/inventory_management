package com.purchase.management.dtos.update;

import com.purchase.management.dtos.create.CreateWarehouseDto;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateWarehouseDto extends CreateWarehouseDto {

    @Size(min = 1,max = 30, groups = ValidateUpdate.class)
    private String name;


}
