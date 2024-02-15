package com.web.service;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.web.domain.AdminCommentEntity;
import com.web.domain.Member;
import com.web.domain.OneToOneInquiryEntity;
import com.web.dto.InquiryDTO;
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
		if(inquiryEntity != null ) {
			inquiryEntity.setInquiryStatus("처리중");
		}
		oneToOneInquiryRepository.save(inquiryEntity);
		return "";
	}
	// USER 전체 리스트
	@Override
	public Page<OneToOneInquiryEntity> InquiryPaging(Pageable pageable, Long memberNum) {
		// TODO Auto-generated method stub
		Page<OneToOneInquiryEntity> paging = oneToOneInquiryRepository.findAllByMemberMemberNum(pageable, memberNum);
		return paging;
	}
	// 이건 몰라 시발
	@Override
	public List<OneToOneInquiryEntity> getList(Long emberNum) {
		// TODO Auto-generated method stub
		return null;
	}

	// 처리중인 리스트
	@Override
	public Page<OneToOneInquiryEntity> UnInquiryList(Pageable pageable, Long memberNum) {
		// TODO Auto-generated method stub
		Page<OneToOneInquiryEntity> paging=oneToOneInquiryRepository.findAllByMemberMemberNumAndInquiryStatus(pageable,memberNum,"처리중");
		return paging;
	}
	
	// 완료 처리 리스트
	@Override
	public Page<OneToOneInquiryEntity> OkInquiryList(Pageable pageable, Long memberNum) {
		// TODO Auto-generated method stub
		Page<OneToOneInquiryEntity> paging =oneToOneInquiryRepository.findAllByMemberMemberNumAndInquiryStatus(pageable,memberNum,"답변완료");
		return paging;
	}
	// ADMIN 전체 리스트
	@Override
	public Page<InquiryDTO> InquiryAllList(Pageable pageable) {
		// TODO Auto-generated method stub
		Page<OneToOneInquiryEntity> paging=oneToOneInquiryRepository.findAll(pageable);
		 return paging.map(entity -> {
	            InquiryDTO dto = new InquiryDTO();
	            dto.setInquiryId(entity.getInquiryId());
	            dto.setInquirySubject(entity.getInquirySubject());
	            dto.setInquiryType(entity.getInquiryType());
	            dto.setInquiryContent(entity.getInquiryContent());
	            dto.setInquiryDate(entity.getInquiryDate());
	            dto.setInquiryStatus(entity.getInquiryStatus());
	            dto.setMember(entity.getMember());
	            // 회원 정보는 제외 
	            return dto;
	        });
//		Page<OneToOneInquiryEntity> paging=oneToOneInquiryRepository.findAllByMemberMemberNum(pageable);
	}
	//
//	@Override
//	public List<InquiryDTO> InquiryAllList(Pageable pageable) {
//		// TODO Auto-generated method stub
//		OneToOneInquiryEntity inquiryEntity =(OneToOneInquiryEntity) oneToOneInquiryRepository.findAll();
//		InquiryDTO inquiryDTO = new InquiryDTO();
//		
//		inquiryDTO.setInquiryId(inquiryEntity.getInquiryId());
//		inquiryDTO.setInquirySubject(inquiryEntity.getInquirySubject());
//		inquiryDTO.setInquiryContent(inquiryEntity.getInquiryContent());
//		inquiryDTO.setInquiryDate(inquiryEntity.getInquiryDate());
//		inquiryDTO.setMember(inquiryEntity.getMember());
//		inquiryDTO.setInquiryStatus(inquiryEntity.getInquiryStatus());
//		inquiryDTO.setInquiryType(inquiryEntity.getInquiryType());
//		List<InquiryDTO> list = new ArrayList<>();
//		list.add(inquiryDTO);
////		Page<InquiryDTO> paging=oneToOneInquiryRepository.findAll(pageable);
////		Page<OneToOneInquiryEntity> paging=oneToOneInquiryRepository.findAllByMemberMemberNum(pageable);
//		return list;
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
	public AdminCommentEntity AdminCommnet(Long inquiryId, AdminCommentEntity commentEntity) {
	    // 해당 문의 조회
	    OneToOneInquiryEntity inquiry = oneToOneInquiryRepository.findById(inquiryId)
	            .orElseThrow(() -> new EntityNotFoundException("Entity with id " + inquiryId + " not found"));

	    // 답변 작성 시 해당 문의 상태를 업데이트
	    inquiry.setInquiryStatus("답변완료");

	    // 관련 문의 엔터티 저장
	    OneToOneInquiryEntity updatedInquiry = oneToOneInquiryRepository.save(inquiry);

	    // 답변 정보에 관련 문의 ID 설정
	    commentEntity.setInquiryId(inquiryId);
	    // 답변 정보 저장
	    AdminCommentEntity savedCommentEntity = adminCommentRepository.save(commentEntity);

	    return savedCommentEntity; // 저장된 답변 정보 반환
	}


	@Override
	public List<AdminCommentEntity> AdminCommnetList(Long inquiryId) {
		// TODO Auto-generated method stub
		List<AdminCommentEntity> list  = adminCommentRepository.findByInquiryId(inquiryId);
		return list;
	}

	
}
