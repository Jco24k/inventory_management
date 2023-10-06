package com.purchase.management.entities.composite;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.purchase.management.entities.Warehouse;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@DynamicInsert
@DynamicUpdate
@Entity
@Table
@IdClass(ProductWarehouseStockDetailPk.class)
public class ProductWarehouseStockDetail implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @JoinColumn(name = "product_id")
    private Long product;

    @Id
    @JoinColumn(name = "warehouse_id")
    @ManyToOne(cascade = { CascadeType.MERGE})
    @JsonManagedReference
    @JsonIgnoreProperties("productWarehouseDetails")
    private Warehouse warehouse;

    @Column(nullable = false, columnDefinition="Decimal(10,2)")
    private BigDecimal stock;

}
