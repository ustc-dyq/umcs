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

import com.keda.webDemo.umcs.dao.dto.UploadFile;
import com.keda.webDemo.umcs.dto.Data;
import com.keda.webDemo.umcs.service.MsgManageService;
import com.keda.webDemo.umcs.service.UserManageService;
import com.keda.webDemo.umcs.service.UserService;
import com.keda.webDemo.umcs.tools.FilesUtil;

@Controller
@RequestMapping("/api")
public class UmcsController {

	private final static Logger log = LoggerFactory.getLogger(UmcsController.class);

	@Resource
	private UserService userService;
	@Resource
	private UserManageService userManageService;
	@Resource
	private MsgManageService msgManageService;

	@RequestMapping(value = "/preLogin", method = RequestMethod.POST)
	public void preLogin(HttpServletRequest request, HttpServletResponse response) {

		log.info("用户校验成功，开始登录");
		response.setCharacterEncoding("utf-8");
		String userName = request.getParameter("userName");
		String userPasswd = request.getParameter("userPasswd");
		log.info("用户名：" + userName);
		Data data = userManageService.preLogin(userName, userPasswd);
		log.info("登录用户信息：" + toJson(data));
		try {
			response.getWriter().println(toJson(data));
		} catch (IOException e) {
			log.info("网络异常");
		}

	}
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public void login(HttpServletRequest request, HttpServletResponse response) {

		log.info("用户校验成功，开始登录");
		response.setCharacterEncoding("utf-8");
		String userName = request.getParameter("userName");
		String userPasswd = request.getParameter("userPasswd");
		log.info("用户名：" + userName);
		Data data = userManageService.login(userName, userPasswd);
		log.info("登录用户信息：" + toJson(data));
		try {
			response.getWriter().println(toJson(data));
		} catch (IOException e) {
			log.info("网络异常");
		}

	}

	@RequestMapping(value = "/queryUserById", method = RequestMethod.GET)
	public void queryUserById(HttpServletRequest request, HttpServletResponse response) {

		log.info("用户校验成功，开始查询用户信息");
		response.setCharacterEncoding("utf-8");
		Data data = new Data();
		
		try {
			int id = Integer.valueOf(request.getParameter("id"));
			log.info("用户id：" + id);
			data = userManageService.queryUserById(id);
			log.info("查询用户信息：" + toJson(data));
		} catch(NumberFormatException e) {
			log.error("查询参数有误" + e);
			data.setSuccess(false);
			data.setMsg("查询参数有误");
		}
		
		try {
			response.getWriter().println(toJson(data));
		} catch (IOException e) {
			log.info("网络异常");
		}

	}
	
	@RequestMapping(value = "/logout", method = RequestMethod.POST)
	public void logout(HttpServletRequest request, HttpServletResponse response) {

		log.info("用户校验成功，开始登出");
		response.setCharacterEncoding("utf-8");
		Data data = new Data();
		
		try {
			int userId = Integer.valueOf(request.getParameter("userId"));
			log.info("用户id：" + userId);
			data = userManageService.logout(userId);
			log.info("登出用户信息：" + toJson(data));
		} catch(NumberFormatException e) {
			log.error("登出参数有误" + e);
			data.setSuccess(false);
			data.setMsg("登出参数有误");
		}
		
		try {
			response.getWriter().println(toJson(data));
		} catch (IOException e) {
			log.info("网络异常");
		}

	}

	@RequestMapping(value = "/headImg/upload", method = RequestMethod.POST)
	public void uploadHeadImg(HttpServletRequest request, HttpServletResponse response) {

		log.info("用户校验成功，开始上传图片");
		Data data = new Data();
		
		try {			
			MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
			int userId = Integer.valueOf(request.getParameter("id"));
			MultipartFile imgFile = multiRequest.getFile("imgFile");			
			data = userManageService.uploadHeadImg(imgFile, userId);
			log.info("上传结果：" + toJson(data));			
		} catch(NumberFormatException e) {
			log.error("上传参数有误" + e);
			data.setSuccess(false);
			data.setMsg("上传参数有误");
		}
		
		try {
			response.getWriter().println(toJson(data));
		} catch (IOException e) {
			log.info("网络异常");
		}

	}

