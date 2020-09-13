package com.stockexchange.orderprocessor.rest;

import com.stockexchange.orderprocessor.OrderProcessorApplication;
import com.stockexchange.orderprocessor.dto.OrderDto;
import com.stockexchange.orderprocessor.utils.OrdersMetricsResponse;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@TestPropertySource(locations="classpath:application-test.properties")
@SpringBootTest(classes = OrderProcessorApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class OrderProcessorControllerIT {

    @LocalServerPort
    private int port;
    private TestRestTemplate restTemplate = new TestRestTemplate();
    private HttpHeaders headers = new HttpHeaders();
    private String url;
    @Before
    public void setup(){
        url = "http://localhost:" + port + "/orders";
    }

    @Test
    public void processOrder_Test(){
        OrderDto orderDto = new OrderDto();
        orderDto.setSymbol("APPLE");
        orderDto.setQuantity(3);
        HttpEntity<OrderDto> entity = new HttpEntity<>(orderDto, headers);
        ResponseEntity<OrderDto> savedOrder = restTemplate.postForEntity(
                url, entity, OrderDto.class);
        assertNotNull(savedOrder);
        assertEquals(HttpStatus.OK, savedOrder.getStatusCode());
        assertEquals(1, savedOrder.getBody().getId());
        assertEquals("APPLE", savedOrder.getBody().getSymbol());
    }

    @Test
    public void getOrdersMetrics_Test(){
        ResponseEntity<OrdersMetricsResponse> response = restTemplate.getForEntity(
                url + "/metrics", OrdersMetricsResponse.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(2, response.getBody().getTotalProcessedOrder());
    }

}
