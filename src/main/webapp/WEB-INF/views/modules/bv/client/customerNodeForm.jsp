<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>客户节点管理</title>
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
		function selectNodeId() {
            //alert("测试成功");
            $("#showTable").empty();
            $.ajax({
                url : "${ctx}/bv/client/customerNode/ajax",
                async : true,
                type : "post",
               // data:{"":"lucy","age":18},
                success : function(data) {
                    //	alert(data.length);
                    content="";
                    for (var i = 0; i < data.length; i++) {
                        //alert(i);
                        content += "<tr style='cursor:pointer;border: 2px solid #000; width: 100%; padding: 0px; margin: 0px'><td onmouseover='overfn(this)'onnouseout='overoutfn(this)'><input type='checkbox' />"
                            + data[i] + "</td></tr>";
                    }
                    $("#showTable").append(content);
                },
                error : function(data) {
                    alert("请求失败");
                },
                dataType : "json"
            });
        }
        function overfn(obj) {
            // $(obj).children("input").prop("checked",!$(obj).children("input").prop("checked"));
            if($(obj).children("input").prop("checked")){
                $(obj).children("input").prop("checked",false);
                $(obj).css("background", "#fff");
            }else{
                $(obj).children("input").prop("checked",true);
                $(obj).css("background", "#31DF77");
            }
        }
        function enter() {
            //alert($("#showTable .sele").size());
            //alert($("#showTable input:checked").length);
            content = "";
            $("#showTable input:checked").each(function(i, n) {
                content += $(this).parent().text() + ",";
            });
            //alert(content);
            $("#nodeIds").val(content);
            $("#showTable").empty();
        }
	</script>
</head>
<body>
<%--//modelAttribute="customerNode"--%>
	<ul class="nav nav-tabs">
		 <%--<li><a href="${ctx}/bv/client/customerNode/">客户节点列表</a></li>--%>
		<li class="active"><a href="${ctx}/bv/client/customerNode/form?id=${customerNode.id}">客户节点<shiro:hasPermission name="bv:client:customerNode:edit">${not empty customerNode.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="bv:client:customerNode:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="customerNode" action="${ctx}/bv/client/customerNode/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<form:hidden path="usePlaceId" value="${usePlaceId}"/>
		<form:hidden path="usePlaceType" value="${usePlaceType}"/>
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">节点名称(客户自定义)：</label>
			<div class="controls">
				<form:input path="nodeName" htmlEscape="false" maxlength="255" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group" style="position: relative">
			<label class="control-label">节点ID：</label>
			<div class="controls">
				<form:textarea path="customerNodeIds"  id="nodeIds" htmlEscape="false" rows="5" maxlength="200" class="input-xxlarge" onclick="nodenum()"/>
				<input type="button" onclick="selectNodeId()" value="选择节点"/>
				<input type="button" onclick="enter()" value="确定" />
				<div style="position: absolute; z-index: 1000; margin: 0px; padding: 0px; background: #fff; width:300px; border: 1px solid #CCC;">
					<table id="showTable"
						   style="margin: 0px; padding: 0px; width: 100%">
					</table>
				</div>
			</div>
		</div>
<%--		<div class="control-group">
			<label class="control-label">节点ID：</label>
			<div class="controls">
				<form:input path="nodeId" htmlEscape="false" maxlength="20" class="input-xlarge  digits"/>
			</div>
		</div>--%>
		<div class="control-group">
			<label class="control-label">摆放位置：</label>
			<div class="controls">
				<form:input path="placementPosition" htmlEscape="false" maxlength="255" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">上次较准时间：</label>
			<div class="controls">
				<input name="verificationTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
					value="<fmt:formatDate value="${customerNode.verificationTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">较准报告：</label>
			<div class="controls">
				<form:input path="verificationReport" htmlEscape="false" maxlength="255" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">备注信息：</label>
			<div class="controls">
				<form:textarea path="remarks" htmlEscape="false" rows="4" maxlength="255" class="input-xxlarge "/>
			</div>
		</div>
		<%-- <div class="control-group">
			<label class="control-label">使用场所类型：</label>
			<div class="controls">
				<form:input path="usePlaceType" htmlEscape="false" maxlength="255" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">使用场所ID：</label>
			<div class="controls">
				<form:input path="usePlaceId" htmlEscape="false" maxlength="64" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">创建时间：</label>
			<div class="controls">
				<input name="createTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
					value="<fmt:formatDate value="${customerNode.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">更新时间：</label>
			<div class="controls">
				<input name="updateTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
					value="<fmt:formatDate value="${customerNode.updateTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</div>
		</div> --%>
		<div class="form-actions">
			<shiro:hasPermission name="bv:client:customerNode:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>