package com.web.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.web.domain.CommentEntity;
import com.web.domain.Member;
import com.web.dto.CommentDTO;
import com.web.jwt.JWTUtil;
import com.web.repository.CommentRepository;
import com.web.service.CommentService;
import com.web.service.MemberService;
import com.web.service.TokenService;

@RestController
@RequestMapping("/comment")
public class CommentController {
	@Autowired
	private CommentService commentService;
	@Autowired
	private MemberService memberService;
	@Autowired
	private TokenService tockenService;
	@Autowired
	private CommentRepository commentRepository;
	// 토큰을 사용하여 회원정보 불러오기 위해 선언
	private final JWTUtil jwtUtil;

	public CommentController(JWTUtil jwtUtil) {
		this.jwtUtil = jwtUtil;
	}
 
	@PostMapping("/CommentArea")
	public Map<String, Object> CommentArea(@RequestHeader(name = HttpHeaders.AUTHORIZATION, required = false) String token, 
							  @RequestBody CommentEntity comment) {
		Map<String, Object> map = new HashMap<>();
		Member currentMember = tockenService.getMemberByMemberNum(token);
		System.out.println(currentMember);
		comment.setMember(currentMember);
		comment.setMemberName(currentMember.getMemberName());
		String res = commentService.CommentArea(comment);
		// 댓글 저장
//		commentService.CommentArea(comment);
		map.put("result", res);
		return map;

		// 토큰이 없는 경우 또는 올바른 형식이 아닌 경우 등의 예외 처리
		// 여기서는 일단 댓글을 저장하지 않고 null을 반환하도록 했습니다.
	}
	
	@PostMapping("/CommentList")
	public Map<String,Object> CommentList(@RequestBody CommentDTO commentDTO,@RequestHeader(name = HttpHeaders.AUTHORIZATION, required = false) String token) {
		Member currentMember = tockenService.getMemberByMemberNum(token);
		List<CommentDTO> list = commentService.getComments(commentDTO.getIsbn());
		Map<String,Object> map = new HashMap<>();
	    map.put("list", list);
	    map.put("member", currentMember.getMemberNum());
		return map;
	} 
	@PostMapping("/CommentList2")
	public Map<String,Object> CommentList2(@RequestBody CommentDTO commentDTO) {
		System.out.println("오는중??");
		List<CommentDTO> list = commentService.getComments(commentDTO.getIsbn());
		Map<String,Object> map = new HashMap<>();
		map.put("list", list);
		return map;
	} 
	 
	@DeleteMapping("/CommentDelete/{commentId}")
	public String CommentDelete(@PathVariable Long commentId) {
		commentService.CommentDelete(commentId);
		return "success";
	}
	@PostMapping("/CommentUpdate/{commentId}")
	public String CommentUpdate(@PathVariable Long commentId, @RequestBody CommentEntity editedCommentContent) {
		System.out.println(".///////////////////////////////"+editedCommentContent);
		commentService.CommentUpdate(commentId,editedCommentContent);
		return "success";
	}
	
}
