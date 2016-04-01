/**
 * @Title：GroupDaoImpl.java 
 * @Copyright © Suzhou Keda Technology Co.Ltd. All Rights Reserved. 
 * @author： 代永强
 * @date: 2016年3月29日
 * @description:
**/
package com.keda.webDemo.umcs.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.keda.webDemo.umcs.dao.GroupDao;
import com.keda.webDemo.umcs.dao.dto.Group;

@Repository
public class GroupDaoImpl implements GroupDao {

	@Resource
	private SqlSessionTemplate sqlSession;
	
	/* 
	 * @see com.keda.webDemo.umcs.dao.GroupDao#select(int)
	 */
	@Override
	public Group select(int id) {
		
		return sqlSession.selectOne("com.keda.webDemo.umcs.dao.Group.select",id);
	
	}

	/* 
	 * @see com.keda.webDemo.umcs.dao.GroupDao#selectAll()
	 */
	@Override
	public List<Group> selectAll() {
		
		return sqlSession.selectList("com.keda.webDemo.umcs.dao.Group.selectAll");
	
	}

	/* 
	 * @see com.keda.webDemo.umcs.dao.GroupDao#selectByGroupName(java.lang.String)
	 */
	@Override
	public Group selectByGroupName(String groupName) {
		
		return sqlSession.selectOne("com.keda.webDemo.umcs.dao.Group.selectByGroupName",groupName);
	
	}

	/* 
	 * @see com.keda.webDemo.umcs.dao.GroupDao#insert(com.keda.webDemo.umcs.dao.dto.Group)
	 */
	@Override
	public int insert(Group group) {
		
		return sqlSession.insert("com.keda.webDemo.umcs.dao.Group.insert",group);
	
	}

	/* 
	 * @see com.keda.webDemo.umcs.dao.GroupDao#update(com.keda.webDemo.umcs.dao.dto.Group)
	 */
	@Override
	public int update(Group group) {
		
		return sqlSession.update("com.keda.webDemo.umcs.dao.Group.update",group);
	
	}

	/* 
	 * @see com.keda.webDemo.umcs.dao.GroupDao#delete(int)
	 */
	@Override
	public int delete(int id) {
		
		return sqlSession.update("com.keda.webDemo.umcs.dao.Group.delete",id);
	
	}

}





