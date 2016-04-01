/**
 * @Title：Group.java 
 * @Copyright © Suzhou Keda Technology Co.Ltd. All Rights Reserved. 
 * @author： 代永强
 * @date: 2016年3月28日
 * @description: 分组信息
**/
package com.keda.webDemo.umcs.dao.dto;

import java.util.Date;
import java.util.List;

public class Group {

	private Integer id;
	private String groupName;       //分组名
	private Integer addUserId;     //创建人名称
	private Date addTime;	        //创建时间
	private List<User> users;
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
	 * @return the groupName
	 */
	public String getGroupName() {
		return groupName;
	}
	
	/**
	 * @param groupName the groupName to set
	 */
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	
	/**
	 * @return the addUserName
	 */
	public Integer getAddUserId() {
		return addUserId;
	}
	
	/**
	 * @param addUserName the addUserName to set
	 */
	public void setAddUserId(Integer addUserId) {
		this.addUserId = addUserId;
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
	 * @return the user
	 */
	public List<User> getUsers() {
		return users;
	}

	/**
	 * @param user the user to set
	 */
	public void setUsers(List<User> users) {
		this.users = users;
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





