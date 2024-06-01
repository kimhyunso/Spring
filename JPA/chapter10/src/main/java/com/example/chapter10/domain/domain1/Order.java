package com.example.chapter10.domain.domain1;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Table(name = "ORDERS")
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order {

    @Id
    @GeneratedValue
    @Column(name = "ORDER_ID")
    private Long id;

    private int orderAmount;

    @ManyToOne
    @JoinColumn(name = "MEMBER_ID")
    private Member member;

    @Embedded
    private Address address;

    @ManyToOne
    @JoinColumn(name = "PRODUCT_ID")
    private Product product;

    @Builder(builderMethodName = "builder")
    public Order(int orderAmount, Address address, Product product, Member member){
        this.address = address;
        this.orderAmount = orderAmount;
        setProduct(product, orderAmount);
        this.member = member;
    }


    public void setProduct(Product product, int orderAmount) {
        if (product.getOrders() != null){
            product.getOrders().remove(this);
        }

        this.product = product;
        // this.product.getOrders().add(this);
        this.product.setUpdateStockAmount(orderAmount);
    }
}
