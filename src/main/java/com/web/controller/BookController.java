package com.web.controller;

import java.net.URI;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.web.domain.CommentEntity;
import com.web.dto.BookDTO;
import com.web.dto.SendDataDTO;
import com.web.repository.CommentRepository;
import com.web.service.CommentService;

@RestController
public class BookController {
	
	@Autowired
	private CommentRepository commentRepository;
	
	private final String CLIENT_ID = "qEki3LfUNrEp3vUSBXmm";
	private final String CLIENT_SECRET = "9HVDluOLFB";
	
	// 네이버 책검색 api List
//    @PostMapping("/testBook3")
//    public Map<String, Object> testBook3(@RequestBody SendDataDTO dto) {
//    	int display = 10;
//    	URI uri = UriComponentsBuilder
//                .fromUriString("https://openapi.naver.com")
//                .path("/v1/search/book.json")
//                .queryParam("query",dto.getQuery())
//                .queryParam("sort", dto.getSort())
//                .queryParam("display", display)
//                .queryParam("start", (display*(dto.getPage()-1))+1)
//                .encode(Charset.forName("UTF-8"))
//                .build()
//                .toUri();
//        RestTemplate restTemplate = new RestTemplate();
//        // 헤더 추가 위해
//        RequestEntity<Void> req = RequestEntity
//                .get(uri)
//                .header("X-Naver-Client-Id", CLIENT_ID)
//                .header("X-Naver-Client-Secret", CLIENT_SECRET)
//                .build();
//        ResponseEntity<String> result = restTemplate.exchange(req, String.class);
//        Map<String, Object> map = new HashMap<>();
//        map.put("booksList", result.getBody()) ;
//        return map;
//    }
	
	@PostMapping("/testBook3")
	   public ResponseEntity<Map<String, Object>> testBook3(@RequestBody SendDataDTO dto) {
	       try {
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
	           
	           // JSON 문자열을 Map으로 파싱
	           ObjectMapper objectMapper = new ObjectMapper();
	           Map<String, Object> resultMap = objectMapper.readValue(result.getBody(), new TypeReference<Map<String, Object>>(){});
	           List<BookDTO> books = objectMapper.convertValue(resultMap.get("items"), new TypeReference<List<BookDTO>>(){});
	           for(BookDTO book : books) {
	              System.out.println("//////////////////////////////////////////////////////////////////////////////////////////");
	              float avg=0;
	              try {
	                   float tot = commentRepository.sumOfColumn(book.getIsbn());
	                   List<CommentEntity> comments = commentRepository.findByIsbn(book.getIsbn());
	                   if (!comments.isEmpty()) {
	                       avg = tot / comments.size();
	                       // 평균 계산 후 필요한 작업 수행
	                   }
	               } catch (Exception e) {
	                   // 예외 발생 시 처리
	               }
	              System.out.println(avg);
	              book.setStarAvg(avg);
	           }
	           int total = objectMapper.readValue(objectMapper.writeValueAsString(resultMap.get("total")), int.class);
	           // ResponseEntity에 맞게 반환
	           Map<String, Object> responseMap = new HashMap<>();
	           responseMap.put("total", total);
	           responseMap.put("books", books);
	           return ResponseEntity.ok().body(responseMap);
	       } catch (JsonProcessingException e) {
	           e.printStackTrace(); // 또는 로깅 처리
	           return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	       }
	       
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
    	// 별점 계산 
    	String isbn = dto.getIsbn();
    	float tot;
		try {
			tot = commentRepository.sumOfColumn(isbn);
			float avg = tot/commentRepository.findByIsbn(isbn).size();
			
			map.put("starAvg", avg);
		} catch (Exception e) {
			// TODO Auto-generated catch blockd
			e.printStackTrace();
		}
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
