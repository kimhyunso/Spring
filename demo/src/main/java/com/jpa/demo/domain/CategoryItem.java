package com.jpa.demo.domain;


import com.jpa.demo.domain.complexKey.CategoryItemId;
import com.jpa.demo.domain.item.Item;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@Entity
@IdClass(CategoryItemId.class)
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CategoryItem {

    @Id
    @ManyToOne
    @JoinColumn(name = "CATEGORY_ID")
    private Category category;

    @Id
    @ManyToOne
    @JoinColumn(name = "ITEM_ID")
    private Item item;

}
