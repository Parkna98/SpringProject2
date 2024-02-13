package com.sist.service;
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sist.dao.*;
import com.sist.vo.*;
@Service
public class FreeBoardServiceImpl implements FreeBoardService{
	@Autowired
	private FreeBoardDAO fDao;

	@Override
	public List<FreeBoardVO> freeBoardListData(int start, int end) {
		// TODO Auto-generated method stub
		return fDao.freeBoardListData(start, end);
	}

	@Override
	public int freeBoardTotalPage() {
		// TODO Auto-generated method stub
		return fDao.freeBoardTotalPage();
	}

	@Override
	public void freeBoardInsert(FreeBoardVO vo) {
		// TODO Auto-generated method stub
		fDao.freeBoardInsert(vo);
	}

	@Override
	public FreeBoardVO freeboardDetailData(int no) {
		// TODO Auto-generated method stub
		return fDao.freeboardDetailData(no);
	}
}
