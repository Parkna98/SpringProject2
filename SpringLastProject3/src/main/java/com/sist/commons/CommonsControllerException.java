package com.sist.commons;

import org.springframework.web.bind.annotation.ControllerAdvice;
/*
 * 	 	공통 => 모든 화면이나 기능에 적용
 * 		1) Aspect : 중복이 많은 코드에서  (제거 => 자동)
 * 		2) 공통 예외처리 
		3) Interceptor => 필요시마다 처리 (화면마다 처리가 가능)
 */
import java.io.*;
import java.sql.*;
import org.springframework.web.bind.annotation.ExceptionHandler;

// Controller 에서만 처리하는 예외처리 (공통 예외처리)
@ControllerAdvice
public class CommonsControllerException {
	@ExceptionHandler(RuntimeException.class)
	public void runtimeException(RuntimeException ex) {
		System.out.println("========== RuntimeException 발생 ==========");
		ex.printStackTrace();
		System.out.println("==========================================");
	}
	
	@ExceptionHandler(IOException.class)
	public void ioException(IOException ex) {
		System.out.println("========== IOException 발생 ==========");
		ex.printStackTrace();
		System.out.println("==========================================");
	}
	
	@ExceptionHandler(SQLException.class)
	public void SqlException(SQLException ex) {
		System.out.println("========== SQLException 발생 ==========");
		ex.printStackTrace();
		System.out.println("==========================================");
	}
	
}
