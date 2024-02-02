package com.web.controller;

import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.web.domain.BookCrawling;
import com.web.dto.SendDataDTO;
import com.web.repository.BookCrawlingRepository;

@Controller
@ResponseBody
public class CrawlingController {
	@Autowired
	private BookCrawlingRepository bookCrawlingRepository;
	
	@GetMapping("/testCrawling")
	public Map<String, Object> testCrawling() {
		System.out.println("testCrawling");
		Map<String, Object> responseMap = new HashMap<>();
		// 신간도서		: newBookList
		List<BookCrawling> newBookList = bookCrawlingRepository.findAllByType("newBookAladin");
		List<BookCrawling> todayBookKyoboList = bookCrawlingRepository.findAllByType("todayBookKyobo");
		List<BookCrawling> nowThisBookKyoboList = bookCrawlingRepository.findAllByType("nowThisBookKyobo");
		List<BookCrawling> popularBookKyoboList = bookCrawlingRepository.findAllByType("popularBookKyobo");
		responseMap.put("newBookList", newBookList); 
		responseMap.put("todayBookKyoboList", todayBookKyoboList); 
		responseMap.put("nowThisBookKyoboList", nowThisBookKyoboList); 
		responseMap.put("popularBookKyoboList", popularBookKyoboList); 
		return responseMap;
	}
	private final String key = "05377ae946110607a0d89dae94e81960";
	// target=${selectedOption}	: 검색조건
	// &query=${query}			: 검색키워드
	// &page=${page}			: 페이지
	@PostMapping("/testBook")
	public Map<String, Object> testBook(@RequestBody SendDataDTO dto) {
		String url = "https://dapi.kakao.com/v3/search/book";
		System.out.println(dto); 
		url += "?target="+dto.getSelectedOption();
		String unChanegeUrl = url;
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.set("Authorization", "KakaoAK "+key);
		HttpEntity<String> entity = new HttpEntity<>(httpHeaders);
		URI targetUrl = UriComponentsBuilder
				.fromUriString(url)	// 기본 url
				.queryParam("query", dto.getQuery())
				.build()
				.encode(StandardCharsets.UTF_8)
				.toUri();
		ResponseEntity<Map> result = restTemplate.exchange(targetUrl, HttpMethod.GET, entity, Map.class);
		System.out.println(result.getBody().get("meta"));
		String metaData = result.getBody().get("meta").toString();
        int startIndex = metaData.indexOf("pageable_count=");
        int endIndex = metaData.indexOf(",", startIndex);
        String pageableCountString = metaData.substring(startIndex + "pageable_count=".length(), endIndex);
		int totalBook = Integer.parseInt(pageableCountString);
		System.out.println(totalBook);
		int repeatCnt = totalBook/10;
		System.out.println(repeatCnt);
		Map<String, Object> map = new HashMap<>();
		for(int i=1; i<=repeatCnt; i++) {
			url = unChanegeUrl;
			url += "&page="+i;
//			System.out.println(url);
			RestTemplate restTemplate2 = new RestTemplate();
			HttpHeaders httpHeaders2 = new HttpHeaders();
			httpHeaders2.set("Authorization", "KakaoAK "+key);
			HttpEntity<String> entity2 = new HttpEntity<>(httpHeaders2);
			URI targetUrl2 = UriComponentsBuilder
					.fromUriString(url)	// 기본 url
					.queryParam("query", dto.getQuery())
					.build()
					.encode(StandardCharsets.UTF_8)
					.toUri();
			ResponseEntity<Map> result2 = restTemplate2.exchange(targetUrl2, HttpMethod.GET, entity2, Map.class);
//			System.out.println(result2.getBody());
			// db 작업 추가
		}
		map.put("booksList", result.getBody());
		return map;
	}
	
}























