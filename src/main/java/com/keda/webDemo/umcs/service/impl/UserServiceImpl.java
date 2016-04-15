/**
 * @Title：UserServiceImpl.java 
 * @Copyright © Suzhou Keda Technology Co.Ltd. All Rights Reserved. 
 * @author： 代永强
 * @date: 2016年3月28日
 * @description:
**/
package com.keda.webDemo.umcs.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.keda.webDemo.umcs.constants.Constants;
import com.keda.webDemo.umcs.dao.UserDao;
import com.keda.webDemo.umcs.dao.dto.User;
import com.keda.webDemo.umcs.exception.RepeatException;
import com.keda.webDemo.umcs.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	private final static Logger log = LoggerFactory.getLogger(UserServiceImpl.class);
	
	@Resource
	private UserDao userDao;
	

	/* 
	 * @see com.keda.webDemo.umcs.service.UserService#verify(java.lang.String, java.lang.String)
	 */
	@Override
	public boolean verify(String userName, String userPasswd) {
		
		if(null == userName || "".equals(userName)) {
			log.info("用户名为空，验证失败");
			return false;
		}
		
		if(null == userPasswd || "".equals(userPasswd)) {
			log.info("用户密码为空，验证失败");
		}
		
		User user = new User();
		user.setUserName(userName);
		user.setUserPasswd(userPasswd);
		user = userDao.selectByUser(user);
		
		if(null == user) {
			log.info("用户名或密码错误，验证失败");
			return false;
		} else {
			user.setUpdateTime(new Date());
			userDao.update(user);
			return true;
		}
		
		
	}

	/* 
	 * @see com.keda.webDemo.umcs.service.UserService#login(java.lang.String, java.lang.String)
	 */
	@Override
	public User preLogin(String userName, String userPasswd) throws RepeatException{
		
		User user = new User();
		user.setUserName(userName);
		user.setUserPasswd(userPasswd);
		user = userDao.selectByUser(user);
		if(Constants.LOGIN == user.getIsOnline()) {
			throw new RepeatException("请勿重复登录！");
		}
		
		return user;
		
	}
	
	/* 
	 * @see com.keda.webDemo.umcs.service.UserService#login(java.lang.String, java.lang.String)
	 */
	@Override
	public User login(String userName, String userPasswd) throws RepeatException{
		
		User user = new User();
		user.setUserName(userName);
		user.setUserPasswd(userPasswd);
		user = userDao.selectByUser(user);
		if(Constants.LOGIN == user.getIsOnline()) {
			throw new RepeatException("请勿重复登录！");
		}
		user.setIsOnline(Constants.LOGIN);
		userDao.update(user);
		return user;
		
	}

	/* (non-Javadoc)
	 * @see com.keda.webDemo.umcs.service.UserService#logOut(int)
	 */
	@Override
	public void logOut(int userId) {
		
		User user = userDao.select(userId);
		user.setIsOnline(Constants.LOGOUT);
		user.setUpdateTime(new Date());
		userDao.update(user);
		
	}
	
	/* (non-Javadoc)
	 * @see com.keda.webDemo.umcs.service.UserService#addUser(java.lang.String, java.lang.String)
	 */
	@Override
	public User addUser(String userName, String userPasswd) throws RepeatException {
		User user = new User();
		user.setUserName(userName);
		User userInfo = userDao.selectByUser(user);
		
		if(null != userInfo) {
			log.info("用户已存在，添加用户失败");
			throw new RepeatException("用户名重复");
		}
		
		user.setUserPasswd(userPasswd);
		user.setGroupId(Constants.NOTINGROUP);
		user.setUserPriv(Constants.NORMALPRIV);
		user.setAddTime(new Date());
		userDao.insert(user);
		
		return user;
		
	}

	/* 
	 * @see com.keda.webDemo.umcs.service.UserService#changeUser(com.keda.webDemo.umcs.dao.dto.User)
	 */
	@Override
	public User changeUser(User user) {
		
		userDao.update(user);
		return user;
		
	}

	/* (non-Javadoc)
	 * @see com.keda.webDemo.umcs.service.UserService#deleteUser(int)
	 */
	@Override
	public void deleteUser(int userId) {
		
		userDao.delete(userId);
		
	}

	

	/* (non-Javadoc)
	 * @see com.keda.webDemo.umcs.service.UserService#getAllUser()
	 */
	@Override
	public List<User> getAllUser() {

        List<User> users = userDao.selectAll();
        
		return users;
		
	}

	/* (non-Javadoc)
	 * @see com.keda.webDemo.umcs.service.UserService#queryByUserName(java.lang.String)
	 */
	@Override
	public User queryByUserName(String userName) {
		
		User user = new User();
		user.setUserName(userName);
		user = userDao.selectByUser(user);
		return user;
		
	}

	/* (non-Javadoc)
	 * @see com.keda.webDemo.umcs.service.UserService#queryByUserId(int)
	 */
	@Override
	public User queryByUserId(int userId) {
		
		User user = userDao.select(userId);
		return user;
		
	}

	/* (non-Javadoc)
	 * @see com.keda.webDemo.umcs.service.UserService#queryByGroupId(int)
	 */
	@Override
	public List<User> queryByGroupId(int groupId) {
		
		return userDao.selectByGroupId(groupId);
		
	}

	/* (non-Javadoc)
	 * @see com.keda.webDemo.umcs.service.UserService#getAllManages()
	 */
	@Override
	public List<User> getAllManages() {
		
		return userDao.selectAllManages();
		
	}

	
	
}





