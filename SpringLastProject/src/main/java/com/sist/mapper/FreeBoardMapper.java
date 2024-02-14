package com.sist.mapper;
import java.util.*;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.sist.vo.*;
public interface FreeBoardMapper {
	// 목록 출력
	@Select("SELECT no,name,subject,hit,TO_CHAR(regdate,'YYYY-MM-DD') as dbday,num "
			+ "FROM (SELECT no,name,subject,hit,regdate,rownum as num "
			+ "FROM (SELECT /*+ INDEX_DESC(projectFreeBoard pfb_no_pk) */ no,name,subject,hit,regdate "
			+ "FROM projectFreeBoard)) "
			+ "WHERE num BETWEEN #{start} AND #{end}")
	public List<FreeBoardVO> freeBoardListData(@Param("start") int start,@Param("end") int end);
	
	// 총페이지
	@Select("SELECT CEIL(COUNT(*)/10.0) FROM projectFreeBoard")
	public int freeBoardTotalPage();
	
	// 추가
	@Insert("INSERT INTO projectFreeBoard(no,name,subject,content,pwd) "
			+ "VALUES(pfb_no_seq.nextval,#{name},#{subject},#{content},#{pwd})")
	public void freeBoardInsert(FreeBoardVO vo);
	
	// 상세보기
	@Update("UPDATE projectFreeBoard SET "
			+ "hit=hit+1 "
			+ "WHERE no=#{no}")
	public void hitIncrement(int no);
	
	@Select("SELECT no,name,subject,content,hit,TO_CHAR(regdate,'YYYY-MM-DD HH24:MI:SS') as dbday "
			+ "FROM projectFreeBoard "
			+ "WHERE no=#{no}")
	public FreeBoardVO freeboardDetailData(int no);
	
	// 수정
	@Update("UPDATE projectFreeBoard SET "
			+ "name=#{name},subject=#{subject},content=#{content} "
			+ "WHERE no=#{no}")
	public void freeboardUpdate(FreeBoardVO vo);
	
	// 삭제
	@Select("SELECT pwd FROM projectFreeBoard "
			+ "WHERE no=#{no}")
	public String freeboardGetPassword(int no);
	
	@Delete("DELETE FROM projectFreeBoard "
			+ "WHERE no=#{no}")
	public void freeboardDelete(int no);
}







