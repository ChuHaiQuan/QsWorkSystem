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
    <title>奖惩发布</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <!-- Le styles -->
 	<link href="/resources/scripts/artDialog5.0/skins/idialog.css" rel="stylesheet">
    <link href="/Statics/css/bootstrap.min.css" rel="stylesheet">
    <link href="/Statics/css/self.css" rel="stylesheet">
    <link href="/Statics/css/temp.css" rel="stylesheet">
    <link rel="shortcut icon" href="/Statics/img/logo.bmp">
    <style type="text/css">
      body{padding-top: 60px;background-color: white;}
      a{cursor: pointer;}
    </style>
    <link href="/Statics/css/bootstrap-responsive.css" rel="stylesheet">
	<%@ include file="/WEB-INF/views/common/jquery-validation-css.jsp"%>
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
      <form id="form" class="form-horizontal" action="/rewardPunishmentDetail/saveRewardPunishmentDetail" method="POST">
     		 <input type="hidden" value="${id}" name="id"/>
        <fieldset>
           	<div class="control-group success">
                <label class="control-label" for="selectError">奖惩缘由</label> 
            <div class="controls" >
 				<select id="pointReason" name="pointTypeId" style="width: 40%;float: left;">
 				<option></option>
 				<c:forEach items="${list}" var="awardPunish">
 				<option value="${awardPunish.id}">${awardPunish.content}</option>
 				</c:forEach>
				</select>           	
            </div>
          </div>
           <div class="control-group success">
                <label class="control-label" for="selectError">数量</label> 
            <div class="controls" >
              <input value="1" type="text" id="number" name="number" style="width:40%;float: left;"   placeholder="数量...">
           	</div>
           	</div>           
            <div class="control-group success">
                <label class="control-label" for="selectError">奖惩日期</label> 
            <div class="controls" >
              <input value="${todayDate}" type="text" id="date" name="date" style="width:40%;float: left;"   onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" placeholder="奖惩日期">
           	</div>
           	</div>  
           	<div class="control-group success" id="relativePersonDiv">
	                <label class="control-label" for="selectError">相关人员</label> 
		            <div class="controls"  id="relativePersonInnerDiv">
		 				<select id="relativePerson" name="relativePerson" style="width: 40%;float: left;height:200px;" multiple="multiple">
		 				<c:forEach items="${userList}" var="user1">
		 				<c:if test="${user1.id!=user.id && user1.status==null}">
		 					<option value="${user1.id}">${user1.name}</option>
		 				</c:if>
		 				</c:forEach>
						</select> 
						<!-- <a name="add" style="margin-left: 10px;">新增</a> --> 
		            </div>
         	 </div>          	
           	<div class="control-group success">
                <label class="control-label" for="selectError">备注</label> 
            <div class="controls" >
			<textarea rows="10" cols="20" style="width:40%;float: left;" name="remark" placeholder="备注.." ></textarea>
            </div>
          </div>         
          <div class="form-actions">
            <a id="submit" class="btn btn-primary" style="position:relative;left:60px;">提交</a>
          </div>          		

        </fieldset>
      </form>
    </div>

<%-- 

<div style="display: none;" id="relativePersonDemo">
	<div class="control-group success"  >
       <div class="controls" >
			<select  id="relativePerson" name="relativePerson" style="width: 40%;border-color: #468847;float: left;" >
				<option></option>
				<c:forEach items="${userList}" var="user1">
					<c:if test="${user1.id!=user.id}">
					<option value="${user1.id}">${user1.name}</option>
					</c:if>
				</c:forEach>
			</select> 
			<a name="del" style="margin-left: 10px;">删除</a>
		</div>
	</div>	
</div> 

--%>

	<script src="/Statics/js/jquery-1.8.2.min.js"></script>
	<script  src="<c:url value="/resources/scripts/My97DatePicker/WdatePicker.js"/>" type="text/javascript"></script>
	<%@ include file="/WEB-INF/views/common/jquery-validation_ajaxform-js.jsp"%>
	<script src="/Statics/js/bootstrap.min.js"></script>
	<script>
		$(function() {
			$("#form").validate({
				rules: { 
					pointTypeId:{ 
						required:true
					},
					date:{
						required:true
					},
					number:{
						required:true
					},
				},
				messages: {   
					pointTypeId:{   
						required: "奖惩缘由不能为空!"
			        },
			        date:{   
						required: "日期不能为空!"
			        },
			        number:{
						required: "数量不能为空!",
					}
			    }
			});
			$(document).undelegate("a[name=del]","click");
			$(document).delegate("a[name=del]","click",function(){
				$(this).parent().parent().empty();
			});
			$(document).undelegate("a[name=add]","click");
			$(document).delegate("a[name=add]","click",function(){
				$("#relativePersonDiv").after($("#relativePersonDemo").html());
			});
			var options = {
					cache: false,
					beforeSubmit : function() {

					},
					success : function(data) {
						alert("成功!");
					 if(window.opener!=null){
					window.opener.location.reload();
					window.close();
					 }else{
						 location.href = "/rewardPunishmentDetail/rewardPunishmentDetailListToday";	 
					 }
				},
				error:function(result) {
					alert(result.responseText);
					$("#submit").removeAttr("disabled");
				}
			};
			$("#form").ajaxForm(options);
 			$("#submit").click(function(){
 				$("#form").submit();
 			}); 
			
		});
	</script>
  </body>
</html>
