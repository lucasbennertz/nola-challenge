package com.lucas.arcca.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "item_product_sales")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ItemProductSale {
    @Id
    private Long id;

    @Column(name = "product_sale_id")
    private Long productSaleId;

    @Column(name = "item_id")
    private Long itemId;

    @Column(name = "additional_price")
    private Double additionalPrice;
}
