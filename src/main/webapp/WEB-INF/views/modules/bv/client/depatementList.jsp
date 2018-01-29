<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>部门管理</title>
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
		<li class="active"><a href="${ctx}/bv/client/depatement/">部门列表</a></li>
		<shiro:hasPermission name="bv:client:depatement:edit"><li><a href="${ctx}/bv/client/depatement/form">部门添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="depatement" action="${ctx}/bv/client/depatement/" method="post" class="breadcrumb form-search">
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
				<th>部门名字</th>
				<th>负责人</th>
				<th>联系电话</th>
				<th>所属公司ID</th>
				<th>所属公司名称</th>
				<shiro:hasPermission name="bv:client:depatement:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="depatement">
			<tr>
				<td>${depatement.deptName}</td>
				<td>${depatement.deptHdea}</td>
				<td>${depatement.deadMobile}</td>
				<td>${depatement.companyId}</td>
				<td>${depatement.companyName}</td>
				<shiro:hasPermission name="bv:client:depatement:edit"><td>
    				<a href="${ctx}/bv/client/depatement/form?id=${depatement.id}">修改</a>
					<a href="${ctx}/bv/client/depatement/delete?id=${depatement.id}" onclick="return confirmx('确认要删除该部门吗？', this.href)">删除</a>
<%--				<a href="${ctx}/bv/client/warehouse/list?departmentId=${depatement.id}">查看仓库</a>
					<a href="${ctx}/bv/client/warehouse/form?departmentId=${depatement.id}">增加仓库</a>
					<a href="${ctx}/bv/client/equipment/list?departmentId=${depatement.id}">查看设备</a>
					<a href="${ctx}/bv/client/equipment/form?departmentId=${depatement.id}">增加设备</a>
					<a href="${ctx}/bv/client/trucks/list?departmentId=${depatement.id}">查看车辆</a>
					<a href="${ctx}/bv/client/trucks/form?departmentId=${depatement.id}">增加车辆</a>--%>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>