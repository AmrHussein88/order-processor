package com.stockexchange.orderprocessor.controller;

import com.stockexchange.orderprocessor.domain.Orders;
import com.stockexchange.orderprocessor.service.OrderProcessorService;
import com.stockexchange.orderprocessor.utils.OrderDto;
import com.stockexchange.orderprocessor.utils.OrdersMetricsResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orderProcessor")
public class OrderProcessorController {

    @Autowired
    private OrderProcessorService orderProcessorService;
    @PostMapping("/processOrder")
    public Orders processOrder(@RequestBody OrderDto orderDto){
        return orderProcessorService.process(orderDto);
    }

    @GetMapping("/ordersMetrics")
    @ResponseBody
    public OrdersMetricsResponse getOrdersMetrics(){
        return orderProcessorService.getOrdersMetrics();
    }
}
