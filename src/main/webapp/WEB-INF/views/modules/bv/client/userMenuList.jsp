<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>客户端的用户菜单权限管理</title>
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
		<li class="active"><a href="${ctx}/bv/client/userMenu/">客户端的用户菜单权限列表</a></li>
		<shiro:hasPermission name="bv:client:userMenu:edit"><li><a href="${ctx}/bv/client/userMenu/form">客户端的用户菜单权限添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="userMenu" action="${ctx}/bv/client/userMenu/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>名字/菜单名字：</label>
				<form:input path="name" htmlEscape="false" maxlength="255" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>名字/菜单名字</th>
				<shiro:hasPermission name="bv:client:userMenu:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="userMenu">
			<tr>
				<td><a href="${ctx}/bv/client/userMenu/form?id=${userMenu.id}">
					${userMenu.name}
				</a></td>
				<shiro:hasPermission name="bv:client:userMenu:edit"><td>
    				<a href="${ctx}/bv/client/userMenu/form?id=${userMenu.id}">修改</a>
					<a href="${ctx}/bv/client/userMenu/delete?id=${userMenu.id}" onclick="return confirmx('确认要删除该客户端的用户菜单权限吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>