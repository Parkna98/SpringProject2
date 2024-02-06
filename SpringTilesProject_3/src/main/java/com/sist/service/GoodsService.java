package com.sist.service;
import java.util.*;
import com.sist.vo.*;

public interface GoodsService {
	public List<GoodsVO> goodsListData(Map map);
	public int goodsTotalPage(Map map);
	public GoodsVO goodsDetailData(Map map);
}
