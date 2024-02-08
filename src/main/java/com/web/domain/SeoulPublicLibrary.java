package com.web.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Builder // builder 사용시 entity선언해줘야한다.
@NoArgsConstructor(access=AccessLevel.PROTECTED)
@AllArgsConstructor	
@Data
@Entity
@ToString
@Table(name = "SEOULPUBLICLIBRARY")
@TableGenerator(name = "SEOULPUBLICLIBRARY_SEQ_TABLE",
initialValue = 0,
allocationSize = 1 ,
pkColumnValue = "SEOULPUBLICLIBRARY_SEQ")
public class SeoulPublicLibrary {
//	========================= 서울시 공공도서관 현황 =====================
//	@Id
//	@Column(name = "LBRRY_SEQ_NO")
//	private String lbrrySeqNo; //도서 일련번호
//	@Column(name = "LBRRY_NAME")
//	private String lbbryName; // 도서관명
//	@Column(name = "GU_CODE")
//	private String guCode; // 구 코드
//	@Column(name = "CODE_VALUE")
//	private String codeValue; // 구명
//	@Column(name = "ADRES")
//	private String adres; // 주소
//	@Column(name = "TEL_NO")
//	private String telNo; // 전화번호
//	@Column(name = "HMPG_URL") 
//	private String himpgUrl; // 홈페이지 url
//	@Column(name = "OP_TIME")
//	private String opTime; // 운영시간
//	@Column(name = "FDRM_CLOSE_DATE")
//	private String fdrmCloseDate; // 정기휴일
//	@Column(name = "LBRRY_SE_NAME") 
//	private String lbrrySeName; // 도서관 구분명
//	@Column(name = "XCNTS")
//	private String xcnts; // 위도
//	@Column(name = "YDNTS")
//	private String ydnts; // 경도
//	========================= 서울시 공공도서관 현황 =====================
//	------------------------- 전국 도서관표준 데이터 ---------------------

//	------------------------- 전국 도서관표준 데이터 ---------------------
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE,generator = "SEOULPUBLICLIBRARY_SEQ")
	@Column(name = "LI_NUM_PK")
	private Long liNum;
	@Column(name = "RDNMADR")
	private String rdnmadr; // 주소 
	@Column(name = "LBRRY_NM")
	private String lbrryNm; // 도서관명
	@Column(name = "CTPRVN_NM")
	private String ctprvnNm; // 시도명
	@Column(name = "SIGNGU_NM")
	private String signguNm; // 시군구명
	@Column(name = "LBRRY_SE")
	private String lbrrySe; // 도서관유형
	@Column(name = "CLOSE_DAY")
	private String closeDay; // 휴관일
	@Column(name = "WEEKDAY_OPER_OPEN_HHMM")
	private String weekdayOperOpenHhmm; // 평일운영시작시각
	@Column(name = "WEEKDAY_OPER_COLSE_HHMM")
	private String weekdayOperCloseHhmm; // 평일운영종료시각
	@Column(name = "SAT_OPER_OPER_OPEN_HHMM")
	private String satOperOperOpenHhmm; // 토요일운영시작시각
	@Column(name = "SAT_OPER_CLOSE_HHMM")
	private String satOperCloseHhmm; // 토요일 운영종료시각
	@Column(name = "HOLIDAY_OPER_OPEN_HHMM")
	private String holidayOperOpenHhmm; // 공휴일운영시작시각
	@Column(name = "HOLIDAY_CLOSE_OPEN_HHMM")
	private String holidayCloseOpenHhmm; // 공휴일운영종료시각
	@Column(name = "SEAT_CO")
	private String seatCo; // 열람좌석수
	@Column(name = "BOOK_CO")
	private String bookCo; // 자료수(도서)
	@Column(name = "PBLICTN_CO")
	private String pblicCo; // 자료수(연속간행물)
	@Column(name = "NONE_BOOK_CO")
	private String noneBookCo; // 자료수(비도서)
	@Column(name = "LON_CO")
	private String lonCo; // 대출가능권수
	@Column(name = "LON_DAYCNT")
	private String lonDayCnt; // 대출가능일수
	// 소재지ㅏㅣ도로명주소
	@Column(name = "OPER_INSTITUTION_NM")
	private String operInstitutionNm; // 운영기관명
	@Column(name = "PHONE_NUMBER")
	private String phoneNumber; // 도서관전화번호
	@Column(name = "PLOT_AR")
	private String plotAr; // 부지면적
	@Column(name = "BULD_AR")
	private String buldAr; // 건물면적
	@Column(name = "HOMEPAGE_URL")
	private String homepageUrl; // 홈페이지주소
	@Column(name = "LATITUDE")
	private String latitude; // 위도
	@Column(name = "LONGITUDE")
	private String longitude;// 경도
	@Column(name = "REFERENCE_DATE")
	private String referenceDate;// 데이터기준일자
	@Column(name = "instt_code")
	private String insttCode;// 제공기관코드
}





















