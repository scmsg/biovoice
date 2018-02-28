<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>节点信息管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
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
/* function downloadFile(){
              alert("点击");
            $.ajax({
                url:"${ctx}/bv/node/downloadFile",
                type : "post",
                data:{"fileName":"${node.quasiReport}"},
                success : function(data) {
                    alert("下载完成");
					alert(data);
                },
            });
        }*/
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/bv/node/">节点信息列表</a></li>
		<li class="active"><a href="${ctx}/bv/node/form?id=${node.id}">节点信息<shiro:hasPermission name="bv:node:edit">${not empty node.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="bv:node:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="node" action="${ctx}/bv/node/save" method="post" enctype="multipart/form-data" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">节点ID：</label>
			<div class="controls">
				<form:input path="nodeId" htmlEscape="false" maxlength="20" class="input-xlarge  digits"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">固件版本：</label>
			<div class="controls">
				<form:input path="firmwareVersion" htmlEscape="false" maxlength="255" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">入库时间：</label>
			<div class="controls">
				<input name="enterTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
					value="<fmt:formatDate value="${node.enterTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">上次较准时间：</label>
			<div class="controls">
				<input name="lastQuasiTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
					value="<fmt:formatDate value="${node.lastQuasiTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">上传较准报告：</label>
			<div class="controls">
				<%--<form:input path="quasiReport" htmlEscape="false" maxlength="255" class="input-xlarge "/>--%>
				<input type="file" name="quasiReportFile"  />
				<%--	<input type="text" value="${node.quasiReport}"/>--%>
				<input type="button" value="下载" onclick="avascrtpt:window.location.href='${ctx}/bv/node/downloadFile?fileName=${node.quasiReport}'"/>
				<%--<input type="file" id="file" name="file" class="required"/>${ctx}/bv/node/downloadFile?fileName=${node.quasiReport}-${ctx}/bv/node/form--%>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">备注：</label>
			<div class="controls">
				<form:input path="remark" htmlEscape="false" maxlength="255" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">摆放位置：</label>
			<div class="controls">
				<form:input path="placePosition" htmlEscape="false" maxlength="255" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">所属容器：</label>
			<div class="controls">
				<form:input path="whichVessel" htmlEscape="false" maxlength="255" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">绑定状态(0:未绑定； 1:已绑定)：</label>
			<div class="controls">
				<form:input path="boundStatus" htmlEscape="false" maxlength="11" class="input-xlarge  digits"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">绑定时间：</label>
			<div class="controls">
				<input name="boundTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
					value="<fmt:formatDate value="${node.boundTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">是否已分配(0:未分配； 1:已分配)：</label>
			<div class="controls">
				<form:input path="isAllocated" htmlEscape="false" maxlength="11" class="input-xlarge  digits"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">所属客户：</label>
			<div class="controls">
				<%--<form:input path="whichCustomer" htmlEscape="false" maxlength="255" class="input-xlarge "/>--%>
				<form:input path="customerId" htmlEscape="false" maxlength="20" class="input-xlarge  digits"/>
			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="bv:node:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>