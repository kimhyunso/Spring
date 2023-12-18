package com.jpa.demo.domain;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Category {

    @Id @GeneratedValue
    @Column(name = "CATEGORY_ID")
    private Long id;

    private String name;

//    @ManyToMany
//    @JoinTable(name = "CATEGORY_ITEM",
//            joinColumns = @JoinColumn(name = "CATEGORY_ID"),
//            inverseJoinColumns = @JoinColumn(name = "ITEM_ID"))
//    private List<Item> items = new ArrayList<>();

    @OneToMany(mappedBy = "category")
    private List<CategoryItem> categories = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "PARENT_ID")
    private Category parent;

    // 연관관계 주인 x
    @OneToMany(mappedBy = "parent")
    private List<Category> child = new ArrayList<>();

    public void addChildCategory(Category child){
        this.child.add(child);
        child.setParent(this);
    }

//    public void addItem(Item item){
//        categories.add(item);
//    }


}
