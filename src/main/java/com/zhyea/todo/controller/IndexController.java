package com.zhyea.todo.controller;

import java.util.List;

import com.jfinal.aop.Clear;
import com.zhyea.todo.constant.Constants;
import com.zhyea.todo.global.CustomController;
import com.zhyea.todo.model.Category;
import com.zhyea.todo.model.User;
import com.zhyea.todo.service.CategoryService;
import com.zhyea.todo.service.UserService;
import com.zhyea.todo.utils.StringUtils;
import com.zhyea.todo.vo.JsonResponse;

/**
 * @ClassName: IndexController 
 * @Description: 索引路由控制
 * @Site: www.zhyea.com 
 * @author robin
 * @date 2015年11月28日 上午11:54:06
 */
public class IndexController extends CustomController {

	private final UserService userService = new UserService();

	private final CategoryService catService = new CategoryService();

	/**
	 * 首页
	 */
	public void index() {
		List<Category> list = catService.findAll(getUserIdInSession());
		setAttr("cats", list);
		renderJsp("./main/main.jsp");
	}

	/**
	 * 登录页
	 */
	@Clear
	public void login() {
		if (null != getUserInSession()) {
			redirect("/");
			return;
		}
		String username = getPara("username");
		String password = getPara("password");
		if (StringUtils.isBlank(username) || StringUtils.isBlank(password)) {
			renderJsp("./main/login.jsp");
			return;
		}
		User user = userService.checkLogin(username, password);
		if (null != user) {
			setSessionAttr(Constants.USER_IN_SESSION, user);
			redirect("/");
		} else {
			setAttr("response", new JsonResponse(false, "登录信息错误，请重试！"));
			renderJsp("./main/login.jsp");
		}
	}

	/**
	 * 校验用户登录信息
	 */
	@Clear
	public void check() {
		JsonResponse response = new JsonResponse("用户已登录！");
		if (null == getUserInSession()) {
			response.setSuccess(false);
			response.setMsg("用户登录信息不存在！");
		}
		renderJson(response);
	}
}
