package com.sist.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import java.util.*;
import com.sist.web.entity.*;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;

import com.sist.web.dao.*;

@Controller
public class MainController {
	@Autowired
	private RecipeDAO dao;
	@GetMapping("/")
	public String main_page(Model model,HttpServletRequest request) {
		
		
		Cookie[] cookies=request.getCookies();
		List<Recipe2> cList=new ArrayList<Recipe2>();
		int k=0;
		if(cookies!=null) {
			for(int i=cookies.length-1;i>=0;i--) {
				if(cookies[i].getName().startsWith("recipe")) {
					if(k>8) break;
					String no=cookies[i].getValue();
					Recipe2 r=dao.findByNo(Integer.valueOf(no));
					cList.add(r);
					k++;
				}
			}
		}
		
		List<Recipe2> list=dao.recipeMainData();
		model.addAttribute("list",list);
		model.addAttribute("cList",cList);
		model.addAttribute("main_html","main/home");
		return "main";
	}
}
