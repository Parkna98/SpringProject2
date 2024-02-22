package com.sist.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.session.SessionInformation;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;

import lombok.Setter;
import java.util.*;
import com.sist.vo.*;
import com.sist.service.*;

public class LoginSuccessHandler implements AuthenticationSuccessHandler{
	private RequestCache requestCache=new HttpSessionRequestCache();
	private RedirectStrategy redirectStrategy=new DefaultRedirectStrategy();
	
	@Setter
	private String defaultUrl;
	// 해당 변수만 setter 주기
	
	
	  @Autowired 
	  private MemberService mService;
	 
	
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		// TODO Auto-generated method stub
		// 앞에 on이있는 메소드 => 자동 호출함수
		
		mService.lastLoginUpdate(authentication.getName());
		
		
		MemberVO vo=mService.memberSessionInfoData(authentication.getName()); // id

		HttpSession session=request.getSession();
		session.setAttribute("userId", vo.getUserId());
		session.setAttribute("userName",vo.getUserName());
		session.setAttribute("email",vo.getEmail());
		session.setAttribute("phone",vo.getPhone());
		session.setAttribute("addr",vo.getAddr1()+" "+vo.getAddr2());
		session.setAttribute("sex",vo.getSex());
		
		response.sendRedirect("../main/main.do");
//		resultRedirectStrategy(request, response, authentication);
	}
	
	protected void resultRedirectStrategy(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException{
		
		SavedRequest savedRequest=requestCache.getRequest(request, response);
		if(savedRequest!=null) {
			String targetUrl=savedRequest.getRedirectUrl();
			redirectStrategy.sendRedirect(request, response, targetUrl);
		}
		else {
			redirectStrategy.sendRedirect(request, response, defaultUrl);
		}
	}

}
