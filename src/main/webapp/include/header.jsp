<%@ page contentType="text/html; charset=UTF-8" language="java"%>
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
	<link rel="stylesheet" href="${ctx}/statics/bsdatetime/bootstrap-datetimepicker.min.css">
	<link rel="stylesheet" href="${ctx}/statics/jqueryconfirm/jquery-confirm.css">
	
	<link rel="stylesheet" href="${ctx}/statics/css/theme.css">
</head>
<body>
