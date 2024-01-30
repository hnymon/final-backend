package com.web.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QBookImg is a Querydsl query type for BookImg
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QBookImg extends EntityPathBase<BookImg> {

    private static final long serialVersionUID = 309737527L;

    public static final QBookImg bookImg = new QBookImg("bookImg");

    public final NumberPath<Long> Id = createNumber("Id", Long.class);

    public final StringPath imgName = createString("imgName");

    public final StringPath path = createString("path");

    public QBookImg(String variable) {
        super(BookImg.class, forVariable(variable));
    }

    public QBookImg(Path<? extends BookImg> path) {
        super(path.getType(), path.getMetadata());
    }

    public QBookImg(PathMetadata metadata) {
        super(BookImg.class, metadata);
    }

}

