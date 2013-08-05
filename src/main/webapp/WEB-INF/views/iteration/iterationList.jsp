<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <title>迭代列表</title>
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
			<div class="span12" >
 				<div class="gxd-list-subbar">
 				<c:if test="${vistor.isManager==true}">
					<div class="span12">
							<a  class="btn btn-inverse pull-right" href="/iteration/add" style="position: relative;right: 20px;">
							 <i class="incon-search"></i>新增
							</a>
					</div>
				</c:if>
				</div> 			
				<div class="gxd-list-subbar">
<div class="widget-content-light widget-nopad" id="orderListTable">
	<table
		class="table table-striped table-bordered table-hover table-condensed">
		<thead>
			<tr class="error">
				<th width="15%" style="font-size: 12px">开始时间</th>
				<th width="15%" style="font-size: 12px">结束时间</th>
				<th width="20%"style="font-size: 12px">主题内容</th>								
				<th width="15%" style="font-size: 12px">发布时间</th>
				<th width="40%" style="font-size: 12px">操作</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${page.content}" var="iteration">
				<tr>
					<td id="" style="text-align: center;">${iteration.startTime}</td>
					<td id="" style="text-align: center;">${iteration.endTime}</td>
					<td id="" style="text-align: center;">${iteration.theme}</td>
					<td id="" style="text-align: center;">${iteration.createTime}</td>
					<td  style="text-align: center; font-size:10px;">
					<c:if test="${vistor.isManager==true}">
						<a class="btn btn-mini btn-primary" href="/iteration/add?id=${iteration.id}">编辑</a>&nbsp;&nbsp;&nbsp;
					</c:if>
						<%-- <a href="javascript:;" name="delete" value="${iteration.id}">删除</a> --%>
						 <a class="btn btn-mini" href="/iterationWorkList/list?id=${iteration.id}">任务列表</a>&nbsp;&nbsp;&nbsp;
						 <a class="btn btn-mini" href="/iteration/statistic?id=${iteration.id}">会议统计</a> 
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
    			location.href="/iteration/delById?id="+$(this).attr("value");
    		}
    	}); 
     });
    </script>
  </body>
</html>
;