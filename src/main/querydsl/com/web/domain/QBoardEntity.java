package com.web.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QBoardEntity is a Querydsl query type for BoardEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QBoardEntity extends EntityPathBase<BoardEntity> {

    private static final long serialVersionUID = 195742726L;

    public static final QBoardEntity boardEntity = new QBoardEntity("boardEntity");

    public final StringPath admin = createString("admin");

    public final StringPath boardContent = createString("boardContent");

    public final DateTimePath<java.util.Date> boardDate = createDateTime("boardDate", java.util.Date.class);

    public final NumberPath<Long> boardSeq = createNumber("boardSeq", Long.class);

    public final StringPath boardTitle = createString("boardTitle");

    public final NumberPath<Integer> boardViews = createNumber("boardViews", Integer.class);

    public QBoardEntity(String variable) {
        super(BoardEntity.class, forVariable(variable));
    }

    public QBoardEntity(Path<? extends BoardEntity> path) {
        super(path.getType(), path.getMetadata());
    }

    public QBoardEntity(PathMetadata metadata) {
        super(BoardEntity.class, metadata);
    }

}

