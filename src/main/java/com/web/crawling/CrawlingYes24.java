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
public class CrawlingYes24 {
	@Autowired
	private BookCrawlingRepository bookCrawlingRepository;
	 
//	@Scheduled(cron = "0/30 * * * * *") // 30초
	@Scheduled(cron = "0 0 */1 * * *") // 1시간
//	@PostConstruct
	public void testCrawling() {
		List<BookCrawling> todayBookYes24List = bookCrawlingRepository.findAllByType("todayBookYes24");
		bookCrawlingRepository.deleteAllInBatch(todayBookYes24List);
		List<BookCrawling> nowThisBookYes24List = bookCrawlingRepository.findAllByType("nowThisBookYes24");
		bookCrawlingRepository.deleteAllInBatch(nowThisBookYes24List);
		List<BookCrawling> popularBookYes24List = bookCrawlingRepository.findAllByType("popularBookYes24");
		bookCrawlingRepository.deleteAllInBatch(popularBookYes24List);
	    final String yes24URL = "https://www.yes24.com/main/default.aspx";
	    Connection conn = Jsoup.connect(yes24URL);
	    try {
	    	List<BookCrawling> yesList = new ArrayList<>();
	    	int cnt = 0;
	        Document document = conn.get();
	        // 오늘의 책
	        Elements todayBook = document.select(".todayBUnit");
	        for(Element element : todayBook) {
	        	cnt ++;
	        	String bookName = element.select(".tb_name").text();
	        	String bookImg = element.select("img").attr("src");
	        	String bookInfoUrl = "https://www.yes24.com/"+element.select("a").attr("href");
	        	Connection connInfo = Jsoup.connect(bookInfoUrl);
	        	Document documentInfo = connInfo.get();
	        	String isbn10 = documentInfo.select(".infoSetCont_wrap table.tb_nor.tb_vertical tbody.b_size tr:has(th:containsOwn(ISBN10))").select("td").text();
	        	String isbn13 = documentInfo.select(".infoSetCont_wrap table.tb_nor.tb_vertical tbody.b_size tr:has(th:containsOwn(ISBN13))").select("td").text();
	        	BookCrawling dto = new BookCrawling();
	        	dto.setBookName(bookName);
	        	dto.setImgUrl(bookImg);
	        	dto.setIsbn10(isbn10); 
	        	dto.setIsbn13(isbn13); 
	        	dto.setType("todayBookYes24");
	        	dto.setUniqueCol(dto.getType()+cnt);
	        	yesList.add(dto);
	        }
	        // 지금 이 책
	        Elements nowThisBook = document.select(".nowBookSet li");
	        for(Element element : nowThisBook) {
	        	cnt ++;
	        	String bookName = element.select(".goods_tCmt").text();
	        	String bookImg = element.select("img").attr("data-original");
	        	String bookInfoUrl = "https://www.yes24.com/"+element.select("a").attr("href");
	        	Connection connInfo = Jsoup.connect(bookInfoUrl);
	        	Document documentInfo = connInfo.get();
	        	String isbn10 = documentInfo.select(".infoSetCont_wrap table.tb_nor.tb_vertical tbody.b_size tr:has(th:containsOwn(ISBN10))").select("td").text();
	        	String isbn13 = documentInfo.select(".infoSetCont_wrap table.tb_nor.tb_vertical tbody.b_size tr:has(th:containsOwn(ISBN13))").select("td").text();
	        	BookCrawling dto = new BookCrawling();
	        	dto.setBookName(bookName);
	        	dto.setImgUrl(bookImg);
        		dto.setIsbn10(isbn10); 
        		dto.setIsbn13(isbn13); 
	        	dto.setType("nowThisBookYes24");
	        	dto.setUniqueCol(dto.getType()+cnt);
	        	yesList.add(dto);
	        }
	        // 크레마샵 화제의 책
	        Elements popularBooks = document.select(".bookClubSet li");
	        for(Element element : popularBooks) {
	        	cnt ++;
	        	String bookName = element.select(".goods_name").text();
	        	String bookImg = element.select("img").attr("data-original");
	        	String bookInfoUrl = "https://www.yes24.com/"+element.select("a").attr("href");
	        	Connection connInfo = Jsoup.connect(bookInfoUrl);
	        	Document documentInfo = connInfo.get();
	        	String isbn10 = documentInfo.select(".infoSetCont_wrap table.tb_nor.tb_vertical tbody.b_size tr:has(th:containsOwn(ISBN10))").select("td").text();
	        	String isbn13 = documentInfo.select(".infoSetCont_wrap table.tb_nor.tb_vertical tbody.b_size tr:has(th:containsOwn(ISBN13))").select("td").text();
	        	BookCrawling dto = new BookCrawling();
	        	dto.setBookName(bookName);
	        	dto.setImgUrl(bookImg);
	        	dto.setIsbn10(isbn10);
	        	dto.setIsbn13(isbn13); 
	        	dto.setType("popularBookYes24");
	        	dto.setUniqueCol(dto.getType()+cnt);
	        	yesList.add(dto);
	        }
	        bookCrawlingRepository.saveAll(yesList);
	    } catch (IOException e) {
	        e.printStackTrace(); 
	    }
	}
}
