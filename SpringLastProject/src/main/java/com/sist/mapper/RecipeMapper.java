package com.sist.mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import java.util.*;
import com.sist.vo.*;
/*
CNO       NOT NULL NUMBER        
CHEF      NOT NULL VARCHAR2(100) 
POSTER    NOT NULL VARCHAR2(500) 
MEM_CONT1          NUMBER        
MEM_CONT2          NUMBER        
MEM_CONT3          NUMBER        
MEM_CONT7          NUMBER   
 */
/*
NO        NOT NULL NUMBER         
TITLE     NOT NULL VARCHAR2(2000) 
POSTER    NOT NULL VARCHAR2(500)  
CHEF      NOT NULL VARCHAR2(200)  
LINK      NOT NULL VARCHAR2(300)  
JJIMCOUNT          NUMBER         
HIT                NUMBER    
 */
public interface RecipeMapper {
	@Select("SELECT cno,chef,poster,rownum "
			+ "FROM (SELECT cno,chef,poster "
			+ "FROM chef ORDER BY mem_cont7 DESC) "
			+ "WHERE rownum<=12")
	public List<ChefVO> chefTop12();
	
	@Select("SELECT no,title,poster,rownum "
			+ "FROM (SELECT no,title,poster "
			+ "FROM recipe ORDER BY hit DESC) "
			+ "WHERE rownum<=12")
	public List<RecipeVO> recipeTop12();
	
	// 목록
	@Select("SELECT COUNT(*) FROM recipe "
			+ "WHERE no IN(SELECT no FROM recipedetail)")
	public int recipeCount();
	
	@Select("SELECT no,title,poster,num "
			+ "FROM (SELECT no,title,poster,rownum as num "
			+ "FROM (SELECT /*+ INDEX_ASC(recipe recipe_no_pk)*/ no,title,poster "
			+ "FROM recipe WHERE no IN(SELECT no FROM recipedetail))) "
			+ "WHERE num BETWEEN #{start} AND #{end}")
	public List<RecipeVO> recipeListData(@Param("start") int start, @Param("end") int end);
	
	@Select("SELECT CEIL(COUNT(*)/20.0) FROM recipe WHERE no IN(SELECT no FROM recipedetail)")
	public int recipeTotalPage();
	
	// 상세보기
	@Select("SELECT * FROM recipedetail "
			+ "WHERE no=#{no}")
	public RecipeDetailVO recipeDetailData(int no);
	
	
	// 쉐프
	@Select("SELECT cno,chef,poster,mem_cont1,mem_cont2,mem_cont3,mem_cont7,num "
			+ "FROM (SELECT cno,chef,poster,mem_cont1,mem_cont2,mem_cont3,mem_cont7,rownum as num "
			+ "FROM (SELECT /*+ INDEX_ASC(recipe recipe_no_pk)*/ cno,chef,poster,mem_cont1,mem_cont2,mem_cont3,mem_cont7 "
			+ "FROM chef)) "
			+ "WHERE num BETWEEN #{start} AND #{end}")
	public List<ChefVO> chefListData(@Param("start") int start, @Param("end") int end);
	
	@Select("SELECT CEIL(COUNT(*)/20.0) FROM chef")
	public int chefTotalPage();
	
	// 쉐프별 레시피
	@Select("SELECT no,title,poster,chef,num "
			+ "FROM (SELECT no,title,poster,chef,rownum as num "
			+ "FROM (SELECT no,title,poster,chef "
			+ "FROM recipe WHERE chef=(SELECT chef FROM chef WHERE cno=#{cno}))) "
			+ "WHERE num BETWEEN #{start} AND #{end}")
	public List<RecipeVO> chefDetailData(Map map);
	
	@Select("SELECT CEIL(COUNT(*)/20.0) FROM recipe "
			+ "WHERE chef=(SELECT chef FROM chef WHERE cno=#{cno})")
	public int chefDetailTotalPage(int cno);
	
	@Select("SELECT no,title,poster,chef,num "
			+ "FROM (SELECT no,title,poster,chef,rownum as num "
			+ "FROM (SELECT no,title,poster,chef "
			+ "FROM recipe WHERE chef=(SELECT chef FROM chef WHERE cno=#{cno}) AND title LIKE '%'||#{ss}||'%')) "
			+ "WHERE num BETWEEN #{start} AND #{end}")
	public List<RecipeVO> chefDetailFindData(Map map);
	
	@Select("SELECT CEIL(COUNT(*)/20.0) FROM recipe "
			+ "WHERE chef=(SELECT chef FROM chef WHERE cno=#{cno}) AND title LIKE '%'||#{ss}||'%'")
	public int chefDetailFindTotalPage(Map map);
	
	@Select("SELECT goods_poster,goods_name,goods_price "
			+ "FROM goods_all "
			+ "WHERE goods_name LIKE '%'||#{goods_name}||'%'")
	public List<GoodsVO> recipeGoodsData(String goods_name);
	
}
