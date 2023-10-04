package com.product.inventory.management.entities;


import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import java.io.Serial;
import java.io.Serializable;
import java.util.HashSet;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@DynamicInsert
@DynamicUpdate
@Entity
@Table
public class SubCategory extends BaseEntity implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private String name;
    private String description;

    @ManyToOne(fetch = FetchType.EAGER,cascade = {CascadeType.MERGE} ,targetEntity = Category.class)
    @JoinColumn(name = "category_id")
    @JsonBackReference
    private Category category;

}
