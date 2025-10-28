package com.lucas.arcca.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "product_sales")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductSale {
    @Id
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sale_id")
    private Sale sale;

    @Column(name = "product_id")
    private Long productId;

    @Column(name = "quantity")
    private Integer quantity;

    @Column(name = "total_price")
    private Double totalPrice;
}
