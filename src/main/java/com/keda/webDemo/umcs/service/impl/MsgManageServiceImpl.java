/**
 * @Title：MsgManageServiceImpl.java 
 * @Copyright © Suzhou Keda Technology Co.Ltd. All Rights Reserved. 
 * @author： 代永强
 * @date: 2016年3月31日
 * @description:
**/
package com.keda.webDemo.umcs.service.impl;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.keda.webDemo.umcs.constants.Constants;
import com.keda.webDemo.umcs.dao.RecivMsgDao;
import com.keda.webDemo.umcs.dao.SendMsgDao;
import com.keda.webDemo.umcs.dao.dto.RecivMsg;
import com.keda.webDemo.umcs.dao.dto.SendMsg;
import com.keda.webDemo.umcs.dto.Data;
import com.keda.webDemo.umcs.service.MsgManageService;
import com.keda.webDemo.umcs.tools.FilesUtil;

@Service
@Transactional
public class MsgManageServiceImpl implements MsgManageService {

	private final static Logger log = LoggerFactory.getLogger(MsgManageServiceImpl.class);

	@Resource
	private SendMsgDao sendMsgDao;
	@Resource
	private RecivMsgDao recivMsgDao;
	
	
	private void saveMsg(int sendUserId, int recivUserId, String msg, int msgType) {
		
		log.info("保存用户消息入库");
		SendMsg sendMsg = new SendMsg();
		RecivMsg recivMsg = new RecivMsg();
		sendMsg.setSendUserId(sendUserId);
		sendMsg.setRecivUserId(recivUserId);
		sendMsg.setMsg(msg);
		sendMsg.setMsgType(msgType);
		sendMsg.setSendTime(new Date());
		recivMsg.setSendUserId(sendUserId);
		recivMsg.setRecivUserId(recivUserId);
		recivMsg.setMsg(msg);
		recivMsg.setMsgType(msgType);
		recivMsg.setRecivTime(new Date());
		//将消息分别保存至发送表和接收表
		sendMsgDao.insert(sendMsg);
		recivMsgDao.insert(recivMsg);
		
	}



	/* (non-Javadoc)
	 * @see com.keda.webDemo.umcs.service.MsgManageService#sendMsg(javax.servlet.http.HttpServletRequest)
	 */
	@Override
	public Data sendMsg(HttpServletRequest request) {
		
		log.info("用户发送消息");
		Data data = new Data();
		int sendUserId = Integer.valueOf(request.getParameter("sendUserId"));
		int recivUserId = Integer.valueOf(request.getParameter("recivUserId"));
		int msgType = Integer.valueOf(request.getParameter("msgType"));
		log.info("用户" + sendUserId + "发送消息");
		if(Constants.TEXTTYPE == msgType) {
			String msg = request.getParameter("msg");
			saveMsg(sendUserId, recivUserId, msg, msgType);
			data.setSuccess(true);
		} else {
			MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
			MultipartFile file = multiRequest.getFile("file");
			String fileName = file.getOriginalFilename();
			String newFileName = sendUserId + "-" + new Date() + fileName;
			String filePath = Constants.FILESAVEPATH + newFileName;
			try {
				FilesUtil.uploadFile(file, filePath);
			} catch (IOException e) {
				log.info("用户" + sendUserId + "文件发送失败：" + e.getStackTrace());
				data.setSuccess(false);
				data.setMsg("文件发送失败，请重新发送");
			}
			saveMsg(sendUserId, recivUserId, filePath, msgType);
			data.setSuccess(true);
			data.setData(filePath);
		}
		return data;
		
	}



	/* (non-Javadoc)
	 * @see com.keda.webDemo.umcs.service.MsgManageService#recivMsg(int)
	 */
	@Override
	public Data recivMsg(int recivUserId) {
		
		log.info("用户" + recivUserId + "接收消息");
		Data data = new Data();		
		List<RecivMsg> recivMsgs = recivMsgDao.selectByRecivUserId(recivUserId);
		if(null == recivMsgs || 0 >= recivMsgs.size()) {
			data.setSuccess(false);
			return data;
		}
		for(RecivMsg msg : recivMsgs) {
			recivMsgDao.delete(msg.getId());
		}
		data.setSuccess(true);
		data.setData(recivMsgs);
		return data;
	}

}
