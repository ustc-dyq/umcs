/**
 * @Title：UserDao.java 
 * @Copyright © Suzhou Keda Technology Co.Ltd. All Rights Reserved. 
 * @author： 代永强
 * @date: 2016年3月28日
 * @description:
**/
package com.keda.webDemo.umcs.dao;

import java.util.List;

import com.keda.webDemo.umcs.dao.dto.User;

public interface UserDao {

	/**
	 * 将用户信息插入到数据库中的用户表
	 * @param user
	 * @return
	 */
	public int insert(User user);
	
	/**
	 * 更新用户信息
	 * @param user
	 * @return
	 */
	public int update(User user);
	
	/**
	 * 根据主键查询用户信息
	 * @param id
	 * @return
	 */
	public User select(int id);
	
	/**
	 * 根据分组id查询用户信息
	 * @param id
	 * @return
	 */
	public List<User> selectByGroupId(int groupId);
	
	/**
	 * 查询所有的注册用户
	 * @return
	 */
	public List<User> selectAll();
	
	/**
	 * 根据用户的用户名和密码查询用户信息
	 * @param user
	 * @return
	 */
	public User selectByUser(User user);
	
	/**
	 * 删除用户信息
	 * @param id
	 * @return
	 */
	int delete(int id);
	
}





