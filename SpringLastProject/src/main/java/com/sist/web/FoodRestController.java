package com.sist.web;

import org.apache.commons.collections.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import com.sist.vo.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sist.manager.FoodRecommendManager;
import com.sist.service.*;

@RestController
@RequestMapping("food/")
/*
 		사용자(클라이언트 => 브라우저) => 요청(URL) .do
 											===== 현재 사이트에서 가능
 		서버
 		=> 라이브러리 (기본 동작을 위한 틀이 만들어져있기 때문에 => 형식을 맞춰서 사용한다)
 		==========================================================
 		DispatcherServlet => 요청 받기 (request,response)
 			web.xml에 셋팅을 한다
 			= WebApplicationContext => 사용자가 등록한 클래스 관리
 			  ============> 관리가 필요한 모든 클래스를 xml에 설정해서 전송
 			  		<init-param>
 			  		  <param-name>ContextConfigLocation</param-name>
 			  		  <param-value>/WEB-INF/config/application-*.xml</param-value>
 			  		</init-param>
 			  		
 			= HandlerMapping : 요청시에 처리하는 Controller/RestController를 찾는 역할
 			  
 			= ViewResolver : JSP를 찾아서 request를 전송 
 		==========================================================
 		Model : Controller / RestController 
 				=> HandlerMapping에서 해당 메소드를 찾을 수 있게 만든다
 									===========
 									@GeMapping, @PostMapping, @RequestMapping
 				=> 조립기
 				   요청을 받아서 => 응답하기
 				   			처리 => DB
 		=========================================
 		Mapper : 테이블 1개를 다루는 경우
 		Service : 관련된 Mapper가 여러개 있는 경우
 		DAO
 		========================================= DB연동 (MyBatis는 데이터베이스 연결만 하는 역할)
 		View(JSP) : 화면 출력
 		========================================= 요청 (form,a, axios,ajax)
 													  =======  ===========
 													   ㅣ화면 변경 	ㅣ변경없이 데이터 읽기
 		list.do ========> DispatcherServlet (XML, Annotation)
 							 ====> list.do처리 메소드를 찾아라
 							 	   HandlerMapping
 							 -------------------------------------
 						  	 ====> list.do에 대한 처리 ====> 개발자
 						  	  	   @GetMapping("list.do")
 						  	  	   
 						  	 ====> JSP에 응답값을 전송 / 화면변경
 						  	 ------------------------------------- Model
 						  	  	   @Controller, @RestController
 						  	  	   
 						  	 ====> JSP를 찾아서 request를 전송
								   ========================
								   	ViewResolver ==> 경로명, 확장자 확인 => 등록
								   	
 		=> 어노테이션 / XML => 스프링 동작을 위한 메뉴얼 제작
 		=> Model / JSP 
 			ㅣ       
 		  DAO/Service ==> MyBatis 
 		  
 		Model
 		=====
 			=> RestController
 				=> 다른 언어와 연동 ==> JSON 
 				=> 자바스크립트와 자바 연동
 					자바 => VO => {} (Object)
 					자바 => List => [] (Array)
 					=> String => string => "", '' 
 					
 			=> Controller : 화면 이동 (변경)
 				=> forward => request를 전송하는 경우
 							  ======= 스프링에서 제공하는 전송 객체 : Model ( model.addattribute() )
 							  return "폴더명/jsp이름"; 
 				=> redirect => 재호출 => .do 
 							   request를 초기화
 							   return "redirect:~~~.do";
 							    
 */
public class FoodRestController {
	@Autowired
	private FoodService service;
	
	@Autowired
	private FoodRecommendManager mgr;
	
	@GetMapping(value="find_vue.do",produces = "text/plain;charset=UTF-8")
	public String food_find_vue(int page,String fd) throws JsonProcessingException{
		
		int rowSize=20;
		int start=(rowSize*page)-(rowSize-1);
		int end=(rowSize*page);
		Map map=new HashMap();
		map.put("start", start);
		map.put("end", end);
		map.put("address", fd);
		List<FoodVO> list=service.foodFindData(map);
		
		ObjectMapper mapper=new ObjectMapper();
		String json=mapper.writeValueAsString(list); 
		
		return json;
	}
	
