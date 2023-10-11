package com.product.inventory.management.entities;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import java.io.Serial;
import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@DynamicInsert
@DynamicUpdate
@Setter
@Getter
@Entity
@Table
public class Category extends BaseEntity{

    @Column(unique = true, length = 30,nullable = false)
    private String name;

    @Column( length = 120)
    private String description;

    @OneToMany(fetch = FetchType.EAGER,cascade = { CascadeType.PERSIST, CascadeType.MERGE }, mappedBy = "category" ,targetEntity = SubCategory.class)
    @JsonManagedReference
    private Set<SubCategory> subCategories = new HashSet<>();

}
