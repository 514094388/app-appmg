<%@ page language="java" import="java.util.*,org.jsoup.nodes.Document,org.springframework.web.context.support.WebApplicationContextUtils,org.springframework.context.ApplicationContext,com.sql.init.Init" pageEncoding="UTF-8"%>
<%
if(request.getParameter("cid") != null){
	ApplicationContext ac2 = WebApplicationContextUtils.getWebApplicationContext(this.getServletContext());
	Init init = (Init)ac2.getBean("init");
	Document docContent = init.getContent(request.getParameter("cid"));
	out.write(docContent.body().html());
}
%>
