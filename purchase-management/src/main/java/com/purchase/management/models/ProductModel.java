package com.purchase.management.models;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductModel {

    private Long id;
    private String name;
    private String description;
    private BigDecimal price;
    private Boolean hasIgv;
    private Boolean isActive;


}
