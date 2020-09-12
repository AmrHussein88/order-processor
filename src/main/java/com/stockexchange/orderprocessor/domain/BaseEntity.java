package com.stockexchange.orderprocessor.domain;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data
@MappedSuperclass
public abstract class BaseEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Temporal(TemporalType.DATE)
    private Date createDate;
    @Temporal(TemporalType.DATE)
    private Date updateDate;

    @Version
    private Integer version;

    @PrePersist
    protected void preInsert() {
        this.createDate = new Date();
        this.version = 0;
    }

    @PreUpdate
    protected void preUpdate() {
        this.updateDate = new Date();
    }
}
