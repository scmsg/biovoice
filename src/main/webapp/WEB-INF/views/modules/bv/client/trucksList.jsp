<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>车辆管理管理</title>
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
		<li class="active"><a href="${ctx}/bv/client/trucks/">车辆管理列表</a></li>
		<%-- <shiro:hasPermission name="bv:client:trucks:edit"><li><a href="${ctx}/bv/client/trucks/form">车辆管理添加</a></li></shiro:hasPermission> --%>
	</ul>
	<form:form id="searchForm" modelAttribute="trucks" action="${ctx}/bv/client/trucks/" method="post" class="breadcrumb form-search">
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
				<th>车牌号码</th>
				<th>司机</th>
				<th>联系方式</th>
				<th>车辆管理员</th>
				<shiro:hasPermission name="bv:client:trucks:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="trucks">
			<tr>
				<td>${trucks.plateNumber}</td>
				<td>${trucks.driverName}</td>
				<td>${trucks.driverContact}</td>
				<td>${trucks.managerId}</td>
				<shiro:hasPermission name="bv:client:trucks:edit"><td>
    				<a href="${ctx}/bv/client/trucks/form?id=${trucks.id}">修改</a>
					<a href="${ctx}/bv/client/trucks/delete?id=${trucks.id}" onclick="return confirmx('确认要删除该车辆管理吗？', this.href)">删除</a>
					<a href="${ctx}/bv/client/customerNode/list?usePlaceId=${trucks.id}&usePlaceType=3">客户节点列表</a>
					<a href="${ctx}/bv/client/customerNode/form?usePlaceId=${trucks.id}&usePlaceType=3">新增节点</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>