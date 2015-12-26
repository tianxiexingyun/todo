<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/tld/zy.tld" prefix="zy"%>
<div class="breadcrumbs navbar-fixed-top">
	<ul class="breadcrumb">
		<li><i class="icon-home home-icon"></i> <a href="#">首页</a></li>
		<li class="active"><a href="#">控制台</a></li>
	</ul>
</div>
<div class="main">
	<form class="form-horizontal" method="post" action="${ctx}/items/dataConsole" id="consoleForm" onsubmit="return doSubmit(this, getChartData);">
		<div class="col-md-2 control-input">
			<zy:duration idName="duration" paraName="duration" themeName="form-control" value="MONTH" />
		</div>
		<div class="col-md-1 control-input">
			<button class="btn btn-primary" type="submit">
				<i class="icon-ok   bigger-110"></i> 提交
			</button>
		</div>
	</form>
	<br />
	<hr />
	<!-- 为ECharts准备一个具备大小（宽高）的Dom -->
	<div id="daysFrame" style="height: 480px;"></div>
	<script type="text/javascript">
		// 基于准备好的dom，初始化echarts图表
		var daysChart = echarts.init(document.getElementById('daysFrame'));
		var daysOption = {
			tooltip : {
				trigger : 'axis'
			},
			legend : {
				data : [ '事项数量' ]
			},
			toolbox : {
				show : true,
				feature : {
					mark : { show : true },
					magicType : {
						show : true,
						type : [ 'line', 'bar' ]
					},
					restore : { show : true },
					saveAsImage : { show : true }
				}
			},
			calculable : true,
			xAxis : [ {
				type : 'category',
				boundaryGap : false
			} ],
			yAxis : [ {
				type : 'value'
			} ],
			series : [ {
				name : '事项数量',
				type : 'line',
				smooth : true,
				itemStyle : {
					normal : {
						areaStyle : {
							type : 'default'
						}
					}
				}
			} ]
		};

		function getChartData(response) {
			var data = response.data;
			if (data) {
				daysOption.xAxis[0].data = data.daysKey;
				daysOption.series[0].data = data.daysNum;
				daysChart.hideLoading();
				daysChart.setOption(daysOption);
				daysChart.restore();
			}
		}
		
		$("#consoleForm").submit();
	</script>
</div>