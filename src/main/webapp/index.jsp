<%@ page language="java" pageEncoding="UTF-8" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <base href="<%=basePath%>">
    <title>超市账单管理系统</title>
</head>
<frameset rows="100,*" cols="*" frameborder="no" border="0"
          framespacing="0">
    <frame src="securityJsp/index_top.jsp" name="topFrame"
           noresize="noresize" id="topFrame"/>
    <frameset cols="200,*" frameborder="no" border="0" framespacing="0">
        <frame src="securityJsp/index_left.jsp" name="leftFrame" noresize="noresize"
               id="leftFrame"/>
        <frame src="huanying.html" name="mainFrame"
               id="mainFrame"/>
    </frameset>
</frameset>
</html>