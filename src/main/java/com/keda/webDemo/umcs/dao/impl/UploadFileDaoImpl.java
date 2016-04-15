/**
 * @Title：UploadFileDaoImpl.java 
 * @Copyright © Suzhou Keda Technology Co.Ltd. All Rights Reserved. 
 * @author： 代永强
 * @date: 2016年4月8日
 * @description:
**/
package com.keda.webDemo.umcs.dao.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.keda.webDemo.umcs.dao.UploadFileDao;
import com.keda.webDemo.umcs.dao.dto.UploadFile;

@Repository
public class UploadFileDaoImpl implements UploadFileDao {
	
	@Resource
	private SqlSessionTemplate sqlSession;

	/* (non-Javadoc)
	 * @see com.keda.webDemo.umcs.dao.UploadFileDao#select(int)
	 */
	@Override
	public UploadFile select(int id) {
		
		return sqlSession.selectOne("com.keda.webDemo.umcs.dao.File.select",id);
		
	}

	/* (non-Javadoc)
	 * @see com.keda.webDemo.umcs.dao.UploadFileDao#insert(com.keda.webDemo.umcs.dao.dto.UploadFile)
	 */
	@Override
	public int insert(UploadFile uploadFile) {
		
		return sqlSession.insert("com.keda.webDemo.umcs.dao.File.insert",uploadFile);
		
	}

	/* (non-Javadoc)
	 * @see com.keda.webDemo.umcs.dao.UploadFileDao#selectByRecivUserId(int)
	 */
	@Override
	public List<UploadFile> selectByUserId(Map<String,Object> map) {
		
		return sqlSession.selectList("com.keda.webDemo.umcs.dao.File.selectByUserId",map);
	
	}

	/* (non-Javadoc)
	 * @see com.keda.webDemo.umcs.dao.UploadFileDao#delete(int)
	 */
	@Override
	public int delete(int id) {
		
		return sqlSession.update("com.keda.webDemo.umcs.dao.File.delete",id);
		
	}

	/* (non-Javadoc)
	 * @see com.keda.webDemo.umcs.dao.UploadFileDao#update(com.keda.webDemo.umcs.dao.dto.UploadFile)
	 */
	@Override
	public int update(UploadFile uploadFile) {
		
		return sqlSession.update("com.keda.webDemo.umcs.dao.File.update",uploadFile);
	
	}

}





