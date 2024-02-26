package com.sist.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sist.dao.*;
import com.sist.vo.*;

@RestController
@CrossOrigin("http://localhost:3000")
public class GoodsRestController {
	@Autowired
	private GoodsDAO gDao;
	
	@GetMapping(value = "goods/list_react.do",produces = "text/plain;charset=UTF-8")
	public String goods_list(int page) throws Exception{
		int rowSize=20;
		int start=(rowSize*page)-rowSize;
		List<GoodsVO> list=gDao.goodsListData(start);
		
		ObjectMapper mapper=new ObjectMapper();
		String json=mapper.writeValueAsString(list);
		return json;
	}
	
	@GetMapping(value = "goods/page_react.do",produces = "text/plain;charset=UTF-8")
	public String goods_page(int page) throws Exception{
		int totalpage=gDao.goodsTotalPage();
		Map map=new HashMap();
		map.put("curpage", page);
		map.put("totalpage", totalpage);
		ObjectMapper mapper=new ObjectMapper();
		String json=mapper.writeValueAsString(map);
		return json;
	}
}
