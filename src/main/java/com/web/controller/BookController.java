package com.web.controller;

import java.net.URI;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.web.dto.SendDataDTO;

@RestController
public class BookController {
	
	private final String CLIENT_ID = "qEki3LfUNrEp3vUSBXmm";
	private final String CLIENT_SECRET = "9HVDluOLFB";
	
	// 네이버 책검색 api List
    @PostMapping("/testBook3")
    public Map<String, Object> testBook3(@RequestBody SendDataDTO dto) {
    	int display = 10;
    	URI uri = UriComponentsBuilder
                .fromUriString("https://openapi.naver.com")
                .path("/v1/search/book.json")
                .queryParam("query",dto.getQuery())
                .queryParam("sort", dto.getSort())
                .queryParam("display", display)
                .queryParam("start", (display*(dto.getPage()-1))+1)
                .encode(Charset.forName("UTF-8"))
                .build()
                .toUri();
        RestTemplate restTemplate = new RestTemplate();
        // 헤더 추가 위해
        RequestEntity<Void> req = RequestEntity
                .get(uri)
                .header("X-Naver-Client-Id", CLIENT_ID)
                .header("X-Naver-Client-Secret", CLIENT_SECRET)
                .build();
        ResponseEntity<String> result = restTemplate.exchange(req, String.class);
        Map<String, Object> map = new HashMap<>();
        map.put("booksList", result.getBody()) ;
        return map;
    }
	
    // 네이버 책검색 api Detail
    @PostMapping("/testBook4")
    public Map<String, Object> testBook4(@RequestBody SendDataDTO dto) {
    	int display = 10;
    	URI uri = UriComponentsBuilder
    			.fromUriString("https://openapi.naver.com")
    			.path("/v1/search/book_adv.json")
    			.queryParam("d_isbn",dto.getIsbn())
    			.encode(Charset.forName("UTF-8"))
    			.build()
    			.toUri(); 
    	RestTemplate restTemplate = new RestTemplate();
    	// 헤더 추가 위해
    	RequestEntity<Void> req = RequestEntity
    			.get(uri)
    			.header("X-Naver-Client-Id", CLIENT_ID)
    			.header("X-Naver-Client-Secret", CLIENT_SECRET)
    			.build();
    	ResponseEntity<String> result = restTemplate.exchange(req, String.class);
    	Map<String, Object> map = new HashMap<>();
    	map.put("detail", result.getBody()) ;
    	return map;
    }
	
//	private final String key = "05377ae946110607a0d89dae94e81960";
//	// 카카오 다음 책 검색 api
//	@PostMapping("/testBook2")
//	public Map<String, Object> testBook2(@RequestBody SendDataDTO dto) {
//		System.out.println(dto.getSort());
//		String url = "https://dapi.kakao.com/v3/search/book?page="+dto.getPage();
//		if(!dto.getTarget().equals("null")) {
//			url += "&target="+dto.getTarget();
//		}
//		if(dto.getSort().equals("latest")) {
//			url += "&sort="+dto.getSort();
//		}
//		RestTemplate restTemplate = new RestTemplate();
//		HttpHeaders httpHeaders = new HttpHeaders();
//		httpHeaders.set("Authorization", "KakaoAK "+key);
//		HttpEntity<String> entity = new HttpEntity<>(httpHeaders);
//		URI targetUrl = UriComponentsBuilder
//				.fromUriString(url)	// 기본 url
//				.queryParam("query", dto.getQuery())
//				.build()
//				.encode(StandardCharsets.UTF_8)
//				.toUri();
//		ResponseEntity<Map> result = restTemplate.exchange(targetUrl, HttpMethod.GET, entity, Map.class);
//		System.out.println(result);
//		Map<String, Object> map = new HashMap<>();
//		map.put("booksList", result.getBody());
//		return map;
//	}
//	
//	
	
}
