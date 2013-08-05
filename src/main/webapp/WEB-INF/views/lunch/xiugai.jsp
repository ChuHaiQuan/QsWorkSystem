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
    <title>修改界面</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">

    <!-- Le styles -->
        <link href="/resources/script/artDialog5.0/skins/idialog.css" rel="stylesheet">
    <link href="/Statics/css/bootstrap.min.css" rel="stylesheet">
    <link href="/Statics/css/self.css" rel="stylesheet">
    <link href="/Statics/css/temp.css" rel="stylesheet">

    <style type="text/css">
      body{padding-top: 60px;}
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
         <div class="span8 offset3">
      <form class="form-horizontal" action="/doSubmitMyXiuGai" method="post">
        <fieldset>
         <div class="control-group success">
            <label class="control-label">姓名</label>
            <div class="controls">
              <span class="input-xlarge uneditable-input" style="width:209px;">${lunch.userName}</span>
              
              <input type="hidden" value="${lunch.userName}" name="userName"></input>
              <input type="hidden" value="${lunch.lunchName}" id="lunchName"></input>
              <input type="hidden" value="${lunch.id}" name="id">
              <input type="hidden" value="${lunch.dateTom}" name="dateTom">
              
            </div>
          </div>
        <div class="control-group success">
            <label class="control-label" for="selectError">餐名选择</label>
            <div class="controls">
              <select  name="lunchName"  id="selectLunchName">
              <option></option>
              <c:forEach items="${page.content}" var="menuList">
                <option>${menuList.menuName}</option>
            </c:forEach>
            </select>
            </div>
          </div>
          <div class="control-group success">
            <label class="control-label">配汤</label>
            <div class="controls">
              <span class="input-xlarge uneditable-input" style="width:209px;">${page.content[0].peiTang}</span>
              <input type="hidden" value="${page.content[0].peiTang}" name="peiTang"/>
              </div>
              </div>
            <div class="control-group success">
            <label class="control-label" for="selectError">就餐时间段</label>
            <div class="controls">
              <select id="selectError" name="eatTime" >
              	<option>11:20-11:50</option>
              	<option>17:00-18:00</option>
              </select>
            </div>
          </div>
           <div class="control-group success">
            <label class="control-label" for="inputSuccess">备注</label>
            <div class="controls" >
              <input type="text" id="beizhu" name="beiZhu" value="${lunch.beiZhu}">
            </div>
          </div>
          <div class="form-actions">
            <button type="submit" class="btn btn-primary" style="position:relative;top:1px;left:15px;">提交</button>
          </div>
             <div style="position:relative;top:-49px;left:304px;">
            <a class="btn btn-success " href="/" >回到首页</a> &nbsp;&nbsp;<span style="color: red;">${message}</span>
          </div>
          
          
        </fieldset>
      </form>
    </div>

        <script src="/Statics/js/jquery-1.8.2.min.js"></script>
    <script src="/Statics/js/bootstrap.min.js"></script>
    <script>
    $(function(){
    	 var valueLunchName=$("#lunchName").val();
    	 for(var i=0;$("#selectLunchName").children("option").size();i++){
    		 if($("#selectLunchName").children("option").eq(i).html()==valueLunchName){
    			 $("#selectLunchName").children("option").eq(i).attr("selected","selected");
    			 return;
    		 }
    	 }
    	  
    });
    </script>
  </body>
</html>
