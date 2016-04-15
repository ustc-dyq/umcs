/**
 * @Title：GroupServiceImpl.java 
 * @Copyright © Suzhou Keda Technology Co.Ltd. All Rights Reserved. 
 * @author： 代永强
 * @date: 2016年3月31日
 * @description:
**/
package com.keda.webDemo.umcs.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.keda.webDemo.umcs.dao.GroupDao;
import com.keda.webDemo.umcs.dao.UserDao;
import com.keda.webDemo.umcs.dao.dto.Group;
import com.keda.webDemo.umcs.service.GroupService;

@Service
public class GroupServiceImpl implements GroupService{
	
	@Resource
	private GroupDao groupDao;
	@Resource
	private UserDao userDao;
	
	/* (non-Javadoc)
	 * @see com.keda.webDemo.umcs.service.UserService#createGroup(java.lang.String, java.lang.String)
	 */
	@Override
	public Group createGroup(String groupName, int addUserId) {
		
		Group group = new Group();
		group.setGroupName(groupName);
		group.setAddUserId(addUserId);
		group.setAddTime(new Date());
		groupDao.insert(group);
		return group;
		
	}

	/* (non-Javadoc)
	 * @see com.keda.webDemo.umcs.service.UserService#changeGroup(com.keda.webDemo.umcs.dao.dto.Group)
	 */
	@Override
	public void changeGroup(Group group) {
		
		groupDao.update(group);
		
	}

	/* (non-Javadoc)
	 * @see com.keda.webDemo.umcs.service.UserService#queryAllGroups()
	 */
	@Override
	public List<Group> queryAllGroups() {
		List<Group> groups = groupDao.selectAll();
		return groups;
		
	}

	/* (non-Javadoc)
	 * @see com.keda.webDemo.umcs.service.UserService#queryByGroupId(int)
	 */
	@Override
	public Group queryByGroupId(int groupId) {
		
		return groupDao.select(groupId);
		
	}

	/* (non-Javadoc)
	 * @see com.keda.webDemo.umcs.service.UserService#queryByGroupName(String)
	 */
	@Override
	public Group queryByGroupName(String groupName) {
		
		return groupDao.selectByGroupName(groupName);
		
	}
	
	/* (non-Javadoc)
	 * @see com.keda.webDemo.umcs.service.UserService#deleteGroup(int)
	 */
	@Override
	public void deleteGroup(int groupId) {
		
		groupDao.delete(groupId);
		
	}

	
}





