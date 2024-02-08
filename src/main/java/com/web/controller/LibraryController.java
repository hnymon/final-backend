package com.web.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.web.domain.SeoulPublicLibrary;
import com.web.repository.LibraryRepository;
@ResponseBody
@Controller
public class LibraryController {
	@Autowired
	private LibraryRepository libraryRepository;

	public Map<String, Object> library() {
		Map<String, Object> result = new HashMap<>();
		try {

		} catch (Exception e) {
			// TODO: handle exception
		}
		return result;
	}

//	@GetMapping("/Library")
//	public ResponseEntity<Map <String, List<SeoulPublicLibrary>>> ParseExample() {
//		Map<String, List<SeoulPublicLibrary>> map = new HashMap<>();
//		List<SeoulPublicLibrary> csvList = new ArrayList<>(); 
//		csvList = libraryRepository.findAll();
//		int a = csvList.size();
//		System.out.println(a);
//		map.put("csvList", csvList);
////		System.out.println(map.get("list").get(0));
//		return ResponseEntity.ok(map);
//	}
	// 되는 코드 map에 담아서 코드로 보내주기
	@GetMapping("/Library")
	public Map <String,Object > ParseExample() {
		Map<String, Object> map = new HashMap<>();
		List<SeoulPublicLibrary> csvList = libraryRepository.findAll();
		map.put("csvList", csvList);
//		System.out.println(map.get("list").get(0));
		return map;
	}
}
