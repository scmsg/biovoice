<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>客户信息管理</title>
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
		<li class="active"><a href="${ctx}/bv/customer/clientCustomerList">客户信息列表</a></li>
		<li><a href="${ctx}/bv/customer/addClientCustomer">客户信息添加</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="customer" action="${ctx}/bv/customer/" method="post" class="breadcrumb form-search">
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
				<th>单位名称</th>
				<th>账号</th>
				<th>联系人</th>
				<th>联系电话</th>
				<th>操作</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="customer">
			<tr>
				<td>${customer.companyName}</td>
				<td>${customer.adminAccount}</td>
				<td>${customer.contact}</td>
				<td>${customer.mobile}</td>
				<td>
					<a href="${ctx}/bv/customer/addMenuToUser?id=${customer.id}">编辑用户权限</a>
					<a href="${ctx}/bv/customer/addClientCustomer?id=${customer.id}">修改</a>
					<a href="${ctx}/bv/customer/delete?id=${customer.id}" onclick="return confirmx('确认要删除该客户信息吗？', this.href)">删除</a>
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>