package com.sist.mapper;
/*
 	3차플젝
 	= 자바 (jdk => 17이상) => 3.0 (톰캣 10)
 	= STS
 	= WebStorm (React 편집기)
 	= MySQL
 	= NodeJS
 	= create-react-app
 	
 	파이썬
 	= 파이썬
 	= 파이참
 	= 아나콘다
 */
import java.util.*;

import org.apache.ibatis.annotations.Select;

import com.sist.vo.*;
public interface GoodsMapper {
	@Select("SELECT no,goods_price,goods_name,goods_poster "
			+ "FROM goods_all "
			+ "ORDER BY no ASC "
			+ "LIMIT #{start},20")
	public List<GoodsVO> goodsListData(int start);
	
	@Select("SELECT CEIL(COUNT(*)/20.0) FROM goods_all")
	public int goodsTotalPage();
	
}
