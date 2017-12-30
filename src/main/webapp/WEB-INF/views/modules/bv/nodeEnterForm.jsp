<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>节点入库管理</title>
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
		<li><a href="${ctx}/bv/nodeEnter/">节点入库列表</a></li>
		<li class="active"><a href="${ctx}/bv/nodeEnter/form?id=${nodeEnter.id}">节点入库<shiro:hasPermission name="bv:nodeEnter:edit">${not empty nodeEnter.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="bv:nodeEnter:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="nodeEnter" action="${ctx}/bv/nodeEnter/save" method="post" class="form-horizontal">
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
				<form:input path="enterNumber" htmlEscape="false" maxlength="11" class="input-xlarge  digits"/>
			</div>
		</div>
		<%-- <div class="control-group">
			<label class="control-label">结束ID：</label>
			<div class="controls">
				<form:input path="endId" htmlEscape="false" maxlength="20" class="input-xlarge  digits"/>
			</div>
		</div> --%>
		<div class="control-group">
			<label class="control-label">入库时间：</label>
			<div class="controls">
				<input name="enterTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
					value="<fmt:formatDate value="${nodeEnter.enterTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="bv:nodeEnter:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="确定入库"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>