	@GetMapping(value="page_vue.do",produces = "text/plain;charset=UTF-8")
	public String food_page_vue(int page,String fd) throws JsonProcessingException {
		
		final int BLOCK=10;
		int startPage=((page-1)/BLOCK*BLOCK)+1;
		int endPage=((page-1)/BLOCK*BLOCK)+BLOCK;
		Map map=new HashMap();
		map.put("address", fd);
		int totalpage=service.foodFindCount(map);
		if(endPage>totalpage)
			endPage=totalpage;
		
		map=new HashMap();
		map.put("startPage", startPage);
		map.put("endPage", endPage);
		map.put("totalpage", totalpage);
		map.put("curpage", page);
		
		ObjectMapper mapper=new ObjectMapper();
		String json=mapper.writeValueAsString(map);
		
		return json;
	}
	
	/*
	 		중요
	 		= 어노테이션은 반드시 밑에 있는 변수, 메소드, 클래스를 제어
	 */
	
	@GetMapping(value="detail_vue.do",produces = "text/plain;charset=UTF-8")
	public String food_detail_vue (int fno) throws JsonProcessingException {
		FoodVO vo=service.foodDetailData(fno);
		
		// json 만드는 라이브러리 => jackson
		ObjectMapper mapper=new ObjectMapper();
		String json=mapper.writeValueAsString(vo);
		
		return json;
	}
	
	@GetMapping(value="food_list_vue.do",produces = "text/plain;charset=UTF-8")
	public String food_list_vue(int page) throws JsonProcessingException{
		int rowSize=20;
		int start=(rowSize*page)-(rowSize-1);
		int end=(rowSize*page);
		List<FoodVO> list=service.foodListData(start, end);
		
		ObjectMapper mapper=new ObjectMapper();
		String json=mapper.writeValueAsString(list);
		
		return json;
	}
	
	@GetMapping(value="food_cookie_vue.do",produces = "text/plain;charset=UTF-8")
	public String food_cookie(HttpServletRequest request) throws Exception{
		Cookie[] cookies=request.getCookies();
		List<FoodVO> list=new ArrayList<FoodVO>();
		int k=0;
		if(cookies!=null) {
			for(int i=0;i<cookies.length;i++) {
				if(k<9) {
					if(cookies[i].getName().startsWith("food_")) {
						String fno=cookies[i].getValue();
						FoodVO vo=service.foodDetailData(Integer.parseInt(fno));
						list.add(vo);
					}
					k++;
				}
			}
		}
		
		ObjectMapper mapper=new ObjectMapper();
		String json=mapper.writeValueAsString(list);
		
		return json;
	}
	
	@GetMapping(value="food_page_vue.do",produces = "text/plain;charset=UTF-8")
	public String food_page_vue(int page) throws JsonProcessingException{
		final int BLOCK=10;
		int startPage=((page-1)/BLOCK*BLOCK)+1;
		int endPage=((page-1)/BLOCK*BLOCK)+BLOCK;
		int totalpage=service.foodListTotalPage();
		if(endPage>totalpage)
			endPage=totalpage;
		
		Map map=new HashMap();
		map.put("startPage", startPage);
		map.put("endPage", endPage);
		map.put("totalpage", totalpage);
		map.put("curpage", page);
		
		ObjectMapper mapper=new ObjectMapper();
		String json=mapper.writeValueAsString(map);
		
		return json;
	}
	
	@GetMapping(value="food_detail_vue.do",produces = "text/plain;charset=UTF-8")
	public String food_list_detail_vue (int fno) throws JsonProcessingException {
		FoodVO vo=service.foodDetailInfoData(fno);
		
		// json 만드는 라이브러리 => jackson
		ObjectMapper mapper=new ObjectMapper();
		String json=mapper.writeValueAsString(vo);
		
		return json;
	}
	
