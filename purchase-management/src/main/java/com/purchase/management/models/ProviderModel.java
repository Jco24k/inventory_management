package com.purchase.management.models;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProviderModel {

    private Long id;
    private String name;
    private String ruc;
    private String address;
    private Set<String> phones;
    private Boolean isActive;
}
