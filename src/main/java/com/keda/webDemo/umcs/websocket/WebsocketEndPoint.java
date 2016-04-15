/**
 * @Title：WebsocketEndPoint.java 
 * @Copyright © Suzhou Keda Technology Co.Ltd. All Rights Reserved. 
 * @author： 代永强
 * @date: 2016年4月7日
 * @description:
**/
package com.keda.webDemo.umcs.websocket;

import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

class WebsocketEndPoint extends TextWebSocketHandler {
	@Override
	protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
		super.handleTextMessage(session, message);
		TextMessage returnMessage = new TextMessage(message.getPayload() + " received at server");
		session.sendMessage(returnMessage);
	}
}
