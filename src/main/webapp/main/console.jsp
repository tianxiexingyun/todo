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
	<form class="form-horizontal" method="post"
		action="${ctx}/items/dataConsole" id="consoleForm"
		onsubmit="return doSubmit(this, getChartData);">
		<div class="col-md-2 control-input">
			<zy:duration idName="duration" paraName="duration"
				themeName="form-control" value="DATE" />
		</div>
		<div class="col-md-1 control-input">
			<button class="btn btn-primary" type="submit">
				<i class="icon-ok   bigger-110"></i> 提交
			</button>
		</div>
	</form>
	<br />
	<hr />
	<div style="clear: both;">
		<!-- 为ECharts准备一个具备大小（宽高）的Dom -->
		<div id="finishFrame" style="padding:56px 0px 0px 72px; float: left; width: 47%; height: 360px;"></div>
		<div id="catsFrame" style="float: right; width: 47%; height: 360px;"></div>
	</div>
	<script type="text/javascript">
		// 基于准备好的dom，初始化echarts图表
		var catsChart = echarts.init(document.getElementById('catsFrame'));
		var catsOption = {
			toolbox : {
				show : true,
				feature : {
					mark : {
						show : true
					},
					dataView : {
						show : true,
						readOnly : false
					},
					restore : {
						show : true
					},
					saveAsImage : {
						show : true
					}
				}
			},
			tooltip : {},
			calculable : true,
			series : [ {
				name : '事项分类',
				type : 'pie',
				radius : '55%',
				center : [ '50%', '60%' ]
			} ]
		};

		// 基于准备好的dom，初始化echarts图表
		var finishChart = echarts.init(document.getElementById('finishFrame'));
		var finishOption = {
			tooltip : {},
			calculable : true,
			series : [ {
				name : '韦恩图',
				type : 'venn',
				itemStyle : {
					normal : {
						label : {
							show : true,
							textStyle : {
								fontFamily : 'Arial, Verdana, sans-serif',
								fontSize : 16,
								fontStyle : 'italic',
								fontWeight : 'bolder'
							}
						},
						labelLine : {
							show : false,
							length : 10,
							lineStyle : {
								width : 1,
								type : 'solid'
							}
						}
					},
					emphasis : {
						color : '#cc99cc',
						borderWidth : 3,
						borderColor : '#FF0'
					}
				}
			} ]
		};

		function getChartData(response) {
			var data = response.data;
			if (data) {
				catsOption.series[0].data = data.catsMap;
				catsChart.hideLoading();
				catsChart.setOption(catsOption);
				catsChart.restore();

				finishOption.series[0].data = data.finishMap;
				finishChart.hideLoading();
				finishChart.setOption(finishOption);
				finishChart.restore();
			}
		}
	</script>
</div>