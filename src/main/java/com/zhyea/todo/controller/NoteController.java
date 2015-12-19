package com.zhyea.todo.controller;

import com.jfinal.plugin.activerecord.Page;
import com.zhyea.todo.global.CustomController;
import com.zhyea.todo.model.Note;
import com.zhyea.todo.service.NoteService;
import com.zhyea.todo.vo.JsonResponse;

/**
 * @ClassName: NoteController 
 * @Description: 记事本路由控制
 * @Site: www.zhyea.com 
 * @author robin
 * @date 2015年12月19日 上午9:47:54
 */
public class NoteController extends CustomController {

	private NoteService service = new NoteService();

	/**
	 * 打开新增页面
	 */
	public void add() {
		renderJsp("/note/note_add.jsp");
	}

	/**
	 * 打开编辑页面
	 */
	public void edit() {
		Note note = service.get(getParaToInt(0));
		setAttr("note", note);
		renderJsp("/note/note_edit.jsp");
	}

	/**
	 * 保存记录
	 */
	public void save() {
		Note note = getModel(Note.class, "note");
		setUserIdInModel(note);
		JsonResponse response = new JsonResponse("保存成功！");
		if (!service.save(note)) {
			response.setSuccess(false);
			response.setMsg("保存失败！");
		}
		renderJson(response);
	}

	/**
	 * 查看
	 */
	public void list() {
		renderJsp("/note/note_list.jsp");
	}

	/**
	 * 列表数据
	 */
	public void data() {
		String search = (null == getPara("search") ? "" : getPara("search"));
		Page<Note> page = service.findInPage(getBootstrapTableParas(), getUserIdInSession(), search);
		setAttr("total", page.getTotalRow());
		setAttr("rows", page.getList());
		renderJson();
	}

	/**
	 * 删除记录
	 */
	public void delete() {
		JsonResponse response = new JsonResponse("删除成功！");
		if (!service.delete(getPara("ids"))) {
			response.setSuccess(false);
			response.setMsg("删除失败！");
		}
		renderJson(response);
	}
}
