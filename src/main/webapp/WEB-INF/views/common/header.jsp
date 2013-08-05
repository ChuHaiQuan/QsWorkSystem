<%@ page pageEncoding="utf-8"%>
<%@ page import="com.qs.permission.core.support.UserRequestContextInfo" %> 
<link href="/resources/script/artDialog5.0/skins/idialog.css" rel="stylesheet">
<div class="navbar navbar-fixed-top navbar-inverse">
  <div class="navbar-inner">
  <div class="container-fluid" id="">
    <a class="btn btn-navbar" data-toggle="collapse" data-target=".nav-collapse">
      <span class="icon-bar"></span>
      <span class="icon-bar"></span>
      <span class="icon-bar"></span>
    </a>
    <a  class="brand" style="   color: white;font-size: 25px;font-weight: 900;" href="/home">全顺科技</a>
    <div class="btn-group pull-right">
      <a class="btn dropdown-toggle" data-toggle="dropdown" href="#">
        <i class="icon-user"></i><%=UserRequestContextInfo.getVisitorName() %>
        <span class="caret"></span>
      </a>
      <ul class="dropdown-menu">
        <li><a href="/user/logout">退出</a></li>
      </ul>
    </div>
    <div class="nav-collapse">
      <ul class="nav">
        <li class="dropdown">
            <a href="#" class="dropdown-toggle" data-toggle="dropdown"  style="color: white;font-size: 13px;font-weight: 900;">
            	     账户管理
     	          <b class="caret"></b>
            </a>
            <ul class="dropdown-menu">
                <li><a href="/user/account/changePassword">修改密码</a></li>
            </ul>
        </li>      
        <li class="dropdown">
            <a href="#" class="dropdown-toggle" data-toggle="dropdown"  style="color: white;font-size: 13px;font-weight: 900;">
                                                   订餐
                  <b class="caret"></b>
            </a>
            <ul class="dropdown-menu">
                <li><a target="_blank" href="http://192.168.64.102:8080/apply">我要订餐</a></li>
                <li><a target="_blank" href="http://192.168.64.102:8080/searchAllApply">订餐列表</a></li>
            </ul>
        </li>
        <li class="dropdown">
              <a href="#" class="dropdown-toggle" data-toggle="dropdown" style="color: white;font-size: 13px;font-weight: 900;">
                		  今日看板
                    <b class="caret"></b>
              </a>
              <ul class="dropdown-menu">
                <li ><a href="/home">今日看板</a> </li>
      	       </ul>
        </li>        
        <li class="dropdown">
              <a href="#" class="dropdown-toggle" data-toggle="dropdown" style="color: white;font-size: 13px;font-weight: 900;">
                		    每日任务
                    <b class="caret"></b>
              </a>
              <ul class="dropdown-menu">
                <li ><a href="/dailyWork/publish"> 发布任务</a> </li>
                <li ><a href="/dailyWork/dailyWorkListToday"> 任务列表</a></li>
                <li  ><a href="javascript:;" id="weekly">任务周报导出</a></li>
      	       </ul>
        </li>
        <li class="dropdown">
              <a href="#" class="dropdown-toggle" data-toggle="dropdown" style="color: white;font-size: 13px;font-weight: 900;">
                    每日奖惩
                    <b class="caret"></b>
              </a>
              <ul class="dropdown-menu">
                <li><a class="theme-switch-default" href="/rewardPunishmentDetail/addRewardPunishmentDetail">发布奖惩</a></li>
                  <li><a class="theme-switch-amelia" href="/rewardPunishmentDetail/rewardPunishmentDetailListToday">奖惩列表</a></li>
                  <li id="rewardPunishmentStatistics"><a class="theme-switch-amelia" href="/rewardPunishmentStastics/rewardPunishmentStasticsList">人员奖惩统计</a></li>
             	  <li id="rewardPunishmentStatistics"><a class="theme-switch-amelia" href="/rewardPunishmentStastics/rewardPunishmentStasticsByCategoty">奖惩分类统计</a></li>
              </ul>
        </li>
        <li class="dropdown">
              <a href="#" class="dropdown-toggle" data-toggle="dropdown" style="color: white;font-size: 13px;font-weight: 900;">
                  	   轮值
                    <b class="caret"></b>
              </a>
              <ul class="dropdown-menu">
                <li><a class="theme-switch-default" href="/turn/publishTurn">发布轮值</a></li>
                 <li><a class="theme-switch-amelia" href="/turn/turnList">轮值列表</a></li>
              </ul>
        </li>          
	        <li class="dropdown">
              <a href="#" class="dropdown-toggle" data-toggle="dropdown" style="color: white;font-size: 13px;font-weight: 900;">
                     	培训
                    <b class="caret"></b>
              </a>
              <ul class="dropdown-menu">
              <li><a class="theme-switch-default" href="/trainingStatistics/publishTheme">发布培训</a></li>
                <li><a class="theme-switch-default" href="/trainingStatistics/publishTrainingStatistics?type=0">敏捷培训打分</a></li>
                <li><a class="theme-switch-default" href="/trainingStatistics/publishTrainingStatistics?type=1">常规培训打分</a></li>
                 <li><a class="theme-switch-amelia" href="/trainingStatistics/trainingStatisticsList">培训列表</a></li>
              </ul>
        </li> 
        <%if(Boolean.TRUE.equals(UserRequestContextInfo.getIsManager())){%>
 	        <li class="dropdown">
              <a href="#" class="dropdown-toggle" data-toggle="dropdown" style="color: white;font-size: 13px;font-weight: 900;">
                     	系统管理
                    <b class="caret"></b>
              </a>
              <ul class="dropdown-menu">
                <li><a class="theme-switch-default" href="/projectType/projectMaintain">项目维护</a></li>
              </ul>
        </li>        
        <%}%>
        	        <li class="dropdown">
              <a href="#" class="dropdown-toggle" data-toggle="dropdown" style="color: white;font-size: 13px;font-weight: 900;">
                     	 工具集
                    <b class="caret"></b>
              </a>
              <ul class="dropdown-menu">
                <li><a target="_blank" class="theme-switch-default" href="http://192.168.64.225:8060/">git服务器</a></li>
                <li><a target="_blank" class="theme-switch-default" href="http://www.easybug.net/">easyBug</a></li>
                <li><a target="_blank" class="theme-switch-default" href="http://exmail.qq.com/login">公司邮箱</a></li>
				<li><a target="_blank" class="theme-switch-default" href="http://192.168.64.102:8380/">WiKi</a></li>
				<li><a target="_blank" class="theme-switch-default" href="http://192.168.64.225:8082/jenkins">java持续集成服务器</a></li>
				<li><a target="_blank" class="theme-switch-default" href="http://192.168.64.225:9000/">java质量管理平台</a></li>
				<li><a target="_blank" class="theme-switch-default" href="http://183.129.133.147:8082/nexus/index.html#welcome">java maven私服</a></li>
				<li><a target="_blank" class="theme-switch-default" href="http://192.168.64.225:18888/gitstack/">gitstack
			</a></li>
              </ul>
        </li>   
	     <li class="dropdown">
             <a href="#" class="dropdown-toggle" data-toggle="dropdown" style="color: white;font-size: 13px;font-weight: 900;">
                    	会议文档
                   <b class="caret"></b>
             </a>
             <ul class="dropdown-menu">
               <li><a class="theme-switch-default" href="/conferenceDocument/publish">文档上传</a></li>
               <li><a class="theme-switch-default" href="/conferenceDocument/list?type=0">启动会议文档</a></li>
               <li><a class="theme-switch-default" href="/conferenceDocument/list?type=1">迭代会议文档</a></li>
               <li><a class="theme-switch-default" href="/conferenceDocument/list?type=2">年度会议文档</a></li>
             </ul>
       </li>
	     <li class="dropdown">
             <a href="#" class="dropdown-toggle" data-toggle="dropdown" style="color: white;font-size: 13px;font-weight: 900;">
                    	迭代信息统计
                   <b class="caret"></b>
             </a>
             <ul class="dropdown-menu">
                <li><a class="theme-switch-default" href="/iteration/iterationList">迭代管理</a></li>
             </ul>
       </li>            
                   
      </ul>
    </div><!--/.nav-collapse -->
  </div>
  </div>
