package com.jpa.demo.proxy.domain5;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QParent is a Querydsl query type for Parent
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QParent extends EntityPathBase<Parent> {

    private static final long serialVersionUID = 152728594L;

    public static final QParent parent = new QParent("parent");

    public final ListPath<Child, QChild> children = this.<Child, QChild>createList("children", Child.class, QChild.class, PathInits.DIRECT2);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public QParent(String variable) {
        super(Parent.class, forVariable(variable));
    }

    public QParent(Path<? extends Parent> path) {
        super(path.getType(), path.getMetadata());
    }

    public QParent(PathMetadata metadata) {
        super(Parent.class, metadata);
    }

}

