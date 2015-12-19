package com.zhyea.todo;

import com.jfinal.config.Constants;
import com.jfinal.config.Handlers;
import com.jfinal.config.Interceptors;
import com.jfinal.config.JFinalConfig;
import com.jfinal.config.Plugins;
import com.jfinal.config.Routes;
import com.jfinal.kit.PathKit;
import com.jfinal.plugin.activerecord.ActiveRecordPlugin;
import com.jfinal.plugin.activerecord.dialect.Sqlite3Dialect;
import com.jfinal.plugin.druid.DruidPlugin;
import com.jfinal.render.ViewType;
import com.zhyea.todo.controller.CategoryController;
import com.zhyea.todo.controller.IndexController;
import com.zhyea.todo.controller.ItemsController;
import com.zhyea.todo.controller.MemoryController;
import com.zhyea.todo.controller.NoteController;
import com.zhyea.todo.controller.UserController;
import com.zhyea.todo.global.LoginInterceptor;
import com.zhyea.todo.model.Category;
import com.zhyea.todo.model.Items;
import com.zhyea.todo.model.Memory;
import com.zhyea.todo.model.Note;
import com.zhyea.todo.model.User;

/**
 * @ClassName: TodoConfig 
 * @Description: 应用Jfinal配置信息
 * @Site: www.zhyea.com 
 * @author robin
 * @date 2015年11月28日 上午11:54:56
 */
public class TodoConfig extends JFinalConfig {

	/** 系统信息配置 */
	@Override
	public void configConstant(Constants me) {
		loadPropertyFile("jdbc.properties");
		me.setDevMode(com.zhyea.todo.constant.Constants.devMode);
		me.setViewType(ViewType.JSP);
	}

	/** 路由信息配置 */
	@Override
	public void configRoute(Routes me) {
		me.add("/", IndexController.class);
		me.add("/user", UserController.class);
		me.add("/items", ItemsController.class);
		me.add("/cat", CategoryController.class);
		me.add("/note", NoteController.class);
		me.add("/memory", MemoryController.class);
	}

	/** 插件信息配置 */
	@Override
	public void configPlugin(Plugins me) {
		String url = getProperty("jdbc.url");
		if (!getPropertyToBoolean("jdbc.abs", false)) {
			url = url.replace("appDir", PathKit.getWebRootPath());
		}
		// 添加数据源
		DruidPlugin druidPlugin = new DruidPlugin(url, getProperty("jdbc.user"), getProperty("jdbc.pwd"));
		druidPlugin.setDriverClass("org.sqlite.JDBC");
		me.add(druidPlugin);
		// 添加数据源ActiveRecord支持
		ActiveRecordPlugin arp = new ActiveRecordPlugin("todo", druidPlugin).setDialect(new Sqlite3Dialect());
		me.add(arp);

		/*-------------------------------数据库映射-------------------------------*/
		arp.addMapping("dt_user", User.class);
		arp.addMapping("dt_category", Category.class);
		arp.addMapping("dt_items", Items.class);
		arp.addMapping("dt_note", Note.class);
		arp.addMapping("dt_memory", Memory.class);
	}

	/** 拦截器配置 */
	@Override
	public void configInterceptor(Interceptors me) {
		me.add(new LoginInterceptor());
	}

	/** Handler信息配置 */
	@Override
	public void configHandler(Handlers me) {
	}

}
