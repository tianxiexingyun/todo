package com.zhyea.todo.model;

import com.jfinal.plugin.activerecord.Page;
import com.zhyea.todo.global.CustomModel;
import com.zhyea.todo.vo.BootstrapTableParams;

/**
 * @ClassName: Items 
 * @Description: 待办事项Model
 * @Site: www.zhyea.com 
 * @author robin
 * @date 2015年11月29日 下午6:57:51
 */
public class Items extends CustomModel<Items> {

	private static final long serialVersionUID = -4607953384698528757L;

	public enum Status {
		INIT("初始化"), // 初始化
		HANDLING("处理中"), // 处理中
		FINISHED("已完成"), // 已完成
		DISCARDED("已丢弃"); // 已放弃

		public final String des;

		Status(String _des) {
			this.des = _des;
		}
	}

	public enum Level {
		S("非常紧急"), A("紧急"), B("一般"), C("不急");

		public final String desc;

		Level(String _desc) {
			this.desc = _desc;
		}
	}

	@Override
	public Page<Items> paginate(BootstrapTableParams bootstrapTableParams, String select, String sqlExceptSelect, Object... paras) {
		Page<Items> page = super.paginate(bootstrapTableParams, select, sqlExceptSelect, paras);
		return page;
	}

}
