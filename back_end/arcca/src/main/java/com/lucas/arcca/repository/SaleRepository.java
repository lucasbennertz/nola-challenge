package com.lucas.arcca.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.lucas.arcca.entity.Sale;

import java.util.List;

@Repository
public interface SaleRepository extends JpaRepository<Sale, Long> {

    // simples
    @Query("SELECT s FROM Sale s WHERE s.saleStatusDesc = 'COMPLETED' AND DATE(s.createdAt) = :date")
    List<Sale> findCompletedSalesByDate(java.sql.Date date);

    // agregação diária por store/channel via materialized view (exemplo)
    @Query(value = "SELECT date, store_id, channel_id, orders, total_revenue, avg_ticket FROM mv_daily_sales_store_channel WHERE date = :date", nativeQuery = true)
    List<Object[]> findDailySalesFromMV(java.sql.Date date);

    // top products (native)
    @Query(value = "SELECT p.name, t.total_sold FROM mv_top_products_day_channel t JOIN products p ON p.id = t.product_id WHERE t.date = :date AND t.channel_id = :channelId ORDER BY t.total_sold DESC LIMIT :limit", nativeQuery = true)
    List<Object[]> topProductsByChannel(java.sql.Date date, Long channelId, int limit);

    // customers not returning in X days
    @Query(value = "SELECT c.id, c.customer_name, MAX(s.created_at) as last_purchase FROM customers c JOIN sales s ON s.customer_id = c.id WHERE s.sale_status_desc='COMPLETED' GROUP BY c.id, c.customer_name HAVING MAX(s.created_at) < :cutoff", nativeQuery = true)
    List<Object[]> customersNotReturning(java.sql.Timestamp cutoff, Pageable pageable);
}
