package com.zhyea.todo.vo;

/**
 * @ClassName: BootstrapTableParams 
 * @Description: Bootstrap table 请求参数
 * @Site: www.zhyea.com 
 * @author robin
 * @date 2015年12月13日 上午7:53:59
 */
public class BootstrapTableParams {

	public enum DIRECT {
		ASC, DESC;
	}

	/** 记录的偏移量 */
	private int offset;
	/** 查询的页面长度 */
	private int limit;
	/** 排序字段 */
	private String sort = "id";
	/** 排序方向 */
	private DIRECT order = DIRECT.DESC;

	/*--------------------------------------*/
	/**
	 * 获取记录在数据集中的偏移量
	 * @return
	 */
	public int getOffset() {
		return offset;
	}

	/**
	 * 设置记录在数据集中的偏移量
	 * @param offset
	 * 			记录在数据集中的偏移量
	 */
	public void setOffset(int offset) {
		this.offset = offset;
	}

	/**
	 * 获取查询记录长度
	 * @return
	 */
	public int getLimit() {
		return limit;
	}

	/**
	 * 设置查询记录长度
	 * @param limit
	 */
	public void setLimit(int limit) {
		this.limit = limit;
	}

	/**
	 * 获取排序字段
	 * @return
	 */
	public String getSort() {
		return sort;
	}

	/**
	 * 设置查询时排序字段
	 * @param sort
	 */
	public void setSort(String sort) {
		this.sort = sort;
	}

	/**
	 * 获取查询排序的方向
	 * @return
	 */
	public DIRECT getOrder() {
		return order;
	}

	/**
	 * 设置查询排序的方向
	 * @param order
	 */
	public void setOrder(DIRECT order) {
		this.order = order;
	}
}
