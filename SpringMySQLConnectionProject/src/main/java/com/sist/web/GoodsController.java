package com.sist.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.*;
import com.sist.vo.*;
import com.sist.dao.*;

@Controller
public class GoodsController {
	@Autowired
	private GoodsDAO dao;
	
	@GetMapping("goods/list.do")
	public String goods_list(String page,Model model) {
		// Model 객체 => jsp로 값을 전송할 때 사용 (전송객체)
		if(page==null) {
			page="1";
		}
		int curpage=Integer.parseInt(page);
		int rowSize=20;
		int start=(rowSize*curpage)-rowSize; // MySQL의 LIMIT은 0부터 시작 
		// LIMIT => 0번부터 시작, rownum => 1번부터
		List<GoodsVO> list=dao.goodsListData(start);
		int totalpage=dao.goodsTotalPage();
		// JSP에서 출력할 데이터 전송
		model.addAttribute("list",list);
		model.addAttribute("totalpage",totalpage);
		model.addAttribute("curpage",curpage);
		
		return "goods/list";
	}
}
