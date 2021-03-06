/**
 * @Title：RecivMsgDaoImpl.java 
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

import com.keda.webDemo.umcs.dao.RecivMsgDao;
import com.keda.webDemo.umcs.dao.dto.RecivMsg;

@Repository
public class RecivMsgDaoImpl implements RecivMsgDao {

	@Resource
	private SqlSessionTemplate sqlSession;
	
	/* 
	 * @see com.keda.webDemo.umcs.dao.RecivMsgDao#select(int)
	 */
	@Override
	public List<RecivMsg> selectByUserId(RecivMsg recivMsg) {
		
		return sqlSession.selectList("com.keda.webDemo.umcs.dao.RecivMsg.selectByUserId",recivMsg);
	
	}

	/* 
	 * @see com.keda.webDemo.umcs.dao.RecivMsgDao#insert(com.keda.webDemo.umcs.dao.dto.RecivMsg)
	 */
	@Override
	public int insert(RecivMsg recivMsg) {
		
		return sqlSession.insert("com.keda.webDemo.umcs.dao.RecivMsg.insert",recivMsg);
	
	}

	/* 
	 * @see com.keda.webDemo.umcs.dao.RecivMsgDao#delete(int)
	 */
	@Override
	public int delete(int id) {
		
		return sqlSession.delete("com.keda.webDemo.umcs.dao.RecivMsg.delete",id);
	
	}

	@Override
	public int updateReadState(int id) {
		
		return sqlSession.update("com.keda.webDemo.umcs.dao.RecivMsg.updateReadState",id);
	
	}

	/* (non-Javadoc)
	 * @see com.keda.webDemo.umcs.dao.RecivMsgDao#selectBySendMsgId(int)
	 */
	@Override
	public List<RecivMsg> selectBySendMsgId(int sendMsgId) {
		return sqlSession.selectList("com.keda.webDemo.umcs.dao.RecivMsg.selectBySendMsgId",sendMsgId);
	}

}





