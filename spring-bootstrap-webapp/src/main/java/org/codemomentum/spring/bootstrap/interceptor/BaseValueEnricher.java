package org.codemomentum.spring.bootstrap.interceptor;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 * @author codemomentum@gmail.com
 */
public class BaseValueEnricher extends HandlerInterceptorAdapter {

	@Override
	public void postHandle(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
		modelAndView.addObject("STATIC_URL", "static/");        //todo find a better way
		modelAndView.addObject("CONTEXT_ROOT", "/webapp");      //todo find a better way
	}
}
