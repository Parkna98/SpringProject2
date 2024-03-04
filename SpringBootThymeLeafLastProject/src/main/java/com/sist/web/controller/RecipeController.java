package com.sist.web.controller;
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.sist.web.entity.*;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;

import com.sist.web.dao.*;
// Model => 요청 처리
@Controller
public class RecipeController {
	// 스프링이 클래스 관리자 => 메모리 할당 => 할당된 주소를 얻어 온다
	@Autowired
	private RecipeDAO dao;
	
	@Autowired
	private RecipeDetailDAO rDao;
	
	@GetMapping("/recipe/main")
	public String recipe_main(String page,Model model) {
		// Model => HttpServletRequest를 대체 => 전송 객체
		
		if(page==null) {
			page="1";
		}
		int curpage=Integer.parseInt(page);
		int rowSize=20;
		int start=(rowSize*curpage)-rowSize;
		List<Recipe2> list=dao.recipeListData(start);
		int count=dao.recipeRowCount();
		int totalpage=(int)(Math.ceil(count/20.0));
		
		final int BLOCK=10;
		int startPage=((curpage-1)/BLOCK*BLOCK)+1;
		int endPage=((curpage-1)/BLOCK*BLOCK)+BLOCK;
		
		if(endPage>totalpage)
			endPage=totalpage;

		model.addAttribute("curpage", curpage);
		model.addAttribute("totalpage", totalpage);
		model.addAttribute("startPage", startPage);
		model.addAttribute("endPage", endPage);
		model.addAttribute("count", count);
		model.addAttribute("list", list);
		
		
		model.addAttribute("main_html","recipe/main");
		
		return "main";
	}
	
	@GetMapping("/recipe/before_detail")
	public String recipe_before(int no,RedirectAttributes ra,HttpServletResponse response) {
		
		// 쿠키에 저장
		Cookie cookie=new Cookie("recipe"+no, String.valueOf(no));
		// cookie는 저장시에 문자열만 저장이 가능
		cookie.setPath("/");
		cookie.setMaxAge(60*60*24);
		response.addCookie(cookie); // 클라이언트 브라우저로 전송
		
		ra.addAttribute("no",no);
		return "redirect:../recipe/detail";
		/*
		 *		RedirectAttributes sendRedirect을 이용해서 데이터 전송 
		 *		Model : forward 
		 */
	}
	
	@GetMapping("/recipe/detail")
	public String recipe_detail(int no,Model model) {
		
		Recipedetail vo=rDao.findByNo(no);
		List<String> pList=new ArrayList<String>();
		List<String> iList=new ArrayList<String>();
		/*
		String fm=vo.getFoodmake();
		String[] fms=fm.split("\n");
		for(String s:fms) {
			StringTokenizer st=new StringTokenizer(s,"^");
			pList.add(st.nextToken());
			iList.add(st.nextToken());
		}
		
		String ss=vo.getStuff();
		String[] stus=ss.split(",");
		List<String> sList=new ArrayList<String>();
		for(String s:stus) {
			sList.add(s.replace("구매", "").trim());
		} */
		
		//model.addAttribute("sList",sList);
		model.addAttribute("vo",vo);
		model.addAttribute("pList",pList);
		model.addAttribute("iList",iList);
		
		model.addAttribute("main_html","recipe/detail");
		
		return "main";
	}
	
}
