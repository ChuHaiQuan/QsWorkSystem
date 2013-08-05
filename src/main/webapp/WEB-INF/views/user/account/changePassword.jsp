<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/views/common/taglibs.jsp"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
 <head>
 <style type="text/css">
 </style>
   <meta charset="utf-8"></meta>
   <title>修改密码</title>
   <meta name="viewport" content="width=device-width, initial-scale=1.0"></meta>
   <meta name="description" content=""></meta>
   <meta name="author" content=""></meta>
   <link href="/resources/scripts/artDialog5.0/skins/idialog.css" rel="stylesheet"></link>
   <link href="/Statics/css/bootstrap.min.css" rel="stylesheet"></link>
   <link href="/Statics/css/self.css" rel="stylesheet"></link>
   <link href="/Statics/css/temp.css" rel="stylesheet"></link>
	<link rel="shortcut icon" href="/Statics/img/logo.bmp">
   <style type="text/css">
     body{padding-top: 60px;background-color: white;}
   </style>
   <link href="/Statics/css/bootstrap-responsive.css" rel="stylesheet"></link>
   <link rel="shortcut icon" href="/ico/favicon.ico"></link>
   <%@ include file="/WEB-INF/views/common/jquery-validation-css.jsp"%>
   <link rel="apple-touch-icon-precomposed" sizes="144x144" href="/ico/apple-touch-icon-144-precomposed.png"></link>
   <link rel="apple-touch-icon-precomposed" sizes="114x114" href="/ico/apple-touch-icon-114-precomposed.png"></link>
   <link rel="apple-touch-icon-precomposed" sizes="72x72" href="/ico/apple-touch-icon-72-precomposed.png"></link>
   <link rel="apple-touch-icon-precomposed" href="/ico/apple-touch-icon-57-precomposed.png"></link>
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
      <form id="pwdform" class="form-horizontal"  method="POST">
        <fieldset>
           	<div class="control-group success">
                <label class="control-label" for="selectError">输入当前密码<font color = "red"> &nbsp;* </font ></label> 
            <div class="controls" >
              <input  value="" type="password" onClick="" id="oldpwd" name="oldpwd" style="width:40%;float: left;margin-right: 5px;"   placeholder="输入当前密码..." />
           	</div>
           	</div>
           	<div class="control-group success">
                <label class="control-label" for="selectError">输入新密码<font color = "red"> &nbsp;* </font ></label> 
            <div class="controls" >
              <input value="" type="password" id="newpwd"  name="newpwd" style="width:40%;float: left;margin-right: 5px;"   placeholder="输入新密码..." />
           	</div>
           	</div>
           	<div class="control-group success">
                <label class="control-label" for="selectError">确认密码</label> 
            <div class="controls" >
              <input value="" type="password" id="confirmpwd"  name="confirmpwd" style="width:40%;float: left;margin-right: 5px;"   placeholder="确认密码..." />
            </div>
          </div>
          <div class="form-actions">
            <button id="submit" type="submit" class="btn btn-primary" style="position:relative;left:60px;">确认 修改</button>
          </div>          		
        </fieldset>
      </form>
    </div>
<script src="/Statics/js/jquery-1.8.2.min.js"></script>
<script src="/Statics/js/bootstrap.min.js"></script>
<%@ include file="/WEB-INF/views/common/jquery-validation_ajaxform-js.jsp"%>
<%@ include file="/WEB-INF/views/common/artDialog-js.jsp"%>
<script type="text/javascript" src="/resources/scripts/leftNavi.js"></script>
<script type="text/javascript">
$(function(){
	//提交密码修改的验证
	$("#pwdform").validate({
		rules: { 
			oldpwd:{ 
				required:true
			},
			newpwd:{
				required:true
			},
			confirmpwd:{
				required:true,
				equalTo: "#newpwd"
			}
		},
		messages: {   
			oldpwd:{   
				required: "请输入当前密码"
	        },
	        newpwd:{   
				required: "请输入新密码"
	        },
			confirmpwd:{
				required: "请输入确认密码",
				equalTo: "新密码与确认密码不一致，请重新输入"
			}
	    }
	});
	
	var options = {cache: false,
		url : '/user/doChangePassword',
		success : function() {
			art.dialog({
				cancel: false,
				lock: true,
				icon: 'succeed',
				time: 2000,
			    content: "密码修改成功！"
			});
			setTimeout("location.reload()", 2000);
		},
		error: function(result){
			art.dialog({cancel:false,lock:true,icon:'error',time:2000,content:result.responseText});
		}
	};
	
	$("#pwdform").ajaxForm(options);
	
});
</script>
</body>
</html>