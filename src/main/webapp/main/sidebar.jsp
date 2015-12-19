<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div class="sidebar">
	<div class="sidebar-shortcuts" id="sidebar-shortcuts">
		<div class="sidebar-shortcuts-large" id="sidebar-shortcuts-large">
			<button class="btn btn-success"> <i class="icon-signal"></i> </button>
			<button class="btn btn-info"> <i class="icon-pencil"></i> </button>
			<button class="btn btn-warning"> <i class="icon-group"></i> </button>
			<button class="btn btn-danger"> <i class="icon-cogs"></i> </button>
		</div>
		<div class="sidebar-shortcuts-mini" id="sidebar-shortcuts-mini">
			<span class="btn btn-success"></span>
			<span class="btn btn-info"></span>
			<span class="btn btn-warning"></span>
			<span class="btn btn-danger"></span>
		</div>
	</div>

	<ul class="nav nav-sidebar">
		<li><a href="javascript:load('${ctx}/items/all')">
				<i class="icon-dashboard"></i>  <span class="menu-text">控制台</span>
			</a>
		</li>
		<li><a href="javascript:load('${ctx}/note/list')">
				<i class="icon-edit"></i>
				<span class="menu-text">记事本</span>
			</a>
		</li>
		<li><a href="javascript:load('${ctx}/memory/list')">
				<i class="icon-cloud"></i>
				<span class="menu-text">备忘录</span>
			</a>
		</li>
		<li><a href="#" class="nav-header collapsed">
				<i class="icon-road"></i>
				<span class="menu-text">TODO</span>
				<b class="arrow icon-angle-down"></b>
            </a>
			<ul class="submenu nav-sidebar collapse">
				<li><a href="javascript:load('${ctx}/items/all')">全部</a></li>
				<c:forEach items="${cats}" var="c">
					<li><a href="javascript:load('${ctx}/items/listInCat/${c.id}')"> ${c.name}</a></li>
				</c:forEach>
			</ul>
		</li>
        <li><a href="#" class="nav-header collapsed">
        		<i class="icon-cog"></i>
        		<span class="menu-text">系统管理</span>
                <b class="arrow icon-angle-down"></b>
            </a>
            <ul class="submenu nav-sidebar collapse">
                <li><a href="javascript:load('${ctx}/user/list')">用户管理</a></li>
				<li><a href="javascript:load('${ctx}/cat/list')">分类信息</a></li>
            </ul>
        </li>
	</ul>
</div>