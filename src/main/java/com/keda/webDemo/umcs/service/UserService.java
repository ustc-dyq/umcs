/**
 * @Title：UserService.java 
 * @Copyright © Suzhou Keda Technology Co.Ltd. All Rights Reserved. 
 * @author： 代永强
 * @date: 2016年3月28日
 * @description: 用户原子操作
**/
package com.keda.webDemo.umcs.service;

import java.util.List;

import com.keda.webDemo.umcs.dao.dto.User;
import com.keda.webDemo.umcs.exception.RepeatException;

public interface UserService {
	
	/**
	 * 校验用户名和密码
	 * @param user
	 * @return
	 */
	public boolean verify(String userName, String userPasswd);
	
	/**
	 * 预登陆
	 * @param user
	 */
	public User preLogin(String userName, String userPasswd) throws RepeatException;
	
	/**
	 * 登陆
	 * @param user
	 */
	public User login(String userName, String userPasswd) throws RepeatException;
	
	
	
	/**
	 * 登出
	 * @param user
	 */
	public void logOut(int userId);
	
	/**
	 * 添加用户
	 * @param user
	 */
	public User addUser(String userName, String userPasswd) throws RepeatException;
	
	/**
	 * 更改用户信息
	 * @param user
	 */
	public User changeUser(User user);
	
	/**
	 * 删除用户
	 * @param user
	 */
	public void deleteUser(int userId);
	
	/**
	 * 查询用户信息
	 * @param userName
	 * @return
	 */
	public User queryByUserName(String userName);
	
	/**
	 * 查询用户信息
	 * @param userName
	 * @return
	 */
	public User queryByUserId(int userId);
	
	/**
	 * 获取所有用户信息
	 * @return
	 */
	public List<User> getAllUser();
	
	/**
	 * 获取所有用户信息
	 * @return
	 */
	public List<User> getAllManages();
	
	/**
	 * 获取同组好友
	 * @param groupId
	 * @return
	 */
	public List<User> queryByGroupId(int groupId);
	
	
	
}





