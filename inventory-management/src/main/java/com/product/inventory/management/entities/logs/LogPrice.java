package com.product.inventory.management.entities.logs;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.product.inventory.management.entities.Product;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@DynamicInsert
@DynamicUpdate
@Entity
@Table
public class LogPrice implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, columnDefinition="Decimal(10,2)")
    private BigDecimal previous_price;

    @Column(nullable = false, columnDefinition="Decimal(10,2)")
    private BigDecimal new_price;

    @CreationTimestamp
    @Column(name = "created_at")
    private Date createdAt;

    @ManyToOne(fetch = FetchType.EAGER,cascade = {CascadeType.MERGE} ,targetEntity = Product.class)
    @JoinColumn(name = "product_id")
    @JsonBackReference
    private Product product;

}
