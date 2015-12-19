<%@ page contentType="text/html; charset=UTF-8" language="java"%>
<nav class="navbar navbar-inverse navbar-fixed-top">
	<div class="container-fluid">
		<div class="navbar-header">
			<button type="button" 
					class="navbar-toggle collapsed"
					data-toggle="collapse" 
					data-target="#navbar" >
				<span class="sr-only">Toggle navigation</span> 
				<span class="icon-bar"></span> 
				<span class="icon-bar"></span> 
				<span class="icon-bar"></span>
			</button>
			<a class="navbar-brand" href="${ctx}/"><i class="glyphicon glyphicon-leaf"></i>&nbsp;&nbsp;TODO LIST</a>
		</div>
		
		<div id="navbar" class="navbar-collapse collapse">
			<ul class="nav navbar-nav navbar-right">
				<li><a href="#"><i class="glyphicon glyphicon-dashboard"></i> 控制台</a></li>
				<li><a href="/logout"><i class="glyphicon glyphicon-off"></i> 退出</a></li>
				<li><a href="#"><i class="glyphicon glyphicon-user"></i> 简介</a></li>
				<li><a href="#"><i class="glyphicon glyphicon-phone-alt"></i> 帮助</a></li>
			</ul>
		</div>
	</div>
</nav>