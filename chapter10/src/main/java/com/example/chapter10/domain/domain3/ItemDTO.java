package com.example.chapter10.domain.domain3;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ItemDTO {

    private String username;
    private int price;

//
//    public ItemDTO(String username, int price){
//        this.username = username;
//        this.price = price;
//    }


}
