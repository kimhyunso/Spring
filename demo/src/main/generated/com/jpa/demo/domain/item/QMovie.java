package com.jpa.demo.domain.item;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QMovie is a Querydsl query type for Movie
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QMovie extends EntityPathBase<Movie> {

    private static final long serialVersionUID = 1364832404L;

    public static final QMovie movie = new QMovie("movie");

    public final QItem _super = new QItem(this);

    public final StringPath actor = createString("actor");

    public final StringPath director = createString("director");

    //inherited
    public final NumberPath<Long> id = _super.id;

    //inherited
    public final ListPath<com.jpa.demo.domain.CategoryItem, com.jpa.demo.domain.QCategoryItem> items = _super.items;

    //inherited
    public final StringPath name = _super.name;

    //inherited
    public final NumberPath<Integer> price = _super.price;

    //inherited
    public final NumberPath<Integer> stockQuantity = _super.stockQuantity;

    public QMovie(String variable) {
        super(Movie.class, forVariable(variable));
    }

    public QMovie(Path<? extends Movie> path) {
        super(path.getType(), path.getMetadata());
    }

    public QMovie(PathMetadata metadata) {
        super(Movie.class, metadata);
    }

}

