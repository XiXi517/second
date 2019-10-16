/**
 * 
 */
package com.senlang.sdip.aspect;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.Enumeration;

/**
 * @author Mc.D
 *
 */

@Aspect
@Component
@Order(-5)
public class LogAspect {
	private final Logger logger = LogManager.getLogger(this.getClass());

	@Pointcut("execution(public * com.senlang..api.*.*(..))")
	public void writeLog() {
	}

	ThreadLocal<Long> startTime = new ThreadLocal<Long>();

	@Before("writeLog()")
	public void before(JoinPoint joinPoint) {
		startTime.set(System.currentTimeMillis());

		// 接收到请求，记录请求内容
		logger.info("WebLogAspect.doBefore()");

		ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();

		HttpServletRequest request = attributes.getRequest();

		// 记录下请求内容
		logger.info("URL : " + request.getRequestURL().toString());
		logger.info("HTTP_METHOD : " + request.getMethod());
		logger.info("IP : " + request.getRemoteAddr());
		logger.info("CLASS_METHOD : " + joinPoint.getSignature().getDeclaringTypeName() + "."
				+ joinPoint.getSignature().getName());

		logger.info("ARGS : " + Arrays.toString(joinPoint.getArgs()));

		// 获取所有参数方法一：

		Enumeration<String> enu = request.getParameterNames();

		while (enu.hasMoreElements()) {
			String paraName = (String) enu.nextElement();
			System.out.println(paraName + ": " + request.getParameter(paraName));
		}
	}

	@AfterReturning("writeLog()")
	public void afterReturning(JoinPoint joinPoint) {
		// 处理完请求，返回内容
		logger.info("耗时（毫秒） : " + (System.currentTimeMillis() - startTime.get()));
	}
}
