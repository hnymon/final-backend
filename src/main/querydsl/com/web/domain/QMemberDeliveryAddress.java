package com.web.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QMemberDeliveryAddress is a Querydsl query type for MemberDeliveryAddress
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QMemberDeliveryAddress extends EntityPathBase<MemberDeliveryAddress> {

    private static final long serialVersionUID = -380595005L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QMemberDeliveryAddress memberDeliveryAddress = new QMemberDeliveryAddress("memberDeliveryAddress");

    public final StringPath addrDetail = createString("addrDetail");

    public final StringPath address = createString("address");

    public final NumberPath<Long> addrNum = createNumber("addrNum", Long.class);

    public final StringPath deliberyRequest = createString("deliberyRequest");

    public final BooleanPath isDefault = createBoolean("isDefault");

    public final QMember member;

    public final StringPath recipientName = createString("recipientName");

    public final StringPath recipientTel = createString("recipientTel");

    public final StringPath zipcode = createString("zipcode");

    public QMemberDeliveryAddress(String variable) {
        this(MemberDeliveryAddress.class, forVariable(variable), INITS);
    }

    public QMemberDeliveryAddress(Path<? extends MemberDeliveryAddress> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QMemberDeliveryAddress(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QMemberDeliveryAddress(PathMetadata metadata, PathInits inits) {
        this(MemberDeliveryAddress.class, metadata, inits);
    }

    public QMemberDeliveryAddress(Class<? extends MemberDeliveryAddress> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.member = inits.isInitialized("member") ? new QMember(forProperty("member"), inits.get("member")) : null;
    }

}

