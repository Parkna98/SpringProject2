package com.sist.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

// JSP와 연결 => 화면 변경 => forward(request 전송) "경명/파일명" /sendRedirect(request를 초기화)
// "redirect: ... .do" => 글쓰기 => 목록
@Controller
public class FoodController {
	@GetMapping("food/list.do")
	public String food_list() {
		return "food/list";
	}
}
