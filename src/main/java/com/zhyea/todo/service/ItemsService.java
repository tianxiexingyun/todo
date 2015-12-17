package com.zhyea.todo.service;

import java.util.Date;
import java.util.List;

import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Page;
import com.zhyea.todo.model.Items;
import com.zhyea.todo.model.Items.Status;
import com.zhyea.todo.utils.DateUtils;
import com.zhyea.todo.utils.StringUtils;
import com.zhyea.todo.vo.BootstrapTableParams;

/**
 * @ClassName: ItemService 
 * @Description: 待办事项业务处理类
 * @Site: www.zhyea.com 
 * @author robin
 * @date 2015年12月1日 下午2:34:32
 */
public class ItemsService {

	private final Items dao = new Items();

	CategoryService catsService = new CategoryService();

	/** sql语句，查询字段 */
	private static final String sqlSelect = "select id, user_id, category_id, brief, detail, expected_time, complete_time, status, level, deleted ";
	/** 批量删除命令 */
	private static final String sqlBatchDelete = "delete from dt_items where id in (?1)";
	/** 批量更新状态 */
	private static final String sqlBatchUpdateStatus = "update dt_items set status=? where id in (?1)";
	/** 批量标记完成 */
	private static final String sqlBatchFinish = "update dt_items set status=?, complete_time=? where id in (?1)";
	/** 批量更新级别 */
	private static final String sqlBatchUpdateLevel = "update dt_items set level=? where id in (?1)";

	/**
	 * 按条件查询分类记录
	 */
	public Page<Items> findItemsInPage(BootstrapTableParams paras, String brief, int catId, int userId) {
		return decorateItemsPage(dao.paginate(paras, sqlSelect, " from dt_items where deleted=0 and category_id=? and user_id=? and brief like ? ", catId, userId, "%" + brief + "%"));
	}

	/**
	 * 按条件查询分类记录
	 */
	public Page<Items> findItemsInPage(BootstrapTableParams paras, String brief, int userId) {
		return decorateItemsPage(dao.paginate(paras, sqlSelect, " from dt_items where deleted=0 and user_id=? and brief like ?", userId, "%" + brief + "%"));
	}

	/**
	 * 获取单条记录
	 * @param id
	 * @return
	 */
	public Items get(Integer id) {
		return dao.findById(id);
	}

	/**
	 * 保存待办事项记录
	 * @param item
	 * 			待办事项信息
	 * @return
	 */
	public boolean save(Items item) {
		return item.save();
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
	 * 批量更新事项状态
	 * @param ids
	 * 			记录ID
	 * @param status
	 * 			事项状态
	 * @return
	 */
	public boolean updateStatus(String ids, Status status) {
		if (status == Status.FINISHED) {
			return Db.update(sqlBatchFinish.replace("?1", ids), status, DateUtils.format(new Date())) > 0;
		} else {
			return Db.update(sqlBatchUpdateStatus.replace("?1", ids), status) > 0;
		}

	}

	/**
	 * 批量更新事项级别
	 * @param ids
	 * 			记录ID
	 * @param level
	 * 			级别
	 * @return
	 */
	public boolean updateLevel(String ids, String level) {
		return Db.update(sqlBatchUpdateLevel.replace("?1", ids), level) > 0;
	}

	/**
	 * 修饰Items的分页信息
	 */
	private Page<Items> decorateItemsPage(Page<Items> page) {
		updateRequiredColumns(page.getList());
		return page;
	}

	/**
	 * 更新某些必要的字段
	 * @param list
	 */
	private void updateRequiredColumns(List<Items> list) {
		for (Items tmp : list) {
			String statusStr = tmp.get("status");
			if (!StringUtils.isBlank(statusStr)) {
				Status status = Status.valueOf(statusStr);
				tmp.set("status", status.des);
			}
			tmp.set("category_id", catsService.getCat(tmp.getInt("category_id")));
		}
	}

}
