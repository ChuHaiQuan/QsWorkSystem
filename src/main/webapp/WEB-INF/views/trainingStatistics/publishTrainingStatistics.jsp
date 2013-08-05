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
    <title>培训打分</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">

    <!-- Le styles -->
        <link href="/resources/script/artDialog5.0/skins/idialog.css" rel="stylesheet">
    <link href="/Statics/css/bootstrap.min.css" rel="stylesheet">
    <link href="/Statics/css/self.css" rel="stylesheet">
    <link href="/Statics/css/temp.css" rel="stylesheet">
	<link rel="shortcut icon" href="/Statics/img/logo.bmp">
    <style type="text/css">
      body{padding-top: 60px;
      		background-color: white;
      }
    </style>
    <link href="/Statics/css/bootstrap-responsive.css" rel="stylesheet">

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
      <form id="form" class="form-horizontal" action="/trainingStatistics/saveTrainingStatistics" method="POST"enctype="multipart/form-data" >
        <fieldset>
        <input type="hidden" name="status" value="${trainingStatistics.trainingType}"></input>
         <input type="hidden" name="id" value="${trainingStatistics.id}"></input>
          <div class="control-group success">
            <label class="control-label" >演讲人</label>
            <div class="controls">
			<input value="${trainingStatistics.userName}" type="text" id="name" name="name" style="width:40%;"    placeholder="姓名.." readonly="readonly" />
            </div>
          </div>
             	<div class="control-group success">
                <label class="control-label" for="selectError">培训主题</label> 
            <div class="controls" >
              <input value="${trainingStatistics.trainingTheme}" type="text" id="trainingTheme" name="trainingTheme" style="width:40%;"    placeholder="培训主题" readonly="readonly" />
           	</div>
           	</div>
          <div class="control-group success">
            <label class="control-label" >主题类型</label>
            <div class="controls">
              <select   id="trainingType" name="trainingType" style="width:40%;" >
              <c:choose>
              	<c:when test="${trainingStatistics.trainingType=='0'}">
              	  <option value="0" selected="selected">敏捷培训</option>
              	</c:when>
              	<c:otherwise><option value="1" selected="selected">常规培训</option></c:otherwise>
              </c:choose>
            
              </select>
            </div>
          </div>  
             <div class="control-group success">
                <label class="control-label" for="selectError">演讲日期</label> 
            <div class="controls" >
              <input value="${trainingStatistics.date}" type="text" id="date" name="date" style="width:40%;"   readonly="readonly" />
           	</div>
           	</div>
           	<c:if test="${status!=null}">
            	<div class="control-group success" id="relativePersonDiv">
	                <label class="control-label" for="selectError">参与人员</label> 
		            <div class="controls"  id="relativePersonInnerDiv">
		 				<select id="relativePerson" name="relativePerson" style="width: 40%;float: left;height:200px;" multiple="multiple">
		 				<c:forEach items="${userList}" var="user1">
		 				<c:if test="${user1.id!=user.id && user1.status==null}">
		 					<option value="${user1.id}">${user1.name}</option>
		 				</c:if>
		 				</c:forEach>
						</select> 
		            </div>
         	 </div>          	
           	</c:if>
             <div class="control-group success">
                <label class="control-label" for="selectError">打分</label> 
            <div class="controls" >
              <input type="text" id="point" name="point" style="width:40%;"    placeholder="打分..">
           	</div>
           	</div> 
               	<div class="control-group success">
                <label class="control-label" for="selectError">评论</label> 
            <div class="controls" >
			<textarea rows="10" cols="20" style="width:40%;" name="comment" placeholder="评论" ></textarea>
             <span style="display: none;" id="msg">${msg}</span>
            </div>
          </div>               	            	               	         
          <div class="form-actions">
            <a id="submit" type="button" class="btn btn-primary" style="position:relative;left:60px;">提交</a>
          </div>          		

        </fieldset>
      </form>
    </div>

	<script src="/Statics/js/jquery-1.8.2.min.js"></script>
	<script
		src="<c:url value="/resources/script/My97DatePicker/WdatePicker.js"/>"
		type="text/javascript"></script>
	<script
		src="<c:url value="/resources/scripts/jquery-ajaxForm/jquery.form.js"/>"
		type="text/javascript"></script>
	<script src="/Statics/js/bootstrap.min.js"></script>
	<script>
		$(function() {
			if ($("#msg").html() != "" && $("#msg").html() != undefined) {
				alert($("#msg").html());
			}
			var options = {
					cache: false,
					beforeSubmit : function() {
					},
					success : function(data) {
						if(data=='1'){
							alert("今天培训的参与人数还未录入,请轮值人员及时录入!");
							return false;
						}else if(data=='2'){
							alert("请不要重复打分!");
							location.href="/trainingStatistics/trainingStatisticsList";
						}else{
							alert("操作成功!");
							location.href="/trainingStatistics/trainingStatisticsList";
						}
					},
					error : function(result) {
					alert(result.responseText);
					}
				};
			$("#form").ajaxForm(options);
			$("#submit")
					.click(
							function() {
								for ( var i = 0; i < $("input[name],select[name],textarea[name]").length; i++) {
									if ($("input[name],select[name],textarea[name]").eq(i)
											.val() == "" ) {
										if (($("input[name],select[name],textarea[name]")
												.eq(i).attr("type") == "hidden")) {
											continue;
										}
										if ($("input[name],select[name],textarea[name]").eq(i)
												.parent().siblings().html() == "演讲人") {
											alert("现在不是打分时间!");
										} else {
											alert($("input[name],select[name],textarea[name]")
													.eq(i).parent().siblings()
													.html()
													+ "不能为空!");
										}
										return false;
									}
								}
								$("#form").submit();
							});

		});
	</script>
  </body>
</html>
