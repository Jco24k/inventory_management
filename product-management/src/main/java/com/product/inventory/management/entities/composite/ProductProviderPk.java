package com.product.inventory.management.entities.composite;

import lombok.*;

import java.io.Serial;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductProviderPk implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private Long product;
    private Long provider;
}
