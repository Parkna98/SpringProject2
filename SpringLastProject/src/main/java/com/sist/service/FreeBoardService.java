package com.sist.service;
import java.util.*;
import com.sist.vo.*;
public interface FreeBoardService {
	public List<FreeBoardVO> freeBoardListData(int start,int end);
	public int freeBoardTotalPage();
	public void freeBoardInsert(FreeBoardVO vo);
	public FreeBoardVO freeboardDetailData(int no);
	public String freeboardDelete(int no,String pwd);
	public FreeBoardVO freeboardUpData(int no);
	public String freeboardUpdate(FreeBoardVO vo);
}