	@GetMapping(value="food_recommend_sub.do",produces = "text/plain;charset=UTF-8")
	public String food_recommend(int no) throws Exception{
			String[] menu1= {
				"퇴근길", "휴식", "휴가", "여행", "드라이브", "고백", "해변", "공연"	
			};
			String[] menu2= {
				"기분전환", "외로움", "슬픔", "밤","사랑", "스트레스","그리움", "추억", "우울", "행복", "기쁨", "축하"
			};
			String[] menu3= {
				"밝은", "신나는", "편안한", "따뜻한", "잔잔한", "달콤한", "시원한"	
			};
			String[] menu4= {
				"봄", "여름", "가을", "겨울", "맑은날", "추운날", "흐린날", "비오는날", "더운날", "눈오는날"
			};
			
			ObjectMapper mapper=new ObjectMapper();
			String json="";
			
			if(no==1) {
				json=mapper.writeValueAsString(menu1);
			}
			else if(no==2) {
				json=mapper.writeValueAsString(menu2);
			}
			else if(no==3) {
				json=mapper.writeValueAsString(menu3);
			}
			else if(no==4) {
				json=mapper.writeValueAsString(menu4);
			}
			
			return json;
	}
	
	@GetMapping(value = "food_recommend_data.do",produces = "text/plain;charset=UTF-8")
	public String food_recommend_data(String fd) throws Exception{
		
		List<String> list=mgr.newsFind(fd);
		List<String> fList=service.foodAllData();
		
		/*
		 * 	  1. 단어 => contains()
		 * 	  2. 기호 : * (0이상) 		맛있다, 맛있고, 맛있는 => 맛*
		 * 			   + (1이상) 		맛+
		 * 			   ? (0,1)		
		 * 			   | (선택)
		 			   . (임의의 한글자) 맛.
		 			   
		 			   => 실제 기호 : \\+
		 	  		   [] => 범위
		 	  		      숫자 [0-3] => [0-9]
		 	  		      영문 [A-Z] [a-z] => [A-Za-z]
		 	  		      한글 [가-힣]
		 	  		      
		 	  		   {} => 개수
		 	  		   {3}, {1,3}  
		 	  		   [0-9]{1,3} => 숫자 1~3글자
		 	  		   [0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3} ==> IP 전체
		 	  		   
		 	  		   ^ : startsWith
		 	  		   ^[가-힣] => 한글 시작
		 	  		   [^가-힣] => 한글을 제외하고
		 	  		   
		 	  		   $ : endsWith
		 	  		   [가-힣]$ => 한글로 끝남
		 	  		   문자의 형태
		 */
		
		
		List<String> rList=new ArrayList<String>();
		int[] count=new int[fList.size()];
		for(String s:list) { // 블로그에서 추천한 내용들
			System.out.println(s);
			for(int i=0;i<fList.size();i++) {
				if(s.contains(fList.get(i))) {
					System.out.println(fList.get(i));
					rList.add(fList.get(i));
					count[i]++;
				}
			}
					
		}
		
		Set set=new HashSet();
		for(int i=0;i<rList.size();i++) {
			set.add(rList.get(i)); // 중복제거
		}
		
		List<FoodVO> dList=new ArrayList<FoodVO>();
		Iterator iter=set.iterator();
		while(iter.hasNext()) {
			String ss=iter.next().toString();
			FoodVO vo=service.foodNameInfoData(ss);
			dList.add(vo);
		}
		
		ObjectMapper mapper=new ObjectMapper();
		String json=mapper.writeValueAsString(dList);
		
		return json;
	}
	
	@GetMapping(value="food_detail_recipeee.do",produces = "text/plain;charset=UTF-8")
	public String food_detail_recipeee(int fno) throws Exception{
		System.out.println("loading...");
//		String s=type.replace("///", "|");
		
		FoodVO vo=service.foodDetailData(fno);
		String s=vo.getType().replace("/", "|");
		List<RecipeVO> list=service.foodRecipeData(s);
		ObjectMapper mapper=new ObjectMapper();
		String json=mapper.writeValueAsString(list);
		System.out.println("sending...");
		return json;
	}
	
	
	
	
	
	
}
