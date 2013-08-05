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
    <title>轮值发布</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">

    <!-- Le styles -->
        <link href="/resources/scripts/artDialog5.0/skins/idialog.css" rel="stylesheet">
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

  <body>
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
      <form id="form" class="form-horizontal" action="/turn/addTurner" method="POST">
        <fieldset>
                    <div class="control-group success">
            <label class="control-label" >轮值类型</label>
            <div class="controls">
             <select id="category" name="category" style="width:40%;" >
             <option>行政轮值</option>
              </select>
            </div>
          </div>
           	<div class="control-group success">
                <label class="control-label" for="selectError">轮值日期</label> 
            <div class="controls" >
              <input value="${todayDate}" type="text" id="workDate" name="workDate" style="width:40%;"   onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" placeholder="轮值日期">
           	</div>
           	</div>
           	<div class="control-group success">
                <label class="control-label" for="selectError">备注</label> 
            <div class="controls" >
			<textarea rows="10" cols="20" style="width:40%;" id="remark" name="remark" placeholder="备注.." ></textarea>
			<span style="color:red;">${msg}</span>
            </div>
          </div>
          <div class="form-actions">
            <a id="submit" type="submit" class="btn btn-primary" style="position:relative;left:60px;">提交</a>
          </div>          		
        </fieldset>
      </form>
    </div>
     <script src="<c:url value="/resources/scripts/My97DatePicker/WdatePicker.js"/>" type="text/javascript"></script>
    <script src="/Statics/js/jquery-1.8.2.min.js"></script>
    <script src="/Statics/js/bootstrap.min.js"></script>
      <script>
      $(function(){
			$("#submit").click(function(){
				for(var i=0;i<$("input[name],select[name]").length;i++){
					if($("input[name],select[name]").eq(i).val()==""){
						if(($("input[name],select[name]").eq(i).attr("type")=="hidden")){
							continue;
						}
					alert($("input[name],select[name]").eq(i).parent().siblings().html()+"不能为空!");
					return false;
					}
				}
				$("#form").submit();
				$("#submit").attr("disabled","disabled");
			});        	
	      });
	   </script>
  </body>
</html>
