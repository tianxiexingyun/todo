package com.zhyea.todo.global;

import com.jfinal.core.Controller;
import com.zhyea.todo.constant.Constants;
import com.zhyea.todo.model.User;
import com.zhyea.todo.vo.BootstrapTableParams;
import com.zhyea.todo.vo.BootstrapTableParams.DIRECT;

public abstract class CustomController extends Controller {

	/**
	 * 获取session中缓存的用户对象
	 * @return
	 */
	protected User getUserInSession() {
		return getSessionAttr(Constants.USER_IN_SESSION);
	}

	/**
	 * 获取在session中缓存的用户ID
	 * @return
	 * 		缓存的用户ID
	 */
	protected int getUserIdInSession() {
		User user = getSessionAttr(Constants.USER_IN_SESSION);
		if (null != user) {
			return user.getInt("id");
		}
		return -1;
	}

	/**
	 * 为记录模型设置用户ID
	 * @param model
	 * 			记录Model	
	 */
	protected void setUserIdInModel(CustomModel<?> model) {
		int userId = getUserIdInSession();
		model.set("user_id", userId);
		model.set("update_user_id", userId);
	}

	protected BootstrapTableParams getBootstrapTableParas() {
		BootstrapTableParams bootstrapTableParams = new BootstrapTableParams();
		bootstrapTableParams.setOffset(getParaToInt("offset"));
		bootstrapTableParams.setLimit(getParaToInt("limit"));
		bootstrapTableParams.setSort(getPara("sort"));
		bootstrapTableParams.setOrder(DIRECT.valueOf(getPara("order").toUpperCase()));
		return bootstrapTableParams;
	}

}
