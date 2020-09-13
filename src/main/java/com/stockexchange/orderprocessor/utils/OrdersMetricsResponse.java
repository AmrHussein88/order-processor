package com.stockexchange.orderprocessor.utils;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class OrdersMetricsResponse {
    private Long totalProcessedOrder;
    private Double averageExecutionPrice;

    public OrdersMetricsResponse(Long totalProcessedOrder, Double averageExecutionPrice) {
        this.totalProcessedOrder = totalProcessedOrder == null ? 0 : totalProcessedOrder;
        this.averageExecutionPrice = averageExecutionPrice == null ? 0 : averageExecutionPrice;
    }
}
