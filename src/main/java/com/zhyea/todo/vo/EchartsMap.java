package com.zhyea.todo.vo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @ClassName: EchartsMap 
 * @Description: 适用于echarts的key-value对
 * @Site: www.zhyea.com 
 * @author robin
 * @date 2015年12月26日 上午10:46:14
 */
public class EchartsMap implements Cloneable {

	private Logger logger = LoggerFactory.getLogger(EchartsMap.class);

	private EchartsMap me = null;

	/** 名称 */
	private String name;
	/** 值 */
	private Integer value;

	/**
	 * 构造器
	 */
	public EchartsMap() {
	}

	/**
	 * 构造器
	 * @param _name
	 * 				名称
	 * @param _value
	 * 				值
	 */
	public EchartsMap(String _name, int _value) {
		this.name = _name;
		this.value = _value;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getValue() {
		return value;
	}

	public void setValue(Integer value) {
		this.value = value;
	}

	/**
	 * clone对象
	 */
	@Override
	public EchartsMap clone() {
		try {
			me = (EchartsMap) super.clone();
		} catch (Exception e) {
			logger.error("clone EchartsMap error...", e);
		}
		return me;
	}
}
