<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div class="breadcrumbs navbar-fixed-top">
	<ul class="breadcrumb">
		<li>
			<i class="icon-home home-icon"></i>
			<a href="#">首页</a>
		</li>
		<li class="active"><a href="#">备忘信息列表</a></li>
	</ul>
</div>
<div class="main">
	<div id="toolbar">
		<div class="btn-group">
	        	<button id="btnAdd" class="btn btn-default" onclick="load('${ctx}/memory/add')"><i class="icon-plus"></i> 新增</button>
	        	<button id="btnDelete" class="btn btn-default" disabled><i class="icon-minus"></i> 删除</button>
	        	<button id="btnDealed" class="btn btn-default" disabled><i class="icon-ok"></i> 已处理</button>
        </div>
    </div>
	<table id="memoryTable"
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
		   data-url="${ctx}/memory/data">
		<thead>
			<tr>
				<th data-checkbox=true data-valign="middle" data-width="48px"  data-field="status">&nbsp;</th>
				<th data-sortable=true data-valign="middle" data-width="72px"  data-field="id" data-align="left">ID</th>
				<th data-sortable=true data-valign="middle" data-field="brief" data-formatter=memoryBriefFormatter>标题</th>
				<th data-sortable=true data-valign="middle" data-width="180px" data-field="create_time" data-align="center">日期</th>
				<th data-sortable=true data-valign="middle" data-width="120px" data-align="center" 
									   data-formatter=memoryOperateFormatter   data-events=memoryOperateEvents>操作</th>
			</tr>
		</thead>
	</table>
</div>
<script type="text/javascript">
var $table = $('#memoryTable'),
	$btnDelete = $('#btnDelete'),
	$btnDealed = $('#btnDealed'),
	selections = [];

/**
 * 更新安装disabled状态
 */
function updateBtnState(){
	var isChecked = !$table.bootstrapTable('getSelections').length;
	$btnDelete.prop('disabled', isChecked);
	$btnDealed.prop('disabled', isChecked);
}

/**
 * 操作 -> 编辑
 */
function memoryOperateFormatter(value, row, index) {
    return [
         '<button class="update btn btn-success btn-xs" title="编辑记事信息">',
         '<i class="glyphicon glyphicon-pencil"></i> 编辑',
         '</button>'
    ].join('');
}
window.memoryOperateEvents = {
	    'click .update': function (e, value, row, index) {
	        load('${ctx}/memory/edit/'+row.id);
	    }
	};
/**
 * 标题
 */
function memoryBriefFormatter(value, row, index) {
    return [
            '<a href="javascript:load(\'${ctx}/memory/edit/'+row.id+'\')">',
            value,
            '</a>'
       ].join('');
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
$btnDelete.click(function () {bootstrapTableDelete($table, "${ctx}/memory/delete?ids="+selections);});
/**
 * 点击已完成按钮
 */
$btnDealed.click(function () {bootstrapTablePost($table, "${ctx}/memory/dealed?ids="+selections);});

$table.bootstrapTable({});

function rowStyle(row, index) {
	// 默认样式 active、success、info、warning、danger
    if (row.status == "DEALED") {
        return {
            classes: 'finished'
        };
    }
    return {};
}
</script>