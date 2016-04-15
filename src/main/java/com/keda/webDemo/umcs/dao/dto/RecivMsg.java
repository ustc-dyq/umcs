/**
 * @Title：RecivMsg.java 
 * @Copyright © Suzhou Keda Technology Co.Ltd. All Rights Reserved. 
 * @author： 代永强
 * @date: 2016年3月28日
 * @description: 接收信息
**/
package com.keda.webDemo.umcs.dao.dto;

import java.util.Date;

public class RecivMsg {

	private Integer id;
	private Integer sendUserId;
	private Integer recivUserId;
	private String msg;               //发送消息内容
	private Integer msgType;              //发送消息类型，1代表文本，2代表图片，3代表文件
	private Date recivTime;           //接收时间
	
	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}
	
	/**
	 * @param id the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	
	/**
	 * @return the sendUserId
	 */
	public Integer getSendUserId() {
		return sendUserId;
	}
	
	/**
	 * @param sendUserId the sendUserId to set
	 */
	public void setSendUserId(Integer sendUserId) {
		this.sendUserId = sendUserId;
	}
	
	/**
	 * @return the recivUserId
	 */
	public Integer getRecivUserId() {
		return recivUserId;
	}
	
	/**
	 * @param recivUserId the recivUserId to set
	 */
	public void setRecivUserId(Integer recivUserId) {
		this.recivUserId = recivUserId;
	}
	
	/**
	 * @return the msg
	 */
	public String getMsg() {
		return msg;
	}
	
	/**
	 * @param msg the msg to set
	 */
	public void setMsg(String msg) {
		this.msg = msg;
	}
	
	/**
	 * @return the msgType
	 */
	public Integer getMsgType() {
		return msgType;
	}
	
	/**
	 * @param msgType the msgType to set
	 */
	public void setMsgType(Integer msgType) {
		this.msgType = msgType;
	}
	
	/**
	 * @return the recivTime
	 */
	public Date getRecivTime() {
		return recivTime;
	}

	/**
	 * @param recivTime the recivTime to set
	 */
	public void setRecivTime(Date recivTime) {
		this.recivTime = recivTime;
	}

	
}





