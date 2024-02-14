package com.web.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.web.domain.AdminCommentEntity;
import com.web.domain.Member;
import com.web.domain.OneToOneInquiryEntity;
import com.web.repository.AdminCommentRepository;
import com.web.repository.OneToOneLnqueryRepository;

@Service
public class OneToOneLnqueryServiceImpl implements OneToOneLnqueryService {
	@Autowired
	private OneToOneLnqueryRepository oneToOneInquiryRepository;
	// 문의 답변
	@Autowired
	private AdminCommentRepository adminCommentRepository;

	@Override
	public String InquiryArea(OneToOneInquiryEntity inquiryEntity, Member currentMember) {
		// TODO Auto-generated method stub
		inquiryEntity.setMember(currentMember);
		System.out.println(inquiryEntity);
		oneToOneInquiryRepository.save(inquiryEntity);
		return "";
	}

//	@Override
//	public List<OneToOneInquiryEntity> getList(Long emberNum) {
//		// TODO Auto-generated method stub
//		try {
//			List<OneToOneInquiryEntity> list = oneToOneInquiryRepository.findAllByMemberMemberNum(emberNum);
//			System.out.println(list);
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		return null;
//	}

	@Override
	public Page<OneToOneInquiryEntity> InquiryPaging(Pageable pageable, Long memberNum) {
		// TODO Auto-generated method stub
		Page<OneToOneInquiryEntity> paging = oneToOneInquiryRepository.findAllByMemberMemberNum(pageable, memberNum);
		return paging;
	}

	@Override
	public List<OneToOneInquiryEntity> getList(Long emberNum) {
		// TODO Auto-generated method stub
		return null;
	}

	
	// @Override
//	public void InquiryPaging(Pageable pageable,Long memberNum) {
//		// TODO Auto-generated method stub
//		try {
//			System.out.println(memberNum);
//			System.out.println(oneToOneInquiryRepository.findByMemberMemberNum(pageable, memberNum));
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//		}
//	}
	// 1대1 상세정보
	@Override
	public OneToOneInquiryEntity InquiryDetail(Long inquiryId) {
		// TODO Auto-generated method stub
		Optional<OneToOneInquiryEntity> optional = oneToOneInquiryRepository.findById(inquiryId);
		System.out.println("optional" + optional);
		return optional.get();
	}
	// 1대1 문의 답변
	@Override
	public AdminCommentEntity AdminCommnet(Long inquiryId,AdminCommentEntity commentEntity) {
		// TODO Auto-generated method stub
		commentEntity.setInquiryId(inquiryId);
		adminCommentRepository.save(commentEntity);
		return null;
	}

	@Override
	public List<AdminCommentEntity> AdminCommnetList(Long inquiryId) {
		// TODO Auto-generated method stub
		List<AdminCommentEntity> list  =adminCommentRepository.findByInquiryId(inquiryId);
		return list;
	}

	
}
