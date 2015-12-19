package com.zhyea.todo.model;

import com.zhyea.todo.global.CustomModel;

/**
 * @ClassName: Memory 
 * @Description: 备忘录Model
 * @Site: www.zhyea.com 
 * @author robin
 * @date 2015年12月19日 上午8:11:36
 */
public class Memory extends CustomModel<Memory> {

	private static final long serialVersionUID = -8077816138652269489L;

	public enum Status {
		INIT("新建"), DEALED("已完成");

		public final String des;

		Status(String _des) {
			this.des = _des;
		}
	}

}
