package com.sist.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;
import java.util.*;
import com.sist.service.*;
import com.sist.vo.*;


@Controller
public class ChatController {
	@Autowired
	private MemberService service;
	
	@GetMapping("chat/chat.do")
	public String chat_chat(Model model,Principal p) {
		MemberVO vo=service.memberInfoData(p.getName());
		model.addAttribute("vo",vo);
		return "site/chat/chat"; // ViewResolver
	}
}


