package com.sist.web;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
/*
 *
상황
출/퇴근길 휴식 일/공부 집 카페 휴가/여행 드라이브 산책 운동 하우스파티 시상식 집중 거리 클럽 고백 해변 공연 라운지 애도
감성
기분전환 외로움 슬픔 힘찬 이별 지침/힘듦 설렘 오후 위로 밤 새벽 저녁 아침 사랑 스트레스/짜증 그리움 추억 우울 행복 불안 분노 기쁨 축하
스타일
밝은 신나는 편안한 따뜻한 그루브한 부드러운 로맨틱한 웅장한 매혹적인 영화음악 잔잔한 몽환적인 댄서블한 달콤한 시원한 애절한 어두운 연주음악 발렌타인데이 화이트데이
날씨/계절
봄 여름 가을 겨울 맑은날 추운날 흐린날 비오는날 더운날 안개낀날 눈오는날
 */
@Controller
public class FoodController {
	@GetMapping("food/food_find.do")
	public String food_find() {
		return "food/food_find";
	}
	
	@GetMapping("food/food_list.do")
	public String food_list() {
		return "food/food_list";
	}
	
	@GetMapping("food/food_before_list_detail.do")
	public String food_before_detail(int fno,RedirectAttributes ra,HttpServletResponse response) {
		
		Cookie cookie=new Cookie("food_"+fno,String.valueOf(fno)); // 쿠키는 문자열만 저장이 가능
		cookie.setPath("/");
		cookie.setMaxAge(60*60*24);
		response.addCookie(cookie); // 브라우저로 전송
		
		// forward형식으로 값보낼때는 Model.addAttribute
		// redirect형식으로 값보낼때는 RedirectAttributes.addAttribute
		ra.addAttribute("fno", fno);
		return "redirect:../food/food_list_detail.do";
	}
	
	@GetMapping("food/food_list_detail.do")
	public String food_detail(int fno, Model model) {
		model.addAttribute("fno",fno);
		return "food/food_detail";
	}
	
	@GetMapping("food/food_test.do")
	public String food_test() {
		return "food/food_test";
	}
	
	@GetMapping("food/food_recommend.do")
	public String food_recommend(String type,Model model) {
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
		
		return "food/food_recommend";
	}
}
