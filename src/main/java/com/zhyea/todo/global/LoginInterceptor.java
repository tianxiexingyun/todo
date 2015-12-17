package com.zhyea.todo.global;

import com.jfinal.aop.Interceptor;
import com.jfinal.aop.Invocation;
import com.jfinal.core.Controller;
import com.zhyea.todo.constant.Constants;
import com.zhyea.todo.model.User;

/**
 * @ClassName: LoginInterceptor 
 * @Description: 登录拦截器，主要用来校验是否登录
 * @author robin
 * @date 2015年9月23日 下午6:04:41
 */
public class LoginInterceptor implements Interceptor {

	@Override
	public void intercept(Invocation inv) {
		Controller c = inv.getController();
		if (Constants.devMode) {
			System.out.println("------the remote address is : " + c.getRequest().getRemoteAddr());
			System.out.println("");
		}

		User user = c.getSessionAttr(Constants.USER_IN_SESSION);
		if (null == user) {
			c.redirect("/login");
		} else {
			try {
				inv.invoke();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
