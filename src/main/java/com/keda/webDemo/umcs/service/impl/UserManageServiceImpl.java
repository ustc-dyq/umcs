/**
 * @Title：UserManageServiceImpl.java 
 * @Copyright © Suzhou Keda Technology Co.Ltd. All Rights Reserved. 
 * @author： 代永强
 * @date: 2016年3月30日
 * @description: 用户管理业务类
**/
package com.keda.webDemo.umcs.service.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.keda.webDemo.umcs.constants.Constants;
import com.keda.webDemo.umcs.dao.GroupDao;
import com.keda.webDemo.umcs.dao.UserDao;
import com.keda.webDemo.umcs.dao.dto.Group;
import com.keda.webDemo.umcs.dao.dto.User;
import com.keda.webDemo.umcs.dto.Data;
import com.keda.webDemo.umcs.service.UserManageService;
import com.keda.webDemo.umcs.tools.FilesUtil;

@Service
public class UserManageServiceImpl implements UserManageService {

	private final static Logger log = LoggerFactory.getLogger(UserManageServiceImpl.class);
	
	@Resource
	private UserDao userDao;
	@Resource
	private GroupDao groupDao;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.keda.webDemo.umcs.service.UserManageService#login(java.lang.String,
	 * java.lang.String)
	 */
	@Override
	public Data preLogin(String userName, String userPasswd) {

		log.info(userName+"登录");
		Data data = new Data();		
		User user = new User();
		user.setUserName(userName);
		user.setUserPasswd(userPasswd);
		user = userDao.selectByUser(user);
		if(Constants.LOGIN == user.getIsOnline()) {
			data.setSuccess(false);
			data.setMsg("登录失败，请勿重复登录！");
		} else {
			data.setSuccess(true);
			data.setData(user);
		}
				
		return data;

	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.keda.webDemo.umcs.service.UserManageService#login(java.lang.String,
	 * java.lang.String)
	 */
	@Override
	public Data login(String userName, String userPasswd) {

		log.info(userName+"登录");
		Data data = new Data();		
		User user = new User();
		user.setUserName(userName);
		user.setUserPasswd(userPasswd);
		user = userDao.selectByUser(user);
		if(Constants.LOGIN == user.getIsOnline()) {
			data.setSuccess(false);
			data.setMsg("该账户已经在线，请换个账户登录！");
		} else {
			user.setIsOnline(Constants.LOGIN);
			userDao.update(user);
			data.setSuccess(true);
			data.setData(user);
		}
		
		return data;

	}

	/* (non-Javadoc)
	 * @see com.keda.webDemo.umcs.service.UserManageService#logout(int)
	 */
	@Override
	public Data logout(int userId) {
		log.info(userId + "登出");
		Data data = new Data();
		User user = userDao.select(userId);
		user.setIsOnline(Constants.LOGOUT);
		user.setUpdateTime(new Date());
		userDao.update(user);
		data.setSuccess(true);
		return data;
		
	}

	
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.keda.webDemo.umcs.service.UserManageService#uploadHeadImg(org.
	 * springframework.web.multipart.MultipartFile)
	 */
	@Override
	public Data uploadHeadImg(MultipartFile imgFile, int userId) {
		
		log.info("用户" + userId + "上传图片");
		Data data = new Data();
		String imgName = imgFile.getOriginalFilename();
		String newImgName = userId + "-" + new Date().getTime() + "-" + String.valueOf(imgName);
		String imgPath = Constants.HEADIMGPATH + newImgName;
		try {
			FilesUtil.uploadFile(imgFile,imgPath);
		} catch (IOException e) {
			data.setSuccess(false);
			data.setMsg("上传失败，请重新上传");
			log.info("上传失败：" + e.getStackTrace());
			return data;
		}
		
		User user = userDao.select(userId);;
		FilesUtil.deleteFile(Constants.HEADIMGPATH + user.getImgName());
		user.setImgName(newImgName);;
		userDao.update(user);
		data.setSuccess(true);
		data.setData(newImgName);
		return data;
		
	}

	@Override
	public Data uploadFile(MultipartFile file, int sendUserId) {
		
		log.info("用户" + sendUserId + "上传文件");
		Data data = new Data();
		String fileName = file.getOriginalFilename();
		String newFileName = sendUserId + new Date().getTime() + "-" + String.valueOf(fileName);
		String localPath = Constants.FILESAVEPATH + newFileName;
		try {
			FilesUtil.uploadFile(file,localPath);
		} catch (IOException e) {
			data.setSuccess(false);
			data.setMsg("上传失败，请重新上传");
			log.info("上传失败：" + e.getStackTrace());
			return data;
		}
		
		data.setSuccess(true);
		data.setData(newFileName);
		return data;
		
	}
	
	/*
	 * (non-Javadoc)
	 * 获取成员列表
	 * @see
	 * com.keda.webDemo.umcs.service.UserManageService#getAllUsers(java.lang.
	 * String)
	 */
	@Override
	public Data getAllUsers(int userId,int userPriv) {
		
		log.info("用户" + userId + "查询所有的好友");
		Data data = new Data();
		Map<String,Object> map = new HashMap<String,Object>();
		List<User> users = new ArrayList<User>();
		List<Group> groups = new ArrayList<Group>();
		if(Constants.MANAGEMENTPRIV == userPriv) {
			//查询所有未分组的成员
			users = userDao.selectAll();
			//查询所有分组及其成员
			groups = groupDao.selectAll();		
			
		} else {
			//查询用户详情
			User user = userDao.select(userId);
			//查询管理员信息
			List<User> manages = userDao.selectAllManages();
			users.addAll(manages);
			if(Constants.NOTINGROUP != user.getGroupId()) {
				Group group = groupDao.select(user.getGroupId());;
				users.addAll(userDao.selectByGroupId(group.getId()));
				groups.add(group);
			} else {
				users.add(user);
			}
			
		}
		map.put("groups", groups);
		map.put("users", users);
		data.setData(map);
		data.setSuccess(true);
		return data;
	}
	
	
	

	/* (non-Javadoc)
	 * @see com.keda.webDemo.umcs.service.UserManageService#createUser(java.lang.String, java.lang.String)
	 */
	@Override
	public Data createUser(String userName, String userPasswd) {
		
		log.info("创建新用户");
		Data data = new Data();
		User user = new User();
		user.setUserName(userName);
		User userInfo = userDao.selectByUser(user);
		
		if(null != userInfo) {
			log.info("用户已存在，添加用户失败");
			data.setSuccess(false);
			data.setMsg("用户已存在，添加用户失败");
		} else {
			user.setUserPasswd(userPasswd);
			user.setGroupId(Constants.NOTINGROUP);
			user.setUserPriv(Constants.NORMALPRIV);
			user.setAddTime(new Date());
			userDao.insert(user);
			data.setSuccess(true);
			data.setData(user);
		}
		
		return data;
		
	}

	/* (non-Javadoc)
	 * @see com.keda.webDemo.umcs.service.UserManageService#deleteUser(int)
	 */
	@Override
	public Data deleteUser(int userId) {
		
		log.info("删除用户");
		userDao.delete(userId);
		Data data = new Data();
		data.setSuccess(true);
		return data;
		
	}

	/* (non-Javadoc)
	 * @see com.keda.webDemo.umcs.service.UserManageService#changeUser(com.keda.webDemo.umcs.dao.dto.User)
	 */
	@Override
	public Data changeUser(int userId, String userName, String userPasswd) {
		
		log.info("修改用户信息");
		Data data = new Data();
		User user = userDao.select(userId);
		if(user.getUserName().equals(userName)) {
			user.setUserPasswd(userPasswd);
			user.setUpdateTime(new Date());
			userDao.update(user);;
			data.setSuccess(true);
			data.setData(user);
		} else {
			User temp = new User();
			temp.setUserName(userName);
			User userInfo = userDao.selectByUser(temp);
			if(null != userInfo) {
				log.info("用户已存在，修改用户失败");
				data.setSuccess(false);
				data.setMsg("用户已存在，修改用户失败");
			} else {
				user.setUserName(userName);
				user.setUserPasswd(userPasswd);
				user.setUpdateTime(new Date());
				userDao.update(user);
				data.setSuccess(true);
				data.setData(user);
			}
		}
		
		return data;
		
	}

	/* (non-Javadoc)
	 * @see com.keda.webDemo.umcs.service.UserManageService#createGroup(com.keda.webDemo.umcs.dao.dto.Group)
	 */
	@Override
	public Data createGroup(int userId, String groupName) {
		
		log.info("创建分组");
		Group group = groupDao.selectByGroupName(groupName);
		Data data = new Data();
		
		if(null != group) {
			data.setSuccess(false);
			data.setMsg("分组名重复！");
		} else {
			group = new Group();
			group.setGroupName(groupName);
			group.setAddUserId(userId);
			group.setAddTime(new Date());
			groupDao.insert(group);
			data.setSuccess(true);
			data.setData(group);
		}
		
		return data;
		
	}

	/* (non-Javadoc)
	 * @see com.keda.webDemo.umcs.service.UserManageService#changeGroup(com.keda.webDemo.umcs.dao.dto.Group)
	 */
	@Override
	public Data changeGroup(int id, String groupName) {
		
		log.info("修改分组");
		
		Group temp = groupDao.selectByGroupName(groupName);
		Data data = new Data();
		if(null != temp) {
			data.setSuccess(false);
			data.setMsg("分组名重复！");
		} else {
			Group group = new Group();
			group.setId(id);
			group.setGroupName(groupName);
			group.setUpdateTime(new Date());
			groupDao.update(group);
			data.setSuccess(true);
			data.setData(group);
		}		
		return data;
		
	}

	/* (non-Javadoc)
	 * @see com.keda.webDemo.umcs.service.UserManageService#deleteGroup(int)
	 */
	@Override
	public Data deleteGroup(int groupId) {
		
		log.info("删除分组");
		//删除分组中的用户
		List<User> users = userDao.selectByGroupId(groupId);
		for(User user : users) {
			user.setGroupId(Constants.NOTINGROUP);
			userDao.update(user);
		}
		//删除分组信息
		groupDao.delete(groupId);
		
		Data data = new Data();
		data.setSuccess(true);
		return data;
		
	}

	
	/* (non-Javadoc)
	 * @see com.keda.webDemo.umcs.service.UserManageService#changeGroupMember(int, int)
	 */
	@Override
	public Data changeGroupMember(int userId, int groupId) {
		
		//修改成员所在分组
		User user = new User();
		user.setId(userId);
		user.setGroupId(groupId);
		userDao.update(user);
		Data data = new Data();
		data.setSuccess(true);
		data.setData(user);
		return data;
		
	}


	/* (non-Javadoc)
	 * @see com.keda.webDemo.umcs.service.UserManageService#queryUserById(int)
	 */
	@Override
	public Data queryUserById(int id) {
		Data data = new Data();
		data.setData(userDao.select(id));
		data.setSuccess(true);;
		return data;
	}

}
