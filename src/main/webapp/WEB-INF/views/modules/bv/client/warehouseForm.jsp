<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>仓库管理管理</title>
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
	<link href="${ctxStatic}/bv/style.css" type="text/css" rel="stylesheet" />
	<script src="${ctxStatic}/bv/tab.js"></script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/bv/client/warehouse/">仓库管理列表</a></li>
		<li class="active"><a href="${ctx}/bv/client/warehouse/form?id=${warehouse.id}">仓库管理<shiro:hasPermission name="bv:client:warehouse:edit">${not empty warehouse.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="bv:client:warehouse:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="warehouse" action="${ctx}/bv/client/warehouse/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<form:hidden path="departmentId" value="${departmentId}"/>
		<sys:message content="${message}"/>		
		<div class="tab-container">
			<ul class="tab-head">
				<li id="1" class="select"><a href="#">参数设置</a></li>
				<li id="2" class="select"><a href="#">设施信息</a></li>
				<li id="3" class="select"><a href="#">验证信息</a></li>
			</ul>
			<div id="tab-panel" class="tab-panel">
				<section id="panel-1">
					<div class="control-group">
						<label class="control-label">仓库名称：</label>
						<div class="controls">
							<form:input path="warehouseName" htmlEscape="false" maxlength="255" class="input-xlarge "/>
						</div>
					</div>
					<div class="control-group">
						<label class="control-label">负责人：</label>
						<div class="controls">
							<form:input path="managerId" htmlEscape="false" maxlength="64" class="input-xlarge "/>
						</div>
					</div>
					<div class="control-group">
						<label class="control-label">仓库类型：</label>
						<div class="controls">
							<form:input path="warehouseType" htmlEscape="false" maxlength="255" class="input-xlarge "/>
						</div>
					</div>
					<div class="control-group">
						<label class="control-label">高温告警值：</label>
						<div class="controls">
							<form:input path="highTemperatureAlarm" htmlEscape="false" class="input-xlarge  number"/>℃
						</div>
						<label class="control-label">高温预警值：</label>
						<div class="controls">
							<form:input path="highTemperatureWarning" htmlEscape="false" class="input-xlarge  number"/>℃
						</div>
					</div>
					<div class="control-group">
						<label class="control-label">低温预警值：</label>
						<div class="controls">
							<form:input path="lowTemperatureWarning" htmlEscape="false" class="input-xlarge  number"/>℃
						</div>
						<label class="control-label">低温告警值：</label>
						<div class="controls">
							<form:input path="lowTemperatureAlarm" htmlEscape="false" class="input-xlarge  number"/>℃
						</div>
					</div>
					<div class="control-group">
						<label class="control-label">高湿度报警：</label>
						<div class="controls">
							<form:input path="highHumidityAlarm" htmlEscape="false" class="input-xlarge  number"/>%
						</div>
						<label class="control-label">高湿度预警：</label>
						<div class="controls">
							<form:input path="highHumidityWarning" htmlEscape="false" class="input-xlarge  number"/>%
						</div>
					</div>
					<div class="control-group">
						<label class="control-label">低湿度预警：</label>
						<div class="controls">
							<form:input path="lowHumidityWarning" htmlEscape="false" class="input-xlarge  number"/>%
						</div>
						<label class="control-label">低湿度告警：</label>
						<div class="controls">
							<form:input path="lowHumidityAlarm" htmlEscape="false" class="input-xlarge  number"/>%
						</div>
					</div>
					<div class="control-group">
						<label class="control-label">预警电话：</label>
						<div class="controls">
							<form:input path="warningPhone1" htmlEscape="false" maxlength="255" style="width: 150px; "/>
							<form:input path="warningPhone2" htmlEscape="false" maxlength="255" style="width: 150px; "/>
							<form:input path="warningPhone3" htmlEscape="false" maxlength="255" style="width: 150px; "/>
						</div>
					</div>
					<div class="control-group">
						<label class="control-label">告警电话：</label>
						<div class="controls">
							<form:input path="alarmPhone1" htmlEscape="false" maxlength="255" style="width: 150px; "/>
							<form:input path="alarmPhone2" htmlEscape="false" maxlength="255" style="width: 150px; "/>
							<form:input path="alarmPhone3" htmlEscape="false" maxlength="255" style="width: 150px; "/>
						</div>
					</div>
					<div class="control-group">
						<label class="control-label">测量周期：</label>
						<div class="controls">
							<form:input path="measuerPeriod" htmlEscape="false" maxlength="255" class="input-xlarge  digits"/>分钟
						</div>
					</div>
				</section>
				<section id="panel-2">
					<div class="control-group">
						<label class="control-label">仓库储存面积：</label>
						<div class="controls">
							<form:input path="warehouseArea" htmlEscape="false" maxlength="255" class="input-xlarge  digits"/>
						</div>
					</div>
					<div class="control-group">
						<label class="control-label">出风口个数：</label>
						<div class="controls">
							<form:input path="outletNum" htmlEscape="false" maxlength="10" class="input-xlarge  digits"/>
						</div>
					</div>
					<div class="control-group">
						<label class="control-label">建成时间：</label>
						<div class="controls">
							<input name="buildTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
								value="<fmt:formatDate value="${warehouse.buildTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
								onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
						</div>
					</div>
					<div class="control-group">
						<label class="control-label">施工单位：</label>
						<div class="controls">
							<form:input path="constructionUnit" htmlEscape="false" maxlength="255" class="input-xlarge "/>
						</div>
					</div>
					<div class="control-group">
						<label class="control-label">仓库平面图：</label>
						<div class="controls">
							<form:input path="warehousePlan" htmlEscape="false" maxlength="255" class="input-xlarge "/>
						</div>
					</div>
					<div class="control-group">
						<label class="control-label">仓库层高(米)：</label>
						<div class="controls">
							<form:input path="warehouseHight" htmlEscape="false" maxlength="3" class="input-xlarge  digits"/>
						</div>
					</div>
					<div class="control-group">
						<label class="control-label">货位个数：</label>
						<div class="controls">
							<form:input path="placement" htmlEscape="false" maxlength="3" class="input-xlarge  digits"/>
						</div>
					</div>
					<div class="control-group">
						<label class="control-label">货位规格：</label>
						<div class="controls">
							<form:input path="specification" htmlEscape="false" maxlength="255" class="input-xlarge "/>
						</div>
					</div>
					<div class="control-group">
						<label class="control-label">验证单位：</label>
						<div class="controls">
							<form:input path="verificationUnit" htmlEscape="false" maxlength="255" class="input-xlarge "/>
						</div>
					</div>
				</section>
				<section id="panel-3">
					<div class="control-group">
						<label class="control-label">验证时间：</label>
						<div class="controls">
							<input name="verificationTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
								value="<fmt:formatDate value="${warehouse.verificationTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
								onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
						</div>
					</div>
					<div class="control-group">
						<label class="control-label">验证报告：</label>
						<div class="controls">
							<form:input path="verificationReport" htmlEscape="false" maxlength="255" class="input-xlarge "/>
						</div>
					</div>
				</section>
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
					value="<fmt:formatDate value="${warehouse.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">更新时间：</label>
			<div class="controls">
				<input name="updateTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
					value="<fmt:formatDate value="${warehouse.updateTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</div>
		</div> --%>
		<div class="form-actions">
			<shiro:hasPermission name="bv:client:warehouse:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>