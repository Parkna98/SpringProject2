package com.sist.web;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
// 다른 언어와 통신 (자바스크립트 == 자바)
//				  JSON
/*
 *  자바 => [] , {}
 *  자바스크립트 : 객체 (=> VO) 
 *  			배열 (=> List)
 *  			================= JSON (jackson이용해서 변환) 자동 변환
 *  			1) 사용법이 편하다 (소스가 간결하다) => 소스가 복잡하다 => 가독성이 높다
 *  			2) 가독성이 낮다
 *  			3) Spring-Boot
 *  			   public List<FoodVO> foodListData
 *  			{		
 *  				List<FoodVO> list=dao.listdata
 *  				return list;
 *  			}
 *  
 *  
 */

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sist.service.FoodService;
import java.util.*;
import com.sist.vo.*;
@RestController // React / Ajax / Vue => NodeJS
public class FoodRestController {
	@Autowired
	private FoodService service;
	
	@GetMapping(value="food/list_vue.do",produces = "text/plain;charset=UTF-8")
	public String food_list_vue(int page) throws Exception{
		int rowSize=12;
		int start=(page*rowSize)-(rowSize-1);
		int end=(page*rowSize);
		List<FoodVO> list=service.foodListData(start, end);
		
		/*
		JSONArray arr=new JSONArray(); // [] => List
		for(FoodVO vo:list) {
			JSONObject obj=new JSONObject(); // {} => VO
			obj.put("fno", vo.getFno());
			obj.put("poster", vo.getPoster());
			obj.put("name", vo.getName());
			arr.add(obj);
		}
		return arr.toJSONString();
		*/
		// 위의 과정을 만들어주는 메소드 => ObjectMapper.writeValueAsString(Object)
		ObjectMapper mapper=new ObjectMapper();
		String json=mapper.writeValueAsString(list);
		
		return json;
	}
	
	@GetMapping(value="food/page_vue.do",produces = "text/plain;charset=UTF-8")
	public String food_page_vue(int page) throws Exception {
		int totalpage=service.foodTotalPage();
		final int BLOCK=10;
		int startPage=((page-1)/BLOCK*BLOCK)+1;
		int endPage=((page-1)/BLOCK*BLOCK)+BLOCK;
		if(endPage>totalpage) {
			endPage=totalpage;
		}
			
		
		Map map=new HashMap();
		map.put("curpage", page);
		map.put("totalpage", totalpage);
		map.put("startPage", startPage);
		map.put("endPage", endPage);
		
		ObjectMapper mapper=new ObjectMapper();
		String json=mapper.writeValueAsString(map);
		
		return json;
	}
	
}
