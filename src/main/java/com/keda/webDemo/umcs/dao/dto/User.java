/**
 * @Title：User.java 
 * @Copyright：Copyright © Suzhou Keda Technology Co.Ltd. All Rights Reserved. 
 * @author： 代永强
 * @date: 2016年3月28日
 * @description: 用户信息
**/
package com.keda.webDemo.umcs.dao.dto;

import java.util.Date;

public class User {

	private Integer id;
	private String userName;     //用户名
	private String userPasswd;   //用户密码
	private Integer userPriv;        //用户权限，0是普通用户，1是管理员
	private Integer groupId;         //用户所在分组
	private String imgPath;         //头像路径
	private Integer isOnline;         //用户是否在线
	private Date addTime;        //添加时间
	private Date updateTime;     //更新时间
	private Integer delFlag;         //删除标记
	
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
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}
	
	/**
	 * @param userName the userName to set
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	/**
	 * @return the userPasswd
	 */
	public String getUserPasswd() {
		return userPasswd;
	}
	
	/**
	 * @param userPasswd the userPasswd to set
	 */
	public void setUserPasswd(String userPasswd) {
		this.userPasswd = userPasswd;
	}
	
	/**
	 * @return the userPriv
	 */
	public Integer getUserPriv() {
		return userPriv;
	}
	
	/**
	 * @param userPriv the userPriv to set
	 */
	public void setUserPriv(Integer userPriv) {
		this.userPriv = userPriv;
	}
	
	/**
	 * @return the groupId
	 */
	public Integer getGroupId() {
		return groupId;
	}

	/**
	 * @param groupId the groupId to set
	 */
	public void setGroupId(Integer groupId) {
		this.groupId = groupId;
	}

	/**
	 * @return the isOnline
	 */
	public Integer getIsOnline() {
		return isOnline;
	}

	/**
	 * @param isOnline the isOnline to set
	 */
	public void setIsOnline(Integer isOnline) {
		this.isOnline = isOnline;
	}

	/**
	 * @return the imgPath
	 */
	public String getImgPath() {
		return imgPath;
	}

	/**
	 * @param imgPath the imgPath to set
	 */
	public void setImgPath(String imgPath) {
		this.imgPath = imgPath;
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





