package com.sist.dao;
import java.util.*;

import org.apache.ibatis.annotations.Select;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sist.vo.*;
import com.sist.mapper.*;

@Repository
public class GoodsDAO {
	@Autowired
	private GoodsMapper mapper;
	
	/*@Select("SELECT no,goods_price,goods_name,goods_poster "
			+ "FROM goods_all "
			+ "ORDER BY no ASC "
			+ "LIMIT #{start},20")*/
	public List<GoodsVO> goodsListData(int start){
		return mapper.goodsListData(start);
	}
	
	// @Select("SELECT CEIL(COUNT(*)/20.0) FROM goods_all")
	public int goodsTotalPage() {
		return mapper.goodsTotalPage();
	}
}
