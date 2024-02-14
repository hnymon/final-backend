package com.web.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.web.domain.AdminCommentEntity;

public interface AdminCommentRepository extends JpaRepository<AdminCommentEntity, Long>{
	List<AdminCommentEntity> findByInquiryId(Long inquiryId);
}
  