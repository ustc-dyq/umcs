/**
 * @Title：Constants.java 
 * @Copyright © Suzhou Keda Technology Co.Ltd. All Rights Reserved. 
 * @author： 代永强
 * @date: 2016年3月29日
 * @description:
**/
package com.keda.webDemo.umcs.constants;

public class Constants {

	/**
	 * 登录在线状态为1
	 */
	public static final int LOGIN = 1;
	
	/**
	 * 登出下线状态为0
	 */
	public static final int LOGOUT = 0;
	
	/**
	 * 上传头像的保存路径
	 */
	public static final String HEADIMGPATH = "D:\\workspace\\umcs\\src\\main\\webapp\\user\\headImg\\";
	
	/**
	 * 上传文件的保存路径
	 */
	public static final String FILESAVEPATH = "D:\\workspace\\umcs\\src\\main\\webapp\\user\\files\\";
	
	/**
	 * 无分组id
	 */
	public static final int NOTINGROUP = 0;
	
	/**
	 * 管理员账户
	 */
	public static final int MANAGEMENTPRIV = 1;
	
	/**
	 * 非管理员账户
	 */
	public static final int NORMALPRIV = 0;
	
	/**
	 * 是否已读
	 */
	public static final int ISREAD = 1;
	
	/**
	 * 未读
	 */
	public static final int NOTREAD = 0;
	
	/**
	 * 消息为文本
	 */
	public static final int ISTEXT = 1;
	
	/**
	 * 消息为图片
	 */
	public static final int ISIMG = 2;
	
	/**
	 * 消息为文件
	 */
	public static final int ISFILE = 3;
	
	/**
	 * 点对点通信
	 */
	public static final int ONETOONE = 1;
	
	/**
	 * 一对多通信
	 */
	public static final int ONETOMORE = 2;
	
	/**
	 * 多对多通信
	 */
	public static final int MORETOMORE = 3;
	
}





