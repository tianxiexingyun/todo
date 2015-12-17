package com.zhyea.todo.controller;

import com.jfinal.plugin.activerecord.Page;
import com.zhyea.todo.global.CustomController;
import com.zhyea.todo.model.Category;
import com.zhyea.todo.service.CategoryService;
import com.zhyea.todo.vo.BootstrapTableParams;
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
		Integer id = getParaToInt("id");
		Category cat = service.get(id);
		setAttr("cat", cat);
		renderJsp("/category/cat_edit.jsp");
	}

	/**
	 * 保存分类信息
	 */
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
	public void data() {
		BootstrapTableParams paras = getBootstrapTableParas();
		String name = getPara("search");
		name = (null == name ? "" : name);
		Page<Category> page = service.findInPage(paras, getUserIdInSession(), name);
		setAttr("total", page.getTotalRow());
		setAttr("rows", page.getList());
		renderJson();
	}

	/**
	 * 删除分类信息
	 */
	public void delete() {
		String ids = getPara("ids");
		JsonResponse response = new JsonResponse("删除成功！");
		if (!service.delete(ids)) {
			response.setSuccess(false);
			response.setMsg("删除失败！");
		}
		renderJson(response);
	}
}
