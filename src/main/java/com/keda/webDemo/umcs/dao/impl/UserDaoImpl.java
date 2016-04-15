/**
 * @Title：UserDaoImpl.java 
 * @Copyright © Suzhou Keda Technology Co.Ltd. All Rights Reserved. 
 * @author： 代永强
 * @date: 2016年3月28日
 * @description:
**/
package com.keda.webDemo.umcs.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.keda.webDemo.umcs.dao.UserDao;
import com.keda.webDemo.umcs.dao.dto.User;

@Repository
public class UserDaoImpl implements UserDao {

	@Resource
	private SqlSessionTemplate sqlSession;
	
	/* 
	 * @see com.keda.webDemo.umcs.dao.UserDao#insert(com.keda.webDemo.umcs.dao.dto.User)
	 */
	@Override
	public int insert(User user) {
		
		return sqlSession.insert("com.keda.webDemo.umcs.dao.User.insert",user);
	
	}

	/* 
	 * @see com.keda.webDemo.umcs.dao.UserDao#update(com.keda.webDemo.umcs.dao.dto.User)
	 */
	@Override
	public int update(User user) {
		
		return sqlSession.update("com.keda.webDemo.umcs.dao.User.update",user);
	
	}

	/* 
	 * @see com.keda.webDemo.umcs.dao.UserDao#select(int)
	 */
	@Override
	public User select(int id) {
		
		return sqlSession.selectOne("com.keda.webDemo.umcs.dao.User.select",id);
	
	}

	/* 
	 * @see com.keda.webDemo.umcs.dao.UserDao#selectByGroupId(int)
	 */
	@Override
	public List<User> selectByGroupId(int groupId) {
		
		return sqlSession.selectList("com.keda.webDemo.umcs.dao.User.selectByGroupId",groupId);
		
	}
	
	/* 
	 * @see com.keda.webDemo.umcs.dao.UserDao#selectAll()
	 */
	@Override
	public List<User> selectAll() {
		
		return sqlSession.selectList("com.keda.webDemo.umcs.dao.User.selectAll");
	
	}

	/* (non-Javadoc)
	 * @see com.keda.webDemo.umcs.dao.UserDao#select(com.keda.webDemo.umcs.dao.dto.User)
	 */
	@Override
	public User selectByUser(User user) {
		
		return sqlSession.selectOne("com.keda.webDemo.umcs.dao.User.selectByUser",user);
	
	}

	/* 
	 * @see com.keda.webDemo.umcs.dao.UserDao#deleteByUserName(java.lang.String)
	 */
	@Override
	public int delete(int id) {
		
		return sqlSession.update("com.keda.webDemo.umcs.dao.User.delete",id);
	
	}

	/* (non-Javadoc)
	 * @see com.keda.webDemo.umcs.dao.UserDao#selectAllManages()
	 */
	@Override
	public List<User> selectAllManages() {
		return sqlSession.selectList("com.keda.webDemo.umcs.dao.User.selectAllManages");
	}
	
	
}





