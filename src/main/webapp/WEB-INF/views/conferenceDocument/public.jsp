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
    <title>主题发布</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">

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
      <form id="form" class="form-horizontal" action="/conferenceDocument/save" method="POST" enctype="multipart/form-data" >
        <fieldset>
          <div class="control-group success">
            <label class="control-label" >文档类型</label>
            <div class="controls">
             <select id="trainingType" name="type" style="width:40%;" >
              	<option></option>
                <option value="0">启动会议</option>              	
              	<option value="1">迭代会议</option>
              	<option value="2">年度会议</option>
              </select>
            </div>
          </div>  
             <div class="control-group success">
                <label class="control-label" for="selectError">文档上传</label> 
	            <div class="controls" >
	              <input  type="file" id="file" name="files" style="width:40%;"/>
	           	</div>
           	</div>          	
          <div class="form-actions">
            <button id="submit" type="button" class="btn btn-primary" style="position:relative;left:60px;">提交</button>
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
						$("#submit").attr("disabled","disabled");
					},
					success : function(data) {
						alert("操作成功!");
						location.href="/conferenceDocument/list";
					},
					error : function(result) {
					alert(result.responseText);
					}
				};
			$("#form").ajaxForm(options);
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
			});

        });
	    </script>
  </body>
</html>
