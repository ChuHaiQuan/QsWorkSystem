<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <title>已点列表</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">

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
    <div class="container-fluid">
		<div class="row-fluid" id="aeaoofnhgocdbnbeljkmbjdmhbcokfdb-mousedown">
          


			<!--右侧内容正文区开始-->
			<div class="span12">
                    <div class="gxd-list-subbar">

						<!-- 订单号：&nbsp;&nbsp; -->
						<div class="span10" >
							<form class="from-search"
								action="/doSearchForOrderByTimeOrUserName"
								id="orderListForm" method="get" style="position:relative;left:20px;">
								个人用餐情况查询 <input placeholder="请输入姓名.." type="text"
									class="search-query span3" name="userName" style="width:17%;"></input>
								<button class="btn btn-info" >
									<i class="incon-search"></i>查询
								</button>
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;按时间段查询用餐情况 <input
									placeholder="起始时间.." type="text" class="search-query span3"
									id="startTime" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})"
									name="startTime" style="width:17%;"></input>- <input placeholder="截止时间.."
									type="text" class="search-query span3"
									style="position: relative; left: -3px; width:17%;" id="endTime"
									onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" name="endTime" ></input>
									<button class="btn btn-info">
									<i class="incon-search"></i>查询
								</button>
							</form>
						</div>
						</div>
						
				<div class="gxd-list-subbar">
				<div class="btn-toolbar " >
				     <div class="btn-group span1 top" >
    <a class="btn dropdown-toggle btn-info" data-toggle="dropdown" href="#">
          我要排序
    <span class="caret"></span>
    </a>
    <ul class="dropdown-menu">
     <li><a href="/findPageOrderByFoodName">餐名</a></li>
     <li><a href="/searchAllApply">订餐时间</a></li>
    </ul>
    </div>
       <div class="btn-group span2 top" >
    <a class="btn btn-success"  href="/findTodayOrders">
         	查看当日点餐情况
    </a>
    </div>
          
           
    </div>
			<div class="widget-content-light widget-nopad" id="orderListTable">
			  <table
	class="table table-striped table-bordered table-hover table-condensed">
	<thead>
		<tr class="error">
			<th id="" style="font-size: 12px">序号</th>
			<th id="" style="font-size: 12px">姓名</th>
			<th id="" style="font-size: 12px"><abbr>餐名</abbr></th>
			<th id="" style="font-size: 12px">订餐时间</th>
			<th id="" style="font-size: 12px">用餐日期</th>
			<th id="" style="font-size: 12px">用餐时间</th>
			<th id="" style="font-size: 12px">备注</th>
			<th id="" style="font-size: 12px">操作</th>
			
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${page.content}" var="lunchList">
			<tr>
			    <td name="xuhao" style="text-align: center;"></td>
			    <td id="" style="text-align: center;">${lunchList.userName}</td>
			    <td id="" style="text-align: center;">${lunchList.lunchName}</td>
				<td id="" style="text-align: center;">${lunchList.dateNow}</td>
				<td id="" style="text-align: center;"> ${lunchList.dateTom}</td>
				<td id="" style="text-align: center;">${lunchList.eatTime}</td>
				<td id="" style="text-align: center;">${lunchList.beiZhu}</td>
				<td style="text-align: center;">
				<c:if test="${lunchList.ipAttr==ipAttr}">
				<c:if test="${lunchList.status==2}">
				<a href="/changeMyOrder?id=${lunchList.id}">修改</a>
				<a href="javascript:;" id="deleteOne" value="${lunchList.id}">删除</a>
				</c:if>
				</c:if>
				</td>
				
			</tr>
		</c:forEach>
	</tbody>
</table>
  				<%@ include file="/WEB-INF/views/common/page.jsp"%>   
    </div>
    </div>
			</div>

      </div>
      <input type="hidden" value="${page.number}" id="index"/>
            <input type="hidden" value="${page.size}" id="size"/>
    </div>
     <script src="<c:url value="/resources/script/My97DatePicker/WdatePicker.js"/>" type="text/javascript"></script>
    <script src="/Statics/js/jquery-1.8.2.min.js" ></script>
    	<%@ include file="/WEB-INF/views/common/pageJs.jsp"%>
    <script src="/resources/script/artDialog5.0/artDialog.min.js" type="text/javascript"></script>
    <script src="/resources/script/jquery-ajaxForm/jquery.form.js" type="text/javascript"></script>
    <script src="/Statics/js/bootstrap.min.js"></script>
    <script>
      $(function(){  
    	  $("#deleteOne").click(function(){
    		  if(confirm("你确定要删除今日的订餐吗？")){
    			  location.href="/deleteMyOrder?id="+$(this).attr("value");
    		  }
    		  else{
    			  return false;
    		  }
    	  });
    	   for(var i=0;i<$("td[name=xuhao]").length;i++){
    		   $("td[name='xuhao']").eq(i).html($("#index").val()*$("#size").val()+i+1);
    	   }
      });
    
    
    </script>
  </body>
</html>
;