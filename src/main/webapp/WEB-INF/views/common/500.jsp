<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"
	isErrorPage="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="org.slf4j.Logger,org.slf4j.LoggerFactory"%>
<%@ page import="java.io.PrintWriter"%>
<%@ page import="com.qs.core.exception.AppBaseException"%>
<%@ page import="com.qs.core.exception.AppPermissionException"%>
<%
	Throwable ex = null;
	if (exception != null)
		ex = exception;
	if (request.getAttribute("javax.servlet.error.exception") != null)
		ex = (Throwable) request
				.getAttribute("javax.servlet.error.exception");

	//注意此类处理方式只是对某些js类库有用，比如jquery、extjs等。
	if ("XMLHttpRequest".equals(request.getHeader("X-Requested-With"))) {
		response.setCharacterEncoding("utf-8");
		response.setStatus(404);
		PrintWriter writer = response.getWriter();
		if (ex instanceof AppBaseException || ex instanceof AppPermissionException) {
			writer.println(ex.getMessage());
		} else {
			writer.println("抱歉，发生系统错误，请稍后再试！");
		}
		writer.flush();
		writer.close();
	}
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>500 - 系统内部错误</title>
<%@ include file="/WEB-INF/views/common/header.jsp"%>

</head>

<body>
	<%@ include file="/WEB-INF/views/common/top.jsp"%>
	<div class="container">
	    <div class="error_nav">
	   	  	<div class="error_cont">
		      	<div class="img"><img width="160" height="160" src="<c:url value="/resources/images/500.png"/>"></div>
				<div class="title">
					<h3 class="f_16 bold mt_10 ml_10">对不起，很遗憾系统出错了，以下信息有没有您需要的？</h3>			
				</div>
				<div class="oper f_14">
					<ul>
						<li>去其他地方逛逛：<br><a href="javascript:history.go(-1);">返回上一页</a><ins>|</ins><a href="/business/index">业务大厅</a><ins>|</ins><a href="/business/index">订单中心</a><ins>|</ins><a href="/user/index">账户管理</a></li>
					</ul>
				</div>
			</div>
	    </div>
	</div>
	<%@ include file="/WEB-INF/views/common/footer.jsp"%>
</body>