package com.product.inventory.management.entities;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.product.inventory.management.entities.composite.ProductProvider;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
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
public class Provider extends BaseEntity{

    @Column( length = 50)
    private String name;

    @Column( length = 11 , unique = true, nullable = false)
    private String ruc;

    @Column(length = 150)
    private String address;

    @Column(name = "phones" ,columnDefinition = "TEXT[]")
    private Set<String> phones;

    @OneToMany(fetch = FetchType.EAGER,cascade = {CascadeType.PERSIST, CascadeType.MERGE }, mappedBy = "provider" ,targetEntity = ProductProvider.class)
    @JsonBackReference
    private Set<ProductProvider> productProviders = new HashSet<>();

}