	@RequestMapping(value = "/createUser", method = RequestMethod.POST)
	public void createUser(HttpServletRequest request, HttpServletResponse response) {

		log.info("用户校验成功，开始创建新用户");
		response.setCharacterEncoding("utf-8");
		String userName = request.getParameter("userName");
		String userPasswd = request.getParameter("userPasswd");
		Data data = userManageService.createUser(userName, userPasswd);
		log.info("用户创建结果：" + toJson(data));
		try {
			response.getWriter().println(toJson(data));
		} catch (IOException e) {
			log.info("网络异常");
		}

	}
	
	@RequestMapping(value = "/deleteUser", method = RequestMethod.POST)
	public void deleteUser(HttpServletRequest request, HttpServletResponse response) {

		log.info("用户校验成功，开始删除用户");
		response.setCharacterEncoding("utf-8");
		Data data = new Data();
		
		try {
			int id = Integer.valueOf(request.getParameter("id"));
			log.info("用户id：" + id);
			data = userManageService.deleteUser(id);
			log.info("删除用户结果：" + toJson(data));
		} catch(NumberFormatException e) {
			log.error("删除用户参数有误" + e);
			data.setSuccess(false);
			data.setMsg("删除用户参数有误");
		}
		
		try {
			response.getWriter().println(toJson(data));
		} catch (IOException e) {
			log.info("网络异常");
		}

	}
	
	@RequestMapping(value = "/queryUserList", method = RequestMethod.GET)
	public void queryUserList(HttpServletRequest request, HttpServletResponse response) {

		log.info("用户校验成功，开始查询好友列表");
		response.setCharacterEncoding("utf-8");
		Data data = new Data();
		
		try {			
			int userId = Integer.valueOf(request.getParameter("userId"));
			int userPriv = Integer.valueOf(request.getParameter("userPriv"));
			log.info("用户id：" + userId);
			data = userManageService.getAllUsers(userId, userPriv);
			log.info("查询用户列表信息：" + toJson(data));
		} catch(NumberFormatException e) {
			log.error("查询用户列表参数有误" + e);
			data.setSuccess(false);
			data.setMsg("查询用户列表参数有误");
		}
		
		try {
			response.getWriter().println(toJson(data));
		} catch (IOException e) {
			log.info("网络异常");
		}

	}
	
	@RequestMapping(value = "/modifyUser", method = RequestMethod.POST)
	public void modifyUser(HttpServletRequest request, HttpServletResponse response) {

		log.info("用户校验成功，开始修改用户信息");
		response.setCharacterEncoding("utf-8");
        Data data = new Data();
		
		try {			
			int userId = Integer.valueOf(request.getParameter("userId"));
			String userName = request.getParameter("userName");
			String userPasswd = request.getParameter("userPasswd");
			data = userManageService.changeUser(userId, userName, userPasswd);
			log.info("修改用户信息结果：" + toJson(data));
		} catch(NumberFormatException e) {
			log.error("修改用户信息参数有误" + e);
			data.setSuccess(false);
			data.setMsg("修改用户信息参数有误");
		}
		
		try {
			response.getWriter().println(toJson(data));
		} catch (IOException e) {
			log.info("网络异常");
		}

	}

	@RequestMapping(value = "/createGroup", method = RequestMethod.POST)
	public void createGroup(HttpServletRequest request, HttpServletResponse response) {

		log.info("用户校验成功，开始创建分组");
		response.setCharacterEncoding("utf-8");
        Data data = new Data();
		
		try {			
			int userId = Integer.valueOf(request.getParameter("userId"));
			String groupName = request.getParameter("groupName");
			data = userManageService.createGroup(userId, groupName);
			log.info("创建分组结果：" + toJson(data));
		} catch(NumberFormatException e) {
			log.error("创建分组参数有误" + e);
			data.setSuccess(false);
			data.setMsg("创建分组参数有误");
		}
		
		try {
			response.getWriter().println(toJson(data));
		} catch (IOException e) {
			log.info("网络异常");
		}

	}

	@RequestMapping(value = "/changeGroup", method = RequestMethod.POST)
	public void changeGroup(HttpServletRequest request, HttpServletResponse response) {

		log.info("用户校验成功，开始修改分组");
		response.setCharacterEncoding("utf-8");
        Data data = new Data();
		
		try {			
			int id = Integer.valueOf(request.getParameter("id"));
			String groupName = request.getParameter("groupName");
			data = userManageService.changeGroup(id, groupName);
			log.info("修改用户分组结果：" + toJson(data));
		} catch(NumberFormatException e) {
			log.error("修改用户分组参数有误" + e);
			data.setSuccess(false);
			data.setMsg("修改用户分组参数有误");
		}
		
		try {
			response.getWriter().println(toJson(data));
		} catch (IOException e) {
			log.info("网络异常");
		}

	}
	
