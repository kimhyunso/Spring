package com.jpa.demo.valueType.domain3;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QPhoneServiceProvider is a Querydsl query type for PhoneServiceProvider
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QPhoneServiceProvider extends EntityPathBase<PhoneServiceProvider> {

    private static final long serialVersionUID = 1907355237L;

    public static final QPhoneServiceProvider phoneServiceProvider = new QPhoneServiceProvider("phoneServiceProvider");

    public final StringPath name = createString("name");

    public QPhoneServiceProvider(String variable) {
        super(PhoneServiceProvider.class, forVariable(variable));
    }

    public QPhoneServiceProvider(Path<? extends PhoneServiceProvider> path) {
        super(path.getType(), path.getMetadata());
    }

    public QPhoneServiceProvider(PathMetadata metadata) {
        super(PhoneServiceProvider.class, metadata);
    }

}

