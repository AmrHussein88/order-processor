package com.stockexchange.orderprocessor;

import com.stockexchange.orderprocessor.utils.OrderDto;
import com.stockexchange.orderprocessor.utils.OrdersMetricsResponse;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@TestPropertySource(locations="classpath:application-test.properties")
@SpringBootTest(classes = OrderProcessorApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class OrderProcessorControllerIntegrationTest {

    @LocalServerPort
    private int port;
    TestRestTemplate restTemplate = new TestRestTemplate();
    HttpHeaders headers = new HttpHeaders();

    @Test
    public void processOrder_Test(){
        OrderDto orderDto = new OrderDto();
        orderDto.setSymbol("APPLE");
        orderDto.setQuantity(3);
        HttpEntity<OrderDto> entity = new HttpEntity<OrderDto>(orderDto, headers);
        OrderDto savedOrder = restTemplate.postForObject(
                createURLWithPort("/orderProcessor/processOrder"), entity, OrderDto.class);
        assertNotNull(savedOrder);
        assertEquals(savedOrder.getId(), 1);
        assertEquals(savedOrder.getSymbol(), "APPLE");
    }

    @Test
    public void getOrdersMetrics_Test(){

        ResponseEntity<OrdersMetricsResponse> response = restTemplate.getForEntity(
                createURLWithPort("/orderProcessor/ordersMetrics"),OrdersMetricsResponse.class);
        assertEquals(response.getStatusCode(), HttpStatus.OK);
        assertEquals(response.getBody().getTotalProcessedOrder(), 2);
    }

    private String createURLWithPort(String uri) {
        return "http://localhost:" + port + uri;
    }
}
