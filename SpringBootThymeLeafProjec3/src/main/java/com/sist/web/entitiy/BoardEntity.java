package com.sist.web.entitiy;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.hibernate.annotations.DynamicUpdate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.Data; 
// Boot에서는 VO를 Entity라고 쓴다
@Entity
@Table(name="jpaboard")
@DynamicUpdate
// update시에 제외
@Data
// Entity는 추가적인 변수가 존재하면 안된다 => insert,update
public class BoardEntity {
	@Id
	private int no;
	// id밑에 있는 no가 pk라는 뜻
	private String name,subject,content;
	@Column(insertable = true,updatable = false)
	private String pwd;
	// column => insert는 허용, update는 불가
	@Column(insertable = true,updatable = false)
	private int hit;
	@Column(insertable = true,updatable = false)
	private String regdate;
	
	// regdate값이 들어오면 변경해서 가져오는 기능
	@PrePersist
	public void regdate() {
		this.regdate=LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
	}
}
