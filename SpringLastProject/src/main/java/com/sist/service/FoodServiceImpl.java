package com.sist.service;
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sist.dao.*;
import com.sist.vo.*;
@Service
public class FoodServiceImpl implements FoodService{
	@Autowired
	private FoodDAO fDao;

	@Override
	public List<FoodVO> foodFindData(Map map) {
		// TODO Auto-generated method stub
		return fDao.foodFindData(map);
	}

	@Override
	public int foodFindCount(Map map) {
		// TODO Auto-generated method stub
		return fDao.foodFindCount(map);
	}
}
