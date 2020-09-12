package com.stockexchange.orderprocessor.service;

import com.stockexchange.orderprocessor.utils.OrderDto;
import com.stockexchange.orderprocessor.utils.OrdersMetricsResponse;
import com.stockexchange.orderprocessor.domain.Orders;
import com.stockexchange.orderprocessor.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class OrderProcessorServiceImpl implements OrderProcessorService {

    @Autowired
    private OrderRepository orderRepository;

    @Override
    public Orders process(OrderDto orderDto) {
        Random random = new Random();
        double range = random.nextDouble() * (2d -1.1d) + 1.1d;
        double orderExecutionPrice = orderDto.getQuantity() * range;
        orderDto.setPrice(orderExecutionPrice);
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
