package com.jpa.demo.domain;


import com.jpa.demo.domain.item.Item;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name = "ORDER_ITEM")
@Entity
public class OrderItem {
    @Id
    @GeneratedValue
    @Column(name = "ORDER_ITEM_ID")
    private Long id;


//    @Column(name = "ITEM_ID")
//    private Long itemId;
//
//    @Column(name = "ORDER_ID")
//    private Long orderId;


    private int orderPrice;
    private int count;

    @ManyToOne
    @JoinColumn(name = "ITEM_ID")
    private Item item;

    @ManyToOne
    @JoinColumn(name = "ORDER_ID")
    private Order order;

}
