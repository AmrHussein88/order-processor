package com.stockexchange.orderprocessor.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.NonNull;

import javax.persistence.Column;
import javax.persistence.Entity;

@Data
@NoArgsConstructor
@Entity
public class Orders extends BaseEntity {
    @Column
    @NonNull
    public String symbol;
    @Column
    @NonNull
    public Integer quantity;
    @Column
    @NonNull
    public Double price;
}
