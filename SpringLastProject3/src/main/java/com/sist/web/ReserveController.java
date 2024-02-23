package com.sist.web;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.sist.service.*;
import com.sist.vo.ReserveVO;

@Controller
public class ReserveController {
	@Autowired
	private ReserveService rService;
	
	@GetMapping("reserve/reserve_main.do")
	public String food_reserve() {
		return "reserve/reserve_main";
	}
	
	@GetMapping("mypage/mypage.do")
	public String mypage_main() {
		return "mypage/mypage_main";
	}
	
	@GetMapping("adminpage/admin.do")
	public String adminpage_main() {
		return "adminpage/admin_main";
	}
	
	
}
