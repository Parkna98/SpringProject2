package com.sist.dao;
import java.util.*;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sist.mapper.*;
import com.sist.vo.FreeBoardVO;
@Repository
public class FreeBoardDAO {
	@Autowired
	private FreeBoardMapper mapper;
	
	public List<FreeBoardVO> freeBoardListData(int start,int end){
		return mapper.freeBoardListData(start, end);
	}
	
	public int freeBoardTotalPage() {
		return mapper.freeBoardTotalPage();
	}
	
	public void freeBoardInsert(FreeBoardVO vo) {
		mapper.freeBoardInsert(vo);
	}
	
	public FreeBoardVO freeboardDetailData(int no) {
		mapper.hitIncrement(no);
		return mapper.freeboardDetailData(no);
	}
	
	
}
