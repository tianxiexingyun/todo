<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div class="breadcrumbs navbar-fixed-top">
	<ul class="breadcrumb">
		<li>
			<i class="icon-home home-icon"></i>
			<a href="#">首页</a>
		</li>
		<li class="active">事项一览表</li>
	</ul>
</div>
<div class="main">
	<div id="toolbar">
		<div class="btn-group">
	        <button id="btnAdd" class="btn btn-default" onclick="load('${ctx}/items/add')"><i class="icon-plus"></i> 新增</button>
	        <button id="btnDelete" class="btn btn-default" disabled><i class="icon-minus"></i> 删除</button>
	        <button id="btnFinished" class="btn btn-default" disabled><i class="icon-ok"></i> 已完成</button>
	        <button id="btnDiscarded" class="btn btn-default" disabled><i class="icon-trash"></i> 已丢弃</button>
	        <button id="btnRecover" class="btn btn-default" disabled><i class=" icon-undo"></i> 初始化</button>
	        <div class="btn-group">
				<button id="btnLevel" type="button" class="btn btn-default dropdown-toggle" 
						data-toggle="dropdown" 
						disabled>
			    	<i class="icon-tags"></i> 级别 
			    	<span class="caret"></span>
			  	</button>
			  	<ul class="dropdown-menu">
				    <li><a href="javascript:setItemsLevel('C')">C - 不急</a></li>
				    <li><a href="javascript:setItemsLevel('B')">B - 一般</a></li>
				    <li><a href="javascript:setItemsLevel('A')">A - 紧急</a></li>
				    <li><a href="javascript:setItemsLevel('S')">S - 非常紧急</a></li>
			  	</ul>
			</div>
        </div>
    </div>
	<table id="itemsTable"
		   data-toggle="table"
		   data-toolbar="#toolbar"
		   data-show-toggle= true
           data-show-columns= true
		   data-show-refresh = true
		   data-search = true
		   data-pagination = true
		   data-page-size = 10
     	   data-row-style = "rowStyle"
     	   data-sort-name = "id"
     	   data-sort-order = "desc"
     	   data-smart-display = true
           data-mobile-responsive=true
		   data-page-list = [10,15,25,50,All]
		   data-side-pagination = 'server'
           data-check-on-init = true
		   data-url="${ctx}/items/dataAll">
		<thead>
			<tr>
				<th data-checkbox=true data-field="deleted" data-width="48px">&nbsp;</th>
				<th data-sortable=true data-field="id" data-width="72px" data-align="left">ID</th>
				<th data-sortable=true data-field="category_id" data-width="81px" data-align="center">分类</th>
				<th data-sortable=false data-field="brief">事项</th>
				<th data-sortable=true data-field="expected_time" data-width="160px" data-align="center">预计完成时间</th>
				<th data-sortable=true data-field="complete_time" data-width="160px" data-visible=false data-align="center">实际完成时间</th>
				<th data-sortable=true data-field="level" data-width="81px" data-align="center">级别</th>
				<th data-sortable=true data-field="status" data-width="81px" data-align="center">状态</th>
				<th data-sortable=true data-field="detail" data-visible=false>详情</th>
				<th data-sortable=true data-valign="middle" data-width="100px"  data-align="center" 
									   data-formatter=itemsOperateFormatter data-events=itemsOperateEvents>操作</th>
			</tr>
		</thead>
	</table>
</div>
<script type="text/javascript">
var contextUrl = '${ctx}';
</script>
<script src="${ctx}/statics/js/custom_items_op.js"></script>