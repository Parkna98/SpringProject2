package com.sist.web;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
}
