<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8" http-equiv="Content-type" content="text/html">
    <title>奖惩分类汇总统计</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <!-- Le styles -->
        <link href="/resources/scripts/artDialog5.0/skins/idialog.css" rel="stylesheet">
    <link href="/Statics/css/bootstrap.min.css" rel="stylesheet">
    <link href="/Statics/css/self.css" rel="stylesheet">
    <link href="/Statics/css/temp.css" rel="stylesheet">
    <link rel="shortcut icon" href="/Statics/img/logo.bmp">
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
						<form class="from-search" action="/rewardPunishmentStastics/rewardPunishmentStasticsByCategoty" 
							method="get" style="position: relative;">
							<input value="${name}" placeholder="奖惩项.." type="text"
								class="search-query span3" name="name" style="width: 17%;position:relative; left:20px;"></input>							
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
						</form>
					</div>
				</div> 
				<div class="gxd-list-subbar">
					
<div class="widget-content-light widget-nopad" id="orderListTable">
	<table
		class="table table-striped table-bordered table-hover table-condensed">
		<thead>
			<tr class="error">
				<th id="" style="font-size: 12px">奖惩类别</th>								
				<th id="" style="font-size: 12px">奖惩次数</th>
				<th id="" style="font-size: 12px">奖</th>
				<th id="" style="font-size: 12px">惩</th>
				<th id="" style="font-size: 12px"></th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${list}" var="object">
				<tr>
					<td  style="text-align: center;">${object[1] }</td>
					<td  style="text-align: center;" name="num">${object[2] }</td>
					<td  style="text-align: center;" name="lose">${object[3] }</td>
					<td  style="text-align: center;" name="add">${object[4] }</td>
					<td  style="text-align: center;">
					<a name="getUrl" value1="${object[1]}" value2="${startTime}" value3="${endTime}" href="" target="_blank">查看明细</a>
					</td>
				</tr>
			</c:forEach>
				<tr>
					<td  style="text-align: center;"><span style="font-family: 微软雅黑; font-weight: 600;color: red;">小计</span></td>
					<td  style="text-align: center;" id="numTotal"></td>
					<td  style="text-align: center;" id="loseTotal"></td>
					<td  style="text-align: center;" id="allTotal"></td>
					<td  style="text-align: center;" ></td>
				</tr>	
		</tbody>
				</table>
			</div>
		</div>
	</div>
</div>
</div>
     <script src="<c:url value="/resources/scripts/My97DatePicker/WdatePicker.js"/>" type="text/javascript"></script>
    <script src="/Statics/js/jquery-1.8.2.min.js" ></script>
    <script src="/resources/scripts/artDialog5.0/artDialog.min.js" type="text/javascript"></script>
    <script src="/resources/scripts/jquery-ajaxForm/jquery.form.js" type="text/javascript"></script>
    <script src="/Statics/js/bootstrap.min.js"></script>
    <script>
    $(function(){
    	for(var i=0;i<$("a[name=getUrl]").size();i++){
    		$("a[name=getUrl]").eq(i).attr("href","/rewardPunishmentStastics/rewardPunishmentStasticsByCategotyDetail?content="+encodeURI( encodeURI( $("a[name=getUrl]").eq(i).attr("value1")) )+"&startTime="+$("a[name=getUrl]").eq(i).attr("value2")+"&endTime="+$("a[name=getUrl]").eq(i).attr("value3"));
    	}
    	/*做小计汇总start  */
    	function  doSum(sum,aim){
    		var j=0;
    		for(var i=0;i<sum.size();i++){
    			j=j+sum.eq(i).html()*1;
    		}
    		aim.html(j);
    	}    
    	doSum($("td[name=num]"),$("#numTotal"));
        doSum($("td[name=lose]"),$("#loseTotal"));
        doSum($("td[name=add]"),$("#allTotal"));
        /*做小计汇总end*/
    });
    </script>
  </body>
</html>