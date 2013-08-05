<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <title>项目维护</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <!-- Le styles -->
        <link href="/resources/script/artDialog5.0/skins/idialog.css" rel="stylesheet">
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
			<div class="span12" style="padding:0px 70px; ">
 				<div class="gxd-list-subbar">
					<div class="span12">
 						<form class="from-search" action="/projectType/projectMaintain" 
							method="get" style="position: relative;">
							<input value="${name}" placeholder="找项目.." type="text"
								class="search-query span3 offset1" name="name" style="width: 17%;"></input>
							<button class="btn btn-info">
								<i class="incon-search"></i>查询
							</button>
							<a target="_blank"  class="btn btn-inverse pull-right" href="/projectType/addOrEditProject" style="position: relative;right: 20px;">
							 <i class="incon-search"></i>新增
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
				<th id="" style="font-size: 12px">项目名称</th>
				<th id="" style="font-size: 12px">项目描述</th>
				<th id="" style="font-size: 12px">项目管理员</th>								
				<th id="" style="font-size: 12px">操作</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${page.content}" var="project">
				<tr>
					<td id="" style="text-align: center;">${project.typeName}</td>
					<td id="" style="text-align: center;">${project.description}</td>
					<td id="" style="text-align: center;">${project.administrtor}</td>
					<td  style="text-align: center; font-size:10px;"><a target="_blank"
						href="/projectType/addOrEditProject?id=${project.id}">编辑</a>&nbsp;&nbsp;&nbsp;
						<a href="javascript:;" id="delete" value="${project.id}">删除</a>
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
    	$("#delete").click(function(){
    		if(confirm("你确定要删除吗?")){
    			location.href="/projectType/delById?id="+$(this).attr("value");
    		}
    	}); 
     });
    </script>
  </body>
</html>
;