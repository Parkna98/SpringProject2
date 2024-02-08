package com.sist.web;

import org.apache.commons.collections.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.*;
import com.sist.vo.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sist.service.*;

@RestController
@RequestMapping("food/")
public class FoodRestController {
	@Autowired
	private FoodService service;
	
	@GetMapping(value="find_vue.do",produces = "text/plain;charset=UTF-8")
	public String food_find_vue(int page,String fd) throws JsonProcessingException{
		
		int rowSize=20;
		int start=(rowSize*page)-(rowSize-1);
		int end=(rowSize*page);
		Map map=new HashMap();
		map.put("start", start);
		map.put("end", end);
		map.put("address", fd);
		List<FoodVO> list=service.foodFindData(map);
		
		ObjectMapper mapper=new ObjectMapper();
		String json=mapper.writeValueAsString(list); 
		
		return json;
	}
	
	@GetMapping(value="page_vue.do",produces = "text/plain;charset=UTF-8")
	public String food_page_vue(int page,String fd) throws JsonProcessingException {
		
		final int BLOCK=10;
		int startPage=((page-1)/BLOCK*BLOCK)+1;
		int endPage=((page-1)/BLOCK*BLOCK)+BLOCK;
		Map map=new HashMap();
		map.put("address", fd);
		int totalpage=service.foodFindCount(map);
		if(endPage>totalpage)
			endPage=totalpage;
		
		map=new HashMap();
		map.put("startPage", startPage);
		map.put("endPage", endPage);
		map.put("totalpage", totalpage);
		map.put("curpage", page);
		
		ObjectMapper mapper=new ObjectMapper();
		String json=mapper.writeValueAsString(map);
		
		return json;
	}
	
}
