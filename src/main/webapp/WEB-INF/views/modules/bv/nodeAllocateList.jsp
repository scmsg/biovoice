<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>节点分配管理</title>
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
		<li class="active"><a href="${ctx}/bv/nodeAllocate/">节点分配列表</a></li>
		<shiro:hasPermission name="bv:nodeAllocate:edit"><li><a href="${ctx}/bv/nodeAllocate/form">节点分配添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="nodeAllocate" action="${ctx}/bv/nodeAllocate/" method="post" class="breadcrumb form-search">
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
				<th>合作方式</th>
				<th>起租时间</th>
				<th>租期</th>
				<th>节点集合</th>
				<th>出租单价</th>
				<th>校准单价</th>
				<th>网关集合</th>
				<th>计划付款时间</th>
				<th>金额</th>
				<th>押金</th>
				<th>备注</th>
				<shiro:hasPermission name="bv:nodeAllocate:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="nodeAllocate">
			<tr>
				<td>${nodeAllocate.companyName}</td>
				<td>${nodeAllocate.modeCooperate}</td>
				<td><fmt:formatDate value="${nodeAllocate.rentTime}" type="both"/></td>
				<td>${nodeAllocate.rentLong}天</td>
				<td>${nodeAllocate.nodeData}</td>
				<td>${nodeAllocate.erntUnit}</td>
				<td>${nodeAllocate.quasiUnit}</td>
				<td>${nodeAllocate.gateData}</td>
				<td><fmt:formatDate value="${nodeAllocate.payTime}" type="both"/></td>
				<td>${nodeAllocate.amount}</td>
				<td>${nodeAllocate.deposit}</td>
				<td>${nodeAllocate.remark}</td>
				<shiro:hasPermission name="bv:nodeAllocate:edit"><td>
    				<a href="${ctx}/bv/nodeAllocate/form?id=${nodeAllocate.id}">修改</a>
					<a href="${ctx}/bv/nodeAllocate/delete?id=${nodeAllocate.id}" onclick="return confirmx('确认要删除该节点分配吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>