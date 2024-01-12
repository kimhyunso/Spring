package com.jpa.demo.advancedMapping.superDomain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QSeller is a Querydsl query type for Seller
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QSeller extends EntityPathBase<Seller> {

    private static final long serialVersionUID = -780266725L;

    public static final QSeller seller = new QSeller("seller");

    public final QBaseEntity _super = new QBaseEntity(this);

    //inherited
    public final NumberPath<Long> id = _super.id;

    //inherited
    public final StringPath name = _super.name;

    public final StringPath shopName = createString("shopName");

    public QSeller(String variable) {
        super(Seller.class, forVariable(variable));
    }

    public QSeller(Path<? extends Seller> path) {
        super(path.getType(), path.getMetadata());
    }

    public QSeller(PathMetadata metadata) {
        super(Seller.class, metadata);
    }

}

