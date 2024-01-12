package com.jpa.demo.advancedMapping.embeddedIdentDomain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QGrandChildId is a Querydsl query type for GrandChildId
 */
@Generated("com.querydsl.codegen.DefaultEmbeddableSerializer")
public class QGrandChildId extends BeanPath<GrandChildId> {

    private static final long serialVersionUID = -1514503588L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QGrandChildId grandChildId = new QGrandChildId("grandChildId");

    public final QChildId childId;

    public final StringPath id = createString("id");

    public QGrandChildId(String variable) {
        this(GrandChildId.class, forVariable(variable), INITS);
    }

    public QGrandChildId(Path<? extends GrandChildId> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QGrandChildId(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QGrandChildId(PathMetadata metadata, PathInits inits) {
        this(GrandChildId.class, metadata, inits);
    }

    public QGrandChildId(Class<? extends GrandChildId> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.childId = inits.isInitialized("childId") ? new QChildId(forProperty("childId")) : null;
    }

}

