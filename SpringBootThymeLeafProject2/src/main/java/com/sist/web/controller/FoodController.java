package com.sist.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.*;
import com.sist.web.vo.*;
import com.sist.web.service.*;

@Controller
public class FoodController {
	@Autowired // 자동 주입 (주소값) => 객체 주소를 받는 경우 => Spring => 클래스 관리자
	private FoodService fService; // FoodService f=new FoodServiceImpl()
	
	@GetMapping("food/list")
	public String food_list(String page,Model model) {
		if(page==null) {
			page="1";
		}
		int curpage=Integer.parseInt(page);
		int rowSize=12;
		int start=(rowSize*curpage)-rowSize;
		List<FoodVO> list=fService.foodListData(start);
		int totalpage=fService.foodTotalPage();
		
		final int BLOCK=10;
		int startPage=((curpage-1)/BLOCK*BLOCK)+1;
		int endPage=((curpage-1)/BLOCK*BLOCK)+BLOCK;
		
		if(endPage>totalpage)
			endPage=totalpage;
		
		// HTML로 전송
		model.addAttribute("list",list);
		model.addAttribute("curpage",curpage);
		model.addAttribute("totalpage",totalpage);
		model.addAttribute("startPage",startPage);
		model.addAttribute("endPage",endPage);
		
		return "food/list";
	}
	
	// framework에서는 web.xml에 dispatcherServlet에 설정했지만
	// boot에서는 이미 세팅이 된 상태이다.
	@GetMapping("food/detail")
	public String food_detail(int fno,Model model) {
		
		FoodVO vo=fService.foodDetailData(fno);
		model.addAttribute("vo",vo);
		return "food/detail";
	}
	
}
