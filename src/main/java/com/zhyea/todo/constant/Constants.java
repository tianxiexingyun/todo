package com.zhyea.todo.constant;

import com.zhyea.todo.utils.PropKit;

/**
 * @ClassName: Constants 
 * @Description: 常量信息类
 * @Site: www.zhyea.com 
 * @author robin
 * @date 2015年11月29日 下午6:57:03
 */
public class Constants {

	static {
		PropKit.load("app.properties");
	}

	/**
	 * 是否开发模式
	 */
	public static final boolean devMode = PropKit.getBoolean("devMode", true);

	public static final String USER_IN_SESSION = "userinfo";
}
