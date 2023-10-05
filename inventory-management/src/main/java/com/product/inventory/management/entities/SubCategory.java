package com.product.inventory.management.entities;


import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import java.io.Serial;
import java.io.Serializable;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@DynamicInsert
@DynamicUpdate
@Entity
@Table
public class SubCategory extends BaseEntity {

    @Column(unique = true, length = 30,nullable = false)
    private String name;

    @Column(length = 120)
    private String description;

    @ManyToOne(fetch = FetchType.EAGER,cascade = {CascadeType.MERGE} ,targetEntity = Category.class)
    @JoinColumn(name = "category_id")
    @JsonBackReference
    private Category category;

    public SubCategory(String name, String description){
        this.name = name;
        this.description = description;
    }

}
