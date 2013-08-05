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
    <title></title>
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
      <form id="form" class="form-horizontal" action="/iteration/doAddOrUpdate" method="POST">
            <input type="hidden" value="${iteration.id}" name="id"/>
        <fieldset>
           	<div class="control-group success">
                <label class="control-label" for="selectError">开始日期</label> 
            <div class="controls" >
              <input value="${iteration.startTime}" type="text" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" id="satrtTime" name="startTime" style="width:40%;"   placeholder="开始日期..">
           	</div>
           	</div>
           	<div class="control-group success">
                <label class="control-label" for="selectError">截止日期</label> 
            <div class="controls" >
              <input value="${iteration.endTime}" type="text" id="endTime" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" name="endTime" style="width:40%;"   placeholder="截止日期..">
           	</div>
           	</div>
           	<div class="control-group success">
                <label class="control-label" for="selectError">主题内容</label> 
            <div class="controls" >
			<textarea rows="10" cols="20" style="width:40%;" id="theme" name="theme" placeholder="主题内容.." >${iteration.theme}</textarea>
            </div>
          </div>
          <div class="form-actions">
            <button id="submit" type="submit" class="btn btn-primary" style="position:relative;left:60px;">提交</button>
          </div>          		
        </fieldset>
      </form>
    </div>
     <script src="<c:url value="/resources/script/My97DatePicker/WdatePicker.js"/>" type="text/javascript"></script>
    <script src="/Statics/js/jquery-1.8.2.min.js"></script>
    <script src="/Statics/js/bootstrap.min.js"></script>
        <script>
        $(function(){
			$("#submit").click(function(){
				for(var i=0;i<$("input[name],select[name]").length;i++){
					if($("input[name],select[name]").eq(i).val()==""){
						if(($("input[name],select[name]").eq(i).attr("type")=="hidden" ||
								$("input[name],select[name]").eq(i).attr("name")=="administrtor")){
							continue;
						}
					alert($("input[name],select[name]").eq(i).parent().siblings().html()+"不能为空!");
					return false;
					}
				}
				$("#form").submit();
			});        	
        });
	    </script>
  </body>
</html>
