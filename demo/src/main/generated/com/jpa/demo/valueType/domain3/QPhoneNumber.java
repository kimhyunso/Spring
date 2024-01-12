package com.jpa.demo.valueType.domain3;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QPhoneNumber is a Querydsl query type for PhoneNumber
 */
@Generated("com.querydsl.codegen.DefaultEmbeddableSerializer")
public class QPhoneNumber extends BeanPath<PhoneNumber> {

    private static final long serialVersionUID = 597121482L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QPhoneNumber phoneNumber = new QPhoneNumber("phoneNumber");

    public final StringPath areaCode = createString("areaCode");

    public final StringPath localNumber = createString("localNumber");

    public final QPhoneServiceProvider provider;

    public QPhoneNumber(String variable) {
        this(PhoneNumber.class, forVariable(variable), INITS);
    }

    public QPhoneNumber(Path<? extends PhoneNumber> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QPhoneNumber(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QPhoneNumber(PathMetadata metadata, PathInits inits) {
        this(PhoneNumber.class, metadata, inits);
    }

    public QPhoneNumber(Class<? extends PhoneNumber> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.provider = inits.isInitialized("provider") ? new QPhoneServiceProvider(forProperty("provider")) : null;
    }

}

