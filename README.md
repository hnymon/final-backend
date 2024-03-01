# 📖 별책빵
> 국비 파이널 프로젝트
> 
> 온라인 서점 + 내 위치를 기반으로 도서관 찾아가기

# 📃 프로젝트 정보
1. 개발기간
> 2024-01-15~2024-02-20
2. 개발인원: 3명
> |Name|Position|
> |----|----|
> |[윤범헌](https://github.com/hnymon)|Back, Front| 
> |[이아현](https://github.com/appletella)|Back, Front| 
> |[김건휘](https://github.com/gunhwikim)|Back, Front| 


## :wrench:기술 스택

### 프론트엔드
<img src="https://img.shields.io/badge/react-61DAFB?style=for-the-badge&logo=react&logoColor=white"> <img src="https://img.shields.io/badge/java_script-F7DF1E?style=for-the-badge&logo=javascript&logoColor=white">
<img src="https://img.shields.io/badge/styled_components-DB7093?style=for-the-badge&logo=styledcomponents&logoColor=white">

### 백엔드
- java11
- SpringBoot
- Spring Data JPA
- Spring Security
- JWT
- OAuth2.0
- Oracle
- AWS(LightSail)

### 협업툴
<img src="https://img.shields.io/badge/github-181717?style=for-the-badge&logo=github&logoColor=white"> <img src="https://img.shields.io/badge/figma-F24E1E?style=for-the-badge&logo=figma&logoColor=white"> 
<img src="https://img.shields.io/badge/canva-00C4CC?style=for-the-badge&logo=canva&logoColor=white"> 

## 내가 맡은 역할
- 백엔드: Naver 책검색 API, Crawling, Email, 일반 로그인, 회원가입, 책 별 리뷰 평균 별점, 배송주소록, 회원정보 수정, 아이디/비밀번호 찾기, DB설계, JWT, SpringSecurity, 주문 및 주문상세 승인상태
- 프론트: 홈페이지, 회원가입, 일반로그인(아이디 저장기능), Id/Pwd찾기, 책 상세 페이지, 마이페이지(회원정보 수정, 배송주소록), 관리자 페이지 틀 및 주문페이지, 헤더

## 🗄 ERD & UseCase
<details>
    <summary>ERD</summary>
    <img src="https://github.com/hnymon/final-backend/assets/151509541/af2c5f75-860e-4a45-869f-f65e4d5ee247" style="width: 600px;"/>
</details>
<details>
    <summary>UseCase</summary>
    <img src="https://github.com/hnymon/final-backend/assets/151509541/a538bbf7-41a7-4f60-b237-c6dac767dfcf" style="width: 300px;"/>
    <img src="https://github.com/hnymon/final-backend/assets/151509541/e51c7b40-43ed-47a8-bd62-3673b96b328b" style="width: 300px;"/>
</details>



## 🗝️ 프로젝트 주요 기능 
### 1. 책 검색
- 책 검색 리스트
> Naver 책검색 API 를 활용하여 책 이름, 저자, 출판사, ISBN 으로 조회하여 정확도? 가 높은 순으로 검색결과를 가져옴
<details>
    <summary>코드 보기</summary>
    
```java
@RestController
public class BookController {
    // application.properties 에 작성해 놓은 naver api client-name, secret 가져오기    
    @Value("${myapp.naver.api.client-name}")
    private String CLIENT_ID;
    @Value("${myapp.naver.api.client-secret}")
    private String CLIENT_SECRET;

    // 검색어, 정렬 방식, 리스트 수, 시작 번호 등을 uri로 설정 후 naver api 로 보낸 후 리스트 받아오기
    @PostMapping("/testBook3")
    public ResponseEntity<Map<String, Object>> testBook3(@RequestBody SendDataDTO dto) {
        // SendDataDTO는 해당 값들을 편하게 받아오기 위해 생성한 DTO
        try {
            URI uri = UriComponentsBuilder
                .fromUriString("https://openapi.naver.com")
                .path("/v1/search/book.json")
                .queryParam("query",dto.getQuery())
                .queryParam("sort", dto.getSort())
                .queryParam("display", dto.getDisplay())
                .queryParam("start", (dto.getDisplay()*(dto.getPage()-1))+1)
                .encode(Charset.forName("UTF-8"))
                .build()
                .toUri();
            RestTemplate restTemplate = new RestTemplate();

            // 헤더에 client-name, secret 추가
            RequestEntity<Void> req = RequestEntity
                .get(uri)
                .header("X-Naver-Client-Id", CLIENT_ID)
                .header("X-Naver-Client-Secret", CLIENT_SECRET)
                .build();

            // naver에서 json 형태의 반환값을 String 값으로 받음
            ResponseEntity<String> result = restTemplate.exchange(req, String.class);
	           
            // JSON 형태의 문자열을 Map으로 파싱
            ObjectMapper objectMapper = new ObjectMapper();
            Map<String, Object> resultMap = objectMapper.readValue(result.getBody(), new TypeReference<Map<String, Object>>(){});

            // items 에 검색결과가 담겨 있으므로 해당 리스트를 BookDTO 라는 객체에 담아 리스트 형태로 담기
            List<BookDTO> books = objectMapper.convertValue(resultMap.get("items"), new TypeReference<List<BookDTO>>(){});

            // 아래 for문은 naver 책검색 결과와는 별개의 작업으로 해당 책에 리뷰 등록시 별점을 설정 할 수 있음
            // 리뷰가 달려있을 때, BookDTO 객체에 해당 책의 평균 별점을 계산 후 추가하기 위한 작업
            for(BookDTO book : books) {
                float avg=0;
                try {
                    float tot = commentRepository.sumOfColumn(book.getIsbn());
                    List<CommentEntity> comments = commentRepository.findByIsbn(book.getIsbn());
                    if (!comments.isEmpty()) {
                        avg = tot / comments.size();
                    }
                } catch (Exception e) {
                // 예외 발생 시 처리
                e.printStackTrace();
                }
                book.setStarAvg(avg);
            }
            // 프론트에서 페이징을 위해 전체 검색 결과 수
            int total = objectMapper.readValue(objectMapper.writeValueAsString(resultMap.get("total")), int.class);

            // ResponseEntity에 맞게 반환
            Map<String, Object> responseMap = new HashMap<>();

            // 전체 결과 수와 책 결과 리스트를 Map에 put
            responseMap.put("total", total);
            responseMap.put("books", books);
            return ResponseEntity.ok().body(responseMap);
        } catch (JsonProcessingException e) {
        e.printStackTrace();
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
```

</details>

- 책 상세정보
> 책의 고윳값인 ISBN으로 책을 조회하여 해당 책에 대한 정보를 가져옴
<details>
    <summary>코드 보기</summary>
    
```java
@RestController
public class BookController {
    // application.properties 에 작성해 놓은 naver api client-name, secret 가져오기    
    @Value("${myapp.naver.api.client-name}")
    private String CLIENT_ID;
    @Value("${myapp.naver.api.client-secret}")
    private String CLIENT_SECRET;

    // 네이버 책 상세정보 조회
    @PostMapping("/naver_book_adv_Api")
    // 응답시간을 줄이기 위함
    @Cacheable(value = "bookDetails", key = "#dto.isbn")
    public Map<String, Object> naver_book_adv_Api(@RequestBody SendDataDTO dto) {
        // SendDataDTO 에 책의 고윳값인 isbn을 받아온 후 검색
        // 위의 리스트 방식과 다른 이유
        // 위의 리스트 작업에서는 리스트 내의 객체에 별점 평균을 계산 후 넣는 작업을 위해 json 형태로 들어온 데이터를
        // 파싱 후 작업을 했고 해당 코드는 그러한 작업이 필요하지 않아 json 형태로 들어온 데이터를 바로
        // 프론트로 전송해줌
        try {
            URI uri = URI.create("https://openapi.naver.com/v1/search/book_adv.json?d_isbn=" + dto.getIsbn());

            WebClient webClient = WebClient.create();

            String result = webClient.get()
                .uri(uri)
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .header("X-Naver-Client-Id", CLIENT_ID)
                .header("X-Naver-Client-Secret", CLIENT_SECRET)
                .retrieve()
                .bodyToMono(String.class)
                .block();

            Map<String, Object> map = new HashMap<>();
            map.put("detail", result);
            return map;
        } catch (Exception e) {
            e.printStackTrace();
            // 예외 발생 시 적절한 응답을 반환하거나 로깅하도록 처리
            return Collections.singletonMap("error", "Failed to retrieve book details");
        }
    }
}
```

</details>

[내 Naver_API 전체 코드 보러 가기](https://github.com/hnymon/final-backend/blob/master/src/main/java/com/web/controller/BookController.java)

<br/>

참고 자료

[Naver Developers](https://developers.naver.com/docs/common/openapiguide/apilist.md#%EB%B9%84%EB%A1%9C%EA%B7%B8%EC%9D%B8-%EB%B0%A9%EC%8B%9D-%EC%98%A4%ED%94%88-api)


### 2. 웹 크롤링
- 웹사이트(Yes24, Aladin)를 크롤링하여 해당 정보를 DB에 저장
> 처음 크롤링은 페이지에서 책 상세보기를 가져올 수 있는 url을 가져온 후
> 해당 url을 이용하여 상세정보 페이지에서 해당 책에 대한 정보를 가져와 db에 저장

<details>
    <summary>코드 보기</summary>
    
```java
// 크롤링을 계속하거나 서버가 재실행 될때마다 하는 것 보다는 스케줄을 정해놓고 사용하기 위함
@EnableScheduling
@Service
public class CrawlingYes24 {
    @Autowired
	private BookCrawlingRepository bookCrawlingRepository;

 	// 1시간 단위로 메서드를 실행, 정각마다 해당 메서드를 실행
	@Scheduled(cron = "0 0 */1 * * *") 
	// @PostConstruct 는 서버가 실행될 때 해당 메서드를 실행
	// 테스트 용도로 사용했으므로 주석처리
//	@PostConstruct 
	public void testCrawling() {
		// 크롤링을 시작하기 전에 DB에 담겨있던 리스트들을 삭제 후 다시 저장하기 위한 작업
		// deleteAllInBatch 를 사용하는 것이 속도면에서 이롭다 판단하여 해당 리스트들을 삭제 
		List<BookCrawling> todayBookYes24List = bookCrawlingRepository.findAllByType("todayBookYes24");
		bookCrawlingRepository.deleteAllInBatch(todayBookYes24List);
		List<BookCrawling> nowThisBookYes24List = bookCrawlingRepository.findAllByType("nowThisBookYes24");
		bookCrawlingRepository.deleteAllInBatch(nowThisBookYes24List);
		List<BookCrawling> popularBookYes24List = bookCrawlingRepository.findAllByType("popularBookYes24");
		bookCrawlingRepository.deleteAllInBatch(popularBookYes24List);

		// 크롤링 할 URL 설정
		final String yes24URL = "https://www.yes24.com/main/default.aspx";
  		// 크롤링은 Jsoup를 사용하여 진행
		Connection conn = Jsoup.connect(yes24URL);
	    try {
			// BookCrawling 는 엔티티로 크롤링한 데이터를 DB에 저장하기 위해 생성
			// List에 담는 이유는 saveAll을 하기 위함
	    	List<BookCrawling> yesList = new ArrayList<>();

			// 고윳값을 만들기 위한 정수값
	    	int cnt = 0;
	        Document document = conn.get();
	        // 오늘의 책
			// todayBUnit 라는 클래스를 가진 div가 원하는 값을 가진 구역
	        Elements todayBook = document.select(".todayBUnit");
			// for문을 돌려 책의 이름과 이미지경로, 책의 상세정보를 얻기 위한 url 값들을 가져옴 
	        for(Element element : todayBook) {
	        	cnt ++;
				// 책의 이름 가져오기
	        	String bookName = element.select(".tb_name").text();
		  		// 책의 이미지 경로 가져오기
	        	String bookImg = element.select("img").attr("src");
				// 책의 상세정보 페이지로 이동하는 url값을 생성하기 위해 고윳값 가져오기
	        	String bookInfoUrl = "https://www.yes24.com/"+element.select("a").attr("href");
				// 위에서 가져온 url 로 다시 한번 url 지정 후 크롤링
	        	Connection connInfo = Jsoup.connect(bookInfoUrl);
	        	Document documentInfo = connInfo.get();
				// 책의 고윳값으로는 isbn이 있는데 이는 숫자 10개 또는 13개로 이루어져 있으며
				// yes24 에서는 아래와 같은 클래스 이름으로 구분지어져 있고
				// 해당 tr 값으로 ISBN10, ISBN13 과 같이 들어있어 아래와 같은 방식으로 값을 가져옴
	        	String isbn10 = documentInfo.select(".infoSetCont_wrap table.tb_nor.tb_vertical tbody.b_size tr:has(th:containsOwn(ISBN10))").select("td").text();
	        	String isbn13 = documentInfo.select(".infoSetCont_wrap table.tb_nor.tb_vertical tbody.b_size tr:has(th:containsOwn(ISBN13))").select("td").text();
				// yes24는 작가와 출판사를 " 작가 | 출판사 " 와 같이 | 로 구분지어 놓았기 때문에 아래와 같이 split을 사용하여 원하는 값을 저장
				// 책의 작가 가져오기
	        	String author = element.select(".tb_pub").text().split("\\|")[0];
				// 책의 출판사 가져오기
	        	String publisher = element.select(".tb_pub").text().split("\\| ")[1];
				// 책에 대한 요약, 내용을 간략하게 설명한 것
	        	String content = element.select(".tb_readCon").text();
				// 엔티티 객체를 생성하여 List 에 add
				// 아래와 같이 dto 보다는 구별할 수 있도록 이름을 잘 작성하는게 좋다고 생각
				// 0228 수정
	        	BookCrawling bookCrawlingData = new BookCrawling();
	        	bookCrawlingData.setBookName(bookName);
	        	bookCrawlingData.setImgUrl(bookImg);
	        	bookCrawlingData.setIsbn10(isbn10); 
	        	bookCrawlingData.setIsbn13(isbn13); 
	        	bookCrawlingData.setAuthor(author); 
	        	bookCrawlingData.setPublisher(publisher);
	        	bookCrawlingData.setContent(content);
	        	bookCrawlingData.setType("todayBookYes24");
	        	bookCrawlingData.setUniqueCol(dto.getType()+cnt);
	        	yesList.add(bookCrawlingData);
	        }
			// 리스트에 담겨있는 데이터들을 하나하나 save 하지 않고
			// saveAll 을 이용하여 한 번에 처리하는 것이 속도면에서 좋다고 판단
	        bookCrawlingRepository.saveAll(yesList);
	    } catch (IOException e) {
	        e.printStackTrace(); 
	    }
	}
```	
	
</details>


[Crawling_Aladin 전체 코드 보러 가기](https://github.com/hnymon/final-backend/blob/master/src/main/java/com/web/crawling/CrawlingAladin.java)
 
[Crawling_Yes24 전체 코드 보러 가기](https://github.com/hnymon/final-backend/blob/master/src/main/java/com/web/crawling/CrawlingYes24.java)

### 3. 책 상세 정보와 수량을 장바구니에 담고 결제하기 구현
   text
  
 [코드 보러 가기]()
### 4. 공공 데이터를 활용(전국도서관표준데이터)_csv
- 공공데이터 포털에서 해당 데이터를 저장 후 csv 데이터를 list에 담은 후 DB에 저장

<details>
    <summary>코드 보기</summary>
    
```java
@EnableScheduling
@Service
public class CSVParserExample {
	@Autowired
	private LibraryRepository libraryRepository;

//	@PostConstruct // 서버재실행될때마다  메서드 작동 test
//	@Scheduled(cron = "0 0 0 */1 * *")
	public void test() { 
	    libraryRepository.deleteAllInBatch(); // 전체삭제 서버재실행될때마다 삭제
	    String csvFilePath = "src/main/resources/NationalLibraryStandardData.csv";
	    try (CSVReader reader = new CSVReader( 
	            new InputStreamReader(new FileInputStream(csvFilePath), StandardCharsets.UTF_8))) {
	        List<String[]> libraryList = reader.readAll(); // CSV 파일을 읽어와서 리스트에 저장
	        List<SeoulPublicLibrary> list = new ArrayList<>();
	        for (String[] line : libraryList) { 
	        	//for 문을 이용하여 build 방식으로 객체 생성후 DB에 저장 
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
}
```	
	
</details>
  
   [전체 코드 보러 가기](https://github.com/hnymon/final-backend/blob/master/src/main/java/com/web/service/CSVParserExample.java)
   
### 5. 카카오 맵 API를 통해 도서관 위치 표시 < 프론트이므로 프론트에서 정리 
  [프론트엔드 보러 가기](https://github.com/hnymon/final-frontend)
### 6. 주문, 문의 내역 확인
 text
  
[코드 보러 가기]()



