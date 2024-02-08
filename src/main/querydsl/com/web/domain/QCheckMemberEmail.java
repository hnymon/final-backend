package com.web.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QCheckMemberEmail is a Querydsl query type for CheckMemberEmail
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QCheckMemberEmail extends EntityPathBase<CheckMemberEmail> {

    private static final long serialVersionUID = 264394621L;

    public static final QCheckMemberEmail checkMemberEmail = new QCheckMemberEmail("checkMemberEmail");

    public final StringPath email = createString("email");

    public final StringPath randomInitial = createString("randomInitial");

    public QCheckMemberEmail(String variable) {
        super(CheckMemberEmail.class, forVariable(variable));
    }

    public QCheckMemberEmail(Path<? extends CheckMemberEmail> path) {
        super(path.getType(), path.getMetadata());
    }

    public QCheckMemberEmail(PathMetadata metadata) {
        super(CheckMemberEmail.class, metadata);
    }

}

