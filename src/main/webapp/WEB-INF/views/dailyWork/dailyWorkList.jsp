<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <title>工作任务列表</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <!-- Le styles -->
        <link href="/resources/scripts/artDialog5.0/skins/idialog.css" rel="stylesheet">
    <link href="/Statics/css/bootstrap.min.css" rel="stylesheet">
    <link href="/Statics/css/self.css" rel="stylesheet">
    <link href="/Statics/css/temp.css" rel="stylesheet">
    <link rel="shortcut icon" href="/Statics/img/logo.bmp">
	<%@ include file="/WEB-INF/views/common/pageStyle.jsp"%>
    <style type="text/css">
    a{cursor: pointer;}
      body{padding-top: 60px;}
      .top{
         	  left: 18px;
    		  position: relative;
   			  top: -8px;
      }
    </style>
    <link href="/Statics/css/bootstrap-responsive.css" rel="stylesheet">

    <!-- Le HTML5 shim, for IE6-8 support of HTML5 elements -->
    <!--[if lt IE 9]>
      <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
    <![endif]-->

    <!-- Le fav and touch icons -->
    <link rel="shortcut icon" href="/ico/favicon.ico">
    <link rel="apple-touch-icon-precomposed" sizes="144x144" href="/ico/apple-touch-icon-144-precomposed.png">
    <link rel="apple-touch-icon-precomposed" sizes="114x114" href="/ico/apple-touch-icon-114-precomposed.png">
    <link rel="apple-touch-icon-precomposed" sizes="72x72" href="/ico/apple-touch-icon-72-precomposed.png">
    <link rel="apple-touch-icon-precomposed" href="/ico/apple-touch-icon-57-precomposed.png">
  </head>

  <body quick-markup_injected="true">
    	<%@ include file="/WEB-INF/views/common/header.jsp"%>
    <div class="container-fluid" >
		<div class="row-fluid" id="aeaoofnhgocdbnbeljkmbjdmhbcokfdb-mousedown">
			<!--右侧内容正文区开始-->
			<div class="span12">
				<div class="gxd-list-subbar">
				<input type="hidden" value="${isNeeCloseWindow}" id="isNeedCloseWindow" />
					<!-- 订单号：&nbsp;&nbsp; -->
					<div class="span12">
						<form class="from-search" action="/dailyWork/dailyWorkList" 
							method="get" style="position: relative;">
							<c:if test="${isTurn==true }">
								<input value="${name}" placeholder="输入关键字..." type="text"
									class="search-query span3" name="name"
									style="width: 17%; position: relative; left: 20px;"></input>
							</c:if>
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <input
									placeholder="起始时间.." type="text" class="search-query span3"
									id="startTime" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})"
									name="startTime" style="width:17%;" value="${startTime}"></input>- <input placeholder="截止时间.."
									type="text" class="search-query span3"
									style="position: relative; left: -3px; width:17%;" id="endTime"
									onClick="WdatePicker({startDate: '%y-%M-%d',dateFmt:'yyyy-MM-dd'})" name="endTime" value="${endTime}"></input>
									<button class="btn btn-info">
									<i class="icon-search"></i>查询
								</button>
									<a class="btn btn-warning" href="/dailyWork/dailyWorkListToday">重置</a>																
							<a target="_blank"  class="btn pull-right" href="/dailyWork/publish" style="position: relative;right: 62px;"> 
							新增
							</a>
						</form>
					</div>
				</div>
				<div class="gxd-list-subbar">
					
<div class="widget-content-light widget-nopad" id="orderListTable">
	<table
		class="table table-striped table-bordered table-hover table-condensed">
		<thead>
			<tr class="error">
				<th id="" style="font-size: 12px;width: 5%;" >姓名</th>
				<th id="" style="font-size: 12px;width: 7%;" >任务名称</th>
				<th id="" style="font-size: 12px;width: 10%;" >任务类型</th>
				<th id="" style="font-size: 12px;width: 7%;" >所属项目</th>
				<th id="" style="font-size: 12px;width: 7%;" >任务预计时间</th>
				<th id="" style="font-size: 12px;width: 30%;" >任务描述</th>
				<th id="" style="font-size: 12px;width: 7%;" >实际花费时间</th>
				<th id="" style="font-size: 12px;width: 7%;" >发布日期</th>
				<th id="" style="font-size: 12px;width: 7%;" >完成日期</th>
				<th id="" style="font-size: 12px;width: 7%;" >状态</th>
				<th id="" style="font-size: 12px;width: 7%;" >操作</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${page.content}" var="workList">
				<tr>
					<td id="" style="text-align: center;">${workList.user.name}</td>
					<td id="" style="text-align: center;">${workList.taskName}</td>
					<td id="" style="text-align: center;">${workList.taskType}</td>
					<td id="" style="text-align: center;">${workList.dependencyProject.typeName}</td>
					<td id="" style="text-align: center;"><c:if test="${workList.planCostTime!=null}">${workList.planCostTime}小时</c:if></td>
					<td id="" style="text-align: center;">
						${workList.taskDescribe}</td>
					<td id="" style="text-align: center;"><c:if test="${workList.realCostTime!=null}">${workList.realCostTime}小时</c:if></td>
					<td id="" style="text-align: center;">${workList.publishTime}</td>
					<td id="" style="text-align: center;">${workList.compeleteTime}</td>
					<td id="" style="text-align: center;">
					<c:choose>
						<c:when test="${workList.completeStatus==0}">草稿</c:when>
						<c:when test="${workList.completeStatus==1}">进行中</c:when>
						<c:when test="${workList.completeStatus==2}">完成</c:when>
						<c:when test="${workList.completeStatus==3}">未完成</c:when>
						<c:when test="${workList.completeStatus==4}">取消</c:when>
						<c:when test="${workList.completeStatus==5}">废弃</c:when>
					</c:choose>
						</td>
					<td style="text-align: center; font-size:10px;">
					<c:if test="${workList.completeStatus==0 || workList.completeStatus==1 }">
						<c:if test="${user.id==workList.user.id }">
						<a href="/dailyWork/publish?id=${workList.id}" target="_blank">编辑任务</a>
						</c:if>
					</c:if>
					</td>				
				</tr>
			</c:forEach>
			
					</tbody>
				</table>
				<div style="width:100px;position:relative;right:-10px;top:10px;" class="pull-right" ><span>总记录&nbsp;&nbsp;${page.totalElements}条</span></div>
				<%@ include file="/WEB-INF/views/common/page.jsp"%>
			</div>
		</div>
	</div>
</div>
</div>
	<script
		src="<c:url value="/resources/scripts/My97DatePicker/WdatePicker.js"/>"
		type="text/javascript"></script>
	<script src="/Statics/js/jquery-1.8.2.min.js"></script>
	<%@ include file="/WEB-INF/views/common/pageJs.jsp"%>
	<script src="/resources/scripts/artDialog5.0/artDialog.min.js"
		type="text/javascript"></script>
	<script src="/resources/scripts/jquery-ajaxForm/jquery.form.js"
		type="text/javascript"></script>
	<script src="/Statics/js/bootstrap.min.js"></script>
	<script>
	</script>
</body>
</html>
;