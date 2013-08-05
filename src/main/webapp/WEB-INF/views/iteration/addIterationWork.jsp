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
      <form id="form" class="form-horizontal" action="/iterationWork/saveOrUpdate" method="POST">
      <input type="hidden" value="${id}" name="iterationWorkid" id="id"/>
      <input type="hidden" value="${iterationId}" name="iterationId" id="iterationId"/>
        <fieldset>
            <div class="control-group success">
	             <label class="control-label">所属项目</label> 
	            <div class="controls" >
	 				<select id="projectId" name="projectId" style="width: 40%;">
						<c:forEach items="${projectList}" var="project">
							<c:choose>
								<c:when test="${id!=null && iterationWorkList.projectType.typeName==project.typeName}">
								<option value="${project.id}" selected="selected">${project.typeName}</option>
								</c:when>
								<c:otherwise>
									<option value="${project.id}">${project.typeName}</option>
								</c:otherwise>
							</c:choose>
						</c:forEach>
					</select>           	
	            </div>
          </div>
          
           <div class="control-group success">
                <label class="control-label" for="selectError">任务描述</label> 
	            <div class="controls" >
					<textarea rows="10" cols="20" style="width:40%;" name="workDescription" placeholder="对任务做个描述吧.."  >${iterationWorkList.workDescription}</textarea>
	            </div>
          </div>
          
           	<div class="control-group success" id="relativePersonDiv">
	                <label class="control-label" for="selectError">相关人员</label> 
		            <div class="controls"  id="relativePersonInnerDiv">
		 				<select id="relativePerson" name="relativePerson" style="width: 40%;float: left;height: 200px;margin-bottom: 10px;"" multiple="multiple">
		 				<c:forEach items="${userList}" var="user">
		 				<c:if test="${user.status==null}">
	 						<c:choose>
			 						<c:when test="${fn:contains(iterationWorkList.names,user.name)}">
			 								<option value="${user.id}" selected="selected">${user.name}</option>
			 						</c:when>
			 						<c:otherwise>
			 							<option value="${user.id}">${user.name}</option>
			 						</c:otherwise>
		 					</c:choose>		
		 				</c:if>	 					
		 				</c:forEach>
						</select> 
		            </div>
         	 </div>    
       	         
            <div class="control-group success">
                <label class="control-label" for="selectError">预计时间</label> 
	            <div class="controls" >
	            	<c:choose>
	            		<c:when test="${id!=null }">
	    		         	 <input type="text" value="${iterationWorkList.planCostDays}" id="planCostDays" name="planCostDays" style="width:40%;"    /><span style="padding-left: 5px;font-size: 14px;font-weight: 700;">天</span>
	            		</c:when>
	            		<c:otherwise>
	    		        	  <input type="text" value="30" id="planCostDays" name="planCostDays" style="width:40%;"    /><span style="padding-left: 5px;font-size: 14px;font-weight: 700;">天</span>
	            		</c:otherwise>
	            	</c:choose>
	            </div>
           	</div>  
           	
           <div class="control-group success">
                <label class="control-label" for="selectError">备注</label> 
	            <div class="controls" >
					<textarea rows="10" cols="20" style="width:40%;" name="remark" placeholder="备注.."  >${iterationWorkList.remark}</textarea>
	            </div>
          </div>           	        
          <div class="form-actions">
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
							location.href = "/iterationWorkList/list?id="+$("#iterationId").val();	
				},
				error : function(result) {
					alert(result.responseText);
				}
			};
			$("#form").ajaxForm(options);
			$("#submitTijiao")
					.click(
							function() {
								var result = doCheck();
								if (result != false) {
									$("#form").submit();
								};
							});
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
