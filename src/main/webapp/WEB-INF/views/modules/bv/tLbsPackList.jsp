<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>网关地图生成成功管理</title>
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
		<li class="active"><a href="${ctx}/bv/tLbsPack/">网关地图生成成功列表</a></li>
		<shiro:hasPermission name="bv:tLbsPack:edit"><li><a href="${ctx}/bv/tLbsPack/form">网关地图生成成功添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="tLbsPack" action="${ctx}/bv/tLbsPack/" method="post" class="breadcrumb form-search">
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
				<th>网关ID</th>
				<th>时间</th>
				<th>经度</th>
				<th>纬度</th>
			</tr>
			<%--<tr>
				<shiro:hasPermission name="bv:tLbsPack:edit"><th>操作</th></shiro:hasPermission>
			</tr>--%>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="tLbsPack">
			<tr>
				<td>${tLbsPack.masterId}</td>
				<td>${tLbsPack.timeTag}</td>
				<td>${tLbsPack.longitude}</td>
				<td>${tLbsPack.latitude}</td>
<%--			<tr>
				<shiro:hasPermission name="bv:tLbsPack:edit"><td>
    				<a href="${ctx}/bv/tLbsPack/form?id=${tLbsPack.id}">修改</a>
					<a href="${ctx}/bv/tLbsPack/delete?id=${tLbsPack.id}" onclick="return confirmx('确认要删除该网关地图生成成功吗？', this.href)">删除</a>
				</td></shiro:hasPermission>

			</tr>--%>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>