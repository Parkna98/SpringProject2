package com.sist.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;
import com.sist.dao.*;
import com.sist.vo.ReplyVO;
@Service
public class ReplyServiceImpl implements ReplyService{
	@Autowired
	private ReplyDAO dao;

	@Override
	public List<ReplyVO> recipeReplyData(int rno) {
		// TODO Auto-generated method stub
		return dao.recipeReplyData(rno);
	}

	@Override
	public void replyInsert(ReplyVO vo) {
		// TODO Auto-generated method stub
		dao.replyInsert(vo);
	}

	@Override
	public void replyUpdate(ReplyVO vo) {
		// TODO Auto-generated method stub
		dao.replyUpdate(vo);
	}

	@Override
	public void replyDelete(int no) {
		// TODO Auto-generated method stub
		dao.replyDelete(no);
	}
}
