/**
 * @Title：GroupDao.java 
 * @Copyright © Suzhou Keda Technology Co.Ltd. All Rights Reserved. 
 * @author： 代永强
 * @date: 2016年3月28日
 * @description:
**/
package com.keda.webDemo.umcs.dao;

import java.util.List;

import com.keda.webDemo.umcs.dao.dto.Group;

public interface GroupDao {

	/**
	 * 根据主键查询分组信息
	 * @param id
	 * @return
	 */
	public Group select(int id);
	
	/**
	 * 查询所有的分组
	 * @return
	 */
	public List<Group> selectAll();
	
	/**
	 * 根据分组名查询分组信息
	 * @param groupName
	 * @return
	 */
	public Group selectByGroupName(String groupName);
	
	/**
	 * 插入新建分组信息
	 * @param group
	 * @return
	 */
	public int insert(Group group);
	
	/**
	 * 更新分组信息
	 * @param group
	 * @return
	 */
	public int update(Group group);
	
	/**
	 * 删除分组
	 * @param id
	 * @return
	 */
	public int delete(int id);
	
}





