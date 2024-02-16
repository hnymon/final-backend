package com.web.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.web.domain.CheckMemberEmail;
import com.web.domain.Member;
import com.web.domain.MemberDeliveryAddress;
import com.web.domain.Role;
import com.web.dto.JoinDTO;
import com.web.jwt.JWTUtil;
import com.web.service.MemberAddressService;
import com.web.service.MemberService;
import com.web.service.TokenService;

@RestController
public class MemberController {
	// 회원정보 서비스
	@Autowired
	private MemberService memberService;
	@Autowired
	private MemberAddressService addressService;
	@Autowired
	private TokenService tokenService;
	
	
	// 토큰을 사용하여 회원정보 불러오기 위해 선언
	private final JWTUtil jwtUtil;
    public MemberController(JWTUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }
	
	// 아이디 중복확인
	@PostMapping("/checkId")
	public String checkId(@RequestBody JoinDTO joinDTO) {
		String res = memberService.checkId(joinDTO.getUsername());
		return res;
	}
	
	// 이메일 중복확인
	@PostMapping("/checkEmail")
	public String checkEmail(@RequestBody JoinDTO joinDTO) {
		String res = memberService.checkEmail(joinDTO);
		return res;
	}
	
	// 이메일에 인증코드 전송
	@PostMapping("/checkMemberEmail")
	public String checkMemberEmail(@RequestBody JoinDTO joinDTO) {
		String res = memberService.checkMemberEmail(joinDTO);
		return res;
	}
	
	// 전송했던 인증코드 확인
	@PostMapping("/checkCode")
	public String checkCode(@RequestBody JoinDTO joinDTO) {
		String res = memberService.checkCode(joinDTO);
		return res;
	}
	
	// 회원가입
	@PostMapping("/join")
	public String join(@RequestBody JoinDTO joinDTO) {
		String res = memberService.join(joinDTO);
		return res;
	}
	
	// 필요할 때 토큰 넘겨서 username(아이디)로 멤버 정보 불러오기
	@PostMapping("/getIdRole")
    public Map<String, Object> getCurrentMember(@RequestHeader(name = HttpHeaders.AUTHORIZATION, required = false) String token) {
		Map<String, Object> map = new HashMap<>();
        if (token != null && token.startsWith("Bearer ")) {
            String jwtToken = token.substring(7);
            
            // 토큰을 이용해 사용자 정보 추출
            // 권한만 확인
            Role role = jwtUtil.getRole(jwtToken);
            map.put("Role", role);
            
            // 아이디로 멤버 정보 전체 반환
            String username = jwtUtil.getUsername(jwtToken);
            Member member = memberService.getMemberInfo(username);
            if(member != null) {
            	map.put("result", "Success");
            	map.put("member", member);
            } else {
            	map.put("result", "failure");
            }
        }
        return map;
    }
	
	// 토큰으로 정보 불러오기 
	@Transactional
	@PostMapping("/getMemberInfo")
	public Map<String, Object> getMemberInfo(@RequestHeader(name = HttpHeaders.AUTHORIZATION, required = false) String token){
		Map<String, Object> map = new HashMap<>();
		System.out.println(token);
		boolean validTocken = tokenService.existMember(token);
		System.out.println(validTocken);
		if(validTocken) {
			Member currentMember = tokenService.getMemberByMemberNum(token);
			System.out.println(currentMember);
			map.put("result", "Success");
			map.put("currentMember", currentMember);
			System.out.println("성공");
		} else {
			map.put("result", "Failure");
			System.out.println("실패");
		}
		return map;
	}
//	// 토큰으로 정보 불러오기 수정본
//	@Transactional
//	@PostMapping("/getMemberAdrInfo")
//	public Map<String, Object> getMemberAdrInfo(@RequestHeader(name = HttpHeaders.AUTHORIZATION, required = false) String token){
//		Map<String, Object> map = new HashMap<>();
//		boolean validTocken = tokenService.existMember(token);
//		System.out.println(validTocken);
//		if(validTocken) {
//			Member currentMember = tokenService.getMemberByMemberNum(token);
//			System.out.println(currentMember);
//			map.put("result", "Success");
//			JoinDTO dto = new JoinDTO();
//			dto.setMemberNum(currentMember.getMemberNum());
//			dto.setMemberName(currentMember.getMemberName());
//			dto.setEmail(currentMember.getEmail());
//			dto.setPhoneNum(currentMember.getPhoneNum());
//			dto.setSocialType(currentMember.getSocialType());
//			dto.setRole(currentMember.getRole());
//			dto.setUsername(currentMember.getUsername());
//			map.put("currentMember", dto);
//		} else {
//			map.put("result", "Failure");
//		}
//	
//		return map;
//	}
	
	@Transactional
	@PostMapping("/getMemberAdr")
	public Map<String, Object> getMemberAdr(@RequestHeader(name = HttpHeaders.AUTHORIZATION, required = false) String token){
		Map<String, Object> map = new HashMap<>();
		boolean validTocken = tokenService.existMember(token);
		System.out.println(validTocken);
		if(validTocken) {
			Member currentMember = tokenService.getMemberByMemberNum(token);
			map.put("result", "Success");
			List<MemberDeliveryAddress> mda = addressService.findByMemberMemberNum(currentMember.getMemberNum());
			map.put("addrs", mda);
		} else {
			map.put("result", "Failure");
		}
		
		return map;
	}
	
	// 회원정보 수정
	@PostMapping("/editMemberInfo")
	public String editMemberInfo(@RequestBody JoinDTO joinDTO) {
		// 멤버넘버로 찾자
		String res = memberService.editMemberInfo(joinDTO);
		return res;
	}
	
	// Id 찾기
	@PostMapping("/findId")
	public Map<String, Object> findId(@RequestBody JoinDTO joinDTO) {
		System.out.println(joinDTO);
		Map<String, Object> resultMap = memberService.findId(joinDTO);
		return resultMap;
	}
	
	// Pwd 찾기 모달 띄우기 
	@PostMapping("/findPwd")
	public Map<String, Object> findPwd(@RequestBody JoinDTO joinDTO) {
		System.out.println(joinDTO);
		Map<String, Object> resultMap = memberService.findPwd(joinDTO);
		return resultMap;
	}
	// Pwd 재설정 
	@PostMapping("/editPassword")
	public String editPassword(@RequestBody JoinDTO joinDTO) {
		System.out.println(joinDTO);
		String result = memberService.editPwd(joinDTO);
		return result;
	}
}
