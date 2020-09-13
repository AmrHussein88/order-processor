package com.stockexchange.orderprocessor.service;

import com.stockexchange.orderprocessor.dto.OrderDto;
import com.stockexchange.orderprocessor.utils.OrdersMetricsResponse;
import com.stockexchange.orderprocessor.domain.Orders;
import com.stockexchange.orderprocessor.repository.OrderRepository;
import com.stockexchange.orderprocessor.utils.PriceCalculator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderProcessorServiceImpl implements OrderProcessorService {

    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private PriceCalculator priceCalculator;

    @Override
    public Orders process(OrderDto orderDto) {
        orderDto.setPrice(priceCalculator.calculatePrice(orderDto.getQuantity()));
        Orders order = createOrderEntity(orderDto);
        return orderRepository.save(order);
    }

    @Override
    public OrdersMetricsResponse getOrdersMetrics() {
        return orderRepository.findTotalOrdersAndAvgPrice();
    }

    private Orders createOrderEntity(OrderDto orderDto) {
        Orders order  = new Orders();
        order.setPrice(orderDto.getPrice());
        order.setQuantity(orderDto.getQuantity());
        order.setSymbol(orderDto.getSymbol());
        return order;
    }
}
