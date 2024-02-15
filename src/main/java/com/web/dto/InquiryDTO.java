package com.web.dto;

import java.util.Date;

import com.web.domain.Member;

import lombok.Data;

@Data
public class InquiryDTO {
	  private Long inquiryId;
	    private String inquirySubject;
	    private String inquiryContent;
	    private String inquiryType;
	    private Date inquiryDate;
	    private String inquiryStatus;
	    private Member member;
}
