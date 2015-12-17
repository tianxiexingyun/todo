package com.zhyea.todo.tag;

import java.io.IOException;

import javax.servlet.jsp.tagext.SimpleTagSupport;

import com.zhyea.todo.model.Items.Level;

/**
 * @ClassName: ItemsCategoryTag 
 * @Description: 分类标记
 * @Site: www.zhyea.com 
 * @author robin
 * @date 2015年12月13日 下午4:59:45
 */
public class ItemsLevelTag extends SimpleTagSupport {

	/** 表单名称 */
	private String paraName;
	/** 表单样式类 */
	private String themeName;
	/** 值 */
	private String value;

	@Override
	public void doTag() throws IOException {
		StringBuilder builder = new StringBuilder();
		builder.append("<select placeholder='级别' name='").append(paraName).append("' class='").append(themeName).append("' required autofocus>");
		for (Level tmp : Level.values()) {
			builder.append("<option value='").append(tmp).append("'");
			if (Level.valueOf(value) == tmp) {
				builder.append(" selected ");
			}
			builder.append(">");
			builder.append(tmp).append(" - ").append(tmp.desc).append("</option>");
		}
		builder.append("</select>");
		getJspContext().getOut().write(builder.toString());
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
