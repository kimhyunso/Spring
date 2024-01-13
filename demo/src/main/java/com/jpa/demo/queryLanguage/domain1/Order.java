package com.jpa.demo.queryLanguage.domain1;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Table(name = "ORDERS")
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order {

    @Id @GeneratedValue
    @Column(name = "ORDER_ID")
    private Long id;

    private int orderAmount;

    @OneToMany(mappedBy = "order")
    private List<Member> members = new ArrayList<>();

    @Embedded
    private Address address;

    @ManyToOne
    @JoinColumn(name = "PRODUCT_ID")
    private Product product;

    @Builder(builderMethodName = "builder")
    public Order(int orderAmount, Address address, Product product){
        this.address = address;
        this.orderAmount = orderAmount;
        setProduct(product, orderAmount);
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
