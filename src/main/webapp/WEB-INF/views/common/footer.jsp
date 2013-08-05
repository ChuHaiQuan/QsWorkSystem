<%@ page language="java"  pageEncoding="UTF-8"%>
<style>
<!--
.neededLoginTips{
	color:#999999;
	margin-left:14px;
	margin-right:25px;
}
-->
</style>
<div class="footer">
	<a href="http://www.tradeserving.com/" title="全顺科技" target="_blank">全顺科技</a>&nbsp;丨&nbsp;
	<a href="">关于我们</a>&nbsp;丨&nbsp;
	<a href="">联系我们</a>&nbsp;丨&nbsp;
	<a href="">友情链接</a>&nbsp;丨&nbsp;
	©2006-2020 杭州全顺科技有限公司 版权所有 
</div>
<!--content end-->
<!--所有相关-->
<script src="<c:url value="/resources/scripts/jquery-1.7.1.min.js"/>" type="text/javascript"></script>
<script>
    if(!window.jQuery)
    document.write('<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js"><'+'/script>');
</script>
<script type="text/javascript">
$(function(){
	
	
	// 初始化为loading效果图
	$("#ico_loading").show();
	$(".msglist .msgmore").css("top", "51px");
	// 在鼠标移动到消息条数上时进行加载
	$("#msglabel").hover(function(){
		// 维持消息框跟明细框底色一致
		$("#msglabel").css("background-color", "#FFFFFF");
		$("#msglayer").show();
		// 判断loading效果是否存在，不存在则说明已经加载过一次信息，不需要再次加载
		if($("#ico_loading").is(":visible") == true){
			// 获取详细的信息条数
			$.ajax({cache: false,
				type: "GET",
				url: "/user/message/findByReceiver",
				success: function(data){
					$("#ico_loading").hide();
					$("#breadcrumb_msg_list").html("");
					$(".msglist .msgmore").css("top", "7px");
					if(data.length != undefined){
						for(var i=0, j=data.length; i<j; i++){
							var msgLi = "<li><a title='"+data[i].title+"' href='/user/message/"+data[i].id+"'>"+data[i].title+"</a></li>";
							$("#breadcrumb_msg_list").append(msgLi);
						}
					}
				},
				error: function(){
					$("#ico_loading").hide();
					$("#loading_error").show();
					$(".msglist .msgmore").css("top", "51px");
				}
			});
		}
	}, function(){
		$("#msglayer").hide();
		// 移除消息框底色
		$("#msglabel").css("background-color", "transparent");
		$("#msglayer").hover(function(){
			$("#msglabel").css("background-color", "#FFFFFF");
			$("#msglayer").show();
		},function(){
			$("#msglayer").hide();
			$("#msglabel").css("background-color", "transparent");
		});
	});
	
	// 因网速或者别的原因造成系统繁忙可以重新获取消息
	$("#msg_retry").click(function(){
		$(".msglist .msgmore").css("top", "7px");
		$("#loading_error").hide();
		// 获取详细的信息条数
		$.ajax({cache: false,
			type: "GET",
			url: "/user/message/findByReceiver",
			success: function(data){
				$("#ico_loading").hide();
				$("#breadcrumb_msg_list").html("");
				$(".msglist .msgmore").css("top", "7px");
				if(data.length != undefined){
					for(var i=0, j=data.length; i<j; i++){
						var msgLi = "<li><a title='"+data[i].title+"' href='/user/message/"+data[i].id+"'>"+data[i].title+"</a></li>";
						$("#breadcrumb_msg_list").append(msgLi);
					}
				}
			},
			error: function(){
				$("#ico_loading").hide();
				$(".msglist .msgmore").css("top", "51px");
				$("#loading_error").show();
			}
		});
	});
	
});
</script>
<script>
	$(function(){
		if ($("#serviceList").length) {
			$.ajax({cache: false,
				type : "get",
				url : '/user/myServiceList',
				success : function(data) {
					$("#serviceList").html(data);
				},
				error: function(jqXHR, textStatus, errorThrown){
					if(jqXHR.status==999) {
						$.ajax({cache: false,
							type : "get",
							url : '/user/myServiceList?noNeededLogin=true',
							success : function(data) {
								$("#serviceList").html(data);
							}
						});
					}
				}
			});
		}
	});
</script>