package com.web.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QMember is a Querydsl query type for Member
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QMember extends EntityPathBase<Member> {

    private static final long serialVersionUID = -1624053027L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QMember member = new QMember("member1");

    public final QBaseEntity _super = new QBaseEntity(this);

    public final ListPath<MemberDeliveryAddress, QMemberDeliveryAddress> addr = this.<MemberDeliveryAddress, QMemberDeliveryAddress>createList("addr", MemberDeliveryAddress.class, QMemberDeliveryAddress.class, PathInits.DIRECT2);

    public final QCart cart;

    //inherited
    public final DateTimePath<java.util.Date> createDate = _super.createDate;

    public final StringPath email = createString("email");

    public final StringPath memberName = createString("memberName");

    public final NumberPath<Long> memberNum = createNumber("memberNum", Long.class);

    public final StringPath membership = createString("membership");

    //inherited
    public final DateTimePath<java.util.Date> modifyDate = _super.modifyDate;

    public final StringPath password = createString("password");

    public final StringPath phoneNum = createString("phoneNum");

    public final StringPath refreshToken = createString("refreshToken");

    public final EnumPath<Role> role = createEnum("role", Role.class);

    public final StringPath socialId = createString("socialId");

    public final StringPath socialNum = createString("socialNum");

    public final EnumPath<SocialType> socialType = createEnum("socialType", SocialType.class);

    public final StringPath username = createString("username");

    public QMember(String variable) {
        this(Member.class, forVariable(variable), INITS);
    }

    public QMember(Path<? extends Member> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QMember(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QMember(PathMetadata metadata, PathInits inits) {
        this(Member.class, metadata, inits);
    }

    public QMember(Class<? extends Member> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.cart = inits.isInitialized("cart") ? new QCart(forProperty("cart"), inits.get("cart")) : null;
    }

}

