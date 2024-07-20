package com.linkedIn.linkedIn_batch.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
@ToString
public class Order {
    private Long orderId;
    private String firstName;
    private String lastName;
    private String email;
    private BigDecimal cost;
    private String itemId;
    private String itemName;
    private Date shipDate;
}
