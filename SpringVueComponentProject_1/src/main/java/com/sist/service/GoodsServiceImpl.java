package com.sist.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sist.dao.GoodsDAO;
import com.sist.vo.GoodsVO;

@Service
public class GoodsServiceImpl implements GoodsService{
	@Autowired
	private GoodsDAO dao;

	@Override
	public List<GoodsVO> goodsListData(Map map) {
		// TODO Auto-generated method stub
		return dao.goodsListData(map);
	}

	@Override
	public int goodsTotalPage(Map map) {
		// TODO Auto-generated method stub
		return dao.goodsTotalPage(map);
	}

	@Override
	public GoodsVO goodsDetailData(Map map) {
		// TODO Auto-generated method stub
		return dao.goodsDetailData(map);
	}
	// 유지보수를 위한 service사용 
	// service를 한번 거쳐서가서 dao를 수정해도 controller에는 이상이없다
}
