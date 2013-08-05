<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/views/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="shortcut icon" href="/Statics/img/logo.bmp">
<title>登录</title>
<%@ include file="/WEB-INF/views/common/headerLogin.jsp"%>
<%@ include file="/WEB-INF/views/common/jquery-validation-css.jsp"%>
<style type="text/css">
.loginError {
	padding-left: 5px;
}

#resendEmailP img {
	display: none
}

#resendEmailP span {
	display: none
}
</style>
</head>

<body>
	<%@ include file="/WEB-INF/views/common/top.jsp"%>
	<div class="container">
		<div class="login_tit">
			<span class="f_14 bold color51A">用户登录</span>
		</div>
		<div class="login_cont">
			<div class="left">

				<form id="loginForm" method="post"
					action="/user/doLogin?callback=/home">
					<div class="ipt_box">
						<span>账户名</span><input type="text" class="ipt w_240"
							name="loginId" id="loginId" value="<c:if test="${isLogin==true}"><%=UserRequestContextInfo.getVisitorAccountName() %></c:if>"
							style="color: gray" />
					</div>
					<c:if test="${isEmailVerify == 'false'}">
						<div class="tips" style="left: 80px; top: -36px;">
							<div class="tips_nav" style="width: 240px">
								<p>
									<img src="/resources/images/login_erroricon.gif" width="15"
										height="15" />&nbsp;<span class="bold color51A">帐号还未激活</span>
								</p>
								<p id="resendEmailP">
									请到验证邮件中激活，或者<a onclick="resendEmail('${email}')">重发验证邮件</a><span
										id="resendEmailSpan">重发验证邮件</span>&nbsp;<img
										src="/resources/images/loading.gif" />
								</p>
							</div>
							<div class="tips_arrow"></div>
						</div>
					</c:if>
					<div class="ipt_box">
						<span>密 码</span><input class="ipt w_240" name="password"
							type="password" id="password" value="<c:if test="${isLogin==true}"><%=UserRequestContextInfo.getVisitorPassWord() %></c:if>" />
					</div>
					<div class="ipt_box">
						<span></span> <strong class="loginError">${message}${errorMsg}</strong>
					</div>
					<div class="ipt_box submitWrapper">
					<input type="hidden"  name="retainTime" id="retainTime"/>
						<input type="checkbox" id="knowedMe" /> 记住我
						一周&nbsp;&nbsp;&nbsp;&nbsp; <input type="submit"
							class="button btn_log" value="登录" /> <input type="hidden"
							name="retainTime" id="retainTime" /> 
					</div>
				</form>
			</div>
		</div>
	</div>
	<%@ include file="/WEB-INF/views/common/footer.jsp"%>
	<%@ include file="/WEB-INF/views/common/jquery-validation_ajaxform-js.jsp"%>
	<script type="text/javascript">
		$(function() {
			$("#knowedMe").click(function() {
				var value = $(this).attr("checked");
				if (value == "checked") {
					$("#retainTime").val("604800");
				} else {
					$("#retainTime").val("");
				}
			});
		});
		//重新发送验证邮件--start
		function camp() {
			$("#resendEmailP img").hide();
			$("#resendEmailP a").show();
			$("#resendEmailP span").hide();
		}
		function resendEmail(email) {
			$("#resendEmailP img").show();
			$("#resendEmailP a").hide();
			$("#resendEmailP span").show();
			$.ajax({
				cache : false,
				type : "get",
				dataType : "json",
				url : '/user/resendActivationEmail',
				data : ({
					mailAddress : email
				}),
				success : function(data) {
					setTimeout("camp()", 1000);
				},
				error : function(result) {
					alert(result.responseText);
				}
			});
		}
		//重新发送验证邮件--end

		$(function() {
			//提交密码修改的验证
			$("#loginForm").validate({
				rules : {
					loginId : {
						required : true
					},
					password : {
						required : true
					}
				},
				messages : {
					loginId : {
						required : "请输入登录账号"
					},
					password : {
						required : "请输入登录密码"
					}
				}
			});

			var loginId = $("#loginId");
			var tip = loginId.val();
			loginId.focus(function() {
				if (loginId.val() == tip) {
					loginId.val("");
				}
			});
			loginId.blur(function() {
				if (loginId.val() == "") {
					loginId.val(tip);
				}
			});
		});
	</script>
</body>
</html>