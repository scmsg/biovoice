<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>节点信息管理</title>
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
		<li class="active"><a href="${ctx}/bv/node/">节点信息列表</a></li>
		<shiro:hasPermission name="bv:nodeEnter:edit"><li><a href="${ctx}/bv/nodeEnter/form">节点入库添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="node" action="${ctx}/bv/node/" method="post" class="breadcrumb form-search">
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
				<th>节点ID</th>
				<th>入库时间</th>
				<th>是否已分配</th>
				<shiro:hasPermission name="bv:node:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="node">
			<tr>
				<td>${node.nodeId}</td>
				<td><fmt:formatDate value="${node.enterTime}" type="both"/></td>
				<td>${node.isAllocated eq 0?'未分配':'已分配'}</td>
				<shiro:hasPermission name="bv:node:edit"><td>
    				<a href="${ctx}/bv/node/form?id=${node.id}">修改</a>
					<a href="${ctx}/bv/node/delete?id=${node.id}" onclick="return confirmx('确认要删除该节点信息吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>