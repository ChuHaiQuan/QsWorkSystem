<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <title>迭代任务列表</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <!-- Le styles -->
        <link href="/resources/script/artDialog5.0/skins/idialog.css" rel="stylesheet">
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
    <link rel="shortcut icon" href="/ico/favicon.ico">
    <link rel="apple-touch-icon-precomposed" sizes="144x144" href="/ico/apple-touch-icon-144-precomposed.png">
    <link rel="apple-touch-icon-precomposed" sizes="114x114" href="/ico/apple-touch-icon-114-precomposed.png">
    <link rel="apple-touch-icon-precomposed" sizes="72x72" href="/ico/apple-touch-icon-72-precomposed.png">
    <link rel="apple-touch-icon-precomposed" href="/ico/apple-touch-icon-57-precomposed.png">
  </head>

  <body quick-markup_injected="true">
    	<%@ include file="/WEB-INF/views/common/header.jsp"%>
    <div class="container-fluid" style="height:auto;" >
		<div class="row-fluid" id="aeaoofnhgocdbnbeljkmbjdmhbcokfdb-mousedown">
			<!--右侧内容正文区开始-->
			<div class="span12" style="padding:0px 70px; ">
			<c:if test="${vistor.isManager==true}">
 				<div class="gxd-list-subbar">
					<div class="span12">
							<a  class="btn btn-inverse pull-right" href="/iterationWork/addOrEdit?iterationId=${iterationId}" style="position: relative;right: 20px;">
							 <i class="incon-search"></i>新增
							</a>
					</div>
				</div> 
			</c:if>			
				<div class="gxd-list-subbar">
<div class="widget-content-light widget-nopad" id="orderListTable">
	<table
		class="table table-striped table-bordered table-hover table-condensed">
		<thead>
			<tr class="error">
				<th style="font-size: 12px">所属项目</th>
				<th id="" style="font-size: 12px">任务描述</th>
				<th id="" style="font-size: 12px">涉及人员</th>								
				<th id="" style="font-size: 12px">预计时间</th>
				<th id="" style="font-size: 12px">备注</th>
				<th id="" style="font-size: 12px">所属迭代</th>
				<th id="" style="font-size: 12px">完成情况</th>
				<th id="" style="font-size: 12px">操作</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${page.content}" var="iteration">
				<tr>
					<td id="" style="text-align: center;">${iteration.projectType.typeName}</td>
					<td id="" style="text-align: center;">${iteration.workDescription}</td>
					<td id="" style="text-align: center;">${iteration.names}</td>
					<td id="" style="text-align: center;">${iteration.planCostDays}天</td>
					<td id="" style="text-align: center;">${iteration.remark}</td>
					<td id="" style="text-align: center;">${iteration.iteration.startTime}至${iteration.iteration.endTime}</td>
					<td id="" style="text-align: center;">
						<c:choose>
							<c:when test="${iteration.status==1}">进行中</c:when>
							<c:when test="${iteration.status==2}">完成</c:when>
							<c:otherwise>未完成</c:otherwise>
						</c:choose>
					</td>
					<td  style="text-align: center; font-size:10px;">
					<c:if test="${iteration.status==1 && vistor.isManager==true}">
						<a  href="/iterationWork/addOrEdit?id=${iteration.id}&iterationId=${iterationId}">编辑</a>&nbsp;&nbsp;
						<a href="javascript:;" name="delete" value="${iteration.id}">删除</a>&nbsp;&nbsp;
						<a href="javascript:;" name="complete" value="${iteration.id}">完成</a>&nbsp;&nbsp;
						<a href="javascript:;" name="unComplete" value="${iteration.id}">未完成</a>					
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
<input type="hidden" value="${iterationId}" name="iterationId" id="iterationId"/>
     <script src="<c:url value="/resources/script/My97DatePicker/WdatePicker.js"/>" type="text/javascript"></script>
    <script src="/Statics/js/jquery-1.8.2.min.js" ></script>
    	<%@ include file="/WEB-INF/views/common/pageJs.jsp"%>
    <script src="/resources/script/artDialog5.0/artDialog.min.js" type="text/javascript"></script>
    <script src="/resources/script/jquery-ajaxForm/jquery.form.js" type="text/javascript"></script>
    <script src="/Statics/js/bootstrap.min.js"></script>
    <script>
     $(function(){
    	$("a[name=delete]").click(function(){
    		if(confirm("你确定要删除吗?")){
    			location.href="/iterationWork/delById?id="+$(this).attr("value")+"&pageIndex="+$("#pageIndex").val()+"&iterationId="+$("#iterationId").val();
    		}
    	}); 
    	$("a[name=complete]").click(function(){
    		if(confirm("你确定吗?")){
    			location.href="/iterationWork/completeById?id="+$(this).attr("value")+"&pageIndex="+$("#pageIndex").val()+"&iterationId="+$("#iterationId").val();
    		}
    	}); 
    	$("a[name=unComplete]").click(function(){
    		if(confirm("你确定吗?确定之后相关人员就会自动的出处相应的分数!")){
    			location.href="/iterationWork/unCompleteById?id="+$(this).attr("value")+"&pageIndex="+$("#pageIndex").val()+"&iterationId="+$("#iterationId").val();
    		}
    	}); 
     });
    </script>
  </body>
</html>
;