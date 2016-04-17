/**
 * @Title：RecivMsg.java 
 * @Copyright © Suzhou Keda Technology Co.Ltd. All Rights Reserved. 
 * @author： 代永强
 * @date: 2016年3月28日
 * @description: 接收信息
**/
package com.keda.webDemo.umcs.dao.dto;

public class RecivMsg {

	private Integer id;
	private Integer sendMsgId;
	private Integer sendId;
	private Integer recivUserId;
	private Integer isRead;
	
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public Integer getSendMsgId() {
		return sendMsgId;
	}
	
	public void setSendMsgId(Integer sendMsgId) {
		this.sendMsgId = sendMsgId;
	}
	
	public Integer getSendId() {
		return sendId;
	}
	
	public void setSendId(Integer sendId) {
		this.sendId = sendId;
	}
	
	public Integer getRecivUserId() {
		return recivUserId;
	}
	
	public void setRecivUserId(Integer recivUserId) {
		this.recivUserId = recivUserId;
	}
	
	public Integer getIsRead() {
		return isRead;
	}
	
	public void setIsRead(Integer isRead) {
		this.isRead = isRead;
	}
	
}





