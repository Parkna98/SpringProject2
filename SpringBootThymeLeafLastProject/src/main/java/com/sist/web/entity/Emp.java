package com.sist.web.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.FetchType;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

// NoArgsConstructor => 매개변수가 없는 생성자를 만든다
// public Emp(){}
@Entity
@Getter
@Setter
@NoArgsConstructor
public class Emp {
	@Id
	private int empno;
	private String ename,job,hiredate;
	private int mgr,sal,comm;
	@Column(insertable = false,updatable = false)
	private int deptno;
	
	@ManyToOne(fetch = FetchType.EAGER)
	// LAZY(지연), EAGER(즉시로딩)
	@JoinColumn(name="deptno",referencedColumnName = "deptno")
	private Dept dept;
	
}
