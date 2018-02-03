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
	<script src="${ctxStatic}/Highcharts-6.0.4/code/highcharts.js"></script>
	<script src="${ctxStatic}/Highcharts-6.0.4/code/modules/exporting.js"></script>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/bv/client/customerNode/list?usePlaceId=${usePlaceId}&usePlaceType=${usePlaceType}">节点列表</a></li>
		<li><a href="${ctx}/bv/client/customerNodeOfData/list?usePlaceId=${usePlaceId}&usePlaceType=${usePlaceType}">数据列表</a></li>
		<li class="active"><a href="${ctx}/bv/client/customerNodeOfData/charts?usePlaceId=${usePlaceId}&usePlaceType=${usePlaceType}">数据图表</a></li>
		<shiro:hasPermission name="bv:client:customerNode:edit"><li><a href="${ctx}/bv/client/customerNode/form?usePlaceId=${usePlaceId}&usePlaceType=${usePlaceType}">节点添加</a></li></shiro:hasPermission>
<%--		<li><a href="${ctx}/bv/nodeOfData/">节点测量数据列表</a></li>
		<li class="active"><a href="${ctx}/bv/nodeOfData/charts">节点测量数据图表</a></li>
		<shiro:hasPermission name="bv:nodeOfData:edit"><li><a href="${ctx}/bv/nodeOfData/form">节点测量数据添加</a></li></shiro:hasPermission> --%>
	</ul>
	<%--@elvariable id="nodeOfData" type=""--%>
	<form:form id="searchForm" modelAttribute="nodeOfData" action="${ctx}/bv/client/customerNodeOfData/charts" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<input id="usePlaceId" name="usePlaceId" type="hidden" value="${usePlaceId}"/>
		<input id="usePlaceType" name="usePlaceType" type="hidden" value="${usePlaceType}"/>
		
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
	<div id="container" style="min-width: 310px; height: 400px; margin: 0 auto"></div>

	<script type="text/javascript">
	var xAxis = ${xAxis};
	var datas = ${datas}; 
	console.log(xAxis);
	console.log(datas);

Highcharts.chart('container', {
    chart: {
        type: '${chart}'
    },
    title: {
        text: '${title}'
    },
    subtitle: {
        text: '${subtitle}'
    },
    xAxis: {
        categories: xAxis
    },
    yAxis: {
        title: {
            text: '${yAxis}'
        }
    },
    plotOptions: {
        line: {
            dataLabels: {
                enabled: true
            },
            enableMouseTracking: false
        }
    },
    series: [{
        name: '节点ID：${nodeId}',
        data: datas
    }]
});
	</script>
	
	</c:if>
	
	<div class="pagination">${page}</div>
</body>
</html>