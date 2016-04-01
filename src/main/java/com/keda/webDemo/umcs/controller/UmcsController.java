/**
 * @Title：UserDaoImpl.java 
 * @Copyright © Suzhou Keda Technology Co.Ltd. All Rights Reserved. 
 * @author： 代永强
 * @date: 2016年3月28日
 * @description:
**/
package com.keda.webDemo.umcs.controller;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import com.keda.webDemo.umcs.dto.Data;
import com.keda.webDemo.umcs.service.MsgManageService;
import com.keda.webDemo.umcs.service.UserManageService;
import com.keda.webDemo.umcs.service.UserService;
import com.keda.webDemo.umcs.tools.FilesUtil;

@Controller
@RequestMapping("/webDemo")
public class UmcsController {

	private final static Logger log = LoggerFactory.getLogger(UmcsController.class);

	@Resource
	private UserService userService;
	@Resource
	private UserManageService userManageService;
	@Resource
	private MsgManageService msgManageService;

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public void login(HttpServletRequest request, HttpServletResponse response) {

		log.info("用户校验成功，开始登录");
		String userName = request.getParameter("userName");
		Map<String, String[]> temp = request.getParameterMap();
		String userPasswd = request.getParameter("userPasswd");
		Data data = userManageService.login(userName, userPasswd);
		log.info("用户信息：" + toJson(data));
		try {
			response.getWriter().println(toJson(data));
		} catch (IOException e) {
			log.info("网络异常");
		}

	}

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public void logout(HttpServletRequest request, HttpServletResponse response) {

		log.info("用户校验成功，开始登出");
		int userId = Integer.valueOf(request.getParameter("userId"));
		Data data = userManageService.logout(userId);
		try {
			response.getWriter().println(toJson(data));
		} catch (IOException e) {
			log.info("网络异常");
		}

	}

	@RequestMapping(value = "/headImg/upload", method = RequestMethod.POST)
	public void uploadHeadImg(HttpServletRequest request, HttpServletResponse response) {

		log.info("用户校验成功，开始上传图片");
		MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
		MultipartFile imgFile = multiRequest.getFile("file");
		Data data = new Data();
		data = userManageService.uploadHeadImg(imgFile, 1, "root");
		try {
			response.getWriter().println(toJson(data));
		} catch (IOException e) {
			log.info("网络异常");
		}

	}

	@RequestMapping(value = "/createUser", method = RequestMethod.POST)
	public void createUser(HttpServletRequest request, HttpServletResponse response) {

		log.info("用户校验成功，开始创建新用户");
		String userName = request.getParameter("userName");
		String userPasswd = request.getParameter("userPasswd");
		Data data = userManageService.createUser(userName, userPasswd);
		try {
			response.getWriter().println(toJson(data));
		} catch (IOException e) {
			log.info("网络异常");
		}

	}
	
	@RequestMapping(value = "/queryUserList", method = RequestMethod.GET)
	public void queryUserList(HttpServletRequest request, HttpServletResponse response) {

		log.info("用户校验成功，开始查询好友列表");
		int userId = Integer.valueOf(request.getParameter("userId"));
		int userPriv = Integer.valueOf(request.getParameter("userPriv"));
		Data data = userManageService.getAllUsers(userId, userPriv);
		try {
			response.getWriter().println(toJson(data));
		} catch (IOException e) {
			log.info("网络异常");
		}

	}

	@RequestMapping(value = "/modifyUser", method = RequestMethod.POST)
	public void modifyUser(HttpServletRequest request, HttpServletResponse response) {

		log.info("用户校验成功，开始修改用户信息");
		int userId = Integer.valueOf(request.getParameter("userId"));
		String userName = request.getParameter("userName");
		String userPasswd = request.getParameter("userPasswd");
		Data data = userManageService.changeUser(userId, userName, userPasswd);
		try {
			response.getWriter().println(toJson(data));
		} catch (IOException e) {
			log.info("网络异常");
		}

	}

	@RequestMapping(value = "/createGroup", method = RequestMethod.POST)
	public void createGroup(HttpServletRequest request, HttpServletResponse response) {

		log.info("用户校验成功，开始创建分组");
		int userId = Integer.valueOf(request.getParameter("userId"));
		String groupName = request.getParameter("groupName");
		Data data = userManageService.createGroup(userId, groupName);
		try {
			response.getWriter().println(toJson(data));
		} catch (IOException e) {
			log.info("网络异常");
		}

	}

	@RequestMapping(value = "/addGroupMember", method = RequestMethod.POST)
	public void addGroupMember(HttpServletRequest request, HttpServletResponse response) {

		log.info("用户校验成功，开始添加分组用户");
		int userId = Integer.valueOf(request.getParameter("userId"));
		int groupId = Integer.valueOf(request.getParameter("groupId"));
		Data data = userManageService.addGroupMember(userId, groupId);
		try {
			response.getWriter().println(toJson(data));
		} catch (IOException e) {
			log.info("网络异常");
		}

	}

	@RequestMapping(value = "/modifyGroupMember", method = RequestMethod.POST)
	public void modifyGroupMember(HttpServletRequest request, HttpServletResponse response) {

		log.info("用户校验成功，修改分组用户");
		int userId = Integer.valueOf(request.getParameter("userId"));
		int groupId = Integer.valueOf(request.getParameter("groupId"));
		Data data = userManageService.changeGroupMember(userId, groupId);
		try {
			response.getWriter().println(toJson(data));
		} catch (IOException e) {
			log.info("网络异常");
		}

	}

	@RequestMapping(value = "/sendMsg", method = RequestMethod.POST)
	public void sendMsg(HttpServletRequest request, HttpServletResponse response) {

		log.info("用户校验成功，开始发送消息");
		Data data = msgManageService.sendMsg(request);
		try {
			response.getWriter().println(toJson(data));
		} catch (IOException e) {
			log.info("网络异常");
		}

	}

	@RequestMapping(value = "/recivMsg", method = RequestMethod.POST)
	public void recivMsg(HttpServletRequest request, HttpServletResponse response) {
		
		log.info("用户校验成功，开始接收消息");
		int recivUserId = Integer.valueOf(request.getParameter("recivUserId"));
		Data data = msgManageService.recivMsg(recivUserId);
		try {
			response.getWriter().println(toJson(data));
		} catch (IOException e) {
			log.info("网络异常");
		}

	}

	@RequestMapping(value = "/download", method = RequestMethod.GET)
	public void download(HttpServletRequest request, HttpServletResponse response) {
		
		log.info("用户校验成功，开始下载文件");
		String fileName = request.getParameter("fileName");
		response.setCharacterEncoding("utf-8");
		response.setContentType("multipart/form-data");
		response.setHeader("Content-Disposition", "attachment;fileName=" + fileName);

		try {
			
			InputStream inputStream = new FileInputStream(fileName);
			OutputStream outputStream = response.getOutputStream();
			FilesUtil.read(inputStream, outputStream);

		} catch (IOException e) {
			log.info("文件下载失败");
		}

	}

	private String toJson(Data data) {
		JSONObject json = new JSONObject(data);
		return json.toString();
	}

}
