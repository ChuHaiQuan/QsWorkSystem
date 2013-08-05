<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>

<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <title>全顺日常工作辅助系统</title>
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
              <span class="icon"><i class="icon-signal"></i></span><h5>最新动态</h5><div class="buttons"></div>
            </div>
            <div class="widget-content" style="min-height: 230px;">
          	  <h6 style="padding-bottom: 10px;">备注：点击标题查看详情(请使用非IE浏览器)</h6>
	            <div class="accordion" id="accordion2">
					<div class="accordion-group">
	              		<div class="accordion-heading">
	               			 <a class="accordion-toggle" data-toggle="collapse" data-parent="#accordion2" href="#collapseFour" >
	                  			<span style="font-weight:bold;margin-right: 5px;">1.</span>会议文档管理功能
	               			 </a>
	             		 </div>
	              	<div id="collapseFour" class="accordion-body  collapse" style="height: 0px;">
	                		<div class="accordion-inner" style="background-color: white;">
	               				<p style="font-size: 12px;">以后会议文档可直接上传到系统中!</p> 
	                	 	</div>
	                </div>
	          	  </div>	            
					<div class="accordion-group">
	              		<div class="accordion-heading">
	               			 <a class="accordion-toggle" data-toggle="collapse" data-parent="#accordion2" href="#collapseOne" >
	                  			<span style="font-weight:bold;margin-right: 5px;">2.</span>惩罚代扣
	               			 </a>
	             		 </div>
	              	<div id="collapseOne" class="accordion-body  collapse  in" style="height: 0px;">
	                		<div class="accordion-inner" style="background-color: white;">
	               				 <p style="font-size: 12px;text-indent: 2em;">惩罚扣分细则如下：</p>
	               				 <p style="text-indent: 2em; font-size: 10px;">率先有一人发起一个惩罚扣分项,如果该扣分项有需要被扣分的关联人员(俗称铁索连环),
	               				 就需要选择相关的扣分人员一并提交,此时，需要私下告知这些相关人员,如果无异议,需要他们确认之后该惩罚项方能生效(请自觉)!</p>
	                	 	</div>
	                </div>
	          	  </div>	 
					<div class="accordion-group">
	              		<div class="accordion-heading">
	               			 <a class="accordion-toggle" data-toggle="collapse" data-parent="#accordion2" href="#collapseTwo" >
	                  			<span style="font-weight:bold;margin-right: 5px;">3.</span>迟到扣分已上线使用(点击查看详情)
	               			 </a>
	             		 </div>
	              	<div id="collapseTwo" class="accordion-body  collapse " style="height: 0px;">
	                		<div class="accordion-inner">
	               				 <p style="font-size: 12px;">迟到扣分规则如下：</p>
	               				 <p style="text-indent: 2em; font-size: 10px;">迟到1到3次(含3次)扣一分</p>
	               				 <p style="text-indent: 2em; font-size: 10px;">迟到4到5次(含5次)扣两分</p>
	               				 <p style="text-indent: 2em; font-size: 10px;">迟到5次以上, 按照<span style="font-weight: bold;">n-3</span>的规律扣分</p>
	                	 	</div>
	                </div>
	          	  </div>
	          	  
					<div class="accordion-group">
	              		<div class="accordion-heading">
	               			 <a class="accordion-toggle" data-toggle="collapse" data-parent="#accordion2" href="#collapseThree" >
	                  			<span style="font-weight:bold;margin-right: 5px;">4.</span>系统管理员已增加
	               			 </a>
	             		 </div>
	              	<div id="collapseThree" class="accordion-body  collapse" style="height: 0px;">
	                		<div class="accordion-inner" style="background-color: white;">
	               				<p style="font-size: 12px;">系统管理员有系统管理的权限!</p> 
	                	 	</div>
	                </div>
	          	  </div>
	          	  	          	  
	            </div>
            </div>
          </div>
        </div>
	      <div class="span6">
	          <div class="widget-box">
	            <div class="widget-title">
	              <span class="icon"><i class="icon-signal"></i></span><h5>今日培训</h5><div class="buttons"><!-- <a id="refresh" class="btn btn-mini"><i class="icon-refresh"></i> 更新</a> --></div>
	            </div>
	            <div class="widget-content" style="min-height: 120px;">
 					<p id="tTheme">培训主题：</p>
					<p id="tType">主题类型：</p>
					<p id="tUserName">演讲人：</p>
					<p >资料下载：<a id="tFile" href=""></a></p>               	           
	            </div>
	          </div>
	        </div>   
	      <div class="span6">
	          <div class="widget-box">
	            <div class="widget-title">
	              <span class="icon"><i class="icon-signal"></i></span><h5>今日轮值</h5><div class="buttons"><!-- <a id="refresh" class="btn btn-mini"><i class="icon-refresh"></i> 更新</a> --></div>
	            </div>
	            <div class="widget-content">
					<p id="turnType">轮值类型：</p>
					<p id="turnPerson">轮值人员：</p>                	          
	            </div>
	          </div>
	        </div>	
	</div>
    <div class="row-fluid" id="">
      <div class="span6">
          <div class="widget-box">
            <div class="widget-title">
              <span class="icon"><i class="icon-signal"></i></span><h5>本迭代人员奖惩统计</h5>
              	<h5 style="float: right;">本迭代周期：<span id="startTime" style="padding-right: 5px;"></span>至<span id="endTime" style="padding-left: 5px;"></span></h5>
              <div class="buttons"> 
              	
              <!-- <a id="refresh" class="btn btn-mini"><i class="icon-refresh"></i> 更新</a> --></div>
            </div>
            <div class="widget-content">
				<table class="table table-striped table-bordered table-hover table-condensed">
					<thead>
						<tr class="error">
							<th id="" style="font-size: 12px;text-align: center;">姓名</th> 
							<th id="" style="font-size: 12px;text-align: center;">惩罚</th>								
							<th id="" style="font-size: 12px;text-align: center;">奖励</th>
							<th id="" style="font-size: 12px;text-align: center;">奖惩合计</th>
							<th id="" style="font-size: 12px;text-align: center;"></th>
						</tr>
					</thead>
					<tbody id="tbody">
							<tr>
								<td  style="text-align: center;" ></td>
								<td  style="text-align: center;" ></td>
								<td  style="text-align: center;" ></td>
								<td  style="text-align: center;" ></td>
								<td  style="text-align: center;" ></td>
							</tr>
					</tbody>
				</table>        
            </div>
          </div>
        </div>
	      <div class="span6">
	          <div class="widget-box">
	            <div class="widget-title">
	              <span class="icon"><i class="icon-signal"></i></span><h5>轮值人员职责说明</h5><div class="buttons"><!-- <a id="refresh" class="btn btn-mini"><i class="icon-refresh"></i> 更新</a> --></div>
	            </div>
	            <div class="widget-content">
					<p>1.9点召开每日站立会议，检查团员每日系统任务情况发布及纸质任务情况（任务墙）</p>
					<p>2.参与完敏捷培训或者常规培训后，提醒团员给分和评价。</p>
					<p>3.下班后归档团员任务小结及扣分情况（研发部QQ群）,待团员确认无误之后整理到任务墙的囧字板和正字榜。</p>
					<P>4.如果当值日正好碰到启动会议或者演示会议或者回顾会议，则需要负责把会议相关文档记录到“启动会议”、“演示会议”及“回顾会议”的文件夹中 </P>             
	            </div>
	          </div>
	        </div>	
    </div>   
    </div>   
   <script src="/Statics/js/bootstrap.min.js"></script>
   <script src="/Statics/js/bootstrap-collapse.js"></script>
   <script type="text/javascript">
	  	/*做小计汇总start  */
	  	function  doSum(sum,aim){
	  		var j=0;
	  		for(var i=0;i<sum.size();i++){
	  			j=j+sum.eq(i).html()*1;
	  		}
	  		aim.html(j);
	  	}   
	  	/*做小计汇总end*/
   		$(function(){
   			/*获取今天培训信息 */
   			$.ajax({
   				cache:false,
   				type:"get",
   				url:"/homeAttach/getTodayTrainer",
   				success:function(list){
   					for(var i=0;i<list.length;i++){
   						var trainType="";
   						if(list[i].trainingType==0){
   							trainType="敏捷培训";
   						}else{
   							trainType="常规培训";
   						}
   						if(i==0){
   							$("#tTheme").append(list[i].trainingTheme);
   							$("#tType").append(trainType);
   							$("#tFile").html(list[i].file.name);
   							$("#tFile").attr("href",list[i].file.path);
   							$("#tUserName").append(list[i].user.name);
   						}
   					}
   				},
   				error:function(error){
   					alert(error.responseText);
   				}
   			});
   			$.ajax({
   				cache:false,
   				type:"get",
   				url:"/homeAttach/getTodayTurnUser",
   				success:function(list){
   					for(var i=0;i<list.length;i++){
   						if(i==0){
   							$("#turnType").append(list[i].category);
   							$("#turnPerson").append(list[i].userName);
   						}
   						
   					}
   				},
   				error:function(error){
   					alert(error.responseText);
   				}
   			});   			
   			/*获取今天轮值人员 */
   			/*获取本迭代统计信息 */
   			$.ajax({
   				cache:false,
   				type:"get",
   				url:"/rewardPunishmentStastics/findByIteration",
   				success:function(list){
   					for(var i=0;i<list.length;i++){
						$("#tbody").append("<tr><td style='text-align: center;'>"+list[i].userName+"</td> <td  style='text-align: center;' name='lose'>"+list[i].losePoint+"</td> <td  style='text-align: center;' name='add' >"+list[i].addPoint+"</td> <td  style='text-align: center;' name='all'>"+list[i].allPoint+"</td> <td  style='text-align: center;' ></td> </tr>");
   					}
					$("#tbody").append("<tr><td style='text-align: center;'><span style='font-family: 微软雅黑; font-weight: 600;color: red;'>小计</span></td> <td  style='text-align: center;' id='loseTotal'></td> <td  style='text-align: center;' id='addTotal'></td> <td  style='text-align: center;' id='allTotal'></td> <td  style='text-align: center;' ></td> </tr>");
   		   	        doSum($("td[name=add]"),$("#addTotal"));
   		   	        doSum($("td[name=lose]"),$("#loseTotal"));
   		   	        doSum($("td[name=all]"),$("#allTotal"));   					
   				},
   				error:function(error){
   					alert(error.responseText);
   				}
   			});
   			$.ajax({
   				cache:false,
   				type:"get",
   				url:"/rewardPunishmentStastics/getThisIterator",
   				success:function(list){
   					$("#startTime").html(list[0]);
   					$("#endTime").html(list[1]);
   				},
   				error:function(error){
   					alert(error.responseText);
   				}
   			});    			
   		});
   </script>
  </body>
</html>

