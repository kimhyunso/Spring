package com.jpa.demo.domain;

import com.jpa.demo.domain.enums.OrderStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@Entity
@AllArgsConstructor
@Table(name = "ORDERS")
@NoArgsConstructor
public class Order extends BaseEntity{

    @Id
    @GeneratedValue
    @Column(name = "ORDER_ID")
    private Long id;

    @Temporal(TemporalType.TIMESTAMP)
    private Date orderDate;


    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    // 연관관계 주인
    @ManyToOne
    @JoinColumn(name = "MEMBER_ID")
    private Member member;

    // 연관관계 주인 x
    // OrderItem 일대다 관계
    @OneToMany(mappedBy = "order")
    private List<OrderItem> orderItems = new ArrayList<OrderItem>();

    // 일대일 관계
    // 연관관계 주인
    @OneToOne
    @JoinColumn(name = "DELIVERY_ID")
    private Delivery delivery;


    public void setMember(Member member){
        if (this.member != null){
            this.member.getOrders().remove(this);
        }
        this.member = member;
        member.getOrders().add(this);
    }

    public void addOrderItem(OrderItem orderItem){
        orderItems.add(orderItem);
        orderItem.setOrder(this);
    }

    public void setDelivery(Delivery delivery){
        this.delivery = delivery;
        delivery.setOrder(this);
    }


}
