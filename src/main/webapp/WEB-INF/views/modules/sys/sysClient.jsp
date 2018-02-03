<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>客户端--${fns:getConfig('productName')}</title>
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
	<SCRIPT type="text/javascript" >
	
	var ztreeNodes = ${ztreeNodesString};
	console.log(ztreeNodes);
  //<!--
	var zTree;
	var demoIframe;

	var setting = {
		view: {
			dblClickExpand: false,
			showLine: true,
			selectedMulti: false
		},
		data: {
			simpleData: {
				enable:true,
				idKey: "id",
				pIdKey: "pId",
				rootPId: ""
			}
		},
		callback: {
			beforeClick: function(treeId, treeNode) {
				var zTree = $.fn.zTree.getZTreeObj("tree");
				if (treeNode.isParent) {
					demoIframe.attr("src","${ctx}/"+treeNode.file );
					return true;
				} else {
					demoIframe.attr("src","${ctx}/"+treeNode.file );
					return true;
				}
			}
		}
	};

	var zNodes = ztreeNodes;

	$(document).ready(function(){
		var t = $("#tree");
		t = $.fn.zTree.init(t, setting, zNodes);
		demoIframe = $("#testIframe");
		demoIframe.bind("load", loadReady);
		var zTree = $.fn.zTree.getZTreeObj("tree");
		zTree.selectNode(zTree.getNodeByParam("id", 101));

	});

	function loadReady() {
		var bodyH = demoIframe.contents().find("body").get(0).scrollHeight,
		htmlH = demoIframe.contents().find("html").get(0).scrollHeight,
		maxH = Math.max(bodyH, htmlH), minH = Math.min(bodyH, htmlH),
		h = demoIframe.height() >= maxH ? minH:maxH ;
		if (h < 530) h = 530;
		demoIframe.height(h);
	}

  //-->
  </SCRIPT>
</head>
<body>
	<div id="main">
		<div id="header" class="navbar navbar-fixed-top">
			<div class="navbar-inner">
				<div class="brand"><span id="productName" >${fns:getConfig('productName')}</span></div>
				<ul id="userControl" class="nav pull-right">
					<li><a href="${ctx}/sys/user/info"target="testIframe"><i class="icon-user"></i>&nbsp; 用户管理</a></li>
					<li id="userInfo" class="dropdown">
					<a class="dropdown-toggle" data-toggle="dropdown" href="#" title="个人信息">您好, ${fns:getUser().name}&nbsp;<span id="notifyNum" class="label label-info hide"></span></a>
						<%--<ul class="dropdown-menu">
						<li><a href="${ctx}/sys/user/info"target="testIframe"><i class="icon-user"></i>&nbsp; 用户管理</a></li>
						<li><a href="${ctx}/sys/user/info" target="testIframe"><i class="icon-user"></i>&nbsp; 个人信息</a></li>
						<li><a href="${ctx}/sys/user/modifyPwd" target="testIframe"><i class="icon-lock"></i>&nbsp;  修改密码</a></li>
						<li><a href="${ctx}/oa/oaNotify/self" target="testIframe"><i class="icon-bell"></i>&nbsp;  我的通知 <span id="notifyNum2" class="label label-info hide"></span></a></li>
						</ul>--%>
					</li>
					<li><a href="${ctx}/logout" title="退出登录">退出</a></li>
					<li>&nbsp;</li>
				</ul>
			</div>
		</div>
	</div>
	<TABLE border=0 height=600px align=left>
		<TR>
			<TD width=18% align=left valign=top style="BORDER-RIGHT: #999999 1px dashed">
				<ul id="tree" class="ztree" style="width:260px; overflow:auto;"></ul>
			</TD>
			<TD width=100% align=left valign=top><IFRAME ID="testIframe" Name="testIframe" FRAMEBORDER=0 SCROLLING=AUTO width=100%  height=600px SRC="${ctx}/bv/client/depatement"></IFRAME></TD>
		</TR>
	</TABLE>

</body>
</html>