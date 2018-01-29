<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>客户节点管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
		});
		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").submit();
        	return false;
             /*   ?usePlaceId=${usePlaceId}&usePlaceType=${usePlaceType}*/
        }
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/bv/client/customerNode/">客户节点列表</a></li>
		<shiro:hasPermission name="bv:client:customerNode:edit"><li><a href="${ctx}/bv/client/customerNode/form?usePlaceId=${usePlaceId}&usePlaceType=${usePlaceType}">客户节点添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="customerNode" action="${ctx}/bv/client/customerNode/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<input id="usePlaceId" name="usePlaceId" type="hidden" value="${usePlaceId}"/>
		<input id="usePlaceType" name="usePlaceType" type="hidden" value="${usePlaceType}"/>
		<ul class="ul-form">
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>节点名称</th>
				<th>节点ID</th>
				<th>摆放位置</th>
				<th>校准时间</th>
				<th>校准报告</th>
				<th>备注信息</th>
				<shiro:hasPermission name="bv:client:customerNode:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="customerNode">
			<tr>
				<td>${customerNode.nodeName}</td>
				<td>${customerNode.nodeId}</td>
				<td>${customerNode.placementPosition}</td>
				<td>
					<jsp:useBean id="timestamp" class="java.util.Date"/>  
			    	<fmt:formatDate value="${customerNode.verificationTime}" pattern="yyyy-MM-dd HH:mm:ss" type="both"/>
				</td>
				<td>${customerNode.verificationReport}</td>
				<td>${customerNode.remarks}</td>
				<shiro:hasPermission name="bv:client:customerNode:edit"><td>
    				<a href="${ctx}/bv/client/customerNode/form?id=${customerNode.id}">修改</a>
					<a href="${ctx}/bv/client/customerNode/delete?id=${customerNode.id}" onclick="return confirmx('确认要删除该客户节点吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>