package com.zhyea.todo.controller;

import com.jfinal.aop.Before;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.ehcache.CacheInterceptor;
import com.jfinal.plugin.ehcache.CacheName;
import com.jfinal.plugin.ehcache.EvictInterceptor;
import com.zhyea.todo.global.CustomController;
import com.zhyea.todo.model.User;
import com.zhyea.todo.service.UserService;
import com.zhyea.todo.vo.JsonResponse;

/**
 * @ClassName: UserController 
 * @Description: 用户信息管理路由控制
 * @Site: www.zhyea.com 
 * @author robin
 * @date 2015年11月28日 上午11:53:18
 */
public class UserController extends CustomController {

	private final UserService service = new UserService();

	/**
	 * 新增
	 */
	public void add() {
		renderJsp("/user/user_add.jsp");
	}

	/**
	 * 编辑
	 */
	public void edit() {
		User user = service.get(getParaToInt("id"));
		setAttr("user", user);
		renderJsp("/user/user_edit.jsp");
	}

	/**
	 * 保存用户信息
	 */
	@Before(EvictInterceptor.class)
	@CacheName("userCache")
	public void save() {
		User user = getModel(User.class, "user");
		user.set("update_user_id", getUserIdInSession());
		JsonResponse response = new JsonResponse("保存成功！");
		if (!service.save(user)) {
			response.setSuccess(false);
			response.setMsg("保存失败！");
		}
		renderJson(response);
	}

	/**
	 * 查看
	 */
	public void list() {
		renderJsp("/user/user_list.jsp");
	}

	/**
	 * 列表数据
	 */
	@Before(CacheInterceptor.class)
	@CacheName("userCache")
	public void data() {
		String name = getPara("search", "");
		Page<User> page = service.findInPage(getBootstrapTableParas(), name);
		setAttr("total", page.getTotalRow());
		setAttr("rows", page.getList());
		renderJson();
	}

	/**
	 * 删除记录
	 */
	@Before(EvictInterceptor.class)
	@CacheName("userCache")
	public void delete() {
		JsonResponse response = new JsonResponse("删除成功！");
		if (!service.delete(getPara("ids"))) {
			response.setSuccess(false);
			response.setMsg("删除失败！");
		}
		renderJson(response);
	}
}
