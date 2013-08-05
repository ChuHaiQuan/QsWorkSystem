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
	<div class="row-fluid" id="">
	      <div class="span6">
	          <div class="widget-box">
	            <div class="widget-title">
	              <span class="icon"><i class="icon-signal"></i></span><h5>迭代总分汇总详情</h5>
	              <div class="buttons">
	              </div>
	            </div>
	            <div class="widget-content" style="min-height: 120px;">
	            <table class="table table-striped table-bordered table-hover table-condensed">
					<thead>
						<tr class="error">
							<th id="" style="font-size: 12px;text-align: center;">姓名</th> 
							<th id="" style="font-size: 12px;text-align: center;">总分数(分)</th>								
						</tr>
					</thead>
					<tbody id="tbody">	            	
	            	<c:forEach items="${allPointDetailList}" var="object">
						<tr>
							<td  style="text-align: center;" >${object[0]}</td>
							<td  style="text-align: center;" name="allPoint">${object[3]}</td>
						</tr>
	            	</c:forEach>
						<tr>
							<td  style="text-align: center;font-family: 微软雅黑; font-weight: 600;color: red;" >小计</td>
							<td  style="text-align: center;font-weight: 700;font-size: 20px;" id="totalAllPoint"></td>
						</tr>	            	
	            	</tbody>
	            </table> 
	            </div>
	          </div>
	        </div>   
	      <div class="span6">
	          <div class="widget-box">
	            <div class="widget-title">
	              <span class="icon"><i class="icon-signal"></i></span><h5>迭代扣分汇总详情</h5><div class="buttons">
	              </div>
	            </div>
	            <div class="widget-content" style="min-height: 120px;">
	            <table class="table table-striped table-bordered table-hover table-condensed">
					<thead>
						<tr class="error">
							<th id="" style="font-size: 12px;text-align: center;">姓名</th> 
							<th id="" style="font-size: 12px;text-align: center;" >总扣分数(分)</th>								
						</tr>
					</thead>
					<tbody id="tbody">	            	
	            	<c:forEach items="${losePointDetailList}" var="object">
						<tr>
							<td  style="text-align: center;" >${object[0]}</td>
							<td  style="text-align: center;" name="losePoint">${object[1]}</td>
						</tr>
	            	</c:forEach>
						<tr>
							<td  style="text-align: center;font-family: 微软雅黑; font-weight: 600;color: red;" >小计</td>
							<td  style="text-align: center;font-weight: 700;font-size: 20px;" id="totalLosePoint"></td>
						</tr>		            	
	            	</tbody>
	            </table> 
	            </div>
	          </div>
	        </div>	
	</div>
	<div class="row-fluid" id="">
	      <div class="span6">
	          <div class="widget-box">
	            <div class="widget-title">
	              <span class="icon"><i class="icon-signal"></i></span><h5>迭代bug（组外+里程碑）扣分汇总详情</h5>
	              <div class="buttons">
	              </div>
	            </div>
	            <div class="widget-content" style="min-height: 120px;">
					<table class="table table-striped table-bordered table-hover table-condensed">
						<thead>
							<tr class="error">
								<th id="" style="font-size: 12px;text-align: center;">姓名</th> 
								<th id="" style="font-size: 12px;text-align: center;">总bug扣分数(分)</th>								
							</tr>
						</thead>
						<tbody id="tbody">	            	
		            	<c:forEach items="${bugPointDetailList}" var="object">
							<tr>
								<td  style="text-align: center;" >${object[1]}</td>
								<td  style="text-align: center;" name="bugPoint">${object[2]}</td>
							</tr>
		            	</c:forEach>
						<tr>
							<td  style="text-align: center;font-family: 微软雅黑; font-weight: 600;color: red;" >小计</td>
							<td  style="text-align: center;font-weight: 700;font-size: 20px;" id="totalBugPoint"></td>
						</tr>			            	
		            	</tbody>
		            </table> 
	            </div>
	          </div>
	        </div>   
	</div>			
    </div>   
   <script src="/Statics/js/bootstrap.min.js"></script>
   <script src="/Statics/js/bootstrap-collapse.js"></script>
   <script type="text/javascript">
   $(function(){
	  	/*做小计汇总start  */
	  	function  doSum(sum,aim){
	  		var j=0;
	  		for(var i=0;i<sum.size();i++){
	  			j=j+sum.eq(i).html()*1;
	  		}
	  		aim.html(j);
	  	}   
	  	/*做小计汇总end*/
  	        doSum($("td[name=allPoint]"),$("#totalAllPoint"));
	   	    doSum($("td[name=losePoint]"),$("#totalLosePoint"));
	   	    doSum($("td[name=bugPoint]"),$("#totalBugPoint"));	  	
   });
   </script>
  </body>
</html>