	@RequestMapping(value = "/deleteGroup", method = RequestMethod.POST)
	public void deleteGroup(HttpServletRequest request, HttpServletResponse response) {

		log.info("用户校验成功，开始删除分组");
		response.setCharacterEncoding("utf-8");
        Data data = new Data();
		
		try {			
			int id = Integer.valueOf(request.getParameter("id"));
			data = userManageService.deleteGroup(id);
			log.info("删除分组结果：" + toJson(data));
		} catch(NumberFormatException e) {
			log.error("删除分组参数有误" + e);
			data.setSuccess(false);
			data.setMsg("删除分组参数有误");
		}
		
		try {
			response.getWriter().println(toJson(data));
		} catch (IOException e) {
			log.info("网络异常");
		}

	}

	@RequestMapping(value = "/modifyGroupMember", method = RequestMethod.POST)
	public void modifyGroupMember(HttpServletRequest request, HttpServletResponse response) {

		log.info("用户校验成功，修改分组用户");
		response.setCharacterEncoding("utf-8");
        Data data = new Data();
		
		try {			
			int userId = Integer.valueOf(request.getParameter("userId"));
			int groupId = Integer.valueOf(request.getParameter("groupId"));
			data = userManageService.changeGroupMember(userId, groupId);
			log.info("修改分组用户结果：" + toJson(data));
		} catch(NumberFormatException e) {
			log.error("修改分组用户参数有误" + e);
			data.setSuccess(false);
			data.setMsg("修改分组用户参数有误");
		}
		
		try {
			response.getWriter().println(toJson(data));
		} catch (IOException e) {
			log.info("网络异常");
		}

	}

	@RequestMapping(value = "/sendMsg", method = RequestMethod.POST)
	public void sendMsg(HttpServletRequest request, HttpServletResponse response) {

		log.info("用户校验成功，开始发送消息");
		response.setCharacterEncoding("utf-8");
		Data data = msgManageService.sendMsg(request);
		log.info("消息发送结果：" + toJson(data));
		try {
			response.getWriter().println(toJson(data));
		} catch (IOException e) {
			log.info("网络异常");
		}

	}

	@RequestMapping(value = "/recivMsg", method = RequestMethod.GET)
	public void recivMsg(HttpServletRequest request, HttpServletResponse response) {
		
		log.info("用户校验成功，开始接收消息");
		response.setCharacterEncoding("utf-8");
		int recivUserId = Integer.valueOf(request.getParameter("recivUserId"));
		int sendUserId;
		String sendId = request.getParameter("sendUserId");
		if(null == sendId || "".equals(sendId)) {
			sendUserId = 0;
		} else {
			sendUserId = Integer.valueOf(sendId);
		}
		Data data = msgManageService.recivMsg(recivUserId, sendUserId);
		log.info("接收消息：" + toJson(data));
		try {
			response.getWriter().println(toJson(data));
		} catch (IOException e) {
			log.info("网络异常");
		}

	}
	
	

	@RequestMapping(value = "/queryHistoryMsg", method = RequestMethod.GET)
	public void queryHistoryMsg(HttpServletRequest request, HttpServletResponse response) {

		log.info("用户校验成功，开始查询历史消息记录");
		response.setCharacterEncoding("utf-8");
        Data data = new Data();
        
		try {			
			int recivUserId = Integer.valueOf(request.getParameter("recivUserId"));
			int sendUserId = Integer.valueOf(request.getParameter("sendUserId"));
			int limit = Integer.valueOf(request.getParameter("limit"));
			data = msgManageService.queryHistoryMsg(recivUserId, sendUserId, limit);
			log.info("查询历史消息：" + toJson(data));
		} catch(NumberFormatException e) {
			log.error("查询历史参数有误" + e);
			data.setSuccess(false);
			data.setMsg("查询历史参数有误");
		}
		
		try {
			response.getWriter().println(toJson(data));
		} catch (IOException e) {
			log.info("网络异常");
		}

	}

