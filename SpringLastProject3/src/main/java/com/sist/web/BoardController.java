package com.sist.web;

import java.util.*;
import com.sist.vo.*;
import com.sist.manager.*;
import com.sist.service.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("freeboard/")
public class BoardController {
	@Autowired
	private WordManager mgr;
	
	@Autowired
	private FreeBoardService service;
	
	@GetMapping("list.do")
	public String freeboard_list() {
		return "freeboard/list";
	}
	
	@GetMapping("insert.do")
	public String freeboard_insert() {
		return "freeboard/insert";
	}
	
	@GetMapping("detail.do")
	public String freeboard_detail(int no,Model model) {
		FreeBoardVO vo=service.freeboardUpData(no);
		List<WordVO> list=mgr.wordListData(vo.getContent());
		model.addAttribute("list",list);
		model.addAttribute("no",no);
		return "freeboard/detail";
	}
	
	@GetMapping("update.do")
	public String freeboard_update(int no,Model model) {
		model.addAttribute("no",no);
		return "freeboard/update";
	}
}
