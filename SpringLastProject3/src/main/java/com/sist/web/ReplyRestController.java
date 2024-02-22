package com.sist.web;

import org.apache.ibatis.annotations.Insert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sist.service.ReplyService;
import com.sist.vo.*;

import oracle.jdbc.proxy.annotation.Post;

import java.security.Principal;
import java.util.*;

import javax.servlet.http.HttpSession;

@RestController
public class ReplyRestController {
	@Autowired
	private ReplyService rService;
	
	// insert,update,delete
	public String commonsReplyData(int rno) throws Exception{
		ObjectMapper mapper=new ObjectMapper();
		List<ReplyVO> list=rService.recipeReplyData(rno);
		String json=mapper.writeValueAsString(list);
		return json;
	}
	
	@PostMapping(value="recipe/reply_insert_vue.do",produces = "text/plain;charset=UTF-8")
	public String reply_insert(ReplyVO vo,Principal p) throws Exception{
		
		String userId=p.getName();
		MemberVO mvo=rService.memberInfoData(userId);
		String userName=mvo.getUserName();
		vo.setUserId(userId);
		vo.setUserName(userName);
		// 넘어오는 vo => rno,msg
		rService.replyInsert(vo);
		
		return commonsReplyData(vo.getRno());
	}
	
	// 수정
	@PostMapping(value = "recipe/reply_update_vue.do", produces = "text/plain;charset=UTF-8")
	public String reply_update(ReplyVO vo) throws Exception{
		
		rService.replyUpdate(vo);
		
		return commonsReplyData(vo.getRno());
	}
	
	
	// 삭제
	@GetMapping(value="recipe/reply_delete_vue.do",produces = "text/plain;charset=UTF-8")
	public String reply_delete(int no,int rno) throws Exception
	   {
		   rService.replyDelete(no);
		   return commonsReplyData(rno);
	   }
}

