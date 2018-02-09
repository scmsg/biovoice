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
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/bv/client/customerNode/list?usePlaceId=${usePlaceId}&usePlaceType=${usePlaceType}">节点列表</a></li>
		<li class="active"><a href="${ctx}/bv/client/customerNodeOfData/list?usePlaceId=${usePlaceId}&usePlaceType=${usePlaceType}">数据列表</a></li>
		<li><a href="${ctx}/bv/client/customerNodeOfData/charts?usePlaceId=${usePlaceId}&usePlaceType=${usePlaceType}">数据图表</a></li>
		<shiro:hasPermission name="bv:client:customerNode:edit"><li><a href="${ctx}/bv/client/customerNode/form?usePlaceId=${usePlaceId}&usePlaceType=${usePlaceType}">节点添加</a></li></shiro:hasPermission>
		<%-- <li class="active"><a href="${ctx}/bv/nodeOfData/">节点测量数据列表</a></li>
		<li><a href="${ctx}/bv/nodeOfData/charts">节点测量数据图表</a></li>
		<shiro:hasPermission name="bv:nodeOfData:edit"><li><a href="${ctx}/bv/nodeOfData/form">节点测量数据添加</a></li></shiro:hasPermission> --%>
	</ul>
	<form:form id="searchForm" modelAttribute="nodeOfData" action="${ctx}/bv/nodeOfData/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		
		<label>节点ID：</label>
		<input id="nodeId" name="nodeId" type="input" value="${nodeOfData.nodeId}"/>
		
		<ul class="ul-form">
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>设备ID(单机版网关ID)</th>
				<th>节点ID</th>
				<th>产品类型</th>
				<th>是否 充电</th>
				
				<th>设备电池电量</th>
				<th>信号强度</th>
				<th>测量分钟间隔</th>
				<th>查看数据标识</th>
				
				<th>测量时间</th>
				<th>温度值</th>
				
				
				<%-- <shiro:hasPermission name="bv:nodeOfData:edit"><th>操作</th></shiro:hasPermission> --%>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="nodeOfData">
			<tr>
				<td>${nodeOfData.masterId}</td>
				<td>${nodeOfData.nodeId}</td>
				<td>${nodeOfData.productType eq 1?'温度':'温湿度'}</td>
				<td>${nodeOfData.chargingState eq 1?'充电':'未充电'}</td>
				
				<td>${nodeOfData.battery}级</td>
				<td>${nodeOfData.signalIntensity}级</td>
				<td>${nodeOfData.measureInterval}分钟</td>
				<td>${nodeOfData.viewDataFlag eq 1?'是查看':'未查看'}</td>
				
				<td>
					<jsp:useBean id="timestamp" class="java.util.Date"/>  
			    	<jsp:setProperty name="timestamp" property="time" value="${nodeOfData.timeTag * 1000}"/>  
			    	<fmt:formatDate value="${timestamp}" pattern="yyyy-MM-dd HH:mm:ss" type="both"/>
		    	</td>
				<td>${nodeOfData.temperature/10}℃</td>
				<%-- <shiro:hasPermission name="bv:nodeOfData:edit"><td>
    				<a href="${ctx}/bv/nodeOfData/form?id=${nodeOfData.id}">修改</a>
					<a href="${ctx}/bv/nodeOfData/delete?id=${nodeOfData.id}" onclick="return confirmx('确认要删除该节点测量数据吗？', this.href)">删除</a>
				</td></shiro:hasPermission> --%>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>