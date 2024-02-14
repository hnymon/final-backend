package com.web.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QBookCrawling is a Querydsl query type for BookCrawling
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QBookCrawling extends EntityPathBase<BookCrawling> {

    private static final long serialVersionUID = -1975388569L;

    public static final QBookCrawling bookCrawling = new QBookCrawling("bookCrawling");

    public final StringPath bookName = createString("bookName");

    public final StringPath imgUrl = createString("imgUrl");

    public final StringPath isbn10 = createString("isbn10");

    public final StringPath isbn13 = createString("isbn13");

    public final StringPath type = createString("type");

    public final StringPath uniqueCol = createString("uniqueCol");

    public QBookCrawling(String variable) {
        super(BookCrawling.class, forVariable(variable));
    }

    public QBookCrawling(Path<? extends BookCrawling> path) {
        super(path.getType(), path.getMetadata());
    }

    public QBookCrawling(PathMetadata metadata) {
        super(BookCrawling.class, metadata);
    }

}

