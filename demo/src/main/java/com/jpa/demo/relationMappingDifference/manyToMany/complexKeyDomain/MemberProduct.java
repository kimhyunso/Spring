package com.jpa.demo.relationMappingDifference.manyToMany.complexKeyDomain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@IdClass(MemberProductId.class)
@Getter
@Setter
@Table(name = "MEMBER_PRODUCT")
public class MemberProduct {

    @Id
    @ManyToOne
    @JoinColumn(name = "MEMBER_ID")
    private Member member;

    @Id
    @ManyToOne
    @JoinColumn(name = "PRODUCT_ID")
    private Product product;

    private int orderAmount;
}
