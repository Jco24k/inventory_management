package com.product.inventory.management.entities;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.product.inventory.management.entities.composite.ProductProvider;
import com.product.inventory.management.entities.listener.AuditLogPriceListener;
import com.product.inventory.management.entities.logs.LogPrice;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@DynamicInsert
@DynamicUpdate
@Entity
@Table
@EntityListeners({AuditingEntityListener.class, AuditLogPriceListener.class})
public class Product extends BaseEntity {

    @Column(unique = true, length = 30,nullable = false)
    private String name;

    @Column( length = 120)
    private String description;

    @Column(nullable = false, columnDefinition="Decimal(10,2)")
    private BigDecimal price;

    @Column(name = "has_igv", columnDefinition = "boolean default true")
    private Boolean hasIgv;

    @ManyToMany(fetch = FetchType.EAGER,cascade = {CascadeType.MERGE} ,targetEntity = Category.class)
    @JoinTable(name="products_categories",
            joinColumns = @JoinColumn(name="product_id"),
            inverseJoinColumns = @JoinColumn(name="category_id")
    )
    @JsonManagedReference
    private Set<Category> categories = new HashSet<>();

    @OneToMany(fetch = FetchType.EAGER,cascade = {CascadeType.PERSIST,CascadeType.MERGE }, mappedBy = "product" ,targetEntity = ProductProvider.class)
    @JsonManagedReference()
    @JsonIgnoreProperties("product")
    private Set<ProductProvider> productProviders = new HashSet<>();

    @OneToMany(fetch = FetchType.EAGER,cascade = { CascadeType.MERGE }, mappedBy = "product" ,targetEntity = LogPrice.class)
    @JsonBackReference
    private Set<LogPrice> logPrices = new HashSet<>();

}
