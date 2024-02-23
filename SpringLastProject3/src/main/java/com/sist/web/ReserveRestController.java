package com.sist.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
/*
 		사용자 ==> page
 		list_vue.do?page=1 => DispatcherServlet ==> freeboard_list(1)
 		jsp에서 보낸값을 Controller에서 바로 받는것이 아니라
 		DistpatcherServlet에서 받아서 데이터를 controller에게 쏴준다
 		
 		ex) 
 			@Controller
 			public class A{
 				@Autowired
 				private S s;
 				public String freeboard_list(int page)
 				{	
 					s.freeboard_list(page)
 				}
 			}
 			
 			@Service
 			public class S{
 				public String freeboard_list(int page){
 						처리
 				}
 			}
 			
 		=> 
 		
 		
 */
import com.sist.vo.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sist.manager.MailManager;
import com.sist.service.*;
import java.util.*;

import javax.servlet.http.HttpSession;

@RestController
public class ReserveRestController {
	@Autowired
	private ReserveService rService;
	
	@Autowired 
	private MailManager mm;
	
	@GetMapping(value = "reserve/food_list_vue.do",produces = "text/plain;charset=UTF-8")
	public String food_list(String type) throws Exception{
		List<FoodVO> list=rService.foodReserveData(type);
		ObjectMapper mapper=new ObjectMapper();
		String json=mapper.writeValueAsString(list);
		
		return json;
	}
	
	@PostMapping(value="reserve/reserve_ok.do",produces = "text/plain;charset=UTF-8")
	public String reserve_ok(ReserveVO vo,HttpSession session) {
		String result="no";
		try {
			
			vo.setUserId((String)session.getAttribute("userId"));
			/*System.out.println("fno:"+vo.getFno());
			System.out.println("date:"+vo.getRDate());
			System.out.println("time:"+vo.getRTime());
			System.out.println("inwon:"+vo.getRInwon());
			System.out.println("userId:"+vo.getUserId());*/
			
			rService.foodReserveInsert(vo);
			result="yes";
			
		}catch(Exception ex) {
			
			result="no";
		}
		return result;
	}
	
	
	@GetMapping(value="reserve/mypage_list_vue.do",produces = "text/plain;charset=UTF-8")
	public String mypage_list(HttpSession session) throws Exception{
		String userId=(String)session.getAttribute("userId");
		List<ReserveVO> list=rService.reserveMypageData(userId);
		
		ObjectMapper mapper=new ObjectMapper();
		String json=mapper.writeValueAsString(list);
		return json;
	}
	
	@GetMapping(value = "reserve/reserve_cancel_vue.do", produces = "text/plain;charset=UTF-8")
	public String reserve_cancel(int rno) {
		String result="";
		try {
			 
			rService.reserveCancel(rno);
			result="yes";
			
		}catch(Exception ex) {
			result="no";
		}
		
		return result;
	}
	
	@GetMapping(value = "reserve/reserve_admin_vue.do",produces = "text/plain;charset=UTF-8")
	public String reserve_admin() throws JsonProcessingException {
		List<ReserveVO> list=rService.reserveAdminpageData();
		ObjectMapper mapper=new ObjectMapper();
		String json=mapper.writeValueAsString(list);
		
		return json;
	}
	
	@GetMapping(value = "reserve/reserve_ok_vue.do",produces = "text/plain;charset=UTF-8")
	public String reserve_ok(int rno) {
		String result="";
				
		try {
			
			result="yes";
			rService.reserveok(rno);
			ReserveVO vo= rService.reserveInfoData(rno);
			
		}catch(Exception ex) {
			result="no";
		}
		
		return result;		
	}
}






