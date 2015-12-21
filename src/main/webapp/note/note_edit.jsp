<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div class="breadcrumbs navbar-fixed-top">
	<ul class="breadcrumb">
		<li>
			<i class="icon-home home-icon"></i>
			<a href="#">首页</a>
		</li>
		<li class="active"><a href="javascript:load('${ctx}/note/list')">记事信息列表</a></li>
		<li class="active">编辑</li>
	</ul>
</div>
<div class="main">
<div class="container panel panel-primary">
<form class="form-horizontal" method="post" action="${ctx}/note/save" onsubmit="return doCKSubmit(this, addCatCallback);">
	<fieldset>
        <legend>编辑记事信息</legend>
        <input type="hidden" name="note.id" value="${note.id}">
		<div class="form-group">
			<div class="col-md-offset-1 col-md-10">
				<input type="text" name="note.brief" placeholder="标题" class="form-control" value="${note.brief}" required autofocus>
			</div>
		</div>
		<div class="form-group">
			<div class="col-md-offset-1 col-md-10">
				<textarea name="note.content" placeholder="内容" id="noteEditor" class="form-control" rows="12" required autofocus>${note.content}</textarea>
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
				<button class="btn btn-info" type="reset" onclick="load('${ctx}/note/list')">
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
	alertAndRedirect(data, '${ctx}/note/list');
}
CKEDITOR.replace( 'noteEditor' );
</script>