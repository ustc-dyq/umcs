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
import com.keda.webDemo.umcs.dao.dto.RecivMsg;
import com.keda.webDemo.umcs.dao.dto.SendMsg;
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
		log.info("用户" + sendUserId + "发送消息");
		String msg = request.getParameter("msg");
		saveMsg(sendUserId, recivUserId, msg, Constants.ISFILE);
		data.setSuccess(true);
		return data;
		
	}



	/* (non-Javadoc)
	 * @see com.keda.webDemo.umcs.service.MsgManageService#recivMsg(int)
	 */
	@Override
	public Data recivMsg(int recivUserId, int sendUserId) {
		
		log.info("用户" + recivUserId + "接收消息");
		Data data = new Data();	
		RecivMsg recivMsg = new RecivMsg();
		recivMsg.setRecivUserId(recivUserId);
		if(0 != sendUserId) {
			recivMsg.setSendUserId(sendUserId);
		}
		List<RecivMsg> recivMsgs = recivMsgDao.selectByUserId(recivMsg);
		if(null == recivMsgs || 0 >= recivMsgs.size()) {
			data.setSuccess(false);
			data.setMsg("暂无消息");
			return data;
		}
		for(RecivMsg msg : recivMsgs) {
			recivMsgDao.delete(msg.getId());
		}
		data.setSuccess(true);
		data.setData(recivMsgs);
		return data;
	}

	/* (non-Javadoc)
	 * @see com.keda.webDemo.umcs.service.MsgManageService#queryHistoryMsg(int, int, int)
	 */
	@Override
	public Data queryHistoryMsg(int recivUserId, int sendUserId, int limit) {
		
		SendMsg sendMsg = new SendMsg();
		List<SendMsg> sendMsgs = new ArrayList<SendMsg>();
		sendMsg.setSendUserId(sendUserId);
		sendMsg.setRecivUserId(recivUserId);
		int count = sendMsgDao.selectCount(sendMsg);
		int offset = 0;
		if(count > limit) {
			offset = count-limit;
		}
		sendMsgs.addAll(sendMsgDao.selectByUserId(sendMsg, new RowBounds(offset,limit)));
		sendMsg.setSendUserId(recivUserId);
		sendMsg.setRecivUserId(sendUserId);
		count = sendMsgDao.selectCount(sendMsg);
		offset = 0;
		if(count > limit) {
			offset = count-limit;
		}
		sendMsgs.addAll(sendMsgDao.selectByUserId(sendMsg, new RowBounds(offset,limit)));
		Collections.sort(sendMsgs,new ComparatorSendMsg());
		Data data = new Data();
		data.setSuccess(true);
		data.setData(sendMsgs);
		return data;
	}

	
}
