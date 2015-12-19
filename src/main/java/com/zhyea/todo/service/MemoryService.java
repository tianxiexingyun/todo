package com.zhyea.todo.service;

import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Page;
import com.zhyea.todo.model.Memory;
import com.zhyea.todo.model.Memory.Status;
import com.zhyea.todo.vo.BootstrapTableParams;

/**
 * @ClassName: MemoryService 
 * @Description: 备忘录业务处理类
 * @Site: www.zhyea.com 
 * @author robin
 * @date 2015年12月19日 上午8:27:45
 */
public class MemoryService {

	private final Memory dao = new Memory();

	/** 查询字段 */
	private static final String sqlSelect = "select id, user_id, brief, content, status, deleted ";
	/** 查询from语句 */
	private final static String sqlFrom = " from dt_memory where deleted=0 and user_id=? and (brief like ? or content like ?) ";
	/** 批量删除命令 */
	private static final String sqlBatchDelete = "delete from dt_memory where id in (?1)";
	/** 批量更新状态 */
	private static final String sqlBatchUpdateStatus = "update dt_memory set status=? where id in (?1)";

	/**
	 * 保存备忘录对象
	 * @param memory
	 * 			备忘录模型
	 * @return
	 */
	public boolean save(Memory memory) {
		if (!memory.has("status")) {
			memory.set("status", Status.INIT);
		}
		return memory.save();
	}

	/**
	 * 获取备忘录对象
	 * @param id
	 * 			备忘录ID
	 * @return
	 */
	public Memory get(Integer id) {
		return dao.findById(id);
	}

	/**
	 * 分页查询备忘录信息
	 * @param paras
	 * 			bootstrap参数
	 * @param userId
	 * 			用户ID
	 * @param search
	 * 			查询参数
	 * @return
	 */
	public Page<Memory> findInPage(BootstrapTableParams paras, int userId, String search) {
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

	/**
	 * 更新备忘录信息
	 * @param mem
	 * 			备忘录对象
	 * @return
	 */
	public boolean dealed(String ids) {
		return Db.update(sqlBatchUpdateStatus.replace("?1", ids), Status.DEALED) > 0;
	}
}
