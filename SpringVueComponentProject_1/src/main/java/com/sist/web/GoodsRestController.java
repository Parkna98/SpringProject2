package com.sist.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sist.service.GoodsService;
import com.sist.vo.GoodsVO;

@RestController
public class GoodsRestController {
	@Autowired
	private GoodsService service;
	
private String[] tables= {"","goods_all","goods_best","goods_special","goods_new"};
	
	@GetMapping(value="goods/list_vue.do",produces = "text/plain;charset=UTF-8")
	public String goods_main(int page,int typeno) throws JsonProcessingException {
		
		int rowSize=12;
		int start=(rowSize*page)-(rowSize-1);
		int end=(rowSize*page);
		
		
		Map map=new HashMap();
		map.put("table_name", tables[typeno]);
		map.put("start", start);
		map.put("end", end);
		
		List<GoodsVO> list=service.goodsListData(map);
		
		ObjectMapper mapper=new ObjectMapper();
		String json=mapper.writeValueAsString(list);
		
		return json;
		
	}
	
	@GetMapping("goods/page_vue.do")
	public String goods_page(int page, int typeno) throws JsonProcessingException {
		Map map=new HashMap();
		map.put("table_name", tables[typeno]);
		
		int totalpage=service.goodsTotalPage(map);
		final int BLOCK=10;
		int startPage=((page-1)/BLOCK*BLOCK)+1;
		int endPage=((page-1)/BLOCK*BLOCK)+BLOCK;
		if(endPage>totalpage) {
			endPage=totalpage;
		}
		
		map.put("curpage", page);
		map.put("startPage", startPage);
		map.put("endPage", endPage);
		map.put("totalpage", totalpage);
		
		ObjectMapper mapper=new ObjectMapper();
		String json=mapper.writeValueAsString(map);
		
		return json;
	}
}
