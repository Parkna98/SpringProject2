package com.sist.mapper;
import java.util.*;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.sist.vo.*;
public interface FoodMapper {
	@Select("SELECT fno,poster,name,num "
			+ "FROM (SELECT fno,poster,name,rownum as num "
			+ "FROM (SELECT fno,poster,name "
			+ "FROM food_menu_house "
			+ "WHERE address LIKE '%'||#{address}||'%' "
			+ "ORDER BY fno ASC)) "
			+ "WHERE num BETWEEN #{start} AND #{end}")
	public List<FoodVO> foodFindData(Map map);
	
	@Select("SELECT CEIL(COUNT(*)/20.0) FROM food_menu_house "
			+ "WHERE address LIKE '%'||#{address}||'%'")
	public int foodFindCount(Map map);
	
	@Select("SELECT fno,score,poster,name,type,address,phone,theme,price,time,seat "
			+ "FROM food_menu_house "
			+ "WHERE fno=#{fno}")
	public FoodVO foodDetailData(int fno);
	
	@Update("UPDATE food_menu_house SET "
			+ "hit=hit+1 "
			+ "WHERE fno=#{fno}")
	public void foodHitIncrement(int fno);
	
	@Select("SELECT fno,score,poster,name,num "
			+ "FROM (SELECT fno,score,poster,name,rownum as num "
			+ "FROM (SELECT fno,score,poster,name "
			+ "FROM food_menu_house ORDER BY fno ASC)) "
			+ "WHERE num BETWEEN #{start} AND #{end}")
	public List<FoodVO> foodListData(@Param("start") int start, @Param("end") int end);
	
	@Select("SELECT CEIL(COUNT(*)/20.0) FROM food_menu_house")
	public int foodListTotalPage();
	

	@Select("SELECT fno,score,poster,name,type,address,phone,theme,price,time,seat,hit,like_count,jjimcount "
			+ "FROM food_menu_house "
			+ "WHERE fno=#{fno}")
	public FoodVO foodDetailInfoData(int fno);
	
	@Select("SELECT fno,name,rownum "
			+ "FROM (SELECT fno,name FROM food_menu_house "
			+ "ORDER BY hit DESC) "
			+ "WHERE rownum<=7")
	public List<FoodVO> foodListTop7();
	
	@Select("SELECT fno,name,poster,rownum "
			+ "FROM (SELECT fno,name,poster FROM food_menu_house "
			+ "ORDER BY hit DESC) "
			+ "WHERE rownum<=12")
	public List<FoodVO> foodHomeTop12();
	
	@Select("SELECT name FROM food_menu_house "
			+ "WHERE length(name)>1 ORDER BY fno ASC")
	public List<String> foodAllData();
	
	@Select("SELECT fno,name,poster "
			+ "FROM food_menu_house "
			+ "WHERE name=#{name}")
	public FoodVO foodNameInfoData(String name);
	
	@Select("SELECT no,title,poster,rownum "
			+ "FROM recipe "
			+ "WHERE no IN(SELECT no FROM recipe "
			+ "INTERSECT SELECT no FROM recipeDetail) "
			+ "AND REGEXP_LIKE(title,#{type}) "
			+ "AND rownum<=12")
	public List<RecipeVO> foodRecipeData(String type);
	
}
