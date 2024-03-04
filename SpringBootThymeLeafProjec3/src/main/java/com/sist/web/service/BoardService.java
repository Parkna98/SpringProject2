package com.sist.web.service;

import java.util.*;
import com.sist.web.entitiy.*;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface BoardService extends JpaRepository<BoardEntity,Integer>{
	// 상세보기
	public BoardEntity findByNo(int no);
	// SELECT * FROM jpaBoard WHERE no=1
	// insert, update, delete => 자동
	// ------------ save, delete
	// 목록 출력
	@Query(value = "SELECT * FROM jpaBoard ORDER BY no DESC LIMIT :start,10",nativeQuery = true)
	public List<BoardEntity> boardListData(@Param("start") Integer start);
	// 총페이지
	@Query(value = "SELECT CEIL(COUNT(*)/10.0) FROM jpaBoard",nativeQuery = true)
	public int boardTotalPage();
	
}