</div>
<div class="hide" id="weeklyDetail">
<!-- 	<form action="/dailyWork/dailyWorkExportOperator" method="get">
		<input placeholder="起始时间.." type="text" class="search-query span3"
		name="startTime" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})"
		 style="width:30%;" value=""></input>- <input placeholder="截止时间.."
		type="text" class="search-query span3"
		style="position: relative; left: -3px; width:30%;" name="endTime"
		onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})"  value=""></input>
		<button class="btn btn-info" id="daochu">
			导出
		</button>
		</form> -->
</div>
<script src="/Statics/js/jquery-1.8.2.min.js"></script>
<script src="/resources/scripts/artDialog5.0/artDialog.min.js" type="text/javascript"></script>
<script src="<c:url value="/resources/scripts/My97DatePicker/WdatePicker.js"/>" type="text/javascript"></script>
<script type="text/javascript">
$(function(){
	$("#weekly").click(function(){
		$.ajax({
			cache:false,
			type:'GET',
			url:'/dailyWork/dailyWorkExportOperatorFront',
			success:function(data){
				$("#weeklyDetail").html(data);
				art.dialog({
					id:"id",
					lock:true,
					icon:'error',
					content:$("#weeklyDetail").html()
					});	
			},
			error:function(){
				art.dialog({cancel:false,lock:true,icon:'error',time:2000,content:result.responseText});				
			}
		});
	
	});
});
	
</script>