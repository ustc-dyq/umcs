/**
 * @Title：Data.java 
 * @Copyright © Suzhou Keda Technology Co.Ltd. All Rights Reserved. 
 * @author： 代永强
 * @date: 2016年3月29日
 * @description:
**/
package com.keda.webDemo.umcs.dto;

public class Data {

	private boolean success;
	private int code;
	private String msg;
	private Object data;
	
	/**
	 * @return the success
	 */
	public boolean isSuccess() {
		return success;
	}
	
	/**
	 * @param success the success to set
	 */
	public void setSuccess(boolean success) {
		this.success = success;
	}
	
	/**
	 * @return the code
	 */
	public int getCode() {
		return code;
	}
	
	/**
	 * @param code the code to set
	 */
	public void setCode(int code) {
		this.code = code;
	}
	
	/**
	 * @return the msg
	 */
	public String getMsg() {
		return msg;
	}
	
	/**
	 * @param msg the msg to set
	 */
	public void setMsg(String msg) {
		this.msg = msg;
	}
	
	/**
	 * @return the data
	 */
	public Object getData() {
		return data;
	}
	
	/**
	 * @param data the data to set
	 */
	public void setData(Object data) {
		this.data = data;
	}
	
}





