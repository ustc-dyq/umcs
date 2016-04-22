/**
 * @Title：UserVerfy.java 
 * @Copyright © Suzhou Keda Technology Co.Ltd. All Rights Reserved. 
 * @author： 代永强
 * @date: 2016年4月1日
 * @description:
**/
package com.keda.webDemo.umcs.aop;

import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.keda.webDemo.umcs.dao.UserDao;
import com.keda.webDemo.umcs.dao.dto.User;
import com.keda.webDemo.umcs.exception.VerfyErrorException;

@Aspect
@Service
public class UserVerfy {

	private final static Logger log = LoggerFactory.getLogger(UserVerfy.class);

	@Resource
	private UserDao userDao;

	@Before("execution(* com.keda.webDemo.umcs.controller.UmcsController.*(..))")
	public void before(JoinPoint jp) throws Exception {
		
		log.info("用户身份校验");
		Object[] args = jp.getArgs();  
		HttpServletRequest request = (HttpServletRequest) args[0];
		String[] temp = request.getParameter("head").split(":");
		String userName = temp[0];
		String userPasswd = temp[1];
		if (!verify(userName, userPasswd)) {			
			throw new VerfyErrorException("身份验证失败，请重新登录");
		}

	}
	
	private boolean verify(String userName, String userPasswd) {
		User user = new User();
		user.setUserName(userName);
		user.setUserPasswd(userPasswd);
		user = userDao.selectByUser(user);
		if(null == user) {
			return false;
		} else {
			user.setUpdateTime(new Date());
			userDao.update(user);
			return true;
		}
	}
	
}
