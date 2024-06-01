package com.example.chapter07.domain.superDomain;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
@Getter
@Setter
@AttributeOverrides({
        @AttributeOverride(name = "id", column = @Column(name = "SELLER_ID")),
        @AttributeOverride(name = "name", column = @Column(name = "SELLER_NAME"))
})
public class Seller extends BaseEntity{

    private String shopName;

}
