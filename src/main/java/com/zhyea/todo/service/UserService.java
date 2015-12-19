package com.zhyea.todo.service;

import com.jfinal.plugin.activerecord.Page;
import com.zhyea.todo.model.User;
import com.zhyea.todo.utils.Md5Utils;
import com.zhyea.todo.utils.StringUtils;
import com.zhyea.todo.vo.BootstrapTableParams;

/**
 * @ClassName: UserService 
 * @Description: 用户信息业务处理类
 * @Site: www.zhyea.com 
 * @author robin
 * @date 2015年12月11日 上午7:07:40
 */
public class UserService {

	private final User dao = new User();

	private static final String sqlSelect = "select id, username, nickname, remark, deleted ";

	/**
	 * 保存用户对象
	 * @param user
	 * 			用户对象
	 * @return
	 */
	public boolean save(User user) {
		if (user.has("password")) {
			user.set("password", Md5Utils.encrypt(salt + user.getStr("password")));
		}
		return user.save();
	}

	/**
	 * 分页查询用户信息
	 * @param paras
	 * 			bootstrap参数
	 * @param name
	 * 			要查询的用户名
	 * @return
	 */
	public Page<User> findInPage(BootstrapTableParams paras, String name) {
		return dao.paginate(paras, sqlSelect, "from dt_user where deleted=0 and username like ? ", "%" + name + "%");
	}

	/**
	 * 按ID获取记录
	 * @param id
	 * 			记录ID
	 * @return
	 */
	public User get(Integer id) {
		return dao.findById(id);
	}

	/**
	 * 校验登录信息
	 * @param username
	 * 				用户名
	 * @param password
	 * 				密码
	 * @return
	 */
	public User checkLogin(String username, String password) {
		password = Md5Utils.encrypt(salt + password);
		User user = dao.findFirst(sqlSelect + " from dt_user where username=? and password=? and deleted=0", username.trim(), password);
		return user;
	}

	/**
	 * 批量删除记录
	 * @param ids
	 * 			记录ID
	 * @return
	 */
	public boolean delete(String ids) {
		String[] arr = ids.split(",");
		for (String ele : arr) {
			Integer eleInt = StringUtils.toInt(ele);
			if (eleInt > 0) {
				dao.deleteById(eleInt);
			}
		}
		return true;
	}

	/**
	 * 用户密码加密盐
	 */
	private static final String salt = "UserPASSworDSaLT!#$&!";
}
