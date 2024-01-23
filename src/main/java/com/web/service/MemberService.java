package com.web.service;

import com.web.dto.JoinDTO;

public interface MemberService {
	public String join(JoinDTO joinDTO);
	public String checkId(String username);
	
}
