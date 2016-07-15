<%@ page language="java" import="java.util.*,org.jsoup.nodes.Document,org.springframework.web.context.support.WebApplicationContextUtils,org.springframework.context.ApplicationContext,com.sql.init.Init" pageEncoding="UTF-8"%>
<%
if(request.getParameter("id") != null){
	ApplicationContext ac2 = WebApplicationContextUtils.getWebApplicationContext(this.getServletContext());
	Init init = (Init)ac2.getBean("init");
	Document leftMenus = init.getLeftMenus(request.getParameter("id"));
	out.write(leftMenus.body().html());
}
%>
