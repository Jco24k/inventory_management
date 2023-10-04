package com.product.inventory.management.entities;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import java.io.Serial;
import java.io.Serializable;
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
public class Category extends BaseEntity implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private String name;
    private String description;

    @OneToMany(fetch = FetchType.EAGER,cascade = { CascadeType.MERGE }, mappedBy = "category" ,targetEntity = SubCategory.class)
    @JsonManagedReference
    private Set<SubCategory> subCategories = new HashSet<>();

}
