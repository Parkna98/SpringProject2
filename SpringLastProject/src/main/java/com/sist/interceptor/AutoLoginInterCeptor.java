package com.sist.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

// xml <mvc:interceptor>
/*													  ㅣ => preHandle(자동로그인,ID저장)
 *  	main.do ============ DispatcherServlet =============== HandlerMapping
 *  														   url주소를 이용해서 해당 메소드 찾기
 *  														   @GetMapping("main/main.do")
 *  														   public String main_main()
 *  															  ㅣ
 																  ㅣ ==> return ""
 																  ㅣ -- postHandle
 																  ㅣ
 															   ViewResolver
 															   	  ㅣ
 															   	  ㅣ ==> Model(request)
 															   	  ㅣ -- afterCompletion
 															   	  ㅣ
 															     JSP
 															   	  
 */
public class AutoLoginInterCeptor extends HandlerInterceptorAdapter{

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// TODO Auto-generated method stub
		return super.preHandle(request, response, handler);
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub
		super.postHandle(request, response, handler, modelAndView);
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// TODO Auto-generated method stub
		super.afterCompletion(request, response, handler, ex);
	}

}
