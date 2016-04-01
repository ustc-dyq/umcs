/**
 * @Title：JsonTest.java 
 * @Copyright © Suzhou Keda Technology Co.Ltd. All Rights Reserved. 
 * @author： 代永强
 * @date: 2016年3月29日
 * @description:
**/
package com.keda.webDemo.umcs.json;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;
import org.json.JSONWriter;

public class JsonTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Map<String,String> map = new HashMap<String,String>();
		map.put("data", "test");
		
		Data data = new Data();
		data.setSuccess(true);
		data.setCode(1234);
		data.setMsg("test");
		User user = new User();
		user.setId(1);
		user.setName("hello");
		data.setData(user);
		JSONObject json = new JSONObject(data);
		System.out.println(json.toString());

	}

}





