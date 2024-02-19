package com.sist.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.*;

import javax.servlet.http.HttpSession;

import com.sist.vo.*;
import com.sist.service.*;

@RestController
public class MemberRestController {
	@Autowired
	private MemberService service;
	
	@GetMapping(value = "member/idcheck_vue.do", produces = "text/plain;charset=UTF-8")
	public String member_idcheck(String userId) {
		int count=service.memberIdCount(userId);
		return String.valueOf(count);
	}
	
	@GetMapping(value = "member/login_ok_vue.do",produces="text/plain;charset=UTF-8")
	public String member_login_ok(String userId,String userPwd,boolean ck,HttpSession session) {
		return "";
		
	}
//		MemberVO vo=service.equals("OK");


}
