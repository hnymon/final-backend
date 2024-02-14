package com.web.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
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
		List<BookCrawling> newBookList = bookCrawlingRepository.findAllByType("newBookAladin");
		List<BookCrawling> todayBookYes24List = bookCrawlingRepository.findAllByType("todayBookYes24");
		List<BookCrawling> nowThisBookYes24List = bookCrawlingRepository.findAllByType("nowThisBookYes24");
		List<BookCrawling> popularBookYes24List = bookCrawlingRepository.findAllByType("popularBookYes24");
		System.out.println(newBookList.get(0));
		responseMap.put("newBookList", newBookList); 
		responseMap.put("todayBookYes24List", todayBookYes24List); 
		responseMap.put("nowThisBookYes24List", nowThisBookYes24List); 
		responseMap.put("popularBookYes24List", popularBookYes24List); 
		return responseMap;
	}
}























