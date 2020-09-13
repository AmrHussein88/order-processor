package com.stockexchange.orderprocessor.controller;

import com.stockexchange.orderprocessor.domain.Orders;
import com.stockexchange.orderprocessor.service.OrderProcessorService;
import com.stockexchange.orderprocessor.dto.OrderDto;
import com.stockexchange.orderprocessor.utils.OrdersMetricsResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orders")
public class OrderProcessorController {

    @Autowired
    private OrderProcessorService orderProcessorService;
    @PostMapping
    public Orders processOrder(@RequestBody OrderDto orderDto){
        return orderProcessorService.process(orderDto);
    }

    @GetMapping("/metrics")
    @ResponseBody
    public OrdersMetricsResponse getOrdersMetrics(){
        return orderProcessorService.getOrdersMetrics();
    }
}
