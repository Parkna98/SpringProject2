package com.sist.web.vo;

import lombok.Data;

/*
 * FNO int 
POSTER text 
NAME text 
TYPE text 
ADDRESS text 
PHONE text 
SCORE double 
THEME text 
PRICE text 
TIME text 
SEAT text 
CONTENT text 
LINK text 
HIT int 
LIKE_COUNT int 
RDAY text 
JJIMCOUNT int

			Oracle 			MySQL/MariaDB
			=======			==============
			NUMBER			int, double
			VARCHAR2		VARCHAR
			CLOB			text
			
			=> 인라인뷰(페이지) ==> LIMIT 시작,갯수
				=> rownum (1부터시작) 	=> LIMIT(0부터 시작)
				
			=> ANSI조인을 이용한다
			=> 시퀀스 ========== create table a
			   				   (
			   				   		no int auto_increment
			   				   );
			
 */
@Data
public class FoodVO {
	private int fno,hit;
	private String poster,name,type,address,phone,theme,price,time,seat;
	private double score;
}
