package com.jpa.demo.advancedMapping.embeddedIdentDomain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QChildId is a Querydsl query type for ChildId
 */
@Generated("com.querydsl.codegen.DefaultEmbeddableSerializer")
public class QChildId extends BeanPath<ChildId> {

    private static final long serialVersionUID = -1495166202L;

    public static final QChildId childId = new QChildId("childId");

    public final StringPath id = createString("id");

    public final StringPath parentId = createString("parentId");

    public QChildId(String variable) {
        super(ChildId.class, forVariable(variable));
    }

    public QChildId(Path<? extends ChildId> path) {
        super(path.getType(), path.getMetadata());
    }

    public QChildId(PathMetadata metadata) {
        super(ChildId.class, metadata);
    }

}

