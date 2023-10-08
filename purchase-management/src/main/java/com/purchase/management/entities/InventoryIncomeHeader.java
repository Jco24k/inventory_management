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
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
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
    private BigDecimal total;

    @Column(name = "provider_id",nullable = false)
    private Long providerId;

    @Column(name = "user_id",nullable = false)
    private Long userId;

    @OneToMany(fetch = FetchType.EAGER,cascade = { CascadeType.PERSIST,CascadeType.MERGE }, mappedBy = "inventoryIncomeHeader" ,targetEntity = InventoryIncomeDetail.class)
    @JsonManagedReference()
    @JsonIgnoreProperties("inventoryIncomeHeader")
    private Set<InventoryIncomeDetail> inventoryIncomeDetails = new HashSet<>();

    @OneToOne(cascade = { CascadeType.MERGE })
    @JoinColumn(name = "purchase_order_header_id")
    @JsonManagedReference()
    private PurchaseOrderHeader purchaseOrderHeader;

    @ManyToOne(cascade = { CascadeType.MERGE } , targetEntity = Warehouse.class)
    @JoinColumn(name = "warehouse_id")
    @JsonManagedReference
    private Warehouse warehouse;


    @PreUpdate
    @PrePersist
    public void chargeTotal(){
        log.info("Verify: " + inventoryIncomeDetails.size());
        if(inventoryIncomeDetails!=null){
            total = inventoryIncomeDetails.stream().
                    map(detail -> {
                        BigDecimal subtotal = detail.getQuantity().
                                multiply(detail.getCost_amount());
                        detail.setSubtotal(subtotal);
                        return  subtotal;
                    }).reduce(BigDecimal.ZERO,BigDecimal::add);
        }
    }
}
