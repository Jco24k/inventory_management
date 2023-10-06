package com.purchase.management.entities;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.purchase.management.entities.composite.InventoryIncomeDetail;
import com.purchase.management.entities.enums.EIncomeType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.UpdateTimestamp;
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
public class InventoryIncomeHeader extends BaseEntity{


    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    private EIncomeType type;

    @CreationTimestamp
    @Column(name = "created_at")
    private Date createdAt;

    @Column(columnDefinition = "Date")
    private String date_income;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private Date updatedAt;

    @Column(nullable = false, columnDefinition="Decimal(10,2)")
    private BigDecimal cost_amount;

    @Column(name = "provider_id",nullable = false)
    private Long providerId;

    @Column(name = "user_id",nullable = false)
    private Long userId;

    @Column(name = "change_stock", columnDefinition = "bit(1) default 1")
    private Boolean changeStock;

    @OneToMany(fetch = FetchType.EAGER,cascade = {CascadeType.PERSIST,CascadeType.MERGE }, mappedBy = "inventoryIncomeHeader" ,targetEntity = InventoryIncomeDetail.class)
    @JsonManagedReference()
    @JsonIgnoreProperties("inventoryIncomeHeader")
    private Set<InventoryIncomeDetail> inventoryIncomeDetails = new HashSet<>();

    @OneToOne(cascade = { CascadeType.MERGE})
    @JoinColumn(name = "purchase_order_header_id")
    @JsonManagedReference()
    private PurchaseOrderHeader purchaseOrderHeader;
}
