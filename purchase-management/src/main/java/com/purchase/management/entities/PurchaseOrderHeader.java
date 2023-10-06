package com.purchase.management.entities;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.purchase.management.entities.composite.PurchaseOrderDetail;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.Date;
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
public class PurchaseOrderHeader extends BaseEntity{

    @CreationTimestamp
    @Column(name = "created_at")
    private Date createdAt;

    @DateTimeFormat(pattern = "yyyy-MM-dd", iso = DateTimeFormat.ISO.DATE)
    @Column(columnDefinition = "Date")
    private String date_order;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private Date updatedAt;

    @Column(nullable = false, columnDefinition="Decimal(10,2)")
    private BigDecimal cost_amount;

    @Column(name = "provider_id",nullable = false)
    private Long providerId;

    @Column(name = "user_id",nullable = false)
    private Long userId;

    @OneToMany(fetch = FetchType.EAGER,cascade = {CascadeType.PERSIST,CascadeType.MERGE }, mappedBy = "purchaseOrderHeader" ,targetEntity = PurchaseOrderDetail.class)
    @JsonManagedReference()
    @JsonIgnoreProperties("purchaseOrderHeader")
    private Set<PurchaseOrderDetail> purchaseOrderDetails = new HashSet<>();

    @OneToOne(cascade = { CascadeType.MERGE})
    @JsonBackReference()
    private InventoryIncomeHeader inventoryIncomeHeader;
}
