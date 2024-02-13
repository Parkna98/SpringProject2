package com.sist.commons;

import org.springframework.web.bind.annotation.ControllerAdvice;
/*
 * 	 	공통 => 모든 화면이나 기능에 적용
 * 		1) Aspect : 중복이 많은 코드에서  (제거 => 자동)
 * 		2) 공통 예외처리 
		3) Interceptor => 필요시마다 처리 (화면마다 처리가 가능)
 */
@ControllerAdvice
public class CommonsControllerException {
	
}
