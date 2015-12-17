package com.zhyea.todo.vo;

/**
 * @ClassName: JsonResponse 
 * @Description: Json反馈消息
 * @Site: www.zhyea.com 
 * @author robin
 * @date 2015年12月10日 下午12:45:05
 */
public class JsonResponse {
	/** 是否处理成功 */
	private boolean success = true;
	/** 处理结果 */
	private String msg;

	/** 构造器 */
	public JsonResponse(boolean _success, String _msg) {
		this.success = _success;
		this.msg = _msg;
	}

	/** 构造器 */
	public JsonResponse(String _msg) {
		this.msg = _msg;
	}

	/** 获取是否处理成功 */
	public boolean isSuccess() {
		return success;
	}

	/**
	 * 设置处理结果 
	 * @param success
	 * 			是否处理成功
	 */
	public void setSuccess(boolean success) {
		this.success = success;
	}

	/** 获取处理反馈信息 */
	public String getMsg() {
		return msg;
	}

	/**
	 * 设置处理反馈信息
	 * @param msg
	 * 			处理反馈信息
	 */
	public void setMsg(String msg) {
		this.msg = msg;
	}

}
