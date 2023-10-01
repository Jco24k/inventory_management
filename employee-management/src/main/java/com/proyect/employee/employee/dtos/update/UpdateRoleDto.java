package com.proyect.employee.employee.dtos.update;

import com.proyect.employee.employee.dtos.create.CreateRoleDto;
import jakarta.annotation.Nullable;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class UpdateRoleDto extends CreateRoleDto{

    @Nullable
    private String name;

    @Nullable
    private Boolean isActive;

    @Nullable
    private List<Long> permissionIds = new ArrayList<>();
}
