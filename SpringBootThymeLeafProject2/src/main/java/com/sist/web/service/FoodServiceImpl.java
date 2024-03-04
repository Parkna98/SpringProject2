package com.sist.web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;
import com.sist.web.vo.*;
import com.sist.web.mapper.*;

// spring-boot => xml(properties) => xml을 제거 (톰캣이 내장)
// spring-boot => 서버로만 사용 JSTL => ThymeLeaf (EL) => th:

// Thymeleaf => 태그안에서 제어
// th:each (for문), th:text, th:html, th:if, th:else ...
// #number(startPage,endPage)

@Service
public class FoodServiceImpl implements FoodService{
	@Autowired
	private FoodMapper mapper;

	@Override
	public List<FoodVO> foodListData(int start) {
		// TODO Auto-generated method stub
		return mapper.foodListData(start);
	}

	@Override
	public int foodTotalPage() {
		// TODO Auto-generated method stub
		return mapper.foodTotalPage();
	}

	@Override
	public FoodVO foodDetailData(int fno) {
		// TODO Auto-generated method stub
		return mapper.foodDetailData(fno);
	}
	
}
