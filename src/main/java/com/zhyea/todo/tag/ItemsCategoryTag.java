package com.zhyea.todo.tag;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.servlet.jsp.tagext.Tag;
import javax.servlet.jsp.tagext.TagSupport;

import com.zhyea.todo.constant.Constants;
import com.zhyea.todo.model.Category;
import com.zhyea.todo.model.User;
import com.zhyea.todo.service.CategoryService;
import com.zhyea.todo.utils.StringUtils;

/**
 * @ClassName: ItemsCategoryTag 
 * @Description: 分类标记
 * @Site: www.zhyea.com 
 * @author robin
 * @date 2015年12月13日 下午4:59:45
 */
public class ItemsCategoryTag extends TagSupport {

	private static final long serialVersionUID = 7487174413534925605L;

	private final CategoryService service = new CategoryService();
	/** 表单名称 */
	private String paraName;
	/** 表单样式类 */
	private String themeName;
	/** 表单值 */
	private String value;

	@Override
	public int doStartTag() {
		try {
			List<Category> cats = service.findAll(getUserId());
			StringBuilder builder = new StringBuilder();
			builder.append("<select placeholder='分类' name='").append(paraName).append("' class='").append(themeName).append("' required autofocus>");
			for (Category tmp : cats) {
				builder.append("<option value='").append(tmp.getInt("id")).append("' ");
				if (StringUtils.toInt(this.value) == tmp.getInt("id")) {
					builder.append("selected");
				}
				builder.append(">");
				builder.append(tmp.getStr("name")).append("</option>");
			}
			builder.append("</select>");
			pageContext.getOut().write(builder.toString());
		} catch (IOException e) {
			return Tag.SKIP_BODY;
		}

		return Tag.EVAL_BODY_INCLUDE;
	}

	/**
	 * 从session中获取用户ID
	 * @return
	 */
	private Integer getUserId() {
		HttpSession session = pageContext.getSession();
		User user = (User) session.getAttribute(Constants.USER_IN_SESSION);
		if (null != user) {
			return user.getInt("id");
		}
		return -1;
	}

	public String getParaName() {
		return paraName;
	}

	public void setParaName(String paraName) {
		this.paraName = paraName;
	}

	public String getThemeName() {
		return themeName;
	}

	public void setThemeName(String themeName) {
		this.themeName = themeName;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
}
