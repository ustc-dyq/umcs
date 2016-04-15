/**
 * @Title：GroupService.java 
 * @Copyright © Suzhou Keda Technology Co.Ltd. All Rights Reserved. 
 * @author： 代永强
 * @date: 2016年3月31日
 * @description:
**/
package com.keda.webDemo.umcs.service;

import java.util.List;

import com.keda.webDemo.umcs.dao.dto.Group;

public interface GroupService {
	
	/**
	 * 创建分组
	 * @param groupName
	 * @param addUserName
	 */
	public Group createGroup(String groupName,int addUserId);
	
	/**
	 * 修改分组信息
	 * @param group
	 */
	public void changeGroup(Group group);
	
	/**
	 * 查询所有分组
	 * @return
	 */
	public List<Group> queryAllGroups();
	
	/**
	 * 根据主键查询分组信息
	 * @param groupId
	 * @return
	 */
	public Group queryByGroupId(int groupId);
	
	public Group queryByGroupName(String groupName);
	
	/**
	 * 删除分组
	 * @param groupId
	 */
	public void deleteGroup(int groupId);
	
	
}





