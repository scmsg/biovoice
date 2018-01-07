<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>仓库管理管理</title>
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
		<li class="active"><a href="${ctx}/bv/client/warehouse/">仓库管理列表</a></li>
		<%-- <shiro:hasPermission name="bv:client:warehouse:edit"><li><a href="${ctx}/bv/client/warehouse/form">仓库管理添加</a></li></shiro:hasPermission> --%>
	</ul>
	<form:form id="searchForm" modelAttribute="warehouse" action="${ctx}/bv/client/warehouse/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<input id="departmentId" name="departmentId" type="hidden" value="${departmentId}"/>
		<ul class="ul-form">
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>仓库名称</th>
				<th>负责人</th>
				<th>仓库类型</th>
				
				<th>高温告警值</th>
				<th>高温预警值</th>
				<th>预警电话1</th>
				<th>告警电话1</th>
				
				<shiro:hasPermission name="bv:client:warehouse:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="warehouse">
			<tr>
				<td>${warehouse.warehouseName}</td>
				<td>${warehouse.managerId}</td>
				<td>${warehouse.warehouseType}</td>
				
				<td>${warehouse.highTemperatureAlarm}</td>
				<td>${warehouse.highTemperatureWarning}</td>
				<td>${warehouse.warningPhone1}</td>
				<td>${warehouse.alarmPhone1}</td>
				
				<shiro:hasPermission name="bv:client:warehouse:edit"><td>
    				<a href="${ctx}/bv/client/warehouse/form?id=${warehouse.id}">修改</a>
					<a href="${ctx}/bv/client/warehouse/delete?id=${warehouse.id}" onclick="return confirmx('确认要删除该仓库管理吗？', this.href)">删除</a>
					<a href="${ctx}/bv/client/customerNode/list?usePlaceId=${warehouse.id}&usePlaceType=1">客户节点列表</a>
					<a href="${ctx}/bv/client/customerNode/form?usePlaceId=${warehouse.id}&usePlaceType=1">新增节点</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>