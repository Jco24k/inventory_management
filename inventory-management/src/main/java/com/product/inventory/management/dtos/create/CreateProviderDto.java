package com.product.inventory.management.dtos.create;

import jakarta.annotation.Nullable;
import jakarta.validation.GroupSequence;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@GroupSequence({ CreateProviderDto.class, CreateProviderDto.ValidateUpdate.class })
public class CreateProviderDto {

    @Nullable()
    @Size(min = 1,max = 50, groups = ValidateUpdate.class)
    private String name;

    @Length(max = 11)
    @NotBlank(message = "ruc must not be null")
    private String ruc;

    @Nullable()
    @Size(max = 150, groups = ValidateUpdate.class)
    private String address;

    @Nullable()
    private List<String> phones = new ArrayList<>();
    public interface ValidateUpdate {
    }
}
