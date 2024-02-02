package com.web.crawling;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.web.domain.BookCrawling;
import com.web.repository.BookCrawlingRepository;

@EnableScheduling
@Service
public class CrawlingAladin {
	@Autowired
	private BookCrawlingRepository bookCrawlingRepository;
	
//	@Scheduled(cron = "0/30 * * * * *") // 30초
	@Scheduled(cron = "0 0 */1 * * *") // 1시간
//	@PostConstruct
	public void testCrawling() {
		List<BookCrawling> newBookList = bookCrawlingRepository.findAllByType("newBookAladin");
		bookCrawlingRepository.deleteAllInBatch(newBookList);
	    final String aladinURL = "https://www.aladin.co.kr/home/wbookmain.aspx";
	    Connection conn = Jsoup.connect(aladinURL);
	    try {
	        Document document = conn.get();
	        int cnt = 0;
	        // 신간도서
	        Elements newBook = document.select(".bestseller_book"); 
	        for(Element element : newBook) {
	        	cnt ++;
	        	String bookName = element.select("h4").text();
	        	String bookImg = element.select("img[src$=.jpg]").attr("src");
	        	String bookInfoUrl = element.select("a").attr("href");
	        	Connection connInfo = Jsoup.connect(bookInfoUrl);
	        	Document documentInfo = connInfo.get();
	        	String isbn13 = documentInfo.select(".conts_info_list1 li:last-child").text();
	        	if(isbn13.substring(0,4).equals("ISBN")) {
	        		isbn13 = documentInfo.select(".conts_info_list1 li:last-child").text().substring(7);
	        	}else {
	        		isbn13 = documentInfo.select(".conts_info_list1 li:nth-last-child(2)").text().substring(7);
	        	} 
	        	BookCrawling dto = new BookCrawling();
	        	dto.setBookName(bookName);
	        	dto.setImgUrl(bookImg);
	        	dto.setIsbn13(isbn13); 
	        	dto.setType("newBookAladin");
	        	dto.setUniqueCol(dto.getType()+cnt);
	        	bookCrawlingRepository.save(dto);
	        }
	        Element firstUl = document.selectFirst(".book_col_box_list");
	        Elements newBook2 = firstUl.select("li");
	        System.out.println("//////////////////////////////////////////////////////////////////\n"+newBook2); 
	        for(Element element : newBook2) {
	        	cnt ++;
	        	String bookName = element.select("h4").text();
	        	String bookImg = element.select("img[data-src$=.jpg]").attr("data-src");
	        	String bookInfoUrl = element.select("a").attr("href");
	        	Connection connInfo = Jsoup.connect(bookInfoUrl);
	        	Document documentInfo = connInfo.get();
	        	String isbn13 = documentInfo.select(".conts_info_list1 li:last-child").text();
	        	if(isbn13.substring(0,4).equals("ISBN")) {
	        		isbn13 = documentInfo.select(".conts_info_list1 li:last-child").text().substring(7);
	        	}else {
	        		isbn13 = documentInfo.select(".conts_info_list1 li:nth-last-child(2)").text().substring(7);
	        	} 
	        	BookCrawling dto = new BookCrawling();
	        	dto.setBookName(bookName);
	        	dto.setImgUrl(bookImg);
	        	dto.setIsbn13(isbn13); 
	        	dto.setType("newBookAladin");
	        	dto.setUniqueCol(dto.getType()+cnt);
	        	bookCrawlingRepository.save(dto);
	        }
	    } catch (IOException e) {
	        e.printStackTrace(); 
	    }
	}
}
