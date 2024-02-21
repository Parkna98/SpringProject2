package com.sist.dao;
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sist.vo.*;
import com.sist.mapper.*;
@Repository
public class ReplyDAO {
	@Autowired
	private ReplyMapper mapper;
	
	public List<ReplyVO> recipeReplyData(int rno){
		return mapper.recipeReplyData(rno);
	}
	public void replyInsert(ReplyVO vo) {
		mapper.replyInsert(vo);
	}
	public void replyUpdate(ReplyVO vo) {
		mapper.replyUpdate(vo);
	}
	public void replyDelete(int no) {
		mapper.replyDelete(no);
	}
	public MemberVO memberInfoData(String userId) {
		return mapper.memberInfoData(userId);
	}
}
