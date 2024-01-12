package com.jpa.demo.advancedMapping.embeddedDomain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QParentId is a Querydsl query type for ParentId
 */
@Generated("com.querydsl.codegen.DefaultEmbeddableSerializer")
public class QParentId extends BeanPath<ParentId> {

    private static final long serialVersionUID = 1488564952L;

    public static final QParentId parentId = new QParentId("parentId");

    public final StringPath id1 = createString("id1");

    public final StringPath id2 = createString("id2");

    public QParentId(String variable) {
        super(ParentId.class, forVariable(variable));
    }

    public QParentId(Path<? extends ParentId> path) {
        super(path.getType(), path.getMetadata());
    }

    public QParentId(PathMetadata metadata) {
        super(ParentId.class, metadata);
    }

}

