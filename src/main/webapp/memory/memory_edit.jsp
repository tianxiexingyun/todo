<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div class="breadcrumbs navbar-fixed-top">
	<ul class="breadcrumb">
		<li>
			<i class="icon-home home-icon"></i>
			<a href="#">首页</a>
		</li>
		<li class="active"><a href="javascript:load('${ctx}/memory/list')">备忘信息列表</a></li>
		<li class="active">编辑</li>
	</ul>
</div>
<div class="main">
<div class="container panel panel-primary">
<form class="form-horizontal" method="post" action="${ctx}/memory/save" onsubmit="return doCKSubmit(this, addCatCallback);">
	<fieldset>
        <legend>编辑备忘信息</legend>
        <input type="hidden" name="memory.id" value="${memory.id}">
        <input type="hidden" name="memory.id" value="${memory.status}">
		<div class="clearfix form-group">
			<div class="col-md-offset-1 col-md-10">
				<input type="text" name="memory.brief" placeholder="标题" class="form-control" value="${memory.brief}" required autofocus>
			</div>
		</div>
		<div class="clearfix form-group">
			<div class="col-md-offset-1 col-md-10">
				<textarea name="memory.content" placeholder="内容" id="memoryEditor" class="form-control" rows="12" required autofocus>${memory.content}</textarea>
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
				<button class="btn btn-info" type="reset" onclick="load('${ctx}/memory/list')">
					<i class="icon-hand-left bigger-110"></i>　返　回
				</button>
			</div>
		</div>
	</fieldset>
</form>
</div>
</div>
<script src="${ctx}/statics/ckeditor/ckeditor.js"></script>
<script type="text/javascript">
function addCatCallback(data){
	alertAndRedirect(data, '${ctx}/memory/list');
}
CKEDITOR.replace( 'memoryEditor' );
</script>