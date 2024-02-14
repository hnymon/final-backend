package com.web.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.web.domain.AdminCommentEntity;
import com.web.domain.Member;
import com.web.domain.OneToOneInquiryEntity;

public interface OneToOneLnqueryService {
	// 1대1 문의 작성
	public String InquiryArea(OneToOneInquiryEntity inquiryEntity, Member currentMember);
	// 1대1 페이징 처리 리스트
	public Page<OneToOneInquiryEntity> InquiryPaging (Pageable pageable, Long memberNum);
//	public void InquiryPaging (Pageable pageable, Long memberNum);
	List<OneToOneInquiryEntity> getList(Long emberNum);
	//
	public OneToOneInquiryEntity InquiryDetail(Long inquiryId);
	
	// 1대 1 문의 답변
	public AdminCommentEntity AdminCommnet(Long inquiryId,AdminCommentEntity commentEntity);
	
	// 1대 1 문의 답변 출력
	public List<AdminCommentEntity> AdminCommnetList(Long inquiryId);
}
