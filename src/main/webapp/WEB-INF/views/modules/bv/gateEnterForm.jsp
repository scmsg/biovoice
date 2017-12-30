<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>网关入库管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			//$("#name").focus();
			$("#inputForm").validate({
				submitHandler: function(form){
					loading('正在提交，请稍等...');
					form.submit();
				},
				errorContainer: "#messageBox",
				errorPlacement: function(error, element) {
					$("#messageBox").text("输入有误，请先更正。");
					if (element.is(":checkbox")||element.is(":radio")||element.parent().is(".input-append")){
						error.appendTo(element.parent().parent());
					} else {
						error.insertAfter(element);
					}
				}
			});
		});
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/bv/gateEnter/">网关入库列表</a></li>
		<li class="active"><a href="${ctx}/bv/gateEnter/form?id=${gateEnter.id}">网关入库<shiro:hasPermission name="bv:gateEnter:edit">${not empty gateEnter.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="bv:gateEnter:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="gateEnter" action="${ctx}/bv/gateEnter/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">起始ID：</label>
			<div class="controls">
				<form:input path="startId" htmlEscape="false" maxlength="20" class="input-xlarge  digits"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">数量：</label>
			<div class="controls">
				<form:input path="enterNumber" htmlEscape="false" maxlength="11" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">入库时间：</label>
			<div class="controls">
				<input name="enterTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
					value="<fmt:formatDate value="${gateEnter.enterTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</div>
		</div>
		<%-- <div class="control-group">
			<label class="control-label">结束ID：</label>
			<div class="controls">
				<form:input path="endId" htmlEscape="false" maxlength="20" class="input-xlarge  digits"/>
			</div>
		</div> --%>
		<div class="form-actions">
			<shiro:hasPermission name="bv:gateEnter:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="确定入库"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>