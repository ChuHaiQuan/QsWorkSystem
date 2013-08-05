<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html lang="en">
  <head>
  <style type="text/css">
  </style>
    <meta charset="utf-8">
    <title>任务发布</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">

    <!-- Le styles -->
        <link href="/resources/scripts/artDialog5.0/skins/idialog.css" rel="stylesheet">
    <link href="/Statics/css/bootstrap.min.css" rel="stylesheet">
    <link href="/Statics/css/self.css" rel="stylesheet">
    <link href="/Statics/css/temp.css" rel="stylesheet">

    <style type="text/css">
      body{padding-top: 60px;
      		background-color: white;
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
  <div class="navbar navbar-fixed-top hide">
  <div class="navbar-inner" style=" background-color: #20AAD8;
    background-image: linear-gradient(to bottom, #20AAD8, #20AAD8);">
    <div class="container">

    </div>
  </div>
</div>
         <div class="span8 offset3">
<!--               <p style="position:relative;left:181px;"><span style="color:red;">温馨提示:&nbsp;</span>中餐请于当日10点之前点餐</p> -->
      <form id="form" class="form-horizontal" action="/dailyWork/addNewWork" method="POST">
      <input type="hidden" value="${id}" name="id" id="id"/>
        <fieldset>
            <div class="control-group success">
             <label class="control-label" >任务名称</label> 
            <div class="controls" >
              <input type="text" id="taskName" name="taskName" style="width:40%;" 
              placeholder="任务名称。。" value="${dailyWork.taskName}" <c:if test="${id!=null}">readonly="readonly"</c:if> />
            </div>
          </div> 
            	<div class="control-group success">
                <label class="control-label" for="selectError">日期</label> 
            <div class="controls" >
              <input type="text" value="${todayDate}${dailyWork.publishTime}" id="publishTime" name="publishTime" style="width:40%;"    placeholder="发布日期"
               <c:if test="${id==null}">onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" </c:if>
               <c:if test="${id!=null}"> readonly="readonly"</c:if> />
           	</div>
           	</div>           
          <div class="control-group success">
            <label class="control-label" >任务类型</label>
            <div class="controls">
            <c:choose>
            <c:when test="${id!=null}">
            <input type="text" id="taskType" value="${dailyWork.taskType}"  name="taskType" readonly="readonly" style="width: 40%;" />
            </c:when>
            <c:otherwise>
             <select id="taskType" name="taskType" style="width:40%;" >
              	<option>计划任务</option>
				<option>计划外任务</option>
				<option>备选任务</option>
              </select>
            </c:otherwise>
            </c:choose>
            </div>
          </div>          
            <div class="control-group success">
             <label class="control-label" >所属项目</label> 
            <div class="controls" >
            <c:choose>
            	<c:when test="${id!=null}">
 				<select id="dependencyId" name="dependencyId" style="width: 40%;" >
					<option value="${dailyWork.dependencyProject.id}" selected="selected">${dailyWork.dependencyProject.typeName}</option>
				</select>  
            	</c:when>
            <c:otherwise>
 				<select id="dependencyId" name="dependencyId" style="width: 40%;">
 					<option></option>
					<c:forEach items="${list}" var="project">
					<option value="${project.id}">${project.typeName}</option>
					</c:forEach>
				</select>           	
            </c:otherwise>
            </c:choose>            
            </div>
          </div>                    
           	<div class="control-group success">
                <label class="control-label" for="selectError">计划需要时间</label> 
            <div class="controls" >
            <c:choose>
            	<c:when test="${id!=null}">
            		<input id="planCostTime" type="text" value="${dailyWork.planCostTime}"  name="planCostTime" readonly="readonly" style="width: 40%;" />
            	</c:when>
            <c:otherwise>
 				<select id="planCostTime" name="planCostTime" style="width: 40%;" type="select">
 					<option></option>
					<option>0.5</option>
					<option>1.0</option>
					<option>1.5</option>
					<option>2.0</option>
					<option>2.5</option>					
					<option>3.0</option>
					<option>3.5</option>
					<option>4.0</option>
					<option>4.5</option>
					<option>5.0</option>
					<option>5.5</option>
					<option>6.0</option>
					<option>6.5</option>
					<option>7.0</option>
					<option>7.5</option>
					<option>8.0</option>
				</select>           	
            </c:otherwise>
            </c:choose>
            </div>
          </div>
           	<div class="control-group success">
            <c:if test="${id!=null && dailyWork.realCostTime==null}">
               <label class="control-label">实际消费时间时间</label> 
            <div class="controls" >
 				<select id="realCostTime" name="realCostTime" style="width: 40%;" type="select">
 				<option></option>
 					<option>0</option>
					<option>0.5</option>
					<option>1.0</option>
					<option>1.5</option>
					<option>2.0</option>
					<option>2.5</option>					
					<option>3.0</option>
					<option>3.5</option>
					<option>4.0</option>
					<option>4.5</option>
					<option>5.0</option>
					<option>5.5</option>
					<option>6.0</option>
					<option>6.5</option>
					<option>7.0</option>
					<option>7.5</option>
					<option>8.0</option>
				</select>  
				</div>           
            </c:if>
            <c:if test="${id!=null && dailyWork.realCostTime!=null}">
             <label class="control-label">实际消费时间时间</label> 
            <div class="controls" >
              <input type="text" id="realCostTime" name="realCostTime" style="width:40%;"  value="${dailyWork.realCostTime }" readonly="readonly">
            </div>
            </c:if>
          </div>          
           	<div class="control-group success">
                <label class="control-label" for="selectError">任务描述</label> 
            <div class="controls" >
			<textarea rows="10" cols="20" style="width:40%;" name="taskDescribe" placeholder="对任务做个描述吧.."  >${dailyWork.taskDescribe }</textarea>
            </div>
          </div>
          
           	<div class="control-group success">
            <c:if test="${id!=null && dailyWork.completeStatus==1}">
             <label class="control-label" for="selectError">状态</label> 
            <div class="controls" >
 				<select id="completeStatus" name="completeStatus" style="width: 40%;">
					<option value="2">完成</option>
					<option value="3">未完成</option>
					<option value="4">取消</option>
				</select>   
				</div>          
            </c:if>  
           <c:if test="${id!=null && dailyWork.completeStatus!=null && dailyWork.completeStatus!=1}">
              <label class="control-label" for="selectError">状态</label> 
            <div class="controls" >          
           <input type="text" id="completeStatus" name="completeStatus" style="width:40%;"  value="${dailyWork.completeStatus }" readonly="readonly">
          </div>
           </c:if>                      
          </div>
          <div class="form-actions">
          	<c:if test="${id==null}">
          	<a   id="submitCaoGao" type="button" class="btn btn-primary" style="position:relative;left:60px;" >存为草稿</a>
          	</c:if>
            <a  id="submitTijiao" type="button" class="btn btn-inverse" style="position:relative;left:100px;" >立即提交</a>         	
          </div>          		

        </fieldset>
      </form>
    </div>

	<script src="/Statics/js/jquery-1.8.2.min.js"></script>
	<script
		src="<c:url value="/resources/scripts/My97DatePicker/WdatePicker.js"/>"
		type="text/javascript"></script>
	<script
		src="<c:url value="/resources/scripts/jquery-ajaxForm/jquery.form.js"/>"
		type="text/javascript"></script>		
	<script src="/Statics/js/bootstrap.min.js"></script>
	<script>
		$(function(){
			var options = {
					cache: false,
					beforeSubmit : function() {

					},
					success : function(data) {
						alert("成功!");
						if($("#id").val()=="" || $("#id").val()==null){
							 if(window.opener!=null){
							window.opener.location.reload();
							window.close();
						}else{
							location.href = "/dailyWork/dailyWorkList";	
						}
					} else {
						window.opener.location.reload();
						window.close();
					}
				},
				error : function(result) {
					alert(result.responseText);
				}
			};
			$("#form").ajaxForm(options);
			$("#submitCaoGao").click(function() {
				var result = doCheck();
				if (result != false) {
					var action = $("#form").attr("action");
					$("#form").attr("action", action + "?completeStatus=0");
					$("#form").submit();
					$("#submitCaoGao,#submitCaoGao").attr("disabled","disabled");
				}
			});
			$("#submitTijiao")
					.click(
							function() {
								var result = doCheck();
								if (result != false) {
									var action = $("#form").attr("action");
									if ($("#id").val() == ""
											|| $("#id").val() == null) {
										$("#form").attr("action",
												action + "?completeStatus=1");
									}
									$("#form").submit();
									$("#submitCaoGao,#submitCaoGao").attr("disabled","disabled");
								}
							});
			var planCostTime = $("#planCostTime").val();
			if ($("#realCostTime").attr("type") != "text") {
				for ( var i = 0; i < $("#realCostTime").children("option")
						.size(); i++) {
					if ($("#realCostTime").children("option").eq(i).html() == planCostTime) {
						$("#realCostTime").children("option").eq(i).attr(
								"selected", "selected");
						return;
					}
				}
			}
			function doCheck() {
				for ( var i = 0; i < $("input[name],select[name]").length; i++) {
					if ($("input[name],select[name]").eq(i).val() == "") {
						if (($("input[name],select[name]").eq(i).attr("type") == "hidden")
								|| ($("input[name],select[name]").eq(i).attr(
										"name") == "realCostTime" || ($(
										"input[name],select[name]").eq(i).attr(
										"name") == "completeStatus"))) {
							continue;
						}
						alert($("input[name],select[name]").eq(i).parent()
								.siblings().html()
								+ "不能为空!");
						return false;
					}
				}
				return true;
			}
		});
	</script>
</body>
</html>
