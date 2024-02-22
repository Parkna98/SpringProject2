package com.sist.aop;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.*;

import javax.servlet.http.HttpServletRequest;

import com.sist.manager.NewsManager;
import com.sist.service.*;
import com.sist.vo.*;

@Aspect
@Component
public class CommonsSendAop {
	@Autowired
	private FoodService service;
	
	@Autowired
	private NewsManager mgr;
	
	@After("execution(* com.sist.web.*Controller.*(..))")
	public void footerSend() {
		List<FoodVO> fList=service.foodListTop7();
		List<NoticeVO> nList=service.noticeTop7();
		List<NewsVO> newsList=mgr.newsFind("맛집");
		
		// Model 전송객체는 Controller에서만 쓰일수있기때문에 request를 생성해서 직접보내야한다
		// => @Controller, @RestController를 제외한 모든 클래스에서 request가 필요한 경우에 사용
		//    =============================
		//		=> DispatcherServlet에 의해 request가 매개변수로 주입
		
		// 이외에는 직접생성해서 사용 => HttpServletRequest
		HttpServletRequest request=
				((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
		request.setAttribute("nList", nList);
		request.setAttribute("fList", fList);
		request.setAttribute("newsList", newsList);
	}
}
