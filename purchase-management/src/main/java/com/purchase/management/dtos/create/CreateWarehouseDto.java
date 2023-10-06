package com.purchase.management.dtos.create;

import jakarta.annotation.Nullable;
import jakarta.validation.GroupSequence;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@GroupSequence({ CreateWarehouseDto.class, CreateWarehouseDto.ValidateUpdate.class })
public class CreateWarehouseDto {

    @Size(min = 1,max = 30)
    @NotBlank(message = "name must not be null")
    private String name;

    @Nullable()
    @Size(max = 120,groups = ValidateUpdate.class)
    private String description;

    @Nullable()
    @Size(max = 150, groups = ValidateUpdate.class)
    private String address;
    public interface ValidateUpdate {
    }
}
