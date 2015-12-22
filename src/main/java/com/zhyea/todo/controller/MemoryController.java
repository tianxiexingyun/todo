package com.zhyea.todo.controller;

import com.jfinal.aop.Before;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.ehcache.CacheInterceptor;
import com.jfinal.plugin.ehcache.CacheName;
import com.jfinal.plugin.ehcache.EvictInterceptor;
import com.zhyea.todo.global.CustomController;
import com.zhyea.todo.model.Memory;
import com.zhyea.todo.service.MemoryService;
import com.zhyea.todo.vo.JsonResponse;

/**
 * @ClassName: MemoryController 
 * @Description: 记事本路由控制
 * @Site: www.zhyea.com 
 * @author robin
 * @date 2015年12月19日 上午9:47:54
 */
public class MemoryController extends CustomController {

	private MemoryService service = new MemoryService();

	/**
	 * 打开新增页面
	 */
	public void add() {
		renderJsp("/memory/memory_add.jsp");
	}

	/**
	 * 打开编辑页面
	 */
	public void edit() {
		Memory memory = service.get(getParaToInt(0));
		setAttr("memory", memory);
		renderJsp("/memory/memory_edit.jsp");
	}

	/**
	 * 保存记录
	 */
	@Before(EvictInterceptor.class)
	@CacheName("memoryCache")
	public void save() {
		Memory memory = getModel(Memory.class, "memory");
		setUserIdInModel(memory);
		JsonResponse response = new JsonResponse("保存成功！");
		if (!service.save(memory)) {
			response.setSuccess(false);
			response.setMsg("保存失败！");
		}
		renderJson(response);
	}

	/**
	 * 查看
	 */
	public void list() {
		renderJsp("/memory/memory_list.jsp");
	}

	/**
	 * 列表数据
	 */
	@Before(CacheInterceptor.class)
	@CacheName("memoryCache")
	public void data() {
		String search = (null == getPara("search") ? "" : getPara("search"));
		Page<Memory> page = service.findInPage(getBootstrapTableParas(), getUserIdInSession(), search);
		setAttr("total", page.getTotalRow());
		setAttr("rows", page.getList());
		renderJson();
	}

	/**
	 * 删除记录
	 */
	@Before(EvictInterceptor.class)
	@CacheName("memoryCache")
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
	@CacheName("memoryCache")
	public void dealed() {
		JsonResponse response = new JsonResponse("处理成功！");
		if (!service.dealed(getPara("ids"))) {
			response.setSuccess(false);
			response.setMsg("处理失败！");
		}
		renderJson(response);
	}
}
