package com.zhyea.todo.vo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.zhyea.todo.model.Items;

/**
 * @ClassName: ItemsStaticVO 
 * @Description: 统计视图
 * @Site: www.zhyea.com 
 * @author robin
 * @date 2015年12月23日 下午2:00:58
 */
public class ItemsStaticVO {
	/** 超时记录 */
	private List<Items> timeoutList = new ArrayList<Items>();
	/** 未完成记录 */
	private List<Items> unfinishList = new ArrayList<Items>();
	/** 分类信息与统计信息映射 */
	private HashMap<String, Integer> catsMap = new HashMap<String, Integer>();
	/** 分类信息与统计信息映射 */
	private HashMap<String, Integer> daysMap = new HashMap<String, Integer>();
	/** 超时记录总数 */
	private int timeoutNums = 0;
	/** 未完成记录总数 */
	private int unfinishNums = 0;
	/** 记录总数 */
	private int total = 0;

	/**
	 * 设置记录总数
	 * @param itemNum
	 * 			记录总数
	 */
	public void setItemsNum(int itemNum) {
		this.total = itemNum;
	}

	/**
	 * 添加超时记录
	 * @param item
	 * 			超时记录
	 */
	public void addTimeout(Items item) {
		this.timeoutList.add(item);
		this.timeoutNums++;
	}

	/**
	 * 添加未完成的记录
	 * @param item
	 * 			未完成记录
	 */
	public void addUnfinished(Items item) {
		this.unfinishList.add(item);
		this.unfinishNums++;
	}

	/**
	 * 添加分类信息
	 * @param cat
	 * 			分类信息
	 */
	public void addItemInCats(String cat) {
		int count = 1;
		if (catsMap.containsKey(cat)) {
			count = catsMap.get(cat);
			catsMap.put(cat, ++count);
		} else {
			catsMap.put(cat, count);
		}
	}

	/**
	 * 按日期管理事项
	 * @param date
	 * 			日期信息
	 */
	public void addItemInDays(String date) {
		int count = 1;
		if (daysMap.containsKey(date)) {
			count = daysMap.get(date);
			daysMap.put(date, ++count);
		} else {
			daysMap.put(date, count);
		}
	}

	/*------------------------getter & setter------------------------*/
	/**
	 * 获取过期记录总数
	 * @return
	 * 		过期记录总数
	 */
	public int getTotalTimeout() {
		return timeoutNums;
	}

	/**
	 * 获取未完成记录总数
	 * @return
	 * 		未完成记录总数
	 */
	public int getTotalUnfinish() {
		return unfinishNums;
	}

	/**
	 * 获取记录总数
	 * @return
	 * 		记录总数
	 */
	public int getTotal() {
		return total;
	}
}
