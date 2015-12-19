package com.zhyea.todo.service;

import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Page;
import com.zhyea.todo.model.Note;
import com.zhyea.todo.vo.BootstrapTableParams;

/**
 * @ClassName: NoteService 
 * @Description: 记事本业务处理类
 * @Site: www.zhyea.com 
 * @author robin
 * @date 2015年12月19日 上午8:14:05
 */
public class NoteService {

	private final Note dao = new Note();
	/** 查询字段 */
	private static final String sqlSelect = "select id, user_id, brief, content, deleted ";
	/** 查询from语句 */
	private static final String sqlFrom = " from dt_note where deleted=0 and user_id=? and (brief like ? or content like ?)";
	/** 批量删除命令 */
	private static final String sqlBatchDelete = "delete from dt_note where id in (?1)";

	/**
	 * 保存记事本对象
	 * @param note
	 * 			记事本模型
	 * @return
	 */
	public boolean save(Note note) {
		return note.save();
	}

	/**
	 * 获取记事本对象
	 * @param id
	 * 			记事本ID
	 * @return
	 */
	public Note get(Integer id) {
		return dao.findById(id);
	}

	/**
	 * 分页查询记事本信息
	 * @param paras
	 * 			bootstrap参数
	 * @param userId
	 * 			用户ID
	 * @param search
	 * 			查询参数
	 * @return
	 */
	public Page<Note> findInPage(BootstrapTableParams paras, int userId, String search) {
		return dao.paginate(paras, sqlSelect, sqlFrom, userId, "%" + search + "%", "%" + search + "%");
	}

	/**
	 * 执行批量删除
	 * @param ids
	 * 			记录ID
	 * @return
	 */
	public boolean delete(String ids) {
		return Db.update(sqlBatchDelete.replace("?1", ids)) > 0;
	}
}
