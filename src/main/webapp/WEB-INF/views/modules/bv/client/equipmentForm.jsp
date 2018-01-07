<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>设备管理管理</title>
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
		<li><a href="${ctx}/bv/client/equipment/">设备管理列表</a></li>
		<li class="active"><a href="${ctx}/bv/client/equipment/form?id=${equipment.id}">设备管理<shiro:hasPermission name="bv:client:equipment:edit">${not empty equipment.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="bv:client:equipment:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="equipment" action="${ctx}/bv/client/equipment/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<form:hidden path="departmentId" value="${departmentId}"/>
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">设备名称：</label>
			<div class="controls">
				<form:input path="equipmentName" htmlEscape="false" maxlength="255" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">负责人：</label>
			<div class="controls">
				<form:input path="managerId" htmlEscape="false" maxlength="64" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">高温告警值：</label>
			<div class="controls">
				<form:input path="highTemperatureAlarm" htmlEscape="false" class="input-xlarge  number"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">高温预警值：</label>
			<div class="controls">
				<form:input path="highTemperatureWarning" htmlEscape="false" class="input-xlarge  number"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">低温预警值：</label>
			<div class="controls">
				<form:input path="lowTemperatureWarning" htmlEscape="false" class="input-xlarge  number"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">低温告警值：</label>
			<div class="controls">
				<form:input path="lowTemperatureAlarm" htmlEscape="false" class="input-xlarge  number"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">高湿度报警：</label>
			<div class="controls">
				<form:input path="highHumidityAlarm" htmlEscape="false" class="input-xlarge  number"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">高湿度预警：</label>
			<div class="controls">
				<form:input path="highHumidityWarning" htmlEscape="false" class="input-xlarge  number"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">低湿度预警：</label>
			<div class="controls">
				<form:input path="lowHumidityWarning" htmlEscape="false" class="input-xlarge  number"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">低湿度告警：</label>
			<div class="controls">
				<form:input path="lowHumidityAlarm" htmlEscape="false" class="input-xlarge  number"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">告警电话1：</label>
			<div class="controls">
				<form:input path="warningPhone1" htmlEscape="false" maxlength="255" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">告警电话2：</label>
			<div class="controls">
				<form:input path="warningPhone2" htmlEscape="false" maxlength="255" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">预警电话3：</label>
			<div class="controls">
				<form:input path="warningPhone3" htmlEscape="false" maxlength="255" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">告警电话：</label>
			<div class="controls">
				<form:input path="alarmPhone1" htmlEscape="false" maxlength="255" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">告警电话2：</label>
			<div class="controls">
				<form:input path="alarmPhone2" htmlEscape="false" maxlength="255" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">告警电话3：</label>
			<div class="controls">
				<form:input path="alarmPhone3" htmlEscape="false" maxlength="255" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">测量周期：</label>
			<div class="controls">
				<form:input path="measuerPeriod" htmlEscape="false" maxlength="255" class="input-xlarge  digits"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">设备生产日期：</label>
			<div class="controls">
				<input name="dateOfEquipmentProduction" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
					value="<fmt:formatDate value="${equipment.dateOfEquipmentProduction}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">设备购买日期：</label>
			<div class="controls">
				<input name="equipmentPurchaseDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
					value="<fmt:formatDate value="${equipment.equipmentPurchaseDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">设备品牌：</label>
			<div class="controls">
				<form:input path="equipmentBrand" htmlEscape="false" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">验证单位：</label>
			<div class="controls">
				<form:input path="verificationUnit" htmlEscape="false" maxlength="255" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">验证时间：</label>
			<div class="controls">
				<input name="verificationTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
					value="<fmt:formatDate value="${equipment.verificationTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">认证报告：</label>
			<div class="controls">
				<form:input path="verificationReport" htmlEscape="false" maxlength="255" class="input-xlarge "/>
			</div>
		</div>
		<%-- <div class="control-group">
			<label class="control-label">部门ID：</label>
			<div class="controls">
				<form:input path="departmentId" htmlEscape="false" maxlength="64" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">创建时间：</label>
			<div class="controls">
				<input name="createTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
					value="<fmt:formatDate value="${equipment.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">更新时间：</label>
			<div class="controls">
				<input name="updateTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
					value="<fmt:formatDate value="${equipment.updateTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</div>
		</div> --%>
		<div class="form-actions">
			<shiro:hasPermission name="bv:client:equipment:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>