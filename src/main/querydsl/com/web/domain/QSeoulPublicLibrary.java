package com.web.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QSeoulPublicLibrary is a Querydsl query type for SeoulPublicLibrary
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QSeoulPublicLibrary extends EntityPathBase<SeoulPublicLibrary> {

    private static final long serialVersionUID = -1799261567L;

    public static final QSeoulPublicLibrary seoulPublicLibrary = new QSeoulPublicLibrary("seoulPublicLibrary");

    public final StringPath bookCo = createString("bookCo");

    public final StringPath buldAr = createString("buldAr");

    public final StringPath closeDay = createString("closeDay");

    public final StringPath ctprvnNm = createString("ctprvnNm");

    public final StringPath holidayCloseOpenHhmm = createString("holidayCloseOpenHhmm");

    public final StringPath holidayOperOpenHhmm = createString("holidayOperOpenHhmm");

    public final StringPath homepageUrl = createString("homepageUrl");

    public final StringPath insttCode = createString("insttCode");

    public final StringPath latitude = createString("latitude");

    public final StringPath lbrryNm = createString("lbrryNm");

    public final StringPath lbrrySe = createString("lbrrySe");

    public final NumberPath<Long> liNum = createNumber("liNum", Long.class);

    public final StringPath lonCo = createString("lonCo");

    public final StringPath lonDayCnt = createString("lonDayCnt");

    public final StringPath longitude = createString("longitude");

    public final StringPath noneBookCo = createString("noneBookCo");

    public final StringPath operInstitutionNm = createString("operInstitutionNm");

    public final StringPath pblicCo = createString("pblicCo");

    public final StringPath phoneNumber = createString("phoneNumber");

    public final StringPath plotAr = createString("plotAr");

    public final StringPath rdnmadr = createString("rdnmadr");

    public final StringPath referenceDate = createString("referenceDate");

    public final StringPath satOperCloseHhmm = createString("satOperCloseHhmm");

    public final StringPath satOperOperOpenHhmm = createString("satOperOperOpenHhmm");

    public final StringPath seatCo = createString("seatCo");

    public final StringPath signguNm = createString("signguNm");

    public final StringPath weekdayOperCloseHhmm = createString("weekdayOperCloseHhmm");

    public final StringPath weekdayOperOpenHhmm = createString("weekdayOperOpenHhmm");

    public QSeoulPublicLibrary(String variable) {
        super(SeoulPublicLibrary.class, forVariable(variable));
    }

    public QSeoulPublicLibrary(Path<? extends SeoulPublicLibrary> path) {
        super(path.getType(), path.getMetadata());
    }

    public QSeoulPublicLibrary(PathMetadata metadata) {
        super(SeoulPublicLibrary.class, metadata);
    }

}

