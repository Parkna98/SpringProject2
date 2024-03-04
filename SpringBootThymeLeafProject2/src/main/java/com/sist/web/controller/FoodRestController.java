package com.sist.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.*;
import com.sist.web.service.*;
import com.sist.web.vo.*;

// Vue3, ReactJS, NextJS(Vue+라이브러리) => TypeScript

@RestController
@CrossOrigin("http://localhost:3000")
public class FoodRestController {
	@Autowired
	private FoodService fService;
	
	// react에서는 value나 produces가 자동처리되기때문에 쓰지않는다
	@GetMapping("/food/list_react")
	public List<FoodVO> food_list(int page){
		int rowSize=12;
		int start=(rowSize*page)-rowSize;
		List<FoodVO> list=fService.foodListData(start);
		return list;
	}
	
	@GetMapping("/food/page_react")
	public Map food_page(int page) {
		int totalpage=fService.foodTotalPage();
		final int BLOCK=10;
		int startPage=((page-1)/BLOCK*BLOCK)+1;
		int endPage=((page-1)/BLOCK*BLOCK)+BLOCK;
		if(endPage>totalpage)
			endPage=totalpage;
		
		Map map=new HashMap();
		map.put("curpage", page);
		map.put("totalpage", totalpage);
		map.put("startPage", startPage);
		map.put("endPage", endPage);
		
		return map;
		
	}
}
