<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <title>打分人员详情</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <!-- Le styles -->
        <link href="/resources/scripts/artDialog5.0/skins/idialog.css" rel="stylesheet">
    <link href="/Statics/css/bootstrap.min.css" rel="stylesheet">
    <link href="/Statics/css/self.css" rel="stylesheet">
    <link href="/Statics/css/temp.css" rel="stylesheet">
    <link rel="shortcut icon" href="/Statics/img/logo.bmp">
	<%@ include file="/WEB-INF/views/common/pageStyle.jsp"%>
    <style type="text/css">
    .iptHide{
    width: 40px;
position: relative;
top: 6px;
height: 13px;
line-height: 13px;
}
    }
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
    	<div style="height:auto;width:auto;margin: auto;">
    <div class="container-fluid" >
		<div class="row-fluid" id="aeaoofnhgocdbnbeljkmbjdmhbcokfdb-mousedown">
			<!--右侧内容正文区开始-->
			<div class="span12">
				<%-- <div class="gxd-list-subbar">

					<!-- 订单号：&nbsp;&nbsp; -->
					<div class="span12">
						<form class="from-search" action="/trainingStatistics/trainingStatisticsList" 
							method="get" style="position: relative;">
							
							<input placeholder="找人.." type="text" value="${name}"
								class="search-query span2 offset1" name="name" style="width: 17%;"></input>
							<button class="btn btn-info">
								<i class="incon-search"></i>查询
							</button>
						</form>
					</div>
				</div> --%>
				<div class="gxd-list-subbar">
					
<div class="widget-content-light widget-nopad" id="orderListTable">
	<table
		class="table table-striped table-bordered table-hover table-condensed">
		<thead>
			<tr class="error">
				<th id="" style="font-size: 12px">日期</th>
				<th id="" style="font-size: 12px">名字</th>
				<th id="" style="font-size: 12px">参与的培训</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${page.content}" var="trainingStatisticsAttach">
				<tr>
					<td id="" style="text-align: center;">${trainingStatisticsAttach.date}</td>
					<td id="" style="text-align: center;">${trainingStatisticsAttach.userName}</td>
					<td id="" style="text-align: center;">${trainingStatisticsAttach.trainingStatistics.trainingTheme}</td>
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
</div>		
		<script src="/Statics/js/jquery-1.8.2.min.js" ></script>
     <script src="<c:url value="/resources/scripts/My97DatePicker/WdatePicker.js"/>" type="text/javascript"></script>
    	<%@ include file="/WEB-INF/views/common/pageJs.jsp"%>
    <script src="/resources/scripts/artDialog5.0/artDialog.min.js" type="text/javascript"></script>
    <script src="/resources/scripts/jquery-ajaxForm/jquery.form.js" type="text/javascript"></script>
    <script src="/Statics/js/bootstrap.min.js"></script>
    <script>
    $(function(){
    	var options = {
				cache: false,
				beforeSubmit : function() {
				},
				success : function(data) {
					alert("录入成功!");
					location.reload(); 
				},
				error : function(result) {
					alert(result.responseText);
				}
			};
    	$("form[name=myForm]").ajaxForm(options);
    });

    </script>
  </body>
</html>
;