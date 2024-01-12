package com.jpa.demo.advancedMapping.embeddedDomain;

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

    private static final long serialVersionUID = -1419678499L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QParent parent = new QParent("parent");

    public final QParentId id;

    public final StringPath name = createString("name");

    public QParent(String variable) {
        this(Parent.class, forVariable(variable), INITS);
    }

    public QParent(Path<? extends Parent> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QParent(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QParent(PathMetadata metadata, PathInits inits) {
        this(Parent.class, metadata, inits);
    }

    public QParent(Class<? extends Parent> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.id = inits.isInitialized("id") ? new QParentId(forProperty("id")) : null;
    }

}

