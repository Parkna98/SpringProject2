package com.sist.service;

import java.util.List;
import java.util.Map;

import com.sist.vo.FoodVO;
import com.sist.vo.NoticeVO;

public interface FoodService {
	public List<FoodVO> foodFindData(Map map);
	public int foodFindCount(Map map);
	public FoodVO foodDetailData(int fno);
	public List<FoodVO> foodListData(int start, int end);
	public int foodListTotalPage();
	public FoodVO foodDetailInfoData(int fno);
	public List<NoticeVO> noticeTop7();
	public List<FoodVO> foodListTop7();
	// recipe
}
