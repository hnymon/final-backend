package com.web.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString(exclude = { "Inquery" })
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Entity
@Table(name = "ONETOONEINQUIRY")
@TableGenerator(name = "INQUIRY_SEQ",	// 테이블 이름  
table = "ALL_SEQUENCE", 		// 시퀀스 생성
pkColumnValue = "INQUIRY",	// 시퀀스 이름
initialValue = 0,				// 시퀀스 시작 값
allocationSize = 1)				// 시퀀스 증가 값
public class OneToOneInquiryEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE,generator = "INQUIRY_SEQ")
	@Column(name = "INQUIRY_ID") // 문의 고유 id 값
	private Long inquiryId;
	
	@Column(name = "INQUIRY_SUBJECT") // 문의 제목
	private String inquirySubject;
	
	@Column(name = "INQUIRY_TYPE") // 문의 유형
	private String inquiryType;
	
	@Column(name = "INQUIRY_CONTENT") // 문의 내용
	private String inquiryContent;

	@Column(name = "INQUIRY_DATE", insertable = false , updatable = false, columnDefinition = "date default sysdate") // 문의 등록 날짜
	private Date inquiryDate;
	
	@Column(name = "INQUIRY_STATUS") // 문의 상태
    private String inquiryStatus; 
	@ManyToOne
	@JoinColumn(name = "MEMBER_NUM") // member 외래키 FK
	@JsonIgnore
	private Member member;
	
	@OneToMany(mappedBy = "Inquery", cascade=CascadeType.ALL)
	private List<AdminCommentEntity> Inquery = new ArrayList<>();
}
 