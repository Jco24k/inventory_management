package com.purchase.management.entities.composite;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.purchase.management.entities.PurchaseOrderHeader;
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
@IdClass(PurchaseOrderDetailPk.class)
public class PurchaseOrderDetail implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;


    @Id
    @JoinColumn(name = "purchase_order_header_id")
    @ManyToOne(cascade = { CascadeType.PERSIST,CascadeType.MERGE})
    @JsonManagedReference
    @JsonIgnoreProperties("purchaseOrderDetails")
    private PurchaseOrderHeader purchaseOrderHeader;

    @Id
    @JoinColumn(name = "product_id")
    private Long product;


    @Column(nullable = false, columnDefinition="Decimal(10,2)")
    private BigDecimal cost_amount;

    @Column(nullable = false, columnDefinition="Decimal(10,2)")
    private BigDecimal quantity;

    @Column(nullable = false, columnDefinition="Decimal(10,2)")
    private BigDecimal subtotal;

    @Column(nullable = true, columnDefinition="Decimal(10,2)")
    private BigDecimal suggested_price;

    @PreUpdate
    @PrePersist
    public void chargeTotal(){
        subtotal = quantity.multiply(cost_amount);
    }
}
