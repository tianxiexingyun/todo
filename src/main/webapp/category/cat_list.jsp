<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div class="breadcrumbs navbar-fixed-top">
	<ul class="breadcrumb">
		<li>
			<i class="icon-home home-icon"></i>
			<a href="#">首页</a>
		</li>
		<li class="active">分类信息列表</li>
	</ul>
</div>
<div class="main">
	<div id="toolbar">
		<div class="btn-group">
	        	<button id="btnAdd" class="btn btn-default" onclick="load('${ctx}/cat/add')"><i class="icon-plus"></i> 新增</button>
	        	<button id="btnDelete" class="btn btn-default" disabled><i class="icon-minus"></i> 删除</button>
        </div>
    </div>
	<table id="categoryTable"
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
		   data-url="${ctx}/cat/data">
		<thead>
			<tr>
				<th data-checkbox=true data-valign="middle" data-width="48px"  data-field="deleted">&nbsp;</th>
				<th data-sortable=true data-valign="middle" data-width="72px"  data-field="id" data-align="left">ID</th>
				<th data-sortable=true data-valign="middle" data-field="name" >分类名称</th>
				<th data-sortable=true data-valign="middle" data-field="name_en">分类简称</th>
				<th data-sortable=true data-valign="middle" data-field="remark">描述</th>
				<th data-sortable=true data-valign="middle" data-width="96px"  data-align="center" 
									   data-formatter=categoryOperateFormatter data-events=categoryOperateEvents>操作</th>
			</tr>
		</thead>
	</table>
</div>
<script type="text/javascript">
var $table = $('#categoryTable'),
	$btnDelete = $('#btnDelete'),
	selections = [];

/**
 * 根据窗口大小调整表格高度
 */
$(window).resize(function () {
	$table.bootstrapTable('resetView', {
	    height: getHeight()
	});
});

/**
 * 操作 -> 编辑
 */
function categoryOperateFormatter(value, row, index) {
    return [
         '<button id="user_add" class="update btn btn-success btn-xs" title="编辑分类信息">',
         '<i class="glyphicon glyphicon-pencil"></i> 编辑',
         '</button>'
    ].join('');
}
window.categoryOperateEvents = {
	    'click .update': function (e, value, row, index) {
	        load('${ctx}/cat/edit?id='+row.id);
	    }
	};

/**
 * 更新安装disabled状态
 */
function updateBtnState(){
	var isChecked = !$table.bootstrapTable('getSelections').length;
	$btnDelete.prop('disabled', isChecked);
}

/**
 * 设置触发事件
 */
$table.on('check.bs.table uncheck.bs.table check-all.bs.table uncheck-all.bs.table', 
			function () {
				updateBtnState();
				selections = getSelections($table);
			});

/**
 * 点击删除按钮
 */
$btnDelete.click(function () {bootstrapTableDelete($table, "${ctx}/cat/delete?ids="+selections);});


$table.bootstrapTable({});
</script>