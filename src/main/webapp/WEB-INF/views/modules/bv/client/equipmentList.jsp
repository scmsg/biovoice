<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>设备管理管理</title>
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
		<li class="active"><a href="${ctx}/bv/client/equipment/">设备管理列表</a></li>
		<%-- <shiro:hasPermission name="bv:client:equipment:edit"><li><a href="${ctx}/bv/client/equipment/form">设备管理添加</a></li></shiro:hasPermission> --%>
	</ul>
	<form:form id="searchForm" modelAttribute="equipment" action="${ctx}/bv/client/equipment/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
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
				
				<th>高温告警值</th>
				<th>高温预警值</th>
				<th>告警电话1</th>
				<th>告警电话</th>
				<shiro:hasPermission name="bv:client:equipment:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="equipment">
			<tr>
				<td>${equipment.equipmentName}</td>
				<td>${equipment.managerId}</td>
				
				<td>${equipment.highTemperatureAlarm}</td>
				<td>${equipment.highTemperatureWarning}</td>
				<td>${equipment.warningPhone1}</td>
				<td>${equipment.warningPhone2}</td>
				
				<shiro:hasPermission name="bv:client:equipment:edit"><td>
    				<a href="${ctx}/bv/client/equipment/form?id=${equipment.id}">修改</a>
					<a href="${ctx}/bv/client/equipment/delete?id=${equipment.id}" onclick="return confirmx('确认要删除该设备管理吗？', this.href)">删除</a>
					<a href="${ctx}/bv/client/customerNode/list?usePlaceId=${equipment.id}&usePlaceType=2">客户节点列表</a>
					<a href="${ctx}/bv/client/customerNode/form?usePlaceId=${equipment.id}&usePlaceType=2">新增节点</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>