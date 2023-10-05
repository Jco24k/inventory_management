package com.product.inventory.management.entities.composite;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.product.inventory.management.entities.Product;
import com.product.inventory.management.entities.Provider;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@DynamicInsert
@DynamicUpdate
@Entity
@Table
@IdClass(ProductProviderPk.class)
public class ProductProvider {
    @Id
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "product_id")
    @JsonBackReference
    private Product product;

    @Id
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "provider_id")
    @JsonBackReference
    private Provider provider;

    @Column(nullable = false, columnDefinition="Decimal(10,2)")
    private BigDecimal cost_amount;
}
