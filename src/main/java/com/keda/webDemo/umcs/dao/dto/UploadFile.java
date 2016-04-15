/**
 * @Title：UploadFile.java 
 * @Copyright © Suzhou Keda Technology Co.Ltd. All Rights Reserved. 
 * @author： 代永强
 * @date: 2016年4月8日
 * @description:
**/
package com.keda.webDemo.umcs.dao.dto;

import java.util.Date;

public class UploadFile {

	private Integer id;
	private Integer sendUserId;
	private Integer recivUserId;
	private String originName;
	private String newName;
	private String localPath;
	private String remoteUrl;
	private Date addTime;	        //创建时间
	private Date updateTime;
	private Integer isRead;
	private Integer delFlag;
	
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
	 * @return the originName
	 */
	public String getOriginName() {
		return originName;
	}
	
	/**
	 * @param originName the originName to set
	 */
	public void setOriginName(String originName) {
		this.originName = originName;
	}
	
	/**
	 * @return the newName
	 */
	public String getNewName() {
		return newName;
	}
	
	/**
	 * @param newName the newName to set
	 */
	public void setNewName(String newName) {
		this.newName = newName;
	}
	
	/**
	 * @return the localPath
	 */
	public String getLocalPath() {
		return localPath;
	}
	
	/**
	 * @param localPath the localPath to set
	 */
	public void setLocalPath(String localPath) {
		this.localPath = localPath;
	}
	
	/**
	 * @return the remoteUrl
	 */
	public String getRemoteUrl() {
		return remoteUrl;
	}
	
	/**
	 * @param remoteUrl the remoteUrl to set
	 */
	public void setRemoteUrl(String remoteUrl) {
		this.remoteUrl = remoteUrl;
	}
	
	/**
	 * @return the addTime
	 */
	public Date getAddTime() {
		return addTime;
	}
	
	/**
	 * @param addTime the addTime to set
	 */
	public void setAddTime(Date addTime) {
		this.addTime = addTime;
	}
	
	/**
	 * @return the updateTime
	 */
	public Date getUpdateTime() {
		return updateTime;
	}
	
	/**
	 * @param updateTime the updateTime to set
	 */
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	
	/**
	 * @return the isRead
	 */
	public Integer getIsRead() {
		return isRead;
	}

	/**
	 * @param isRead the isRead to set
	 */
	public void setIsRead(Integer isRead) {
		this.isRead = isRead;
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





