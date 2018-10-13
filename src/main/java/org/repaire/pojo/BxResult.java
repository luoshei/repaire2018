package org.repaire.pojo;

import java.util.List;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class BxResult {
	
	private static final ObjectMapper MAPPER = new ObjectMapper();
	
	private Integer status;
	
	private String msg;
	
	private Object data;
	
	public static BxResult build(Integer status, String msg, Object data) {
		return new BxResult(status,msg,data);
	}
	
	public static BxResult ok(Object data) {
		return new BxResult(data);
	}
	
	public static BxResult ok() {
		return new BxResult(null);
	}
	
	public static BxResult build(Integer status, String msg) {
		return new BxResult(status,msg,null);
	}

	public BxResult(Integer status, String msg, Object data) {
		this.status = status;
		this.msg = msg;
		this.data = data;
	}
	
	public BxResult(Object data) {
		this.status = 200;
		this.msg = "OK";
		this.data = data;
	}
	
	public Integer getStatus() {
		return status;
	}
	
	public void setStatus(Integer status) {
		this.status = status;
	}
	
	public String getMsg() {
		return msg;
	}
	
	public void setMsg(String msg) {
		this.msg = msg;
	}
	
	public Object getData() {
		return data;
	}
	
	public void setData(Object data) {
		this.data = data;
	}
	
	/**
	 * 将json结果集转化为BxResult对象
	 * 
	 * @paramjsonData json数据
	 * @paramclazz JingXiResult中的object类型
	 * @return
	 */
	public static BxResult formatToPojo(String jsonData, Class<?> clazz) {
		try {
			if(clazz == null) {
				return MAPPER.readValue(jsonData, BxResult.class);
			}
			JsonNode jsonNode = MAPPER.readTree(jsonData);
			JsonNode data = jsonNode.get("data");
			Object obj = null;
			
			if(clazz != null) {
				if(data.isObject()) {
					obj = MAPPER.readValue(data.traverse(), clazz);
				}else if(data.isTextual()) {
					obj = MAPPER.readValue(data.asText(), clazz);
				}
			}
			return build(jsonNode.get("status").intValue(),jsonNode.get("msg").asText(),obj);
			
		}catch(Exception e) {
			return null;
		}
			
	}
	
	
	/*
	 * 没有object对象的转化
	 *  @paramjson 
	 *  @return
	 */
	public static BxResult format(String json) {
		try {
			return MAPPER.readValue(json, BxResult.class);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * Object 是集合化
	 * @param jsonData
	 * @param clazz
	 * @return
	 */
	public static BxResult formatToList(String jsonData, Class<?> clazz) {
		try {
			JsonNode jsonNode = MAPPER.readTree(jsonData);
			JsonNode data = jsonNode.get("data");
			
			Object obj = null;
			
			if(data.isArray() && data.size()>0) {
				obj = MAPPER.readValue(data.traverse(), MAPPER.getTypeFactory().constructCollectionType(List.class,clazz));
			}
			return build(jsonNode.get("status").intValue(),jsonNode.get("msg").asText(),obj);
		}catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	
	
	
}
