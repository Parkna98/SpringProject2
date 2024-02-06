package com.sist.vo;

import lombok.Data;
// 사용자 정의 데이터형 => 메모리 할당을 요청하지 않는다
@Data
public class FoodVO {
	private int fno;
	private double score;
	private String poster,name,type,address,phone,theme,price,time,seat,sessionId;
	
}
