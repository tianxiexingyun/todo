package com.zhyea.todo.controller;

import com.jfinal.aop.Before;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.ehcache.CacheInterceptor;
import com.jfinal.plugin.ehcache.CacheName;
import com.jfinal.plugin.ehcache.EvictInterceptor;
import com.zhyea.todo.global.CustomController;
import com.zhyea.todo.model.Items;
import com.zhyea.todo.model.Items.Status;
import com.zhyea.todo.service.CategoryService;
import com.zhyea.todo.service.ItemsService;
import com.zhyea.todo.vo.ItemsStaticVO;
import com.zhyea.todo.vo.JsonResponse;

/**
 * @ClassName: ItemsController 
 * @Description: 待办事项管理路由控制
 * @Site: www.zhyea.com 
 * @author robin
 * @date 2015年11月28日 上午11:53:18
 */
public class ItemsController extends CustomController {

	private final ItemsService service = new ItemsService();

	CategoryService catsService = new CategoryService();

	/**
	 * 新增
	 */
	@Before(CacheInterceptor.class)
	public void add() {
		renderJsp("/items/items_add.jsp");
	}

	/**
	 * 编辑
	 */
	public void edit() {
		Items items = service.get(getParaToInt(0));
		setAttr("items", items);
		renderJsp("/items/items_edit.jsp");
	}

	/**
	 * 保存记录
	 */
	@Before(EvictInterceptor.class)
	@CacheName("itemsAllCache")
	public void save() {
		Items item = getModel(Items.class, "items");
		setUserIdInModel(item);
		item.set("status", Status.INIT);
		JsonResponse response = new JsonResponse("保存成功！");
		if (!service.save(item)) {
			response.setSuccess(false);
			response.setMsg("保存失败！");
		}
		renderJson(response);
	}

	/**
	 * 依分类新增
	 */
	public void addInCat() {
		Integer catId = getParaToInt(0);
		setAttr("catName", catsService.getCat(catId));
		setAttr("catId", catId);
		renderJsp("/items/items_add_in_cat.jsp");
	}

	/**
	 * 编辑
	 */
	public void editInCat() {
		Items items = service.get(getParaToInt(0));
		setAttr("catName", catsService.getCat(items.getInt("category_id")));
		setAttr("items", items);
		renderJsp("/items/items_edit_in_cat.jsp");
	}

	/**
	 * 全部待办事项
	 */
	public void all() {
		renderJsp("/items/items_all.jsp");
	}

	/**
	 * 列表
	 */
	public void listInCat() {
		Integer catId = getParaToInt(0);
		setAttr("catId", catId);
		setAttr("catName", catsService.getCat(catId));
		renderJsp("/items/items_in_cat.jsp");
	}

	/**
	 * 分类列表数据
	 */
	@Before(CacheInterceptor.class)
	@CacheName("itemsAllCache")
	public void dataInCat() {
		String brief = getPara("search", "");
		Page<Items> page = service.findItemsInPage(getBootstrapTableParas(), brief, getParaToInt("catId"), getUserIdInSession());
		setAttr("total", page.getTotalRow());
		setAttr("rows", page.getList());
		renderJson();
	}

	/**
	 * 全部记录
	 */
	@Before(CacheInterceptor.class)
	@CacheName("itemsAllCache")
	public void dataAll() {
		String brief = getPara("search", "");
		Page<Items> page = service.findItemsInPage(getBootstrapTableParas(), brief, getUserIdInSession());
		setAttr("total", page.getTotalRow());
		setAttr("rows", page.getList());
		renderJson();
	}

	/**
	 * 删除记录
	 */
	@Before(EvictInterceptor.class)
	@CacheName("itemsAllCache")
	public void delete() {
		JsonResponse response = new JsonResponse("删除成功！");
		if (!service.delete(getPara("ids"))) {
			response.setSuccess(false);
			response.setMsg("删除失败！");
		}
		renderJson(response);
	}

	/**
	 * 标记已完成
	 */
	@Before(EvictInterceptor.class)
	@CacheName("itemsAllCache")
	public void finish() {
		JsonResponse response = new JsonResponse("处理成功！");
		if (!service.updateStatus(getPara("ids"), Status.FINISHED)) {
			response.setSuccess(false);
			response.setMsg("处理失败！");
		}
		renderJson(response);
	}

	/**
	 * 标记已丢弃
	 */
	@Before(EvictInterceptor.class)
	@CacheName("itemsAllCache")
	public void discard() {
		JsonResponse response = new JsonResponse("处理成功！");
		if (!service.updateStatus(getPara("ids"), Status.DISCARDED)) {
			response.setSuccess(false);
			response.setMsg("处理失败！");
		}
		renderJson(response);
	}

	/**
	 * 恢复初始化
	 */
	@Before(EvictInterceptor.class)
	@CacheName("itemsAllCache")
	public void recover() {
		JsonResponse response = new JsonResponse("处理成功！");
		if (!service.updateStatus(getPara("ids"), Status.INIT)) {
			response.setSuccess(false);
			response.setMsg("处理失败！");
		}
		renderJson(response);
	}

	/**
	 * 标记事项等级
	 */
	@Before(EvictInterceptor.class)
	@CacheName("itemsAllCache")
	public void setLevel() {
		JsonResponse response = new JsonResponse("处理成功！");
		if (!service.updateLevel(getPara("ids"), getPara("level"))) {
			response.setSuccess(false);
			response.setMsg("处理失败！");
		}
		renderJson(response);
	}

	/**
	 * 控制台统计信息
	 */
	public void dataConsole() {
		String duration = getPara("duration", "WEEK");
		ItemsStaticVO vo = service.statistics(getUserIdInSession(), duration);
		renderJson("data", vo);
	}
}
