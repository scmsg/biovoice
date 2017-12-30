<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>节点测量数据管理</title>
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
		<li><a href="${ctx}/bv/nodeOfData/">节点测量数据列表</a></li>
		<li class="active"><a href="${ctx}/bv/nodeOfData/form?id=${nodeOfData.id}">节点测量数据<shiro:hasPermission name="bv:nodeOfData:edit">${not empty nodeOfData.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="bv:nodeOfData:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="nodeOfData" action="${ctx}/bv/nodeOfData/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">设备ID(单机版NodeID)：</label>
			<div class="controls">
				<form:input path="masterId" htmlEscape="false" maxlength="20" class="input-xlarge  digits"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">设备ID：</label>
			<div class="controls">
				<form:input path="nodeId" htmlEscape="false" maxlength="20" class="input-xlarge  digits"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">7~4Bit=（保留 ）3~0Bit=产品类型：1＝温度 2＝温湿度：</label>
			<div class="controls">
				<form:input path="productType" htmlEscape="false" maxlength="4" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">充电标识，1为充电，0为未充电：</label>
			<div class="controls">
				<form:input path="chargingState" htmlEscape="false" maxlength="4" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">设备电池电量（最大4级 0、1、2、3）：</label>
			<div class="controls">
				<form:input path="battery" htmlEscape="false" maxlength="4" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">信号dbm表（最大16级）：</label>
			<div class="controls">
				<form:input path="signalIntensity" htmlEscape="false" maxlength="4" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">测量分钟间隔：</label>
			<div class="controls">
				<form:input path="measureInterval" htmlEscape="false" maxlength="6" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">查看数据标识1、是查看：</label>
			<div class="controls">
				<form:input path="viewDataFlag" htmlEscape="false" maxlength="4" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">记录开启标识 1、开始记录：</label>
			<div class="controls">
				<form:input path="startRecordFlag" htmlEscape="false" maxlength="4" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">开箱标记,0开关箱,1是开箱：</label>
			<div class="controls">
				<form:input path="lightOn" htmlEscape="false" maxlength="4" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">时钟标签，本包第一组数据的测量时间：</label>
			<div class="controls">
				<form:input path="timeTag" htmlEscape="false" maxlength="20" class="input-xlarge  digits"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">测量温度值：</label>
			<div class="controls">
				<form:input path="temperature" htmlEscape="false" maxlength="6" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">测量湿度值：</label>
			<div class="controls">
				<form:input path="humidity" htmlEscape="false" maxlength="6" class="input-xlarge "/>
			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="bv:nodeOfData:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>