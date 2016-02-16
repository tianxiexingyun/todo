package com.zhyea.todo.service;

import java.util.List;

import com.jfinal.plugin.activerecord.Page;
import com.zhyea.todo.model.Category;
import com.zhyea.todo.utils.StringUtils;
import com.zhyea.todo.vo.BootstrapTableParams;

/**
 * @ClassName: CategoryService 
 * @Description: 分类信息业务处理类
 * @Site: www.zhyea.com 
 * @author robin
 * @date 2015年12月2日 下午5:28:39
 */
public class CategoryService {

	private final Category dao = new Category();

	/** 查询列 */
	private static final String sqlSelect = "select id, name, name_en, remark, deleted ";

	/**
	 * 分页查询分类信息
	 * @param paras
	 * 			bootstrap查询参数
	 * @param userId
	 * 			用户ID信息
	 * @param name
	 * 			分类名称
	 * @return
	 */
	public Page<Category> findInPage(BootstrapTableParams paras, int userId, String name) {
		return dao.paginate(paras, sqlSelect, "from dt_category where deleted=0 and user_id=? and name like ? ", userId, "%" + name + "%");
	}

	/**
	 * 查询指定用户全部的分类记录
	 * @param userId
	 * 			访问用户ID
	 * @return
	 */
	public List<Category> findAll(int userId) {
		return dao.find(sqlSelect + " from dt_category where deleted=0 and user_id=?", userId);
	}

	/**
	 * 查询全部的分类记录
	 * @return
	 */
	public List<Category> findAll() {
		return dao.findByCache("categoryCache", "allcats", sqlSelect + " from dt_category where deleted=0");
	}

	/**
	 * 按ID获取记录
	 * @param id
	 * 			记录ID
	 * @return
	 */
	public Category get(Integer id) {
		return dao.findById(id);
	}

	/**
	 * 保存分类记录
	 * @param cat
	 * 		分类记录信息
	 * @return 
	 * 		是否保存成功
	 */
	public boolean save(Category cat) {
		return cat.save();
	}

	/**
	 * 批量删除记录
	 * @param ids
	 * 			记录ID
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
	 * 获取分类信息
	 * @param catId
	 * 			分类ID
	 * @return 分类名称
	 */
	public String getCat(Integer catId) {
		List<Category> cats = findAll();
		for (Category tmp : cats) {
			if (tmp.getInt("id") == catId) {
				return tmp.getStr("name");
			}
		}
		return "全部";
	}
}
