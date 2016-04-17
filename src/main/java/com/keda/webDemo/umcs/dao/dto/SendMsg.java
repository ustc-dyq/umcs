/**
 * @Title：SendMsg.java 
 * @Copyright © Suzhou Keda Technology Co.Ltd. All Rights Reserved. 
 * @author： 代永强
 * @date: 2016年3月28日
 * @description: 发送信息
**/
package com.keda.webDemo.umcs.dao.dto;

import java.util.Date;

public class SendMsg {

	private int id;
	private Integer sendUserId;
	private Integer recivId;
	private Integer sendType;
	private String msg;               //发送消息内容
	private Integer msgType;              //发送消息类型，1代表文本，2代表图片，3代表文件
	private Date sendTime;            //发送消息时间
	private Integer delFlag;
	
	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}
	
	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
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
	public Integer getRecivId() {
		return recivId;
	}
	
	/**
	 * @param recivUserId the recivUserId to set
	 */
	public void setRecivId(Integer recivId) {
		this.recivId = recivId;
	}
	
	public Integer getSendType() {
		return sendType;
	}

	public void setSendType(Integer sendType) {
		this.sendType = sendType;
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
	 * @return the sendTime
	 */
	public Date getSendTime() {
		return sendTime;
	}

	/**
	 * @param sendTime the sendTime to set
	 */
	public void setSendTime(Date sendTime) {
		this.sendTime = sendTime;
	}

	/**
	 * @return the delFlag
	 */
	public Integer getDelFlag() {
		return delFlag;
	}
	
	/**
	 * @param delFlag the delFlag to set
	 */
	public void setDelFlag(Integer delFlag) {
		this.delFlag = delFlag;
	}
		
}





