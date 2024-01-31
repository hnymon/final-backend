package com.web.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.web.domain.CommentEntity;

import com.web.jwt.JWTUtil;
import com.web.service.CommentService;
import com.web.service.MemberService;

@RestController
@RequestMapping("/comment")
public class CommentController {
	@Autowired
	private CommentService commentService;
	@Autowired
	private MemberService memberService;
	// 토큰을 사용하여 회원정보 불러오기 위해 선언
	private final JWTUtil jwtUtil;

	public CommentController(JWTUtil jwtUtil) {
		this.jwtUtil = jwtUtil;
	}

	@PostMapping("/CommentArea")
	public String CommentArea(@RequestBody CommentEntity commentEntity) {
		System.out.println(commentEntity);
		// 댓글 저장
		commentService.CommentArea(commentEntity);
		return "success";

		// 토큰이 없는 경우 또는 올바른 형식이 아닌 경우 등의 예외 처리
		// 여기서는 일단 댓글을 저장하지 않고 null을 반환하도록 했습니다.

	}
//	전체 리스트
//	@GetMapping("/CommentList")
//	public List<CommentEntity> CommentList() {
//		List<CommentEntity> list = commentService.commnetList();
//		return list;
//	}
//	페이징처리
	@GetMapping("/CommentList")
	public Page<CommentEntity> CommentList(@PageableDefault(size = 10, page = 0) Pageable pageable, @RequestParam String isbn) {
		System.out.println("lhhhhhh");
		Page<CommentEntity> paging = commentService.getComments(pageable ,isbn);
		System.out.println(paging);
		return paging;
	} 
	
	@DeleteMapping("/CommentDelete/{commentId}")
	public String CommentDelete(@PathVariable Long commentId) {
		commentService.CommentDelete(commentId);
		return "success";
	}
	
}
