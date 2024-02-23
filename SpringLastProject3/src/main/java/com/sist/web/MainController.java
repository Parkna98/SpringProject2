package com.sist.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;
import java.util.*;

import javax.servlet.http.HttpSession;

import com.sist.vo.*;
import com.sist.service.*;

@Controller
public class MainController {
	@Autowired
	private FoodService service;
	
	@Autowired
	private RecipeService rService;
	
	@Autowired
	private MemberService mService;
	
	@GetMapping("main/main.do")
	public String main_main(Model model,Principal p,HttpSession session) {
		
		if(p!=null) {
			
			MemberVO vo=mService.memberSessionInfoData(p.getName()); // id
			session.setAttribute("userId", vo.getUserId());
			session.setAttribute("userName",vo.getUserName());
			session.setAttribute("email",vo.getEmail());
			session.setAttribute("phone",vo.getPhone());
			session.setAttribute("addr",vo.getAddr1()+" "+vo.getAddr2());
			session.setAttribute("sex",vo.getSex());
		}
		// JSP로 값을 전송 객체 => 전송 객체 ==> Model (HttpServletRequest)
		List<FoodVO> foodList=service.foodHomeTop12();
		model.addAttribute("foodList",foodList);
		List<RecipeVO> reList=rService.recipeTop12();
		model.addAttribute("reList",reList);
		List<ChefVO> chList=rService.chefTop12();
		model.addAttribute("chList", chList);
		
		
		
		return "main";
	}
}
