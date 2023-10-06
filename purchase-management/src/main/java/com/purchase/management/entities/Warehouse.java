package com.purchase.management.entities;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.purchase.management.entities.composite.ProductWarehouseStockDetail;
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
public class Warehouse extends BaseEntity {

    @Column(unique = true, length = 30,nullable = false)
    private String name;

    @Column( length = 120)
    private String description;

    @Column( length = 150)
    private String address;

    @OneToMany(fetch = FetchType.EAGER,cascade = {CascadeType.PERSIST,CascadeType.MERGE }, mappedBy = "warehouse" ,targetEntity = ProductWarehouseStockDetail.class)
    @JsonManagedReference()
    @JsonIgnoreProperties("warehouse")
    private Set<ProductWarehouseStockDetail> productWarehouseStockDetails = new HashSet<>();

}
