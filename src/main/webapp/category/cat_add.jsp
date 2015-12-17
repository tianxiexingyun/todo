<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div class="breadcrumbs navbar-fixed-top">
	<ul class="breadcrumb">
		<li>
			<i class="icon-home home-icon"></i>
			<a href="#">首页</a>
		</li>
		<li class="active">
			<a href="javascript:load('${ctx}/cat/list')">分类信息</a>
		</li>
		<li class="active">新增</li>
	</ul>
</div>
<div class="main">
<div class="container panel panel-primary">
<form class="form-horizontal" method="post" action="${ctx}/cat/save" 
	  onsubmit="return doSubmit(this, addCatCallback);">
	<fieldset>
        <legend>新增分类信息</legend>
		<div class="form-group">
			<label class="col-md-3 control-label">分类名称：</label> 
			<div class="col-md-6 control-input">
				<input type="text" name="cat.name"   placeholder="分类名称" class="form-control" required autofocus>
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-3 control-label">分类简称：</label> 
			<div class="col-md-6 control-input">
				<input type="text" name="cat.name_en" placeholder="分类简称"  class="form-control" required autofocus>
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-3 control-label">备　　注：</label> 
			<div class="col-md-6 control-input">
				<textarea 		  name="cat.remark"   placeholder="备注" class="form-control" rows="2"></textarea>
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
				<button class="btn btn-info" type="reset" onclick="load('${ctx}/cat/list')">
					<i class="icon-hand-left bigger-110"></i>　返　回
				</button>
			</div>
		</div>
	</fieldset>
</form>
</div>
</div>
<script type="text/javascript">
function addCatCallback(data){
	alertAndRedirect(data, '${ctx}/cat/list');
}
</script>