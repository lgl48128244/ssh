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
<link type="text/css" rel="stylesheet" href="css/style.css" />
</head>
<body>
	<div id="header">
		<div class="title"></div>
		<div class="welcome">欢迎您:${user.username}</div>
		<div style="float: right;">
			<span style="color: red;">当前时间：</span> <span style="color: indigo;" id="time"></span>
			<script type="text/javascript">
				window.onload = function() {
					setInterval("document.getElementById('time').innerHTML=new Date().toLocaleString()+' 星期'+'日一二三四五六'.charAt(new Date().getDay());", 1000);
				}
			</script>
		</div>
	</div>
</body>
</html>
