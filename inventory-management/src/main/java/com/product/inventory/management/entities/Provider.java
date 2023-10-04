package com.product.inventory.management.entities;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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

    @OneToMany(fetch = FetchType.EAGER,cascade = { CascadeType.MERGE }, mappedBy = "category" ,targetEntity = SubCategory.class)
    @JsonBackReference
    private Set<SubCategory> subCategories = new HashSet<>();

}
