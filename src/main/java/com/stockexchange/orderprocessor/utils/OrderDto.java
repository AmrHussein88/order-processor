package com.stockexchange.orderprocessor.utils;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

import java.io.Serializable;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class OrderDto implements Serializable {
    @JsonProperty("id")
    public Integer id;
    @JsonProperty("symbol")
    public String symbol;
    @JsonProperty("quantity")
    public int quantity;
    @JsonProperty("price")
    public Double price;

}
