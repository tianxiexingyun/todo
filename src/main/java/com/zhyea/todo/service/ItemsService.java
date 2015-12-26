package com.zhyea.todo.service;

import java.util.Date;
import java.util.List;

import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Page;
import com.zhyea.todo.model.Items;
import com.zhyea.todo.model.Items.Status;
import com.zhyea.todo.utils.DateKit;
import com.zhyea.todo.utils.StringUtils;
import com.zhyea.todo.vo.BootstrapTableParams;
import com.zhyea.todo.vo.ItemsStaticVO;

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
	private static final String sqlSelect = "select id, user_id, category_id, brief, detail, expected_time, complete_time, status, level, deleted, create_time ";
	/** 批量删除命令 */
	private static final String sqlBatchDelete = "delete from dt_items where id in (?1)";
	/** 批量更新状态 */
	private static final String sqlBatchUpdateStatus = "update dt_items set status=? where id in (?1)";
	/** 批量标记完成 */
	private static final String sqlBatchFinish = "update dt_items set status=?, complete_time=? where id in (?1)";
	/** 批量更新级别 */
	private static final String sqlBatchUpdateLevel = "update dt_items set level=? where id in (?1)";
	/** 预计完成时间格式 */
	private static final String EXPECTED_TIME_FORMAT = "yyyy-MM-dd HH:mm";
	/** 事项创建日期格式 */
	private static final String ITEMS_DATE_FORMAT = "yyyyMMdd";

	public enum Duration {
		DATE("当天"), WEEK("本周"), MONTH("本月"), YEAR("今年");
		public final String des;

		Duration(String _des) {
			this.des = _des;
		}
	}

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
	 * 按时间查询记录
	 * @param duration
	 * 			查询时间周期
	 */
	public List<Items> findItems(String duration) {
		return null;
	}

	/**
	 * 获取单条记录
	 * @param id
	 * 			记录ID
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
			return Db.update(sqlBatchFinish.replace("?1", ids), status, DateKit.format(new Date())) > 0;
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
	 */
	private List<Items> updateRequiredColumns(List<Items> list) {
		for (Items tmp : list) {
			String statusStr = tmp.get("status");
			if (!StringUtils.isBlank(statusStr)) {
				Status status = Status.valueOf(statusStr);
				tmp.set("status", status.des);
			}
			tmp.set("category_id", catsService.getCat(tmp.getInt("category_id")));
		}
		return list;
	}

	/**
	 * 记录统计
	 * @param userId  用户ID
	 * @param duration  持续时间
	 */
	public ItemsStaticVO statistics(int userId, String duration) {
		String startTime;
		Duration d = Duration.valueOf(duration);
		switch (d) {
		case WEEK:
			startTime = DateKit.format(DateKit.getThisMonday(), DateKit.FORMAT_DATE_TIME_START);
			break;
		case MONTH:
			startTime = DateKit.format(DateKit.getMonthFirstDay(), DateKit.FORMAT_DATE_TIME_START);
			break;
		case YEAR:
			startTime = DateKit.format(DateKit.getYearFirstDay(), DateKit.FORMAT_DATE_TIME_START);
			break;
		default:
			startTime = DateKit.format(new Date(), DateKit.FORMAT_DATE_TIME_START);
		}
		return statistics(updateRequiredColumns(dao.find(sqlSelect + " from dt_items where deleted=0 and user_id=? and create_time>?", userId, startTime)));
	}

	/**
	 * 执行统计
	 * @param list
	 * 			查询记录总数
	 */
	private ItemsStaticVO statistics(List<Items> list) {
		ItemsStaticVO vo = new ItemsStaticVO();
		vo.setItemsNum(list.size());
		Date expectedTime = null;
		String completeTime = null;
		for (Items item : list) {
			expectedTime = DateKit.parse(item.getStr("expected_time"), EXPECTED_TIME_FORMAT);
			completeTime = item.getStr("complete_time");
			if (null == completeTime) {//结束日期为空，表示未完成
				vo.addUnfinished(item);
				if (DateKit.compareTo(expectedTime, new Date()) < 0) {//结束日期为空，且预期日期小于当前日期，已过期
					vo.addTimeout(item);
					vo.addUnfinishAndTimeoutNums();
				}
			} else if (DateKit.compareTo(expectedTime, DateKit.parse(completeTime)) < 0) {//预期日期小于结束日期，已过期
				vo.addTimeout(item);
			}
			vo.addItemInCats(item.getStr("category_id"));
			vo.addItemInDays(DateKit.format(DateKit.parse(item.getStr("create_time")), ITEMS_DATE_FORMAT));
		}
		return vo;
	}
}
