package com.jpa.demo.domain;

import com.jpa.demo.domain.enums.DeliveryStatus;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Delivery {


    @Id @GeneratedValue
    @Column(name = "DELIVERY_ID")
    private Long id;

    @Column(name = "CITY")
    private String city;

    private String zipcode;

    @Enumerated(EnumType.STRING)
    private DeliveryStatus status;

    // 연관관계 주인 x
    // 일대일 관계
    @OneToOne(mappedBy = "delivery")
    private Order order;

}
