/**
 * @Title：UserVerfy.java 
 * @Copyright © Suzhou Keda Technology Co.Ltd. All Rights Reserved. 
 * @author： 代永强
 * @date: 2016年4月1日
 * @description:
**/
package com.keda.webDemo.umcs.aop;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.keda.webDemo.umcs.dto.Data;
import com.keda.webDemo.umcs.exception.VerfyErrorException;
import com.keda.webDemo.umcs.service.UserService;
import com.keda.webDemo.umcs.service.impl.UserManageServiceImpl;

@Aspect
@Service
public class UserVerfy {

	private final static Logger log = LoggerFactory.getLogger(UserVerfy.class);

	@Resource
	private UserService userService;

	@Before("execution(* com.keda.webDemo.umcs.controller.UmcsController.*(..))")
	public void before(JoinPoint jp) throws Exception {
		
		log.info("用户身份校验");
//		Object[] args = jp.getArgs();  
//		HttpServletRequest request = (HttpServletRequest) args[0];
//		String[] user = request.getHeader("user").split(":");
//		String userName = user[0];
//		String userPasswd = user[1];
//		Data data = new Data();
//		if (!userService.verify(userName, userPasswd)) {
//			data.setSuccess(false);
//			data.setMsg("用户名或者密码错误，请重新登录");
//			log.info("用户名或者密码错误");
//			throw new VerfyErrorException("用户名或者密码错误");
//		}

	}
	
}
