<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>使用场所管理</title>
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
<%--	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/bv/client/usePlace/">使用场所列表</a></li>
		<shiro:hasPermission name="bv:client:usePlace:edit"><li><a href="${ctx}/bv/client/usePlace/form">使用场所添加</a></li></shiro:hasPermission>
	</ul>--%>

	<li style="font-size: medium;float: right;margin-top: 10px;margin-bottom: 20px"><a href="${ctx}/bv/client/warehouse/form?departmentId=${departmentId}">新增仓库</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	<a href="${ctx}/bv/client/trucks/form?departmentId=${departmentId}">新增车辆</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	<a href="${ctx}/bv/client/equipment/form?departmentId=${departmentId}">新增设备</a>
	</li>

<%--<form:form id="searchForm" modelAttribute="usePlace" action="${ctx}/bv/client/usePlace/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<input id="departmentId" name="departmentId" type="hidden" value="${departmentId}"/>
	<ul class="ul-form">
			<li><label>名称：</label>
				<form:input path="name" htmlEscape="false" maxlength="255" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>--%>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed"style="margin-top: 30px">
		<thead>
			<tr>
				<th>名称</th>
				<th>负责人</th>
				<th>预警电话</th>
				<th>告警电话</th>
				<th>测量周期(分钟)</th>
				<shiro:hasPermission name="bv:client:usePlace:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="usePlace">
			<tr>
				<td>${usePlace.name}</td>
				<td>${usePlace.managerId}</td>
				<td>${usePlace.warningPhone}</td>
				<td>${usePlace.alarmPhone}</td>
				<td>${usePlace.measuerPeriod}</td>
				<shiro:hasPermission name="bv:client:usePlace:edit"><td>
					<c:if test="${usePlace.usePlaceType eq 1}">
						<a href="${ctx}/bv/client/warehouse/form?usePlaceId=${usePlace.id}">修改</a>
					<a href="${ctx}/bv/client/warehouse/delete?departmentId=${departmentId}&usePlaceId=${usePlace.id}" onclick="return confirmx('确认要删除该使用场所吗？', this.href)">删除</a>
					</c:if>
					<c:if test="${usePlace.usePlaceType eq 2}">
						<a href="${ctx}/bv/client/equipment/form?usePlaceId=${usePlace.id}">修改</a>
					<a href="${ctx}/bv/client/equipment/delete?usePlaceId=${usePlace.id}" onclick="return confirmx('确认要删除该使用场所吗？', this.href)">删除</a>
					</c:if>
					<c:if test="${usePlace.usePlaceType eq 3}">
						<a href="${ctx}/bv/client/trucks/form?usePlaceId=${usePlace.id}">修改</a>
						<a href="${ctx}/bv/client/trucks/delete?departmentId=${departmentId}&id=usePlaceId=${usePlace.id}" onclick="return confirmx('确认要删除该使用场所吗？', this.href)">删除</a>
					</c:if>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>