package com.stockexchange.orderprocessor.service;

import com.stockexchange.orderprocessor.utils.OrderDto;
import com.stockexchange.orderprocessor.utils.OrdersMetricsResponse;
import com.stockexchange.orderprocessor.domain.Orders;

public interface OrderProcessorService {
    Orders process(OrderDto orderDto);
    OrdersMetricsResponse getOrdersMetrics();
}
