package com.web.service;

import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.opencsv.CSVReader;
import com.web.domain.SeoulPublicLibrary;
import com.web.repository.LibraryRepository;
@EnableScheduling
@Service
public class CSVParserExample {
	@Autowired
	private LibraryRepository libraryRepository;
//  
//	@PostConstruct
//	public void test() {
//	    String csvFilePath = "src/main/resources/publicLibrary1.csv";
//	    try (CSVReader reader = new CSVReader(
//	            new InputStreamReader(new FileInputStream(csvFilePath), StandardCharsets.UTF_8))) {
//	        List<String[]> libraryList = reader.readAll(); // CSV 파일을 읽어와서 리스트에 저장
//	        for (String[] line : libraryList) { 
////	        	System.out.println(line[0]);
//	            SeoulPublicLibrary seoulPublicLibrary = new SeoulPublicLibrary();
//	            seoulPublicLibrary.setLbrrySeqNo(line[0]);  // CSV 열에 따라 인덱스 조정
//	            seoulPublicLibrary.setLbbryName(line[1]); // CSV 열에 따라 인덱스 조정
//	            seoulPublicLibrary.setGuCode(line[2]); // CSV 열에 따라 인덱스 조정
//	            seoulPublicLibrary.setCodeValue(line[3]); // CSV 열에 따라 인덱스 조정
//	            seoulPublicLibrary.setAdres(line[4]); // CSV 열에 따라 인덱스 조정
//	            seoulPublicLibrary.setTelNo(line[5]); // CSV 열에 따라 인덱스 조정
//	            seoulPublicLibrary.setHimpgUrl(line[6]); // CSV 열에 따라 인덱스 조정
//	            seoulPublicLibrary.setOpTime(line[8]); // CSV 열에 따라 인덱스 조정
//	            seoulPublicLibrary.setFdrmCloseDate(line[9]); // CSV 열에 따라 인덱스 조정
//	            seoulPublicLibrary.setLbrrySeName(line[10]); // CSV 열에 따라 인덱스 조정
//	            seoulPublicLibrary.setXcnts(line[11]); // CSV 열에 따라 인덱스 조정
//	            seoulPublicLibrary.setYdnts(line[12]); // CSV 열에 따라 인덱스 조정
//	            libraryRepository.save(seoulPublicLibrary);
//	             
//	        } 
//	        // bookDTOList를 이용하여 데이터베이스에 저장
//
//	    } catch (Exception e) {
//	        e.printStackTrace();
//	    }
//	}
	
	
//	bulid 방식
//	@PostConstruct // 서버재실행될때마다  메서드 작동
	@Scheduled(cron = "0 0 0 */1 * *") // 범인 씹새기
	public void test() { 
	    libraryRepository.deleteAllInBatch(); // 전체삭제 서버재실행될때마다 삭제
	    String csvFilePath = "src/main/resources/NationalLibraryStandardData.csv";
	    try (CSVReader reader = new CSVReader( 
	            new InputStreamReader(new FileInputStream(csvFilePath), StandardCharsets.UTF_8))) {
	        List<String[]> libraryList = reader.readAll(); // CSV 파일을 읽어와서 리스트에 저장
	        List<SeoulPublicLibrary> list = new ArrayList<>();
	        for (String[] line : libraryList) { 
	            SeoulPublicLibrary seoulPublicLibrary = 
	            		SeoulPublicLibrary.builder()
	            			.lbrryNm(line[0])
	            			.ctprvnNm(line[1])
	            			.signguNm(line[2])
	            			.lbrrySe(line[3])
	            			.closeDay(line[4])
	            			.weekdayOperOpenHhmm(line[5])
	            			.weekdayOperCloseHhmm(line[6])
	            			.satOperOperOpenHhmm(line[7])
	            			.satOperCloseHhmm(line[8])
	            			.holidayOperOpenHhmm(line[9])
	            			.holidayCloseOpenHhmm(line[10])
	            			.seatCo(line[11])
	            			.bookCo(line[12])
	            			.pblicCo(line[13])
	            			.noneBookCo(line[14])
	            			.lonCo(line[15])
	            			.lonDayCnt(line[16])
	            			.rdnmadr(line[17])
	            			.operInstitutionNm(line[18])
	            			.phoneNumber(line[19])
	            			.plotAr(line[20])
	            			.buldAr(line[21])
	            			.homepageUrl(line[22])
	            			.latitude(line[23])
	            			.longitude(line[24])
	            			.referenceDate(line[25])
	            			.insttCode(line[26])
	            			.build();
	            System.out.println(seoulPublicLibrary);
	            list.add(seoulPublicLibrary);
	        } 
	        // bookDTOList를 이용하여 데이터베이스에 저장
	        libraryRepository.saveAll(list);
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}
	// 성공한 코드
//	@PostConstruct
////	@Scheduled(cron = "0 0 */1 * * *") 쓰기 x
//	public void test() { 
//	    libraryRepository.deleteAllInBatch(); // 전체삭제 서버재실행될때마다 삭제
//	    String csvFilePath = "src/main/resources/NationalLibraryStandardData.csv";
//	    try (CSVReader reader = new CSVReader( 
//	            new InputStreamReader(new FileInputStream(csvFilePath), StandardCharsets.UTF_8))) {
//	        List<String[]> libraryList = reader.readAll(); // CSV 파일을 읽어와서 리스트에 저장
//	        for (String[] line : libraryList) { 
//	        	SeoulPublicLibrary seoulPublicLibrary = new SeoulPublicLibrary();
//	            seoulPublicLibrary.setLbrryNm(line[0]);
//	            seoulPublicLibrary.setCtprvnNm(line[1]);
//	            seoulPublicLibrary.setSignguNm(line[2]);
//	            seoulPublicLibrary.setLbrrySe(line[3]);
//	            seoulPublicLibrary.setCloseDay(line[4]);
//	            seoulPublicLibrary.setWeekdayOperOpenHhmm(line[5]);
//	            seoulPublicLibrary.setWeekdayOperCloseHhmm(line[6]);
//	            seoulPublicLibrary.setSatOperOperOpenHhmm(line[7]);
//	            seoulPublicLibrary.setSatOperCloseHhmm(line[8]);
//	            seoulPublicLibrary.setHolidayOperOpenHhmm(line[9]);
//	            seoulPublicLibrary.setHolidayCloseOpenHhmm(line[10]);
//	            seoulPublicLibrary.setSeatCo(line[11]);
//	            seoulPublicLibrary.setBookCo(line[12]);
//	            seoulPublicLibrary.setPblicCo(line[13]);
//	            seoulPublicLibrary.setNoneBookCo(line[14]);
//	            seoulPublicLibrary.setLonCo(line[15]);
//	            seoulPublicLibrary.setLonDayCnt(line[16]);
//	            seoulPublicLibrary.setRdnmadr(line[17]);
//	            seoulPublicLibrary.setOperInstitutionNm(line[18]);
//	            seoulPublicLibrary.setPhoneNumber(line[19]);
//	            seoulPublicLibrary.setPlotAr(line[20]);
//	            seoulPublicLibrary.setBuldAr(line[21]);
//	            seoulPublicLibrary.setHomepageUrl(line[22]);
//	            seoulPublicLibrary.setLatitude(line[23]);
//	            seoulPublicLibrary.setLongitude(line[24]);
//	            seoulPublicLibrary.setReferenceDate(line[25]);
//	            seoulPublicLibrary.setInsttCode(line[26]);
//	       
//	            libraryRepository.save(seoulPublicLibrary);
//	        } 
//	        // bookDTOList를 이용하여 데이터베이스에 저장
//  
//	    } catch (Exception e) {
//	        e.printStackTrace();
//	    }
//	}
}
