package com.sist.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("freeboard/")
public class BoardController {
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
		model.addAttribute("no",no);
		return "freeboard/detail";
	}
}
