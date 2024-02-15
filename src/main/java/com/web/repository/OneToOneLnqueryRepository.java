package com.web.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.web.domain.OneToOneInquiryEntity;

public interface OneToOneLnqueryRepository extends JpaRepository<OneToOneInquiryEntity, Long> {
	
//	Page<OneToOneInquiryEntity> findByMemberMemberNum(Pageable pageable, Long memberNum);
//	List<OneToOneInquiryEntity> findAllByMemberMemberNum(Long memberNum);
	Page<OneToOneInquiryEntity> findAllByMemberMemberNum(Pageable pageable,Long memberNum);
	
	OneToOneInquiryEntity findByInquiryStatus(Long InquiryId);
	
	Page<OneToOneInquiryEntity> findAllByMemberMemberNumAndInquiryStatus(Pageable pageable,Long memberNum, String InquiryStatus);
}
