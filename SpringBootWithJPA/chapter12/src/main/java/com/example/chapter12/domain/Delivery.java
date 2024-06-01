package com.example.chapter12.domain;

import com.example.chapter12.domain.enums.DeliveryStatus;
import jakarta.persistence.*;
import lombok.*;
@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Delivery {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "DELIVERY_ID")
    private Long id;

    @Embedded
    private Address address;

    @Enumerated(EnumType.STRING)
    private DeliveryStatus status;

    // 연관관계 주인 x
    // 일대일 관계
    @OneToOne(mappedBy = "delivery")
    private Order order;

    public Delivery(Address address){
        this.address = address;
    }

}
