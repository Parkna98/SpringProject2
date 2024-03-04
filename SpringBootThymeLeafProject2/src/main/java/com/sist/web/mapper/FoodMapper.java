package com.sist.web.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import java.util.*;
import com.sist.web.vo.*;


@Repository
@Mapper
public interface FoodMapper {
	/*
	 * <select id="foodListData" resultType="FoodVO" parameterType="int">
     	SELECT fno,poster,name,price 
	     FROM food_house
	     ORDER BY fno ASC
	     LIMIT #{start},12 
	   </select>
	  */
	//		 resultTYpe	 id			parameterType  => mapping
	public List<FoodVO> foodListData(int start);
	/*
	   <select id="foodTotalPage" resultType="int">
	     SELECT CEIL(COUNT(*)/12.0) FROM food_house
	   </select>
	 */
	public int foodTotalPage();
	/*
	 * <select id="foodDetailData" resultType="FoodVO" parameterType="int">
	 	 SELECT fno,name,CONCAT('http://www.menupan.com',poster) as poster,type,address,phone,theme,price,time,seat,score 
	 	 FROM food_house 
	 	 WHERE fno=#{fno}
	   </select>
	 */
	public FoodVO foodDetailData(int fno);
}
