package com.zhyea.todo.global;

import java.util.Date;

import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.Page;
import com.zhyea.todo.utils.DateUtils;
import com.zhyea.todo.vo.BootstrapTableParams;

@SuppressWarnings("rawtypes")
public abstract class CustomModel<M extends Model> extends Model<M> {

	private static final long serialVersionUID = -7590503904189342566L;

	/**
	 * 检查对象是否包含某个属性
	 * @param attrs
	 * 			属性名称
	 * @return
	 */
	public boolean has(String attrs) {
		return super.getAttrs().containsKey(attrs);
	}

	/**
	 * 保存记录
	 */
	@Override
	public boolean save() {
		String now = DateUtils.format(new Date());
		if (this.has("id")) {
			super.set("update_time", now);
			return super.update();
		} else {
			super.set("create_time", now);
			super.set("update_time", now);
			return super.save();
		}
	}

	/**
	 * 分页查询
	 * @param bootstrapTableParams bootstrap table 请求参数
	 * @param select	查询字段
	 * @param sqlExceptSelect	查询条件
	 * @param paras	查询参数
	 * @return Page
	 */
	public Page<M> paginate(BootstrapTableParams bootstrapTableParams, String select, String sqlExceptSelect, Object... paras) {
		int pageNum = (bootstrapTableParams.getOffset() / bootstrapTableParams.getLimit() + 1);
		sqlExceptSelect += "order by ?1 ?2";
		sqlExceptSelect = sqlExceptSelect.replace("?1", bootstrapTableParams.getSort()).replace("?2", bootstrapTableParams.getOrder().name());
		return super.paginate(pageNum, bootstrapTableParams.getLimit(), select, sqlExceptSelect, paras);
	}
}
