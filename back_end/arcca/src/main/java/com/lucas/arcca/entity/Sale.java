package com.lucas.arcca.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "sales")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Sale {
    @Id
    private Long id;

    @Column(name = "store_id")
    private Long storeId;

    @Column(name = "channel_id")
    private Long channelId;

    @Column(name = "customer_id")
    private Long customerId;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "sale_status_desc")
    private String status;

    @Column(name = "total_amount")
    private Double totalAmount;

    @OneToMany(mappedBy = "sale", fetch = FetchType.LAZY)
    private List<ProductSale> products;
}
