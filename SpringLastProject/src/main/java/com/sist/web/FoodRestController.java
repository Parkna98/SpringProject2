package com.sist.web;

import org.apache.commons.collections.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.*;
import com.sist.vo.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
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
	
}
