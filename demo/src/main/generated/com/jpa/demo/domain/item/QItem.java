package com.jpa.demo.domain.item;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QItem is a Querydsl query type for Item
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QItem extends EntityPathBase<Item> {

    private static final long serialVersionUID = 598101295L;

    public static final QItem item = new QItem("item");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final ListPath<com.jpa.demo.domain.CategoryItem, com.jpa.demo.domain.QCategoryItem> items = this.<com.jpa.demo.domain.CategoryItem, com.jpa.demo.domain.QCategoryItem>createList("items", com.jpa.demo.domain.CategoryItem.class, com.jpa.demo.domain.QCategoryItem.class, PathInits.DIRECT2);

    public final StringPath name = createString("name");

    public final NumberPath<Integer> price = createNumber("price", Integer.class);

    public final NumberPath<Integer> stockQuantity = createNumber("stockQuantity", Integer.class);

    public QItem(String variable) {
        super(Item.class, forVariable(variable));
    }

    public QItem(Path<? extends Item> path) {
        super(path.getType(), path.getMetadata());
    }

    public QItem(PathMetadata metadata) {
        super(Item.class, metadata);
    }

}

