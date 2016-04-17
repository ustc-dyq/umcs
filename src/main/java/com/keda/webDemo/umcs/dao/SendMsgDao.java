/**
 * @Title：SendMsgDao.java 
 * @Copyright © Suzhou Keda Technology Co.Ltd. All Rights Reserved. 
 * @author： 代永强
 * @date: 2016年3月28日
 * @description:
**/
package com.keda.webDemo.umcs.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;

import com.keda.webDemo.umcs.dao.dto.SendMsg;

public interface SendMsgDao {

	/**
	 * 根据主键查询发送消息
	 * @param id
	 * @return
	 */
	public SendMsg select(int id);
	
	/**
	 * 查询消息数量
	 * @param id
	 * @return
	 */
	public int selectCount(SendMsg sendMsg);
	
	/**
	 * 根据用户id和发送方式查询历史消息
	 * @param sendMsg
	 * @return
	 */
	public List<SendMsg> selectHistoryMsg(Map<String,Object> param, RowBounds rowBounds);
	
	public List<SendMsg> selectBySendUserId(SendMsg sendMsg, RowBounds rowBounds);
	
	public List<SendMsg> selectByRecivId(SendMsg sendMsg, RowBounds rowBounds);
	
	/**
	 * 根据发送人或者接收人查询消息
	 * @param sendMsg
	 * @return
	 */
	public List<SendMsg> selectByUserId(SendMsg sendMsg, RowBounds rowBounds);
	
	/**
	 * 保存发送的消息
	 * @param sendMsg
	 * @return
	 */
	public int insert(SendMsg sendMsg);
	
	/**
	 * 删除发送的消息
	 * @param id
	 * @return
	 */
	public int delete(int id);
	
}