	@RequestMapping(value = "/file/upload", method = RequestMethod.POST)
	public void uploadFile(HttpServletRequest request, HttpServletResponse response) {

		log.info("用户校验成功，开始上传文件");
		Data data = new Data();		
		
		try {			
			MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
			int recivUserId = Integer.valueOf(request.getParameter("recivUserId"));
			int sendUserId = Integer.valueOf(request.getParameter("sendUserId"));
			MultipartFile file = multiRequest.getFile("file");
			data = userManageService.uploadFile(file, sendUserId, recivUserId);
			log.info("上传文件结果：" + toJson(data));
		} catch(NumberFormatException e) {
			log.error("上传文件参数有误" + e);
			data.setSuccess(false);
			data.setMsg("上传文件参数有误");
		}
		
		try {
			response.getWriter().println(toJson(data));
		} catch (IOException e) {
			log.info("网络异常");
		}

	}
	
	@RequestMapping(value = "/queryFileList", method = RequestMethod.GET)
	public void queryFileList(HttpServletRequest request, HttpServletResponse response) {

		log.info("用户校验成功，开始查询文件列表");
		response.setCharacterEncoding("utf-8");
		Integer recivUserId = Integer.valueOf(request.getParameter("recivUserId"));
		String sendId = request.getParameter("sendUserId");
		Integer sendUserId = null;
		if(null != sendId && !"".equals(sendId)) {
			sendUserId = Integer.valueOf(sendId);
		}
		
		Data data = new Data();
		data = userManageService.queryFileList(recivUserId, sendUserId);
		log.info("查询文件列表结果：" + toJson(data));
		try {
			response.getWriter().println(toJson(data));
		} catch (IOException e) {
			log.info("网络异常");
		}

	}	
	
	@RequestMapping(value = "/recivFile", method = RequestMethod.GET)
	public void recivFile(HttpServletRequest request, HttpServletResponse response) {
		
		log.info("用户校验成功，开始接收文件");
		response.setCharacterEncoding("utf-8");
        Data data = new Data();
        
		try {			
			Integer recivUserId = Integer.valueOf(request.getParameter("recivUserId"));
			data = userManageService.recivFile(recivUserId);
			log.info("接收文件结果：" + toJson(data));
		} catch(NumberFormatException e) {
			log.error("接收文件参数有误" + e);
			data.setSuccess(false);
			data.setMsg("接收文件参数有误");
		}
		
		try {
			response.getWriter().println(toJson(data));
		} catch (IOException e) {
			log.info("网络异常");
		}

	}
	
	@RequestMapping(value = "/download", method = RequestMethod.GET)
	public void download(HttpServletRequest request, HttpServletResponse response) {
		
		log.info("用户校验成功，开始下载文件");
		response.setCharacterEncoding("utf-8");
		int id = Integer.valueOf(request.getParameter("id"));
		UploadFile uploadFile = userManageService.queryFileInfo(id);
		response.setCharacterEncoding("utf-8");
		response.setContentType("multipart/form-data");
		response.setHeader("Content-Disposition", "attachment;fileName=" + uploadFile.getLocalPath());

		try {
			
			InputStream inputStream = new FileInputStream(uploadFile.getLocalPath());
			OutputStream outputStream = response.getOutputStream();
			FilesUtil.read(inputStream, outputStream);

		} catch (IOException e) {
			log.info("文件下载失败" + e.getStackTrace());
		}

	}

	@RequestMapping(value = "/deleteFile", method = RequestMethod.GET)
	public void deleteFile(HttpServletRequest request, HttpServletResponse response) {
		
		log.info("用户校验成功，开始删除文件");
		response.setCharacterEncoding("utf-8");
        Data data = new Data();
        
		try {			
			int id = Integer.valueOf(request.getParameter("id"));
			data = userManageService.deleteFile(id);
			log.info("删除文件结果：" + toJson(data));
		} catch(NumberFormatException e) {
			log.error("删除文件参数有误" + e);
			data.setSuccess(false);
			data.setMsg("删除文件参数有误");
		}
		
		try {
			response.getWriter().println(toJson(data));
		} catch (IOException e) {
			log.info("网络异常");
		}

	}
	
	private String toJson(Data data) {
		JSONObject json = new JSONObject(data);
		return json.toString();
	}

}
