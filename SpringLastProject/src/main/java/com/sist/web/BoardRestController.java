package com.sist.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.*;
import com.sist.vo.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sist.service.*;
@RestController
@RequestMapping("freeboard/")
public class BoardRestController {
	@Autowired
	private FreeBoardService service;
	
	@GetMapping(value="list_vue.do",produces = "text/plain;charset=UTF-8")
	public String freeBoard_list(int page) throws JsonProcessingException{
		int rowSize=10;
		int start=(rowSize*page)-(rowSize-1);
		int end=rowSize*page;
		List<FreeBoardVO> list=service.freeBoardListData(start, end);
		ObjectMapper mapper=new ObjectMapper();
		String json=mapper.writeValueAsString(list);
		return json;
	}
	
	@GetMapping(value="page_vue.do",produces = "text/plain;charset=UTF-8")
	public String freeBoard_page_list(int page) throws JsonProcessingException{
		int totalpage=service.freeBoardTotalPage();
		Map map=new HashMap();
		map.put("curpage", page);
		map.put("totalpage", totalpage);
		
		ObjectMapper mapper=new ObjectMapper();
		String json=mapper.writeValueAsString(map);
		
		return json;
	}
	
	@PostMapping(value="insert_vue.do",produces = "text/plain;charset=UTF-8")
	public String freeBoard_insert(FreeBoardVO vo) {
		String result="";
		try {
			service.freeBoardInsert(vo);
			result="yes";
		}catch(Exception ex) {
			result=ex.getMessage();
		}
		return result;
	}
	
	@GetMapping(value="detail_vue.do",produces = "text/plain;charset=UTF-8")
	public String freeBoard_detail_vue(int no) {
		FreeBoardVO vo=service.freeboardDetailData(no);
		return "";
	}
}
