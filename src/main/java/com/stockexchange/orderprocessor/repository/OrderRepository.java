package com.stockexchange.orderprocessor.repository;

import com.stockexchange.orderprocessor.utils.OrdersMetricsResponse;
import com.stockexchange.orderprocessor.domain.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Orders, Long> {
    @Query("SELECT new com.stockexchange.orderprocessor.utils.OrdersMetricsResponse(COUNT(o) , avg(o.price)) FROM Orders o")
    OrdersMetricsResponse findTotalOrdersAndAvgPrice();

}
