<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>节点测量数据管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			
		});
		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").submit();
        	return false;
        }
	</script>
</head>
<body>
	<script src="${ctxStatic}/Highcharts-6.0.4/code/highcharts.js"></script>
	<script src="${ctxStatic}/Highcharts-6.0.4/code/modules/exporting.js"></script>
	<script src="${ctxStatic}/Highcharts-6.0.4/code/modules/oldie.js"></script>
	<script src="https://img.hcharts.cn/highcharts-plugins/highcharts-zh_CN.js"></script>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/bv/nodeOfData/">节点测量数据列表</a></li>
		<li class="active"><a href="${ctx}/bv/nodeOfData/charts">节点测量数据图表-时间轴</a></li>
		<%-- <shiro:hasPermission name="bv:nodeOfData:edit"><li><a href="${ctx}/bv/nodeOfData/form">节点测量数据添加</a></li></shiro:hasPermission> --%>
	</ul>
	<%--@elvariable id="nodeOfData" type=""--%>
	<form:form id="searchForm" modelAttribute="nodeOfData" action="${ctx}/bv/nodeOfData/charts" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		
		<label>节点ID：</label>
		<input id="nodeId" name="nodeId" type="input" value="${nodeOfData.nodeId}"/>
		<input id="startTime" name="startTime" type="text" readonly="readonly"
			   maxlength="20" class="input-medium Wdate " value="${start}"
			   onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false,maxDate:'#F{$dp.$D(\'startTime\')}'||'%y-%M-%d %H:%m:%s',isShowClear:true});" />
		<input id="endTime" name="endTime" type="text" readonly="readonly"
			   maxlength="20" class="input-medium Wdate " value="${end}"
			   onclick="WdatePicker({minDate:'#F{$dp.$D(\'startTime\')}',maxDate:'%y-%M-%d %H:%m:%s',dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:true});"/>
		<ul class="ul-form">
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	
	<c:if test="${!empty page.list}">
	<div id="container" style="min-width: 400px; height: 400px; margin: 0 auto"></div>

	<script type="text/javascript">
		//不使用 UTC
        // Highcharts.setOptions({ global: { useUTC: false } });

	var xAxis = ${xAxis};
    var primaryDatas = ${datas};

    var datas = [];

    var oneData = [];
    for (var i = 0; i < primaryDatas.length; i++){
        oneData = primaryDatas[i];
        // console.log(oneData);
        // console.log(oneData.timeTag);

        var buildData = [];
/*
        buildData.push(Date.UTC(2018,1,1,1,1,1));
        buildData.push(110);
        datas.push(buildData);
        //show 02-01 01:01:01.000
        //110
        */

        // var mydate = new Date(oneData.timeTag);
        // console.log(mydate);
/*
		//总是无法计算出正确的时区数据，放弃
        var fullYear = mydate.getUTCFullYear();
        var fullMonth = mydate.getUTCMonth();
        var fullDay =mydate.getUTCDay();
        var fullHours = mydate.getUTCHours();
        var fullMinutes =mydate.getUTCMinutes();
        var fullSenconds =mydate.getUTCSeconds();
        console.log(fullYear);
        console.log(fullMonth);
        console.log(fullDay);
        console.log(fullHours);
        console.log(fullMinutes);
        console.log(fullSenconds);
        buildData.push(Date.UTC(fullYear,fullMonth,fullDay,fullHours,fullMinutes,fullSenconds));
*/

        buildData.push(oneData.timeTag);
        buildData.push(oneData.temperature);
        datas.push(buildData);
	}

	//UTC() 方法可根据世界时返回 1970 年 1 月 1 日 到指定日期的毫秒数。
    // datas = [
     //    [Date.UTC(2013,3,30),17151],
     //    [Date.UTC(2013,4,1),171751],
     //    [Date.UTC(2013,4,2),1095]
    // ]

	console.log(xAxis);
	console.log(datas);

    Highcharts.chart('container', {
    chart: {
        zoomType: 'x'
    },
    title: {
        text: '${title}'
    },
    subtitle: {
        text: '${subtitle}'
    },
    xAxis: {
        type: 'datetime',
        dateTimeLabelFormats: {
            millisecond: '%H:%M:%S.%L',
            second: '%m-%d %H:%M:%S',
            minute: '%m-%d %H:%M',
            hour: '%m-%d %H:%M',
            day: '%m-%d',
            week: '%m-%d',
            month: '%Y-%m',
            year: '%Y'
        }
    },
    tooltip: {
        dateTimeLabelFormats: {
            millisecond: '%H:%M:%S.%L',
            second: '%m-%d %H:%M:%S',
            minute: '%m-%d %H:%M',
            hour: '%m-%d %H:%M',
            day: '%m-%d',
            week: '%m-%d',
            month: '%Y-%m',
            year: '%Y'
        }
    },
    yAxis: {
        title: {
            text: '${yAxis}'
        }
    },
    legend: {
        enabled: false
    },
    plotOptions: {
        area: {
            fillColor: {
                linearGradient: {
                    x1: 0,
                    y1: 0,
                    x2: 0,
                    y2: 1
                },
                stops: [
                    [0, Highcharts.getOptions().colors[0]],
                    [1, Highcharts.Color(Highcharts.getOptions().colors[0]).setOpacity(0).get('rgba')]
                ]
            },
            marker: {
                radius: 2
            },
            lineWidth: 1,
            states: {
                hover: {
                    lineWidth: 1
                }
            },
            threshold: null
        }
    },
    series: [{
        type: 'area',
        name: '节点ID：${nodeId}',
        data: datas
    }]
});
	</script>
	
	</c:if>
	
	<div class="pagination">${page}</div>
</body>
</html>