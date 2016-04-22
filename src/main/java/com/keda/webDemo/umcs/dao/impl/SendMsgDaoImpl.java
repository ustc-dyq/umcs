/**
 * @Title：SendMsgDaoImpl.java 
 * @Copyright © Suzhou Keda Technology Co.Ltd. All Rights Reserved. 
 * @author： 代永强
 * @date: 2016年3月29日
 * @description:
**/
package com.keda.webDemo.umcs.dao.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.ibatis.session.RowBounds;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.keda.webDemo.umcs.dao.SendMsgDao;
import com.keda.webDemo.umcs.dao.dto.SendMsg;

@Repository
public class SendMsgDaoImpl implements SendMsgDao {

	@Resource
	private SqlSessionTemplate sqlSession;
	
	/* 
	 * @see com.keda.webDemo.umcs.dao.SendMsgDao#select(int)
	 */
	@Override
	public SendMsg select(int id) {
		
		return sqlSession.selectOne("com.keda.webDemo.umcs.dao.SendMsg.select",id);
	
	}

	/* 
	 * @see com.keda.webDemo.umcs.dao.SendMsgDao#selectByUser(com.keda.webDemo.umcs.dao.dto.SendMsg)
	 */
	@Override
	public List<SendMsg> selectByUserId(SendMsg sendMsg, RowBounds rowBounds) {
		
		return sqlSession.selectList("com.keda.webDemo.umcs.dao.SendMsg.selectByUserId",sendMsg,rowBounds);
	
	}

	/* 
	 * @see com.keda.webDemo.umcs.dao.SendMsgDao#insert(com.keda.webDemo.umcs.dao.dto.SendMsg)
	 */
	@Override
	public int insert(SendMsg sendMsg) {
		
		return sqlSession.insert("com.keda.webDemo.umcs.dao.SendMsg.insert",sendMsg);
	
	}

	/* 
	 * @see com.keda.webDemo.umcs.dao.SendMsgDao#delete(int)
	 */
	@Override
	public int delete(int id) {
		
		return sqlSession.update("com.keda.webDemo.umcs.dao.SendMsg.delete",id);
	
	}

	/* (non-Javadoc)
	 * @see com.keda.webDemo.umcs.dao.SendMsgDao#selectCount(com.keda.webDemo.umcs.dao.dto.SendMsg)
	 */
	@Override
	public int selectCount(SendMsg sendMsg) {
		return sqlSession.selectOne("com.keda.webDemo.umcs.dao.SendMsg.selectCount",sendMsg);
	}

	@Override
	public List<SendMsg> selectHistoryMsg(Map<String, Object> param, RowBounds rowBounds) {
		
		return sqlSession.selectList("com.keda.webDemo.umcs.dao.SendMsg.selectHistoryMsg",param,rowBounds);
		
	}

	@Override
	public List<SendMsg> selectBySendUserId(SendMsg sendMsg, RowBounds rowBounds) {
		
		return sqlSession.selectList("com.keda.webDemo.umcs.dao.SendMsg.selectBySendUserId",sendMsg,rowBounds);
		
	}

	@Override
	public List<SendMsg> selectByRecivId(SendMsg sendMsg, RowBounds rowBounds) {
		
		return sqlSession.selectList("com.keda.webDemo.umcs.dao.SendMsg.selectByRecivId",sendMsg,rowBounds);
	
	}

	/* (non-Javadoc)
	 * @see com.keda.webDemo.umcs.dao.SendMsgDao#selectByDays(int)
	 */
	@Override
	public List<SendMsg> selectBySendTime(Date sendTime) {
		
		return sqlSession.selectList("com.keda.webDemo.umcs.dao.SendMsg.selectBySendTime",sendTime);
	
	}

}





