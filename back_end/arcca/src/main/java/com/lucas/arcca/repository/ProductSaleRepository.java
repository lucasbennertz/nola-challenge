package com.lucas.arcca.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.lucas.arcca.entity.ProductSale;

import java.sql.Date;
import java.util.List;

@Repository
public interface ProductSaleRepository extends JpaRepository<ProductSale, Long> {

    @Query(value = "SELECT ps.product_id, SUM(ps.quantity) as total_sold, SUM(ps.total_price) as revenue FROM product_sales ps JOIN sales s ON s.id = ps.sale_id WHERE s.sale_status_desc = 'COMPLETED' AND DATE(s.created_at) BETWEEN :from AND :to GROUP BY ps.product_id ORDER BY total_sold DESC LIMIT :limit", nativeQuery = true)
    List<Object[]> topProductsBetween(Date from, Date to, int limit);
}
