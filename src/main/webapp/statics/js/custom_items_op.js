
$('#itemsTable').bootstrapTable({});

var $table = $('#itemsTable'),
	$btnDelete = $('#btnDelete'),
	$btnFinished = $('#btnFinished'),
	$btnDiscarded = $('#btnDiscarded'),
	$btnRecover = $('#btnRecover'),
	$btnLevel = $('#btnLevel'),
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
 * 操作
 */
function itemsOperateFormatter(value, row, index) {
	return ['<button class="update btn btn-success btn-xs" title="编辑事项信息">',
		    '<i class="glyphicon glyphicon-pencil"></i> 编辑',
		    '</button>'].join('');
}
window.itemsOperateEvents = {
    'click .update': function (e, value, row, index) {
        load(contextUrl + '/items/edit/'+row.id);
    }
};

/**
 * 更新安装disabled状态
 */
function updateBtnState(){
	var isChecked = !$table.bootstrapTable('getSelections').length;
		$btnDelete.prop('disabled', isChecked);
		$btnFinished.prop('disabled', isChecked);
		$btnDiscarded.prop('disabled', isChecked);
		$btnRecover.prop('disabled', isChecked);
		$btnLevel.prop('disabled', isChecked);
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
$btnDelete.click(function () {bootstrapTableDelete($table, contextUrl + "/items/delete?ids="+selections);});
/**
 * 点击已处理按钮
 */
$btnFinished.click(function () {bootstrapTablePost($table, contextUrl + "/items/finish?ids="+selections);});
/**
 * 点击已放弃按钮
 */
$btnDiscarded.click(function () {bootstrapTablePost($table, contextUrl + "/items/discard?ids="+selections);});
/**
 * 点击初始化按钮
 */
$btnRecover.click(function () {bootstrapTablePost($table, contextUrl + "/items/recover?ids="+selections);});

/**
 * 设置事项紧急级别
 */
function setItemsLevel(level){
	bootstrapTablePost($table, contextUrl + "/items/setLevel?ids="+selections + "&level=" + level);
}
			
/**
 * 设置行格式
 */
function rowStyle(row, index) {
	// 默认样式 active、success、info、warning、danger
    if (row.status == "已丢弃") {
        return {
            classes: 'discarded'
        };
    }
    if (row.status == "已完成") {
        return {
            classes: 'finished'
        };
    }
    if (row.level == "S") {
        return {
            classes: 'urgent'
        };
    }
    if (row.level == "A") {
        return {
            classes: 'danger'
        };
    }
    if (row.level == "C") {
        return {
            classes: 'info'
        };
    }
    return {};
}