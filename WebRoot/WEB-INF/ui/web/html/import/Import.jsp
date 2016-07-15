<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
;
%>
<link href="css/framework/BgAndTopMenu-1.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<%=basePath%>js/jquery/jquery.min.js"></script>
<script type="text/javascript"  src="<%=basePath%>js/framework/BgAndTopMenu-1.js"></script>
<!--  左边菜单栏JS脚本：LeftMenu.js   -->
<script type="text/javascript">
$(document).ready(function(){
						   
	/* 滑动/展开 */
	$("ul.expmenu li > div.header").click(function(){
												   
		var arrow = $(this).find("span.arrow");
	
		if(arrow.hasClass("up")){
			arrow.removeClass("up");
			arrow.addClass("down");
		}else if(arrow.hasClass("down")){
			arrow.removeClass("down");
			arrow.addClass("up");
		}
	
		$(this).parent().find("ul.menu").slideToggle();
		
	});
	
});
</script>
<!-- end 左边菜单栏JS脚本：LeftMenu.js   -->