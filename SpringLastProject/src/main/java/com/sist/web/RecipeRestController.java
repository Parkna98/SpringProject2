package com.sist.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.*;
import com.sist.vo.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sist.service.*;

@RestController // 다른언어와 연동 (js,kotlin...) => XML,JSON은 모든 언어에 호환되기때문에 이 형식으로 보낸다
public class RecipeRestController {
	@Autowired
	private RecipeService rService;
	
	// Vue,React에서 요청 ==> 자바스크립트가 인식하는 데이터로 변경후에 전송
	//										  ========== JSON : JavaScript Object Notation
	
	@GetMapping(value = "recipe/recipe_list_vue.do",produces = "text/plain;charset=UTF-8")
	public String recipe_list_vue(int page) throws Exception{
		int rowSize=20;
		int start=(rowSize*page)-(rowSize-1);
		int end=rowSize*page;
		
		List<RecipeVO> list=rService.recipeListData(start, end);
		ObjectMapper mapper=new ObjectMapper();
		String json=mapper.writeValueAsString(list);
		
		return json;
	}
	
	
	
	@GetMapping(value="recipe/recipe_page_vue.do",produces = "text/plain;charset=UTF-8")
	public String recipe_page_vue(int page) throws Exception{
		final int BLOCK=10;
		int startPage=((page-1)/BLOCK*BLOCK)+1;
		int endPage=((page-1)/BLOCK*BLOCK)+BLOCK;
		int totalpage=rService.recipeTotalPage();
		if(endPage>totalpage)
			endPage=totalpage;
		int count=rService.recipeCount();
		
		
		Map map=new HashMap();
		map.put("startPage", startPage);
		map.put("endPage", endPage);
		map.put("totalpage", totalpage);
		map.put("curpage", page);
		map.put("count", count);
		
		ObjectMapper mapper=new ObjectMapper();
		String json=mapper.writeValueAsString(map);
		
		return json;
		
	}
	
	@GetMapping(value = "recipe/chef_list_vue.do",produces = "text/plain;charset=UTF-8")
	public String chef_list_vue(int page) throws JsonProcessingException{
		int rowSize=20;
		int start=(rowSize*page)-(rowSize-1);
		int end=rowSize*page;
		
		List<ChefVO> list=rService.chefListData(start, end);
		ObjectMapper mapper=new ObjectMapper();
		String json=mapper.writeValueAsString(list);
		
		return json;
	}
	
	@GetMapping(value="recipe/chef_page_vue.do",produces = "text/plain;charset=UTF-8")
	public String chef_page_vue(int page) throws Exception{
		final int BLOCK=10;
		int startPage=((page-1)/BLOCK*BLOCK)+1;
		int endPage=((page-1)/BLOCK*BLOCK)+BLOCK;
		int totalpage=rService.chefTotalPage();
		if(endPage>totalpage)
			endPage=totalpage;
		
		Map map=new HashMap();
		map.put("startPage", startPage);
		map.put("endPage", endPage);
		map.put("totalpage", totalpage);
		map.put("curpage", page);
		
		ObjectMapper mapper=new ObjectMapper();
		String json=mapper.writeValueAsString(map);
		
		return json;
		
	}
	
	// chef => 제작한 레시피 출력
	@GetMapping(value = "recipe/chef_detail_vue.do",produces = "text/plain;charset=UTF-8")
	public String chef_detail_vue(int page,int cno,String ss) throws JsonProcessingException {
		int rowSize=20;
		int start=(rowSize*page)-(rowSize-1);
		int end=rowSize*page;
		
		Map map=new HashMap();
		map.put("start", start);
		map.put("end", end);
		map.put("cno", cno);
		List<RecipeVO> list=new ArrayList<RecipeVO>();
		if(ss==null) {
			list=rService.chefDetailData(map);
		}else {
			map.put("ss", ss);
			list=rService.chefDetailFindData(map);
		}
		ObjectMapper mapper=new ObjectMapper();
		String json=mapper.writeValueAsString(list);
		
		return json;
		
	}
	
	@GetMapping(value="recipe/chef_detail_page_vue.do",produces = "text/plain;charset=UTF-8")
	public String chef_detail_page_vue(int page,int cno,String ss) throws Exception{
		final int BLOCK=10;
		int startPage=((page-1)/BLOCK*BLOCK)+1;
		int endPage=((page-1)/BLOCK*BLOCK)+BLOCK;
		int totalpage=0;
		if(ss==null) {
			totalpage=rService.chefDetailTotalPage(cno);
		}else {
			Map map=new HashMap();
			map.put("ss", ss);
			map.put("cno", cno);
			totalpage=rService.chefDetailFindTotalPage(map);
		}
		
		if(endPage>totalpage)
			endPage=totalpage;
		
		Map map=new HashMap();
		map.put("startPage", startPage);
		map.put("endPage", endPage);
		map.put("totalpage", totalpage);
		map.put("curpage", page);
		
		ObjectMapper mapper=new ObjectMapper();
		String json=mapper.writeValueAsString(map);
		
		return json;
		
	}
	
	@GetMapping(value="recipe/recipe_test_vue.do",produces = "text/plain;charset=UTF-8")
	public String recipe_test_vue(int page) throws Exception {
		int rowSize=20;
		int start=(rowSize*page)-(rowSize-1);
		int end=rowSize*page;
		
		List<RecipeVO> list=rService.recipeListData(start, end);
		
		final int BLOCK=10;
		int startPage=((page-1)/BLOCK*BLOCK)+1;
		int endPage=((page-1)/BLOCK*BLOCK)+BLOCK;
		int totalpage=rService.recipeTotalPage();
		if(endPage>totalpage)
			endPage=totalpage;
		int count=rService.recipeCount();
		
		
		Map map=new HashMap();
		map.put("startPage", startPage);
		map.put("endPage", endPage);
		map.put("totalpage", totalpage);
		map.put("curpage", page);
		map.put("count", count);
		
		Map tMap=new HashMap();
		tMap.put("list", list);
		tMap.put("pages", map);
		
		ObjectMapper mapper=new ObjectMapper();
		String json=mapper.writeValueAsString(tMap);
		
		return json;
	}
	
}
