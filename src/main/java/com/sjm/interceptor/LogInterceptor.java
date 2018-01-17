package com.sjm.interceptor;

import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 * 日志拦截器，打印查询操作
 * @author sonkabin
 *
 */
@Component
public class LogInterceptor extends HandlerInterceptorAdapter {
	
	private static final Logger logger = LoggerFactory.getLogger(LogInterceptor.class);
	
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		HandlerMethod handlerMethod = (HandlerMethod) handler;
		//获取方法名
		Method method = handlerMethod.getMethod();
		logger.info(method.toString());
		
		//获取方法的参数名
		MethodParameter[] parameters = handlerMethod.getMethodParameters();
		for (MethodParameter parameter : parameters) {
			String paraName = parameter.getParameterName();
			//通过参数名获取参数值
			String value = request.getParameter(paraName);
			logger.info(paraName + ":" + value);
		}
	}
}
