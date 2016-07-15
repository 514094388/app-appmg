<%@ page language="java" import="java.util.*,org.jsoup.nodes.Document,org.springframework.beans.factory.annotation.Autowired" pageEncoding="UTF-8"%>
<%@ page import="org.springframework.web.context.support.WebApplicationContextUtils"%>
 <%@ page import="org.springframework.context.ApplicationContext"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";

%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
  <head>
    <base href="<%=basePath%>">
    
    <title>创建Java项目</title>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<meta name="viewport" content="width=device-width, initial-scale=1"  />
	<jsp:include page="../import/Import.jsp" />
	<style>
	
	</style>
  </head>
  
  <body>
  	<div id="top_bg">
	<jsp:include page="../framework/TopMenu.jsp" />
	<div>
		<form action="W3C/GetW3CContent.dsr" method="post">
			<table>
				<tr>
					<td><input type="text" name="fn"/></td>
				</tr>
				<tr>
					<td>1：抓取整个W3C教程</td>
				</tr>
				<tr>
					<td><input type="submit" name="submit" value="更新数据"></td>
				</tr>
			</table>
		</form>
	</div>
	<div style="text-align:center;margin:200px 0; font:normal 14px/24px 'MicroSoft YaHei';">
		<p>适用浏览器：IE8、360、FireFox、Chrome、Safari、Opera、傲游、搜狗、世界之窗. </p>
		<p>来源：<a href="http://sc.chinaz.com/" target="_blank">站长素材</a></p>

	</div>

	</div>
  </body>
</html>
