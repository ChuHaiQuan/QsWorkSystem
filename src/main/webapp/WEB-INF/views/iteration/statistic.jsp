<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>

<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <title></title>
    <!-- Le styles -->
    <link href="/Statics/css/bootstrap.min.css" rel="stylesheet">
    <link href="/Statics/css/self.css" rel="stylesheet">
    <link href="/Statics/css/temp.css" rel="stylesheet">
	<link rel="shortcut icon" href="/Statics/img/logo.bmp">
    <style type="text/css">
      body{padding-top: 60px;}
      a{cursor: pointer;}
    </style>
    <link href="/Statics/css/main-controller.css" rel="stylesheet"> 
    <link href="/Statics/css/bootstrap-responsive.css" rel="stylesheet">

    <!-- Le fav and touch icons -->
    <link rel="apple-touch-icon-precomposed" sizes="144x144" href="/ico/apple-touch-icon-144-precomposed.png">
    <link rel="apple-touch-icon-precomposed" sizes="114x114" href="/ico/apple-touch-icon-114-precomposed.png">
    <link rel="apple-touch-icon-precomposed" sizes="72x72" href="/ico/apple-touch-icon-72-precomposed.png">
    <link rel="apple-touch-icon-precomposed" href="/ico/apple-touch-icon-57-precomposed.png">
  </head>

  <body quick-markup_injected="true">
  <%@ include file="/WEB-INF/views/common/header.jsp"%>
