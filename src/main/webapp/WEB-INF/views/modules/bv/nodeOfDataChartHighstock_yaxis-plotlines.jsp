<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>节点测量数据管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			
		});
		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").submit();
        	return false;
        }
	</script>
</head>
<body>
	<script src="${ctxStatic}/Highcharts-6.0.4/code/modules/exporting.js"></script>
	<script src="https://img.hcharts.cn/highstock/highstock.js"></script>
	<script src="https://img.hcharts.cn/highcharts-plugins/highcharts-zh_CN.js"></script>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/bv/nodeOfData/">节点测量数据列表</a></li>
		<li class="active"><a href="${ctx}/bv/nodeOfData/charts">节点测量数据图表-Y轴标示线</a></li>
		<%-- <shiro:hasPermission name="bv:nodeOfData:edit"><li><a href="${ctx}/bv/nodeOfData/form">节点测量数据添加</a></li></shiro:hasPermission> --%>
	</ul>
	<%--@elvariable id="nodeOfData" type=""--%>
	<form:form id="searchForm" modelAttribute="nodeOfData" action="${ctx}/bv/nodeOfData/charts" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		
		<label>节点ID：</label>
		<input id="nodeId" name="nodeId" type="input" value="${nodeOfData.nodeId}"/>
		<input id="startTime" name="startTime" type="text" readonly="readonly"
			   maxlength="20" class="input-medium Wdate " value="${start}"
			   onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false,maxDate:'#F{$dp.$D(\'startTime\')}'||'%y-%M-%d %H:%m:%s',isShowClear:true});" />
		<input id="endTime" name="endTime" type="text" readonly="readonly"
			   maxlength="20" class="input-medium Wdate " value="${end}"
			   onclick="WdatePicker({minDate:'#F{$dp.$D(\'startTime\')}',maxDate:'%y-%M-%d %H:%m:%s',dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:true});"/>
		<ul class="ul-form">
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	
	<c:if test="${!empty page.list}">
	<div id="container" style="min-width: 400px; height: 400px; margin: 0 auto"></div>

<script type="text/javascript">
		//不使用 UTC
        // Highcharts.setOptions({ global: { useUTC: false } });

	var xAxis = ${xAxis};
    var primaryDatas = ${datas};

    var datas = [];

    var oneData = [];
    for (var i = 0; i < primaryDatas.length; i++){
        oneData = primaryDatas[i];
        // console.log(oneData);

        var buildData = [];

        buildData.push(oneData.timeTag);
        buildData.push(oneData.temperature);
        datas.push(buildData);
	}

	//UTC() 方法可根据世界时返回 1970 年 1 月 1 日 到指定日期的毫秒数。
    // datas = [
     //    [Date.UTC(2013,3,30),17151],
     //    [Date.UTC(2013,4,1),171751],
     //    [Date.UTC(2013,4,2),1095]
    // ]

	console.log(xAxis);
	console.log(datas);

	var startDate = new Date(datas[datas.length - 1][0]), // Get year of last data point
		minRate = 100,
		maxRate = 100,
		startPeriod,
		date,
		rate,
		index;
	startDate.setMonth(startDate.getMonth() - 3); // a quarter of a year before last data point
	startPeriod = Date.UTC(startDate.getFullYear(), startDate.getMonth(), startDate.getDate());
	for (index = datas.length - 1; index >= 0; index = index - 1) {
		date = datas[index][0]; // data[i][0] is date
		rate = datas[index][1]; // data[i][1] is exchange rate
		if (date < startPeriod) {
			break; // stop measuring highs and lows
		}
		if (rate > maxRate) {
			maxRate = rate;
		}
		if (rate < minRate) {
			minRate = rate;
		}
	}

	// Create the chart
	$('#container').highcharts('StockChart', {
		rangeSelector: {
			selected: 1
		},
		title: {
			text: '${title}'
		},
		subtitle: {
			text: '${subtitle}'
		},
		yAxis: {
			title: {
				text: '${yAxis}'
			},
			plotLines: [{
				value: minRate,
				color: 'green',
				dashStyle: 'shortdash',
				width: 10,
				label: {
					text: '低温线'
				}
			}, {
				value: maxRate,
				color: 'red',
				dashStyle: 'shortdash',
				width: 10,
				label: {
					text: '高温线'
				}
			}]
		},
		series: [{
			name: '节点ID：${nodeId}',
			data: datas,
			tooltip: {
				valueDecimals: 4
			}
		}]
	});
</script>
	
	</c:if>
	
	<div class="pagination">${page}</div>
</body>
</html>