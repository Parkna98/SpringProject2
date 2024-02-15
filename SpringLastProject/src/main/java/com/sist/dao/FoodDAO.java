package com.sist.dao;
import java.util.*;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sist.mapper.*;
import com.sist.vo.*;
@Repository
public class FoodDAO {
	@Autowired
	private FoodMapper mapper;
	
	public List<FoodVO> foodFindData(Map map){
		return mapper.foodFindData(map);
	}
	
	public int foodFindCount(Map map) {
		return mapper.foodFindCount(map);
	}
	
	public FoodVO foodDetailData(int fno) {
		return mapper.foodDetailData(fno);
	}
	
	public List<FoodVO> foodListData(int start, int end){
		return mapper.foodListData(start, end);
	}
	
	public int foodListTotalPage() {
		return mapper.foodListTotalPage();
	}
	
	public FoodVO foodDetailInfoData(int fno) {
		mapper.foodHitIncrement(fno);
		return mapper.foodDetailInfoData(fno);
	}
	
	public List<FoodVO> foodListTop7(){
		return mapper.foodListTop7();
	}
	
	public List<FoodVO> foodHomeTop12(){
		return mapper.foodHomeTop12();
	}
}
