package com.sist.mapper;
import java.util.*;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.sist.vo.*;

import lombok.Delegate;

public interface ReserveMapper {
	@Select("SELECT fno,poster,name "
			+ "FROM food_menu_house "
			+ "WHERE type LIKE '%'||#{type}||'%'")
	public List<FoodVO> foodReserveData(String type); // 한식 중식 양식 일식
		
	
	@Insert("INSERT INTO reserve "
			+ "VALUES(re_rno_seq.nextval,#{fno},#{userId},#{rDate},#{rTime},#{rInwon},SYSDATE,0)")
	public void foodReserveInsert(ReserveVO vo);
	
	@Results({
		@Result(column="name",property="fvo.name"),
		@Result(column="poster",property = "fvo.poster")
	})
	@Select("SELECT rno,r.fno,name,poster,rDate,rTime,rInwon,reserve_ok,"
			+ "TO_CHAR(regdate,'YYYY-MM-DD HH24:MI:SS') as dbday " 
			+ "FROM reserve r, food_menu_house f "
			+ "WHERE r.fno=f.fno " 
			+ "AND userID=#{userId} "
			+ "ORDER BY rno DESC")
	public List<ReserveVO> reserveMypageData(String userId);
	
	@Delete("DELETE FROM reserve "
			+ "WHERE rno=#{rno}")
	public void reserveCancel(int rno);
	
	@Results({
		@Result(column="name",property="fvo.name"),
		@Result(column="poster",property = "fvo.poster")
	})
	@Select("SELECT rno,r.fno,name,poster,rDate,rTime,rInwon,reserve_ok,"
			+ "TO_CHAR(regdate,'YYYY-MM-DD HH24:MI:SS') as dbday " 
			+ "FROM reserve r, food_menu_house f "
			+ "WHERE r.fno=f.fno " 
			+ "ORDER BY rno DESC")
	public List<ReserveVO> reserveAdminpageData();
	
	@Update("UPDATE reserve SET "
			+ "reserve_ok=1 "
			+ "WHERE rno=#{rno}")
	public void reserveok(int rno);
	
	
	// 메일 전송용 예약내용 Select
	@Results({
		@Result(column="name",property="fvo.name"),
		@Result(column="poster",property = "fvo.poster")
	})
	@Select("SELECT rno,r.fno,userId,name,poster,rDate,rTime,rInwon,reserve_ok,"
			+ "TO_CHAR(regdate,'YYYY-MM-DD HH24:MI:SS') as dbday " 
			+ "FROM reserve r, food_menu_house f "
			+ "WHERE r.fno=f.fno "
			+ "AND rno=#{rno}")
	public ReserveVO reserveInfoData(int rno);
	
}
