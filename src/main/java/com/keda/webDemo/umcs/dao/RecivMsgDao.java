/**
 * @Title：RecivMsgDao.java 
 * @Copyright © Suzhou Keda Technology Co.Ltd. All Rights Reserved. 
 * @author： 代永强
 * @date: 2016年3月28日
 * @description:reciv_msg是个临时表，专门保存未接收的消息
**/
package com.keda.webDemo.umcs.dao;

import java.util.List;

import com.keda.webDemo.umcs.dao.dto.RecivMsg;

public interface RecivMsgDao {

	/**
	 * 根据主键查询接收的信息
	 * @param id
	 * @return
	 */
	public List<RecivMsg> selectByUserId(RecivMsg recivMsg);
	
	/**
	 * 保存接收到的信息
	 * @param recivMsg
	 * @return
	 */
	public int insert(RecivMsg recivMsg);
	
	/**
	 * 更新已读状态
	 * @param id
	 * @return
	 */
	public int updateReadState(int id);
	
	/**
	 * 删除接收到的信息
	 * @param id
	 * @return
	 */
	public int delete(int id);
	
}





