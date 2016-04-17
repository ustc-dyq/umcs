/**
 * @Title：CompositeService.java 
 * @Copyright © Suzhou Keda Technology Co.Ltd. All Rights Reserved. 
 * @author： 代永强
 * @date: 2016年3月29日
 * @description:
**/
package com.keda.webDemo.umcs.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.keda.webDemo.umcs.dao.dto.UploadFile;
import com.keda.webDemo.umcs.dao.dto.User;
import com.keda.webDemo.umcs.dto.Data;

public interface UserManageService {

	public Data preLogin(String userName, String userPasswd);
	public Data login(String userName, String userPasswd);
	public Data logout(int userId);
	public Data uploadHeadImg(MultipartFile imgFile, int userId);
	public Data uploadFile(MultipartFile file, int sendUserId);
	public UploadFile queryFileInfo(int id);
	public Data queryFileList(Integer recivUserId, Integer sendUserId);
	public Data recivFile(Integer recivUserId);
	public Data queryUserById(int id);
	public Data getAllUsers(int userId, int userPriv);
	public Data createUsers(List<User> users);
	public Data createUser(String userName, String userPasswd);
	public Data deleteUsers(List<Integer> userIds);
	public Data deleteUser(int userId);
	public Data changeUsers(List<User> users);
	public Data changeUser(int userId, String userName, String userPasswd);
	public Data createGroup(int userId, String groupName);
	public Data changeGroup(int id, String groupName);
	public Data deleteGroup(int groupId);
	public Data addGroupMember(int userId, int groupId);
	public Data changeGroupMember(int userId, int groupId);
	public Data deleteGroupMember(int userId, int groupId);
	public Data deleteFile(int id);
	
	
}





