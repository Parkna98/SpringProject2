package com.sist.web.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

/*
 *  no bigint 
	title varchar(2000) 
	poster varchar(500) 
	chef varchar(200) 
	hit bigint
 * 
 */
// DB의 테이블명과 클래스명을 동일하게 하면 
// 따로 테이블이름을 지정하지 않아도 된다 (자동으로 인식)
@Entity
@Data
public class Recipe2 {
	@Id
	private int no;
	private String title;
	private String poster;
	private String chef;
	private int hit;
}
