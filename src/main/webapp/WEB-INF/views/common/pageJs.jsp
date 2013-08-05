<%@ page pageEncoding="utf-8"%>
<%
	String ajaxUrl = request.getParameter("ajaxUrl");
	String targetId = request.getParameter("targetId");
	String isJumpByJs = request.getParameter("isJumpByJs");
%>
<%if("true".equals(isJumpByJs)){%>
<script type="text/javascript">
	$(function(){
		if($("#pageIndex").val() >= ($("#totalPage").val()-1)){
			$("#pageInput").attr("value", $("#totalPage").val());
		}
		var href = "<%=ajaxUrl%>";
		var url = href;
		var targetId = "<%=targetId%>";
		var isJumpByJs = "<%=isJumpByJs%>";
		$(document).undelegate(".toPage","click");
		$(document).delegate(".toPage", "click", function() {
			var reg = new RegExp("pageIndex=\-*\\d+","g");
			var reg2 = new RegExp("\\?","g");
			var pageIndex = $(this).attr("pageIndex");
			if(reg.test(href)){
				href = href.replace(reg, "pageIndex=" + pageIndex);
			}else if(reg2.test(href)){
				href = href + "&pageIndex=" + pageIndex;
			}else{
				href = href + "?pageIndex=" + pageIndex;
			}
			$.ajax({
				cache : false,
				type : 'GET',
				url : href,
				data : {
					ajaxUrl : url,
					targetId : targetId,
					isJumpByJs : isJumpByJs
				},
				success : function(data) {
					$("#"+targetId).html(data);
				}
			});
		});
		$(document).undelegate(".sure","click");
		$(document).delegate(".sure", "click", function() {
			var reg = new RegExp("pageIndex=\-*\\d+","g");
			var reg2 = new RegExp("\\?","g");
			var reg3 = new RegExp("[0-9]+","g");
			if(!reg3.test(1*$("#pageInput").val())){
				alert("跳转页码非法");
				return false;
			}
			var pageIndex = 1*$("#pageInput").val()-1;
			if(reg.test(href)){
				href = href.replace(reg, "pageIndex=" + pageIndex);
			}else if(reg2.test(href)){
				href = href + "&pageIndex=" + pageIndex;
			}else{
				href = href + "?pageIndex=" + pageIndex;
			}
			$.ajax({
				cache : false,
				type : 'GET',
				url : href,
				data : {
					ajaxUrl : url,
					targetId : targetId,
					isJumpByJs : isJumpByJs
				},
				success : function(data) {
					$("#"+targetId).html(data);
				}
			});
		});
	});
</script>
<%}else{%>
<script type="text/javascript">
	$(function(){
		if($("#pageIndex").val() < ($("#totalPage").val()-1)){
			$("#pageInput").val(1*$("#pageIndex").val() + 2);
		}else{
			$("#pageInput").val($("#totalPage").val());
		}
		$(".toPage").click(function(){
			var reg = new RegExp("pageIndex=\-*\\d+","g");
			var reg2 = new RegExp("\\?","g");
			var href = location.href;
			var pageIndex = $(this).attr("pageIndex");
			if(reg.test(href)){
				location.href = href.replace(reg, "pageIndex=" + pageIndex);
			}else if(reg2.test(href)){
				location.href = href + "&pageIndex=" + pageIndex;
			}else{
				location.href = href + "?pageIndex=" + pageIndex;
			}
		});
		$("#sure").click(function(){
			var reg = new RegExp("pageIndex=\-*\\d+","g");
			var reg2 = new RegExp("\\?","g");
			var href = location.href;
			var reg3 = new RegExp("[0-9]+","g");
			if(!reg3.test(1*$("#pageInput").val())){
				alert("跳转页码非法");
				return false;
			}
			var pageIndex = 1*$("#pageInput").val()-1;
			if(reg.test(href)){
				location.href = href.replace(reg, "pageIndex=" + pageIndex);
			}else if(reg2.test(href)){
				location.href = href + "&pageIndex=" + pageIndex;
			}else{
				location.href = href + "?pageIndex=" + pageIndex;
			}
		});
	});
</script>
<%} %>