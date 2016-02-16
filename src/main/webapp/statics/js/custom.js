/**
 * 动态加载页面
 * 
 * @param url
 */
function load(url) {
	$.ajax({
		type : 'GET',
		url : "./check",
		data : "",
		dataType : "json",
		success : function(response, status){
							if(response.success){
								$("#ToDoListContent").load(url);
							}else{
								window.location.href = "./login";
							}
					},
		error : function(){
			window.location.href = "./login";
		}
	});
}

/**
 * 获取列表中选择的记录ID
 * @param table 表格对象
 * @returns
 */
function getSelections(table) {
	var $table = $(table);
	return $.map($table.bootstrapTable('getSelections'), function(row) {
		return row.id;
	});
}

/**
 * 普通ajax表单提交
 * @param form 表单对象
 * @param callback 回调函数
 * @param confirmMsg 提示确认信息
 */
function doSubmit(form, callback, confirmMsg) {
	buttonSubmit = $("button[type='submit']")[0];
	if(buttonSubmit){
		buttonSubmit.disabled=true; 
	}
	var $form = $(form);
	var _submitFn = function() {
		$.ajax({
			type : form.method || 'POST',
			url : $form.attr("action"),
			data : $form.serializeArray(),
			dataType : "json",
			cache : false,
			success : callback
		});
	};
	if (confirmMsg) {
		$.alert({
		    title: '提示',
		    content: confirmMsg,
		    confirmButton: '确认',
		    confirmButtonClass: 'btn-info',
		    backgroundDismiss: false,
		    onClose: function(){
		    	_submitFn();
		    }
		});
	} else {
		_submitFn();
	}
	buttonSubmit = $("button[type='submit']")[0];
	if(buttonSubmit){
		buttonSubmit.disabled=false; 
	}
	return false;
}

/**
 * 包含CKEditor的表单ajax提交
 * @param form	表单对象
 * @param callback	回调函数
 * @param confirmMsg 确认信息
 */
function doCKSubmit(form, callback, confirmMsg) {
	for (instance in CKEDITOR.instances){
        CKEDITOR.instances[instance].updateElement();
    }
	doSubmit(form, callback, confirmMsg);
	return false;
}

/**
 * 提示并跳转
 * @param msg 提示信息
 * @param url 提示后要加载的路径
 */
function alertAndRedirect(data, url) {
	$.alert({
	    title: '提示',
	    content: data.msg,
	    confirmButton: '确认',
	    confirmButtonClass: 'btn-info',
	    backgroundDismiss: false,
	    onClose: function(){
	    	if(data.success){
	    		load(url);
	    	}
	    }
	});
}

/**
 * 为BootstrapTable发出的Post请求
 * @param tableObj	对象
 * @param url	请求路径
 */
function bootstrapTablePost(tableObj, url){
	$.post(url, function(data,status){
					tableObj.bootstrapTable('refresh');
					setTimeout(updateBtnState, 200);//延迟一段时间执行，避免刷新导致的延迟
				});
}

/**
 * 为BootstrapTable发出的Post请求
 * @param tableObj	对象
 * @param url	请求路径
 */
function bootstrapTableDelete(tableObj, url){
	$.confirm({
	    title: '提示!',
	    content: '记录删除以后不可恢复！仍然要删除请点击确认按钮！',
	    confirmButton: '确认',
	    cancelButton: '取消',
	    confirmButtonClass: 'btn-danger',
	    cancelButtonClass: 'btn-info',
	    confirm: function(){
			    	$.post(url, function(data,status){
			    					tableObj.bootstrapTable('refresh');
									setTimeout(updateBtnState, 200);//延迟一段时间执行，避免刷新导致的延迟
								});
	    		}
		});
}

/**
* 获取内容窗口高度
*/
function getHeight() {
	return $(window).height() - $('#ToDoListContent').outerHeight(true) - 90;
}




$(document).ready(function(){
	$(".nav-sidebar > li").click(function(){
		$(this).addClass("current");
		$(this).siblings().children("ul").slideUp("slow");
		$(this).children("ul").slideDown("slow");
		$(this).siblings().removeClass("current");
		$(this).siblings().children("ul").children("li").removeClass("current");
	});
});