<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.io.PrintWriter"%>
<%@ page import="org.slf4j.Logger,org.slf4j.LoggerFactory"%>
<%
	
	//记录日志
	Logger logger = LoggerFactory.getLogger("404.jsp");
	//需要结合tomcat等web服务器的access日志来进一步查看详细异常信息
	logger.error("出现404异常!");
	//注意此类处理方式只是对某些js类库有用，比如jquery、extjs等。
	if ("XMLHttpRequest".equals(request.getHeader("X-Requested-With"))) {
		response.setCharacterEncoding("utf-8");
		response.setStatus(404);
		PrintWriter writer = response.getWriter();
		writer.println("抱歉，您请求的资源不存在，请检查请求的链接是否正确！");
		writer.flush();
		writer.close();
	}
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>404 - 页面不存在</title>
<%@ include file="/WEB-INF/views/common/header.jsp"%>

</head>

<body>
	<%@ include file="/WEB-INF/views/common/top.jsp"%>
	<div class="container">
	    <div class="error_nav">
	   	  	<div class="error_cont">
		      	<div class="img"><img width="160" height="160" src="<c:url value="/resources/images/404.png"/>"></div>
				<div class="title">
					<h3 class="f_16 bold mt_10 ml_10">对不起，您浏览的页面不存在，以下信息有没有您需要的？</h3>			
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
</html>