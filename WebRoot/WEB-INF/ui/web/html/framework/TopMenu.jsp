<%@ page language="java" import="java.util.*,org.jsoup.nodes.Document,org.springframework.web.context.support.WebApplicationContextUtils,org.springframework.context.ApplicationContext,com.sql.init.Init" pageEncoding="UTF-8"%>
<%
	ApplicationContext ac2 = WebApplicationContextUtils.getWebApplicationContext(this.getServletContext());
	Init init = (Init)ac2.getBean("init");
	Document TopMenus = init.initMenus();
	out.write(TopMenus.body().html());
%>
<script  type="text/javascript"> 
	$(".navbg").capacityFixed();
</script>
