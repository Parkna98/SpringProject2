package com.sist.dao;
import java.util.*;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sist.mapper.*;
import com.sist.vo.*;
@Repository
public class RecipeDAO {
	@Autowired
	private RecipeMapper mapper;
	
	public List<ChefVO> chefTop12(){
		return mapper.chefTop12();
	}
	
	public List<RecipeVO> recipeTop12(){
		return mapper.recipeTop12();
	}
	
	public int recipeCount() {
		return mapper.recipeCount();
	}
	public List<RecipeVO> recipeListData(int start, int end){
		return mapper.recipeListData(start, end);
	}
	public int recipeTotalPage() {
		return mapper.recipeTotalPage();
	}
	public List<ChefVO> chefListData(int start,int end){
		return mapper.chefListData(start, end);
	}
	public int chefTotalPage() {
		return mapper.chefTotalPage();
	}
	
	public List<RecipeVO> chefDetailData(Map map){
		return mapper.chefDetailData(map);
	}
	
	public int chefDetailTotalPage(int cno) {
		return mapper.chefDetailTotalPage(cno);
	}
	public List<RecipeVO> chefDetailFindData(Map map){
		return mapper.chefDetailFindData(map);
	}
	public int chefDetailFindTotalPage(Map map) {
		return mapper.chefDetailFindTotalPage(map);
	}
}
