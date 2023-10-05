package com.product.inventory.management.entities;


import com.fasterxml.jackson.annotation.JsonManagedReference;
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

    @Column(unique = true, length = 30,nullable = false)
    private String name;

    @Column( length = 11)
    private String ruc;

    @Column(length = 150)
    private String address;

    @Column(name = "phone" ,columnDefinition = "TEXT[]")
    private Set<String> phones;

    @OneToMany(fetch = FetchType.EAGER,cascade = {CascadeType.PERSIST, CascadeType.MERGE }, mappedBy = "provider" ,targetEntity = ProductProvider.class)
    @JsonManagedReference
    private Set<ProductProvider> productProviders = new HashSet<>();

}
