package com.example.yyw.constant;

/**
 * Title: 统一响应结构 
 * Description:使用REST框架实现前后端分离架构，我们需要首先确定返回的JSON响应结构是统一的，
 * 也就是说，每个REST请求将返回相同结构的JSON响应结构。不妨定义一个相对通用的JSON响应结构，其
 * 中包含两部分：元数据与返回值，其中，元数据表示操作是否成功与返回值消息等，返回值对应服务端方法所返回的数据。
 * { "success": true, "message": "ok" , "data": ... }
 * 
 * @author rico
 * @created 2017年7月4日 下午5:06:00
 */
public class ResponseData<T> {

	private static final String SUCCESS = "1";
	private static final String FAILURE = "-1";

	private String code;
	private String message;
	private Object data;

	ResponseData(String code) {
		this.code = code;
	}

	ResponseData(String code, Object data) {
		this.code = code;
		this.data = data;
	}

	ResponseData(String code, String message) {
		this.code = code;
		this.message = message;
	}

	ResponseData(String code, String message, Object data) {
		this.code = code;
		this.message = message;
		this.data = data;
	}

	public static <T> ResponseData<T> success() {
		return new ResponseData<T>(SUCCESS);
	}
	public static <T> ResponseData<T> success(String message,Object data) {
		return new ResponseData<T>(SUCCESS,message,data);
	}

	public static <T> ResponseData<T> success(String message) {
		return new ResponseData<T>(SUCCESS,message);
	}

	public static <T> ResponseData<T> success(Object data) {
		return new ResponseData<T>(SUCCESS,data);
	}

	public static <T> ResponseData<T> failure() {
		return new ResponseData<T>(FAILURE);
	}

	public static <T> ResponseData<T> failure(String message) {
		return new ResponseData<T>(FAILURE,message);
	}

	public static <T> ResponseData<T> failure(String code,String message) {
		return new ResponseData<T>(code,message);
	}

	public String getCode() {
		return code;
	}

	public String getMessage() {
		return message;
	}

	public Object getData() {
		return data;
	}
}