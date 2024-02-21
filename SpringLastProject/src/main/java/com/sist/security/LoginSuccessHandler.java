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
	
	/*
	 * @Autowired private MemberService mService;
	 */
	
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		// TODO Auto-generated method stub
		/*
		HttpSession session=request.getSession();
		mService.lastLoginUpdate(authentication.getName());
		
		
		MemberVO vo=mService.memberSessionData(authentication.getName());
		SessionInfo info=new SessionInfo();
		info.setUserId(vo.getUserId());
		info.setUserName(vo.getUserName());
		info.setEmail(vo.getEmail());
		info.setPhone(vo.getPhone());
		info.setAddress(vo.getAddr1()+" "+vo.getAddr2());
		info.setSex(vo.getSex());
		session.setAttribute("member", info);
		*/
		resultRedirectStrategy(request, response, authentication);
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
