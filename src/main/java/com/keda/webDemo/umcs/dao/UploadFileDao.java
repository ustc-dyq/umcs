/**
 * @Title：UploadFileDao.java 
 * @Copyright © Suzhou Keda Technology Co.Ltd. All Rights Reserved. 
 * @author： 代永强
 * @date: 2016年4月8日
 * @description:
**/
package com.keda.webDemo.umcs.dao;

import java.util.List;
import java.util.Map;

import com.keda.webDemo.umcs.dao.dto.UploadFile;

public interface UploadFileDao {

	public UploadFile select(int id);
	public int insert(UploadFile uploadFile);
	public int update(UploadFile uploadFile);
	public List<UploadFile> selectByUserId(Map<String,Object> map);
	public int delete(int id);
	
}





