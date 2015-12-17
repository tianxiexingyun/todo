-- 删除待办事项表
drop table if exists dt_items;
-- 删除自定义类别表
drop table if exists dt_category;
-- 删除用户信息表
drop table if exists dt_user;

-- ---------------------------------------------------------------------------------

-- 用户信息表
create table dt_user(
	id integer primary key autoincrement,
	username varchar(32) unique, -- 用户名
	password varchar(64), -- 密码
	nickname varchar(32), -- 昵称
	remark varchar(256),  -- 备注
	
	deleted smallint default 0, -- 是否已删除
	update_user_id integer, -- 最近更新用户ID
	create_time varchar(32), -- 创建时间
	update_time varchar(32) -- 更新时间
);
INSERT INTO dt_user
		VALUES (0, 'admin', 'f9195f456cfe67a49467481483c81eca', '管理员', '备注', 0, -1, '2015-12-12 21:38:54', '2015-12-12 21:38:54');
INSERT INTO dt_user 
		VALUES (1, 'robin', 'f9195f456cfe67a49467481483c81eca', '普通用户', '备注', 0, -1, '2015-12-12 21:39:19', '2015-12-12 21:39:19');

-- 自定义分类信息表
create table dt_category(
	id integer primary key autoincrement,
	user_id integer, -- 用户ID
	name varchar(32), -- 分类名称
	name_en varchar(16), -- 分类名称-英文
	remark varchar(128), -- 备注信息
	
	deleted smallint default 0, -- 是否已删除
	update_user_id integer, -- 最近更新用户ID
	create_time varchar(32), -- 创建时间
	update_time varchar(32) -- 更新时间
);
INSERT INTO dt_category 
		VALUES (0, 0, '未分类', 'uncategory', '未分类', 0, 0, '2015-12-12 21:40:40', '2015-12-14 22:11:18');
INSERT INTO dt_category 
		VALUES (1, 0, '工作', 'work',  '工作', 0, 0, '2015-12-14 22:11:38', '2015-12-14 22:11:38');
INSERT INTO dt_category 
		VALUES (2, 0, '健身', 'sport', '健身', 0, 0, '2015-12-14 22:12:26', '2015-12-14 22:12:26');
INSERT INTO dt_category 
		VALUES (3, 0, '学习', 'study', '健身', 0, 0, '2015-12-14 22:12:26', '2015-12-14 22:12:26');

-- 待办事项表
create table dt_items(
	id integer primary key autoincrement,
	user_id integer, -- 用户ID
	category_id integer, -- 类别ID
	brief varchar(128), -- 简述
	detail varchar(256), -- 详情
	expected_time varchar(32), -- 预期完成时间
	complete_time varchar(32), -- 实际完成时间
	level varchar(8) default 'B', -- 紧急程度
	status varchar(16), -- 记录处理状态

	deleted smallint default 0, -- 是否已删除
	update_user_id integer, -- 最近更新用户ID
	create_time varchar(32), -- 创建时间
	update_time varchar(32) -- 更新时间
);
