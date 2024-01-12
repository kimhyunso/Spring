package com.jpa.demo.valueType.domain3;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QZipcode is a Querydsl query type for Zipcode
 */
@Generated("com.querydsl.codegen.DefaultEmbeddableSerializer")
public class QZipcode extends BeanPath<Zipcode> {

    private static final long serialVersionUID = -231187071L;

    public static final QZipcode zipcode = new QZipcode("zipcode");

    public final StringPath plusFour = createString("plusFour");

    public final StringPath zip = createString("zip");

    public QZipcode(String variable) {
        super(Zipcode.class, forVariable(variable));
    }

    public QZipcode(Path<? extends Zipcode> path) {
        super(path.getType(), path.getMetadata());
    }

    public QZipcode(PathMetadata metadata) {
        super(Zipcode.class, metadata);
    }

}

