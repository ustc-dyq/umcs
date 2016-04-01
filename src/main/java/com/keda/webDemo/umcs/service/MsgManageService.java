/**
 * @Title：MsgManageService.java 
 * @Copyright © Suzhou Keda Technology Co.Ltd. All Rights Reserved. 
 * @author： 代永强
 * @date: 2016年3月31日
 * @description:
**/
package com.keda.webDemo.umcs.service;

import javax.servlet.http.HttpServletRequest;

import com.keda.webDemo.umcs.dto.Data;

public interface MsgManageService {

	public Data sendMsg(HttpServletRequest request);
	public Data recivMsg(int recivUserId);
	
	
}





