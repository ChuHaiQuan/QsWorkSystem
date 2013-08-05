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
    <div class="container-fluid" style="height:auto;" >
		<div class="row-fluid" id="aeaoofnhgocdbnbeljkmbjdmhbcokfdb-mousedown">
			<!--右侧内容正文区开始-->
			<input type="hidden" value="${isNeedCloseWindow}" id="isNeedCloseWindow" />
			<div class="span12" style="padding:0px 70px; ">
 				<div class="gxd-list-subbar">
					<div class="span12">
						<form class="from-search" action="/rewardPunishmentDetail/rewardPunishmentDetailList" 
							method="get" style="position: relative;">
							<c:if test="${isTurn==true }">
							<input value="${name}" placeholder="找人.." type="text"
								class="search-query span3" name="name" style="width: 17%;position:relative; left:20px;"></input>							
							</c:if>
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <input
									placeholder="起始时间.." type="text" class="search-query span3"
									id="startTime" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})"
									name="startTime" style="width:17%;" value="${startTime }"></input>- <input placeholder="截止时间.."
									type="text" class="search-query span3"
									style="position: relative; left: -3px; width:17%;" id="endTime"
									onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" name="endTime" value="${endTime}"></input>
									<button class="btn btn-info">
									<i class="icon-search"></i>查询
								</button>		
							<a class="btn btn-warning" href="/rewardPunishmentDetail/rewardPunishmentDetailListToday">重置</a>	
							<a target="_blank" class="btn btn-inverse pull-right" href="/rewardPunishmentDetail/addRewardPunishmentDetail" style="position: relative;right: 20px;"> <i
								class="incon-search"></i>新增
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
				<th width="10%" style="font-size: 12px">姓名</th>
				<th width="5%" style="font-size: 12px">得分</th>
				<th width="10%" style="font-size: 12px">奖惩日期</th>								
				<th width="35%" style="font-size: 12px">奖惩缘由</th>
				<th width="20%" style="font-size: 12px">备注</th>
				<th width="10%" style="font-size: 12px">状态</th>
				<th width="10%" style="font-size: 12px"></th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${page.content}" var="awardPunishment">
				<tr>
					<td id="" style="text-align: center;">${awardPunishment.user.name}</td>
					<td id="" style="text-align: center;">${awardPunishment.point}</td>
					<td id="" style="text-align: center;">${awardPunishment.date}</td>
					<td id="" style="text-align: center;">${awardPunishment.pointReason.content}</td>
					<td id="" style="text-align: center;">${awardPunishment.remark}</td>
					<td id="" style="text-align: center;">
						<c:if test="${awardPunishment.status==0}">待确认</c:if>
					</td>
					<td id="" style="text-align: center;">
						<c:if test="${awardPunishment.user.id==user.id}">
							<c:if test="${awardPunishment.status==0}">
								<a name="confirm" value="${awardPunishment.id}">确认</a>
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
     <script src="<c:url value="/resources/scripts/My97DatePicker/WdatePicker.js"/>" type="text/javascript"></script>
    <script src="/Statics/js/jquery-1.8.2.min.js" ></script>
    	<%@ include file="/WEB-INF/views/common/pageJs.jsp"%>
    <script src="/resources/scripts/artDialog5.0/artDialog.min.js" type="text/javascript"></script>
    <script src="/resources/scripts/jquery-ajaxForm/jquery.form.js" type="text/javascript"></script>
    <script src="/Statics/js/bootstrap.min.js"></script>
    <script>
	$(function(){
		$("a[name=confirm]").click(function(){
			$.ajax({
				cache:false,
				url:"/rewardPunishmentDetail/confirm",
				type:"post",
				data:{
					id:$(this).attr("value")
				},
				success:function(){
					alert("操作成功!");
					location.reload();
				},
				error:function(data){
					alert(data.responseText);
				}
			});
		});
		if($("#isNeedCloseWindow").val()==true){
			window.close();
		};
	});
    </script>
  </body>
</html>
;