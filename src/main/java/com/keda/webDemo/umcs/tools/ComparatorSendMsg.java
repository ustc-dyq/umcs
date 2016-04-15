/**
 * @Title：ComparatorSendMsg.java 
 * @Copyright © Suzhou Keda Technology Co.Ltd. All Rights Reserved. 
 * @author： 代永强
 * @date: 2016年4月8日
 * @description:
**/
package com.keda.webDemo.umcs.tools;

import java.util.Comparator;

import com.keda.webDemo.umcs.dao.dto.SendMsg;

public class ComparatorSendMsg implements Comparator<Object> {

	/* (non-Javadoc)
	 * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
	 */
	@Override
	public int compare(Object o1, Object o2) {
		SendMsg msg1 = (SendMsg) o1;
		SendMsg msg2 = (SendMsg) o2;
		
		return msg1.getSendTime().compareTo(msg2.getSendTime());
	}

}





