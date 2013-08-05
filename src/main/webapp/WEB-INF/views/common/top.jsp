<%@ page language="java"  pageEncoding="UTF-8"%>
<%@ page import="com.qs.permission.core.support.UserRequestContextInfo" %> 
<%
	boolean isLogin = false;
	if(0 < UserRequestContextInfo.getVisitorId()){ 
		
		isLogin = true;
	}
%>
<div class="header">
	<div class="topnav">
    	<p class="f_l"><a href="http://www.tradeserving.com"><span class="bold">全顺科技</span></a>&nbsp;旗下平台</p>
    	<p class="f_r">
    		<% if(isLogin) {%>
    		<a href="/user/account/index">账户管理</a>&nbsp;|&nbsp;
    		<a href="/user/logout">退出</a>
    		<%} %>
    	</p>
    	<p class="f_r"><% if(isLogin) {%>欢迎您回来，<a href="/user/account/index"><%=UserRequestContextInfo.getVisitorName() %></a><%} %>&nbsp;|&nbsp;</p>
    	
    </div>
	<%@include file="/WEB-INF/views/common/mainNav.jsp" %>
</div>

