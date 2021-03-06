<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/tld/zy.tld" prefix="zy"%>
<div class="breadcrumbs navbar-fixed-top">
	<ul class="breadcrumb">
		<li>
			<i class="icon-home home-icon"></i>
			<a href="#">首页</a>
		</li>
		<li class="active"><a href="javascript:load('${ctx}/items/all')">事项一览表</a></li>
		<li class="active">新增</li>
	</ul>
</div>
<div class="main">
<div class="container panel panel-primary">
<form class="form-horizontal" method="post" action="${ctx}/items/save" 
	  onsubmit="return doSubmit(this, addItemsCallback);">
	<fieldset>
        <legend>新增待办事项</legend>
        <input type="hidden" name="items.id" value="${items.id}">
		<div class="form-group">
			<label class="col-md-3 control-label">分类：</label> 
			<div class="col-md-6 control-input">
				<zy:itemcategory paraName="items.category_id" themeName="form-control" value="${items.category_id}"/>
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-3 control-label">等级：</label> 
			<div class="col-md-6 control-input">
				<zy:itemlevel paraName="items.level" themeName="form-control" value="${items.level}"/>
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-3 control-label">预期完成时间：</label> 
			<div class="col-md-6 control-input">
				<input type="text" name="items.expected_time" value="${items.expected_time}" 
						placeholder="预期完成时间" class="form-control" id="expectedTime" required autofocus>
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-3 control-label">简述：</label> 
			<div class="col-md-6 control-input">
				<textarea name="items.brief" placeholder="简述" class="form-control" rows="2" required autofocus>${items.brief}</textarea>
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-3 control-label">详情：</label> 
			<div class="col-md-6 control-input">
				<textarea name="items.detail" placeholder="详情" class="form-control" rows="2" autofocus>${items.detail}</textarea>
			</div>
		</div>
		<div class="clearfix form-group">
			<div class="col-md-offset-4 col-md-5">
				<button class="btn btn-primary" type="submit">
					<i class="icon-ok   bigger-110"></i>　保　存
				</button>
				&nbsp; &nbsp; &nbsp;
				<button class="btn" type="reset">
					<i class="icon-undo bigger-110"></i>　重　置
				</button>
				&nbsp; &nbsp; &nbsp;
				<button class="btn btn-info" type="reset" onclick="load('${ctx}/items/all')">
					<i class="icon-hand-left bigger-110"></i>　返　回
				</button>
			</div>
		</div>
	</fieldset>
</form>
</div>
</div>
<script type="text/javascript">
$('#expectedTime').datetimepicker({
	language: "zh-CN",
	autoclose: "true",
	format: 'yyyy-mm-dd hh:ii'
});

function addItemsCallback(data){
	alertAndRedirect(data, '${ctx}/items/all');
}
</script>