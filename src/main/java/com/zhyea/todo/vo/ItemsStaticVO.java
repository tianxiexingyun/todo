package com.zhyea.todo.vo;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

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
	private Map<String, Integer> catsMap = new LinkedHashMap<String, Integer>();
	/** 分类信息与统计信息映射 */
	private Map<String, Integer> daysMap = new TreeMap<String, Integer>();
	/** 超时记录总数 */
	private int timeoutNums = 0;
	/** 未完成记录总数 */
	private int unfinishNums = 0;
	/** 未完成且超时记录数目 */
	private int unfinishAndTimeoutNums = 0;
	/** 记录总数 */
	@SuppressWarnings("unused")
	private int total = 0;
	/** 映射对象 */
	private EchartsMap mapper = new EchartsMap();

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

	/**
	 * 新增未完成且超时数目
	 */
	public void addUnfinishAndTimeoutNums() {
		this.unfinishAndTimeoutNums++;
	}

	/*------------------------getter & setter------------------------*/
	/**
	 * 获取超时记录
	 * @return
	 * 		超时记录
	 */
	protected List<Items> getTimeoutList() {
		return timeoutList;
	}

	/**
	 * 获取未完成记录
	 * @return
	 * 		未完成记录
	 */
	protected List<Items> getUnfinishList() {
		return unfinishList;
	}

	/**
	 * 获取事项分类
	 * @return
	 * 		分类列表
	 */
	public List<EchartsMap> getCatsMap() {
		List<EchartsMap> list = new ArrayList<EchartsMap>();
		for (String key : catsMap.keySet()) {
			EchartsMap m = mapper.clone();
			m.setName(key);
			m.setValue(catsMap.get(key));
			list.add(m);
		}
		return list;
	}

	/**
	 * 获取事项分类
	 * @return
	 * 		分类列表
	 */
	public List<EchartsMap> getFinishMap() {
		List<EchartsMap> list = new LinkedList<EchartsMap>();
		list.add(new EchartsMap("已过期", this.timeoutNums));
		list.add(new EchartsMap("未完成", this.unfinishNums));
		list.add(new EchartsMap("公共", this.unfinishAndTimeoutNums));
		return list;
	}

	/**
	 * 获取每日日期
	 * @return
	 * 		每日日期
	 */
	public List<String> getDaysKey() {
		List<String> daysKey = new LinkedList<String>();
		for (String key : daysMap.keySet()) {
			daysKey.add(key);
		}
		return daysKey;
	}

	/**
	 * 获取每日事项数目
	 * @return
	 * 		每日事项数目
	 */
	public List<Integer> getDaysNum() {
		List<Integer> daysNum = new LinkedList<Integer>();
		for (String key : daysMap.keySet()) {
			daysNum.add(daysMap.get(key));
		}
		return daysNum;
	}
}
