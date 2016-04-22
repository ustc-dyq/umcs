/**
 * @Title：CompositeService.java 
 * @Copyright © Suzhou Keda Technology Co.Ltd. All Rights Reserved. 
 * @author： 代永强
 * @date: 2016年3月29日
 * @description:
**/
package com.keda.webDemo.umcs.service;

import org.springframework.web.multipart.MultipartFile;

import com.keda.webDemo.umcs.dto.Data;

public interface UserManageService {

	/**
	 * 预登录，主要判断是否已在线
	 * @param userName
	 * @param userPasswd
	 * @return
	 */
	public Data preLogin(String userName, String userPasswd);
	
	/**
	 * 登录，将用户状态置为在线
	 * @param userName
	 * @param userPasswd
	 * @return
	 */
	public Data login(String userName, String userPasswd);
	
	/**
	 * 登出并下线
	 * @param userId
	 * @return
	 */
	public Data logout(int userId);
	
	/**
	 * 上传头像
	 * @param imgFile
	 * @param userId
	 * @return
	 */
	public Data uploadHeadImg(MultipartFile imgFile, int userId);
	
	/**
	 * 上传文件
	 * @param file
	 * @param sendUserId
	 * @return
	 */
	public Data uploadFile(MultipartFile file, int sendUserId);
	
	/**
	 * 查询用户信息
	 * @param id
	 * @return
	 */
	public Data queryUserById(int id);
	
	/**
	 * 获取所有好友信息
	 * @param userId
	 * @param userPriv
	 * @return
	 */
	public Data getAllUsers(int userId, int userPriv);
	
	/**
	 * 创建新用户
	 * @param userName
	 * @param userPasswd
	 * @return
	 */
	public Data createUser(String userName, String userPasswd);
	
	/**
	 * 删除用户
	 * @param userId
	 * @return
	 */
	public Data deleteUser(int userId);
	
	/**
	 * 修改用户信息
	 * @param userId
	 * @param userName
	 * @param userPasswd
	 * @return
	 */
	public Data changeUser(int userId, String userName, String userPasswd);
	
	/**
	 * 创建分组
	 * @param userId
	 * @param groupName
	 * @return
	 */
	public Data createGroup(int userId, String groupName);
	
	/**
	 * 修改分组信息
	 * @param id
	 * @param groupName
	 * @return
	 */
	public Data changeGroup(int id, String groupName);
	
	/**
	 * 删除分组
	 * @param groupId
	 * @return
	 */
	public Data deleteGroup(int groupId);
	
	/**
	 * 修改分组成员
	 * @param userId
	 * @param groupId
	 * @return
	 */
	public Data changeGroupMember(int userId, int groupId);
	
	
}





