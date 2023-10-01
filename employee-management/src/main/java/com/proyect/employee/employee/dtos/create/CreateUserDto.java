package com.proyect.employee.employee.dtos.create;

import com.proyect.employee.employee.dtos.decorators.interfaces.NoDuplicates;
import jakarta.annotation.Nullable;
import jakarta.validation.GroupSequence;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;


@Getter
@Setter
@GroupSequence({ CreateUserDto.class, CreateUserDto.ValidatedUser.class })
public class CreateUserDto {

    @Size(min = 3,max = 30)
    @NotBlank(message = "name must not be null")
    @Email()
    private String username;

    @NotBlank(message = "password must not be null")
    @Size(min = 6, max = 50)
    @Pattern(regexp = "(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).{6,}",
            message = "The password must have a Uppercase, lowercase letter and a number ")
    private String password;

    @Nullable()
    @NoDuplicates()
    private List<@Min(1)Long> roleIds = new ArrayList<>();
    public interface ValidatedUser {
    }
}
