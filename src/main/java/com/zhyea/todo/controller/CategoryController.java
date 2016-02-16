package com.zhyea.todo.controller;

import com.jfinal.aop.Before;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.ehcache.CacheInterceptor;
import com.jfinal.plugin.ehcache.CacheName;
import com.jfinal.plugin.ehcache.EvictInterceptor;
import com.zhyea.todo.global.CustomController;
import com.zhyea.todo.model.Category;
import com.zhyea.todo.service.CategoryService;
import com.zhyea.todo.vo.JsonResponse;

/**
 * @ClassName: CategoryController 
 * @Description: 分类信息路由
 * @Site: www.zhyea.com 
 * @author robin
 * @date 2015年12月1日 下午6:05:32
 */
public class CategoryController extends CustomController {

	private final CategoryService service = new CategoryService();

	/**
	 * 新增分类信息
	 */
	public void add() {
		renderJsp("/category/cat_add.jsp");
	}

	/**
	 * 编辑分类信息
	 */
	public void edit() {
		Category cat = service.get(getParaToInt("id"));
		setAttr("cat", cat);
		renderJsp("/category/cat_edit.jsp");
	}

	/**
	 * 保存分类信息
	 */
	@Before(EvictInterceptor.class)
	@CacheName("categoryCache")
	public void save() {
		Category cat = getModel(Category.class, "cat");
		setUserIdInModel(cat);
		JsonResponse response = new JsonResponse("保存成功！");
		if (!service.save(cat)) {
			response.setSuccess(false);
			response.setMsg("保存失败！");
		}
		renderJson(response);
	}

	/**
	 * 新增分类信息
	 */
	public void list() {
		renderJsp("/category/cat_list.jsp");
	}

	/**
	 * 分类信息列表数据
	 */
	@Before(CacheInterceptor.class)
	@CacheName("categoryCache")
	public void data() {
		String name = getPara("search", "");
		Page<Category> page = service.findInPage(getBootstrapTableParas(), getUserIdInSession(), name);
		setAttr("total", page.getTotalRow());
		setAttr("rows", page.getList());
		renderJson();
	}

	/**
	 * 删除分类信息
	 */
	@Before(EvictInterceptor.class)
	@CacheName("categoryCache")
	public void delete() {
		JsonResponse response = new JsonResponse("删除成功！");
		if (!service.delete(getPara("ids"))) {
			response.setSuccess(false);
			response.setMsg("删除失败！");
		}
		renderJson(response);
	}
}
