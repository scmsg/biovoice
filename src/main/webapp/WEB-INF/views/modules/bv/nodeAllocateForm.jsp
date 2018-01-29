<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>节点分配管理</title>
<meta name="decorator" content="default" />
<style>
.sele {
	font-size: 60px;
}
</style>
<script src="${ctxStatic}/jquery/jquery-1.9.1.min.js"
	type="text/javascript"></script>
<script type="text/javascript">
	$(document).ready(
			function() {
				//$("#name").focus();
				$("#inputForm")
						.validate(
								{
									submitHandler : function(form) {
										loading('正在提交，请稍等...');
										form.submit();
									},
									errorContainer : "#messageBox",
									errorPlacement : function(error, element) {
										$("#messageBox").text("输入有误，请先更正。");
										if (element.is(":checkbox")
												|| element.is(":radio")
												|| element.parent().is(
														".input-append")) {
											error.appendTo(element.parent()
													.parent());
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
					url : "${ctx}/bv/node/ajax",
					async : true,
					type : "post",
					//data:{"name":"lucy","age":18},
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
    function overfnc(obj) {
            $(obj).css("background", "#31DF77");
        }
    function overoutfnc(obj) {
	  //  alert("移出了")
        $(obj).css("background", "#fff");
    }
    function enterCompany(obj){
	   // alert("点击了");
	  //  alert($(obj).text());
	   $("#companyName").val($(obj).text());
        $("#showCompany").empty();
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
	function selectCompany(){
     //   alert("点击");
        $("#showCompany").empty();
	    $.ajax({
		url:"${ctx}/bv/customer/companyList",
        async : true,
        type : "post",
        //data:{"name":"lucy","age":18},
        success : function(data) {
		    //	alert(data.length);
            content="";
            for (var i = 0; i < data.length; i++) {
                content += "<tr style='cursor:pointer;border: 2px solid #000; width: 100%; padding: 0px; margin: 0px'><td onmouseover='overfnc(this)' onmouseout='overoutfnc(this)'  onclick='enterCompany(this)'>"
                    + data[i] + "</td></tr>";
            }
            $("#showCompany").append(content);
        },
        error : function(data) {
            alert("请求失败");
        },
        dataType : "json"
		})
	}
</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/bv/nodeAllocate/">节点分配列表</a></li>
		<li class="active"><a
			href="${ctx}/bv/nodeAllocate/form?id=${nodeAllocate.id}">节点分配<shiro:hasPermission
					name="bv:nodeAllocate:edit">${not empty nodeAllocate.id?'修改':'添加'}</shiro:hasPermission>
				<shiro:lacksPermission name="bv:nodeAllocate:edit">查看</shiro:lacksPermission></a></li>
	</ul>
	<br />
	<form:form id="inputForm" modelAttribute="nodeAllocate"
		action="${ctx}/bv/nodeAllocate/save" method="post"
		class="form-horizontal">
		<form:hidden path="id" />
		<sys:message content="${message}" />
		<div class="control-group">
			<label class="control-label">公司（单位名称）：</label>
			<div class="controls">
				<form:input path="companyName" id = "companyName" readonly="true" htmlEscape="false" maxlength="255"
					class="input-xlarge " /><input type="button" value="选择公司" onclick="selectCompany()"/>
				<div style="position: absolute; z-index: 1000; margin: 0px; padding: 0px; background: #fff; width: 280px; border: 1px solid #CCC;">
					<table id="showCompany"
						   style="margin: 0px; padding: 0px; width: 100%">
					</table>
				</div>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">合作方式：</label>
			<div class="controls">
				<form:input path="modeCooperate" htmlEscape="false" maxlength="255"
					class="input-xlarge " />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">起租时间：</label>
			<div class="controls">
				<input name="rentTime" type="text" readonly="readonly"
					maxlength="20" class="input-medium Wdate "
					value="<fmt:formatDate value="${nodeAllocate.rentTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});" />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">租期：</label>
			<div class="controls">
				<form:input path="rentLong" htmlEscape="false" maxlength="20"
					class="input-xlarge  digits" />
			</div>
		</div>
		<div class="control-group" style="position: relative">
			<label class="control-label">节点集合：</label>
			<div class="controls">
				<form:input path="nodeData" htmlEscape="false" maxlength="255"
					class="input-xlarge " onclick="nodenum()" id="nodeIds" />
					<input type="button" onclick="selectNodeId()" value="选择节点"/>
					<input type="button" onclick="enter()" value="确定" />
				<div style="position: absolute; z-index: 1000; margin: 0px; padding: 0px; background: #fff; width: 280px; border: 1px solid #CCC;">					
					<table id="showTable"
						style="margin: 0px; padding: 0px; width: 100%">
					</table>
				</div>
			</div>

		</div>
		<div class="control-group">
			<label class="control-label">出租单价：</label>
			<div class="controls">
				<form:input path="erntUnit" htmlEscape="false" class="input-xlarge " />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">校准单价：</label>
			<div class="controls">
				<form:input path="quasiUnit" htmlEscape="false"
					class="input-xlarge " />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">网关集合：</label>
			<div class="controls">
				<form:input path="gateData" htmlEscape="false" maxlength="255"
					class="input-xlarge " />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">计划付款时间：</label>
			<div class="controls">
				<input name="payTime" type="text" readonly="readonly" maxlength="20"
					class="input-medium Wdate "
					value="<fmt:formatDate value="${nodeAllocate.payTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});" />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">金额：</label>
			<div class="controls">
				<form:input path="amount" htmlEscape="false" class="input-xlarge " />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">押金：</label>
			<div class="controls">
				<form:input path="deposit" htmlEscape="false" class="input-xlarge " />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">备注：</label>
			<div class="controls">
				<form:input path="remark" htmlEscape="false" maxlength="255"
					class="input-xlarge " />
			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="bv:nodeAllocate:edit">
				<input id="btnSubmit" class="btn btn-primary" type="submit"
					value="保 存" />&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回"
				onclick="history.go(-1)" />
		</div>
	</form:form>
</body>
</html>