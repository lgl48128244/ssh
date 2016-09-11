<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
<title>超市账单管理系统</title>
<link type="text/css" rel="stylesheet" href="css/style.css" />

<style type="text/css">
.biankuang {
	width: 500px;
	margin: 220px 10px 40px 600px
}
</style>
</head>
<frameset rows="100,*" cols="*" frameborder="no" border="0"
	framespacing="0"> <frame src="securityJsp/index_top.jsp" name="topFrame"
	noresize="noresize" id="topFrame"></frame> <frameset cols="200,*"
	frameborder="no" border="0" framespacing="0"> <frame
	src="securityJsp/index_left.jsp" name="leftFrame" noresize="noresize"
	id="leftFrame" /> <frame src="huanying.html" name="mainFrame"
	id="mainFrame" /> </frameset> </frameset>
<noframes>
	<body>
	</body>
</noframes>
</html>
