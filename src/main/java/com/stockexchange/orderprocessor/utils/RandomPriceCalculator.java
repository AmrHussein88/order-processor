package com.stockexchange.orderprocessor.utils;

import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class RandomPriceCalculator implements PriceCalculator {
    @Override
    public Double calculatePrice(Integer quantity) {
        Random random = new Random();
        double range = random.nextDouble() * (2d - 1.1d) + 1.1d;
        return quantity * range;
    }
}
