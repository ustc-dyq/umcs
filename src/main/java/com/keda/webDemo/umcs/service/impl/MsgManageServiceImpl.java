/**
 * @Title：MsgManageServiceImpl.java 
 * @Copyright © Suzhou Keda Technology Co.Ltd. All Rights Reserved. 
 * @author： 代永强
 * @date: 2016年3月31日
 * @description:
**/
package com.keda.webDemo.umcs.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.session.RowBounds;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.keda.webDemo.umcs.constants.Constants;
import com.keda.webDemo.umcs.dao.RecivMsgDao;
import com.keda.webDemo.umcs.dao.SendMsgDao;
import com.keda.webDemo.umcs.dao.UserDao;
import com.keda.webDemo.umcs.dao.dto.RecivMsg;
import com.keda.webDemo.umcs.dao.dto.SendMsg;
import com.keda.webDemo.umcs.dao.dto.User;
import com.keda.webDemo.umcs.dto.Data;
import com.keda.webDemo.umcs.service.MsgManageService;
import com.keda.webDemo.umcs.tools.ComparatorSendMsg;

@Service
@Transactional
public class MsgManageServiceImpl implements MsgManageService {

	private final static Logger log = LoggerFactory.getLogger(MsgManageServiceImpl.class);

	@Resource
	private SendMsgDao sendMsgDao;
	@Resource
	private RecivMsgDao recivMsgDao;
	@Resource
	private UserDao userDao;
	
	

	/* (non-Javadoc)
	 * @see com.keda.webDemo.umcs.service.MsgManageService#sendMsg(javax.servlet.http.HttpServletRequest)
	 */
	@Override
	public Data sendMsg(HttpServletRequest request) {
		
		log.info("用户发送消息");
		Data data = new Data();
		int sendUserId = Integer.valueOf(request.getParameter("sendUserId"));
		int recivId = Integer.valueOf(request.getParameter("recivId"));	
		int sendType = Integer.valueOf(request.getParameter("sendType"));
		String msg = request.getParameter("msg");
		int msgType = Integer.valueOf(request.getParameter("msgType"));
		log.info("用户" + sendUserId + "发送消息" + msg);
		int sendMsgId = this.saveSendMsg(sendUserId, recivId, sendType, msg, msgType);
		this.saveRecivMsg(sendMsgId, sendUserId, recivId, sendType);
		//saveMsg(sendUserId, recivUserId, msg, Constants.ISFILE);
		data.setSuccess(true);
		return data;
		
	}
	
	private int saveSendMsg(int sendUserId,int recivId,int sendType,String msg,int msgType) {
		SendMsg sendMsg = new SendMsg();
		sendMsg.setSendUserId(sendUserId);
		sendMsg.setRecivId(recivId);
		sendMsg.setSendType(sendType);
		sendMsg.setMsg(msg);
		sendMsg.setMsgType(msgType);
		sendMsgDao.insert(sendMsg);
		return sendMsg.getId();
	}
	
	private void saveRecivMsg(int sendMsgId,int sendUserId,int recivId,int sendType) {
		RecivMsg rmsg = new RecivMsg();
		rmsg.setSendMsgId(sendMsgId);
		if(Constants.ONETOONE == sendType) {
			rmsg.setSendId(sendUserId);
			rmsg.setRecivUserId(recivId);
			recivMsgDao.insert(rmsg);
		} else if(Constants.ONETOMORE == sendType) {
			List<User> users = userDao.selectAll();
			for(User user : users) {
				if(user.getId() != sendUserId) {
					rmsg.setSendId(recivId);
					rmsg.setRecivUserId(user.getId());
					recivMsgDao.insert(rmsg);
				}
			}
		} else {
			List<User> users = userDao.selectByGroupId(recivId);
			for(User user : users) {
				if(user.getId() != sendUserId) {
					rmsg.setSendId(recivId);
					rmsg.setRecivUserId(user.getId());
					recivMsgDao.insert(rmsg);
				}
			}
		}
	}

	


	/* (non-Javadoc)
	 * @see com.keda.webDemo.umcs.service.MsgManageService#recivMsg(int)
	 */
	@Override
	public Data recivMsg(int recivUserId, int sendId) {
		
		log.info("用户" + recivUserId + "接收消息");
		Data data = new Data();	
		RecivMsg recivMsg = new RecivMsg();
		recivMsg.setRecivUserId(recivUserId);
		recivMsg.setSendId(sendId);;
		List<RecivMsg> recivMsgs = recivMsgDao.selectByUserId(recivMsg);
		if(null == recivMsgs || 0 >= recivMsgs.size()) {
			data.setSuccess(false);
			data.setMsg("暂无消息");
			return data;
		}
		List<SendMsg> sMsgs = new ArrayList<SendMsg>();
		for(RecivMsg msg : recivMsgs) {
			SendMsg sMsg = sendMsgDao.select(msg.getSendMsgId());
			sMsgs.add(sMsg);
			recivMsgDao.delete(msg.getId());
		}
		data.setSuccess(true);
		data.setData(sMsgs);
		return data;
	}

	/* (non-Javadoc)
	 * @see com.keda.webDemo.umcs.service.MsgManageService#queryHistoryMsg(int, int, int)
	 */
	@Override
	public Data queryHistoryMsg(int recivId, int sendUserId, int sendType, int limit) {
		
		SendMsg sendMsg = new SendMsg();
		List<SendMsg> sendMsgs = new ArrayList<SendMsg>();
		if(Constants.ONETOONE == sendType) {
			sendMsg.setSendUserId(sendUserId);
			sendMsg.setSendType(sendType);
			sendMsgs = sendMsgDao.selectByUserId(sendMsg, new RowBounds(0,limit));
		} else {
			sendMsg.setRecivId(recivId);
			sendMsg.setSendType(sendType);
			sendMsgs = sendMsgDao.selectByUserId(sendMsg, new RowBounds(0,limit));
		}
		
		Data data = new Data();
		data.setSuccess(true);
		data.setData(sendMsgs);
		return data;
	}



	@Override
	public Data queryNotReadMsg(int recivUserId) {
		Data data = new Data();	
		RecivMsg recivMsg = new RecivMsg();
		recivMsg.setRecivUserId(recivUserId);
		recivMsg.setIsRead(Constants.NOTREAD);;
		List<RecivMsg> recivMsgs = recivMsgDao.selectByUserId(recivMsg);
		if(null == recivMsgs || 0 >= recivMsgs.size()) {
			data.setSuccess(false);
			data.setMsg("暂无消息");
			return data;
		}
		List<SendMsg> sMsgs = new ArrayList<SendMsg>();
		for(RecivMsg msg : recivMsgs) {
			SendMsg sMsg = sendMsgDao.select(msg.getSendMsgId());
			sMsgs.add(sMsg);
			recivMsgDao.updateReadState(msg.getId());
		}
		data.setSuccess(true);
		data.setData(sMsgs);
		return data;
	}

	
}
