<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<%--
  Created by IntelliJ IDEA.
  User: len
  Date: 2018/1/17
  Time: 20:38
  To change this template use File | Settings | File Templates.
--%>
<html>
<head>
    <%--<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />--%>
    <meta name="viewport" content="initial-scale=1.0, user-scalable=no" />
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/My97DatePicker/skin/whyGreen/datepicker.css"/>
        <%--<meta name="decorator" content="default"/>--%>
    <style type="text/css">
      #allmap {width: 100%;height: 100%;overflow: hidden;margin:0;font-family:"微软雅黑";}
    </style>
        <script type="text/javascript" src="${pageContext.request.contextPath}/static/My97DatePicker/WdatePicker.js"></script>
    <script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=F38ca0ff62c1b26b2814d7b6af536651"></script>
    <title>网关地图生成成功管理</title>
    <script type="text/javascript">
        function page(n,s){
            $("#pageNo").val(n);
            $("#pageSize").val(s);
            $("#searchForm").submit();
            return false;
        }
    </script>
</head>
<body>
<%--@elvariable id="tLbsPack" type=""--%>
<%--<form:form id="searchForm" modelAttribute="tLbsPack" action="${ctx}/bv/tLbsPack/" method="post">
    <input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
    <input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
    <label class="control-label">网关ID</label>
    <input type="text">
    起始时间<input id="startTime" name="startTime" type="text" height="100px" width="120px"
    maxlength="20" class="input-medium Wdate " value="${start}"
    onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false,maxDate:'#F{$dp.$D(\'startTime\')}'||'%y-%M-%d %H:%m:%s',isShowClear:true});" />
    结束时间<input id="endTime" name="endTime" type="text" readonly="readonly"
    maxlength="20" class="input-medium Wdate " value="${end}"
    onclick="WdatePicker({minDate:'#F{$dp.$D(\'startTime\')}',maxDate:'%y-%M-%d %H:%m:%s',dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:true});"/>
    <input id="btnSubmit" type="submit" value="查询"/>
</form:form>--%>
<div id="allmap" class="allmap"></div>
</body>
</html>
<script type="text/javascript">
    points=new Array();
    var numtext=0;
    var text = new Array();
    <c:forEach items="${page.list}" var="tLbsPack" varStatus="status" >
    point=new BMap.Point(${tLbsPack.latitude/1000000},${tLbsPack.longitude/1000000});
    points.push(point);
     <jsp:useBean id="timestamp" class="java.util.Date"/>
     <jsp:setProperty name="timestamp" property="time" value="${tLbsPack.timeTag*1000}"/>
    time1="<fmt:formatDate value='${timestamp}' pattern='yyyy-MM-dd HH:mm:ss' type='both'/>"
     text[${status.index}]=${tLbsPack.masterId}+"时间"+time1;
    </c:forEach>

    //地图初始化
    var bm = new BMap.Map("allmap");
    bm.centerAndZoom(new BMap.Point(121.673392,31.300006), 15);
    bm.enableScrollWheelZoom(true);
    //坐标转换完之后的回调函数
    translateCallback = function (data){
        if(data.status === 0) {
            for (var i = 0; i < data.points.length; i++) {
                var marker = new BMap.Marker(data.points[i]);
                bm.addOverlay(marker);
                bm.setCenter(data.points[i]);
                marker.setAnimation(BMAP_ANIMATION_BOUNCE);
                 label = new BMap.Label("网关ID:"+text[numtext], {
                    offset: new BMap.Size(15, -25)
                });
                numtext++;
                marker.setLabel(label);
                label.setStyle({
                    display:"none" //给label设置样式，任意的CSS都是可以的
                });
                marker.addEventListener("mouseover", function(){
                    var label = this.getLabel()
                    label.setStyle({display:"block"});//给label设置样式，任意的CSS都是可以的
                })
                marker.addEventListener("mouseout", function(){
                    var label = this.getLabel()
                    label.setStyle({display:"none"});
                })
            }
        }
    }
    var total = 0; //总记录数
    var groupCount = 0; //每次转十条
    if (points.length % 10 > 0) {
        groupCount = (points.length / 10) + 1;
    }
    else {
        groupCount = (points.length / 10);
    }

    for(var i=0;i<groupCount;i++) { //外层循环，有多少组十条
        var pos = new Array();
        for (var j = 0; j < 10; j++) { //内层循环，每组十条
            if (total < points.length) { //不超过总记录数结束
                var point = new BMap.Point(points[(i * 10) + j].lng, points[(i * 10) + j].lat);
                pos.push(point);
            }
            total++;
        }
        conver();
    }
    //setTimeout(function()
    function conver() {
        var convertor = new BMap.Convertor();
        convertor.translate(pos,1, 5, translateCallback)
    }
</script>
