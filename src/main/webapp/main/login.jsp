<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<c:set var="ctx" value="${pageContext.servletContext.contextPath}" />
<!DOCTYPE html>
<html lang="zh-CN">
<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
	
	<title>待办事项列表</title>
	<link rel="stylesheet" href="//cdn.bootcss.com/bootstrap/3.3.5/css/bootstrap.min.css">
	<link rel="stylesheet" href="${ctx}/statics/css/font-awesome.min.css">
	<link rel="stylesheet" href="${ctx}/statics/bstable/bootstrap-table.min.css">
	<link rel="stylesheet" href="${ctx}/statics/jqueryconfirm/jquery-confirm.css">
	
	<link rel="stylesheet" href="${ctx}/statics/css/login.css">
</head>
<body>

<div class="contianer">
<form class="form-signin" method="post" action="${ctx}/check" onsubmit="return checkLogin(this);">
		<div class="form-group">
			<label class="col-md-3 control-label">用户名：</label> 
			<div class="col-md-8 control-input">
				<input type="text" name="username"   placeholder="用户名" class="form-control" required autofocus>
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-3 control-label">密　码：</label> 
			<div class="col-md-8 control-input">
				<input type="password" name="password" placeholder="密码"  class="form-control" required autofocus>
			</div>
		</div>
		<div class="clearfix form-group">
			<div class="col-md-offset-3 col-md-7">
				<button class="btn btn-primary" type="submit">
					<i class="icon-ok   bigger-110"></i>　登　录
				</button>
				&nbsp; &nbsp; &nbsp;
				<button class="btn" type="reset">
					<i class="icon-undo bigger-110"></i>　重　置
				</button>
			</div>
		</div>
</form>
</div>
<script type="text/javascript">
function checkLogin(form){
	var $form = $(form);
	$.ajax({
		type : form.method || 'POST',
		url : $form.attr("action"),
		data : $form.serializeArray(),
		dataType : "json",
		cache : false,
		success : function(data){
						if(!data.success){
								$.alert({
								    title: '提示',
								    content: data.msg,
								    confirmButton: '确认',
								    confirmButtonClass: 'btn-info',
								    backgroundDismiss: false
								});
						}else{
							window.location.href = "./";
						}
					}
	});
	return false;
}
</script>

	<!-- Bootstrap core JavaScript  ================================================== -->
	<!-- Placed at the end of the document so the pages load faster -->
	<script src="//cdn.bootcss.com/jquery/1.11.3/jquery.min.js"></script>
	<script src="//cdn.bootcss.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
	<script src="${ctx}/statics/jqueryconfirm/jquery-confirm.js"></script>
</body>
</html>

