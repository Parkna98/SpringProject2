package com.sist.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import java.util.*;
import com.sist.vo.*;
import com.sist.service.*;

@Controller
public class MainController {
	@Autowired
	private FoodService service;
	
	@Autowired
	private RecipeService rService;
	
	@GetMapping("main/main.do")
	public String main_main(Model model) {
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