<div class="container-fluid">      
<input type="hidden" value="${iteration.id}" id="iterationId" /> 
	<c:if test="${!empty needToConfirmList && vistor.isManager==true}">
		<div class="row-fluid" id="">
			<p>还存在待确认的奖惩项,点击按钮做最后的确认.<a id="confirm"  class="btn btn-primary">确认</a></p>
		</div>
	</c:if>
	<div class="row-fluid" id="">
	      <div class="span6">
	          <div class="widget-box">
	            <div class="widget-title">
	              <span class="icon"><i class="icon-signal"></i></span><h5>全勤奖获奖者名单</h5>
	              <div class="buttons">
	               <c:if test="${empty fullAttendenRewardPunishmentDetaillist && !empty fullAttendenceAwardUserList && vistor.isManager==true}">
	           	  		 <a id="confirmFullAttendenceAward" class="btn btn-mini"><i class="icon-ok"></i>确认</a>
	           	   </c:if>
	              </div>
	            </div>
	            <div class="widget-content" style="min-height: 120px;">
	            	<c:forEach items="${fullAttendenceAwardUserList}" var="user">
	            		<div style="width: 75px;float: left;">${user[1]}</div>
	            	</c:forEach>
	            </div>
	          </div>
	        </div>   
	      <div class="span6">
	          <div class="widget-box">
	            <div class="widget-title">
	              <span class="icon"><i class="icon-signal"></i></span><h5>迭代任务未完成名单</h5><div class="buttons">
	              </div>
	            </div>
	            <div class="widget-content" style="min-height: 120px;">
	            	<c:forEach items="${iterationWorkListList }" var="iterationWorkList">
		            	<div style="float: left;width: 50%;text-align: center;">
			            	<p style="font-size: 15px;font-weight: 700;">${iterationWorkList.projectType.typeName }</p>
			            	<p style="font-size: 12px;">${iterationWorkList.names}</p>
		            	</div>
	            	</c:forEach>
	            </div>
	          </div>
	        </div>	
	</div>
	<div class="row-fluid" id="">
	      <div class="span6">
	          <div class="widget-box">
	            <div class="widget-title">
	              <span class="icon"><i class="icon-signal"></i></span><h5>迭代总分最多者名单</h5>
	              <div class="buttons">
	              <a class="btn btn-mini" href="/iteration/detailInfo?id=${iteration.id}"><i></i>查看详情</a>
	              <c:if test="${empty theHighestAllPointPunishmentDetaillist && !empty theHighesAlltPointUsersList && vistor.isManager==true}">
	           	  	 <a id="confirmHighestAllPointAward" class="btn btn-mini"><i class="icon-ok"></i>确认</a>
	           	  </c:if>
	              </div>
	            </div>
	            <div class="widget-content" style="min-height: 120px;">
	            	<c:forEach items="${theHighesAlltPointUsersList}" var="userPoint">
		            	<div style="float: left;width: 100px;text-align: center;">
			            	<p style="font-size: 15px;font-weight: 700;">${userPoint[0]}</p>
			            	<p style="font-size: 12px;">${userPoint[3]}分</p>
		            	</div>
	            	</c:forEach>
	            </div>
	          </div>
	        </div>   
	      <div class="span6">
	          <div class="widget-box">
	            <div class="widget-title">
	              <span class="icon"><i class="icon-signal"></i></span><h5>迭代扣分最多者名单</h5><div class="buttons">
	              <a class="btn btn-mini" href="/iteration/detailInfo?id=${iteration.id}"><i></i>查看详情</a>
	              <c:if test="${empty theHighestLowPointPunishmentDetaillist && !empty theHighestLowPointUsersList && vistor.isManager==true}">
	            	  <a id="confirmHighestLosePointAward" class="btn btn-mini"><i class="icon-ok"></i>确认</a>
	              </c:if>	              
	              </div>
	            </div>
	            <div class="widget-content" style="min-height: 120px;">
	            	<c:forEach items="${theHighestLowPointUsersList }" var="userPoint">
	            	<div style="float: left;width: 100px;text-align: center;">
		            	<p style="font-size: 15px;font-weight: 700;">${userPoint[0]}</p>
		            	<p style="font-size: 12px;">${userPoint[1]}分</p>
	            	</div>
	            	</c:forEach>
	            </div>
	          </div>
	        </div>	
	</div>
	<div class="row-fluid" id="">
	      <div class="span6">
	          <div class="widget-box">
	            <div class="widget-title">
	              <span class="icon"><i class="icon-signal"></i></span><h5>迭代bug（组外+里程碑）最多者名单</h5>
	              <div class="buttons">
	               <a class="btn btn-mini" href="/iteration/detailInfo?id=${iteration.id}"><i></i>查看详情</a>
	              <c:if test="${empty theHighestBugPointPunishmentDetaillist && !empty theHighestBugPointUsersList && vistor.isManager==true}">
	              	<a id="confirmHighestBugPointAward" class="btn btn-mini"><i class="icon-ok"></i>确认</a>
	              </c:if>
	              </div>
	            </div>
	            <div class="widget-content" style="min-height: 120px;">
	            	<c:forEach items="${theHighestBugPointUsersList}" var="userPoint">
		            	<div style="float: left;width: 100px;text-align: center;">
			            	<p style="font-size: 15px;font-weight: 700;">${userPoint[1]}</p>
			            	<p style="font-size: 12px;">${userPoint[2]}分</p>
		            	</div>
	            	</c:forEach>
	            </div>
	          </div>
	        </div>   
	</div>			
    </div>   
   <script src="/Statics/js/bootstrap.min.js"></script>
   <script src="/Statics/js/bootstrap-collapse.js"></script>
   <script type="text/javascript">
   $(function(){
	   //确认待确认奖惩
	   $("#confirm").click(function(){
			$.ajax({
				cache:false,
				type:"post",
				url:"/rewardPunishmentDetail/finalAutoComfirm",
				success:function(data){
					alert("确认成功!");
					location.reload();
				},
				error:function(error){
					alert(error.responseText);
				}
			});		   
	   });	   
	   //全勤确认
	   $("#confirmFullAttendenceAward").click(function(){
			$.ajax({
				cache:false,
				type:"post",
				url:"/iteration/confirmFullAttendenceAward",
				data:{
					id:$("#iterationId").val()
				},
				success:function(data){
					alert("操作成功!");
					location.reload();
				},
				error:function(error){
					alert(error.responseText);
				}
			});		   
	   });
	   
	   //总分最多确认
	   $("#confirmHighestAllPointAward").click(function(){
			$.ajax({
				cache:false,
				type:"post",
				url:"/iteration/confirmHighestAllPointAward",
				data:{
					id:$("#iterationId").val()
				},
				success:function(data){
					alert("操作成功!");
					location.reload();
				},
				error:function(error){
					alert(error.responseText);
				}
			});		   
	   });	 
	   
	   //扣分最多确认
	   $("#confirmHighestLosePointAward").click(function(){
			$.ajax({
				cache:false,
				type:"post",
				url:"/iteration/confirmHighestLosePointAward",
				data:{
					id:$("#iterationId").val()
				},
				success:function(data){
					alert("操作成功!");
					location.reload();
				},
				error:function(error){
					alert(error.responseText);
				}
			});		   
	   });	
	   
	   //Bug最多确认
	   $("#confirmHighestBugPointAward").click(function(){
			$.ajax({
				cache:false,
				type:"post",
				url:"/iteration/confirmHighestBugPointAward",
				data:{
					id:$("#iterationId").val()
				},
				success:function(data){
					alert("操作成功!");
					location.reload();
				},
				error:function(error){
					alert(error.responseText);
				}
			});		   
	   });		   

   });
   </script>
  </body>
</html>

