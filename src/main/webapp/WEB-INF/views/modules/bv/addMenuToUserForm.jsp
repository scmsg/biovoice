<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>客户端-添加菜单权限--${fns:getConfig('productName')}</title>
	<meta name="decorator" content="blank"/><c:set var="tabmode" value="${empty cookie.tabmode.value ? '0' : cookie.tabmode.value}"/>
    <c:if test="${tabmode eq '1'}"><link rel="Stylesheet" href="${ctxStatic}/jerichotab/css/jquery.jerichotab.css" />
    <script type="text/javascript" src="${ctxStatic}/jerichotab/js/jquery.jerichotab.js"></script></c:if>
	<style type="text/css">
		#main {padding:0;margin:0;} #main .container-fluid{padding:0 4px 0 6px;}
		#header {margin:0 0 8px;position:static;} #header li {font-size:14px;_font-size:12px;}
		#header .brand {font-family:Helvetica, Georgia, Arial, sans-serif, 黑体;font-size:26px;padding-left:33px;}
		#footer {margin:8px 0 0 0;padding:3px 0 0 0;font-size:11px;text-align:center;border-top:2px solid #0663A2;}
		#footer, #footer a {color:#999;} #left{overflow-x:hidden;overflow-y:auto;} #left .collapse{position:static;}
		#userControl>li>a{/*color:#fff;*/text-shadow:none;} #userControl>li>a:hover, #user #userControl>li.open>a{background:transparent;}
	</style>
	
	<link rel="stylesheet" href="${ctxStatic}/jquery-ztree/3.5.12/css/zTreeStyle/zTreeStyle.css" type="text/css">
	<style>
		body {
		background-color: white;
		margin:0; padding:0;
		text-align: center;
		}
		div, p, table, th, td {
			list-style:none;
			margin:0; padding:0;
			color:#333; font-size:12px;
			font-family:dotum, Verdana, Arial, Helvetica, AppleGothic, sans-serif;
		}
		#testIframe {margin-left: 10px;}
	</style>
  
  	<script type="text/javascript" src="${ctxStatic}/jquery-ztree/3.5.12/js/jquery-1.4.4.min.js"></script>
	<script type="text/javascript" src="${ctxStatic}/jquery-ztree/3.5.12/js/jquery.ztree.core-3.5.js"></script>
	<script type="text/javascript" src="${ctxStatic}/jquery-ztree/3.5.12/js/jquery.ztree.excheck-3.5.js"></script>
	<SCRIPT type="text/javascript" >

		//excheck/checkbox.html

        var userMenus = ${customerZtreeNodesString};
        var ztreeNodes = ${ztreeNodesString};
        console.log(userMenus);
        console.log(ztreeNodes);

		//<!--
        var setting = {
            check: {
                enable: true
            },
            data: {
                simpleData: {
                    enable: true
                }
            }
        };

        var showZtreeNodes = [];//所有菜单
        for(var i=0;i<ztreeNodes.length;i++){
            var ztreeNode = ztreeNodes[i];
            ztreeNode.open = "true";
            showZtreeNodes.push(ztreeNode);

            $.each(userMenus,function(j,userMenu){
                if (ztreeNode.id == userMenu.id) {
                    ztreeNode.checked = true;
                    return false;
                }
            });
        }

        var zNodes = showZtreeNodes;
        // var zNodes =[
        //     { id:1, pId:0, name:"随意勾选 1", open:true},
        //     { id:11, pId:1, name:"随意勾选 1-1", open:true},
        //     { id:111, pId:11, name:"随意勾选 1-1-1"},
        //     { id:112, pId:11, name:"随意勾选 1-1-2"},
        //     { id:12, pId:1, name:"随意勾选 1-2", open:true},
        //     { id:121, pId:12, name:"随意勾选 1-2-1"},
        //     { id:122, pId:12, name:"随意勾选 1-2-2"},
        //     { id:2, pId:0, name:"随意勾选 2", checked:true, open:true},
        //     { id:21, pId:2, name:"随意勾选 2-1"},
        //     { id:22, pId:2, name:"随意勾选 2-2", open:true},
        //     { id:221, pId:22, name:"随意勾选 2-2-1", checked:true},
        //     { id:222, pId:22, name:"随意勾选 2-2-2"},
        //     { id:23, pId:2, name:"随意勾选 2-3"}
        // ];

        var code;

        function setCheck() {
            var zTree = $.fn.zTree.getZTreeObj("treeDemo"),
                py = $("#py").attr("checked")? "p":"",
                sy = $("#sy").attr("checked")? "s":"",
                pn = $("#pn").attr("checked")? "p":"",
                sn = $("#sn").attr("checked")? "s":"",
                type = { "Y":py + sy, "N":pn + sn};
            zTree.setting.check.chkboxType = type;
            showCode('setting.check.chkboxType = { "Y" : "' + type.Y + '", "N" : "' + type.N + '" };');
        }
        function showCode(str) {
            if (!code) code = $("#code");
            code.empty();
            code.append("<li>"+str+"</li>");
        }

        $(document).ready(function(){
            $.fn.zTree.init($("#treeDemo"), setting, zNodes);
            setCheck();
            $("#py").bind("change", setCheck);
            $("#sy").bind("change", setCheck);
            $("#pn").bind("change", setCheck);
            $("#sn").bind("change", setCheck);
        });
        //-->
	</SCRIPT>
	<SCRIPT type="text/javascript" >
        $(document).ready(function(){
            $("#saveBtn").click(
                function(){
                    saveMenuToUser();
                }
			);
			// $("#saveBtn").on("click",function(){
			// 	saveMenuToUser();
			// });
        });
        function saveMenuToUser() {
            var nodes = [];
            // var moduleIds = '';//模块id
            // $.each(zNodes,function(index,val){
            //     var onemtree = $.fn.zTree.getZTreeObj("treeDemo");
            //     var onenodes = onemtree.getCheckedNodes(true);
            //     nodes = nodes.concat(onenodes);
            //     if (onenodes.length > 0 && moduleIds.indexOf(val.id) == -1) {
            //         moduleIds +=  moduleIds.length==0 ? val.id : ','+val.id;
            //     }
            // });
            var onemtree = $.fn.zTree.getZTreeObj("treeDemo");
            var onenodes = onemtree.getCheckedNodes(true);
            nodes = nodes.concat(onenodes);

            var attrs = new Array();
            var vals = new Array();
            var ids = "";
            for(var i=0;i<nodes.length;i++){
                if(ids == ""){
                    ids = nodes[i].id;
                }else{
                    ids += ","+nodes[i].id;
                }
            }
            if(ids == ""){
                layer.alert("请选择菜单");
                return;
            }
            // attrs.push("menuIds");
            // vals.push(ids);
            // attrs.push("customerId");
            // vals.push($("#customerId").val());

            var customerId = $("#customerId").val();
            $.post("${ctx}/bv/customer/saveMenuToUser",
				{menuIds:ids, customerId:customerId},
				function(data){
                    alert(data.msg);
                    if (data.code == 'success') {
                        window.location.href = "${ctx}/bv/customer/clientCustomerList";
                    }
				},
				"json"
			);
            <%--formPost.submitAjaxForm("${ctx}/bv/customer/saveMenuToUser", attrs,--%>
                <%--vals, callBackFucs['saveRoleMenuGroupCallBck']);--%>
        }

        var callBackFucs = {
            saveRoleMenuGroupCallBck : function(data){
                parent.layer.alert(data.msg);
                if (data.code == 'success') {

                }
            }
        }
	</SCRIPT>
</head>
<body>
	<div class="content_wrap">
		<input type="hidden" id="customerId" name="customerId" value="${customerId }"/>
		<div>用户${customer.adminAccount}的菜单权限</div>
		<div class="zTreeDemoBackground left">
			<ul id="treeDemo" class="ztree"></ul>
		</div>
	</div>
	<div align="center" style="margin-top:5px;">
		<input type="button" id="saveBtn" value="添加" style="width: 65px;" />
	</div>
</body>
</html>