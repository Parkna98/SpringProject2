package com.sist.web.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data; // VO => 지정된 테이블과 컬럼이 일치 => insert,update,delete
// 추가되는 데이터가 없다 
// 추가하고싶으면 update, insert false같은 조치를 해줘야함
/*
 *  NO bigint 
	POSTER varchar(300) 
	TITLE varchar(1000) 
	CHEF varchar(200) 
	CHEF_POSTER varchar(300) 
	CHEF_PROFILE varchar(1000) 
	INFO1 varchar(100) 
	INFO2 varchar(100) 
	INFO3 varchar(100) 
	CONTENT varchar(4000) 
	FOODMAKE longblob 
	STUFF longblob
 */
@Entity
@Data
public class Recipedetail {
	@Id
	private int no;
	private String poster,title,chef,chef_poster,chef_profile,info1,info2,info3,content,foodmake,stuff;
}
