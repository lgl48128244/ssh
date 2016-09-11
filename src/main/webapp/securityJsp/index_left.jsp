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
<script type="text/javascript">
	function exitsys() {
		var r = confirm("你要退出系统吗");
		if (r) {
			//window.open("login.jsp?type=2", "_parent");
			window.location.href="user/user!logout.action";
			//left.location.replace("user_logout.action");
		}
	}
</script>

</head>
<body class="frame-bd">
	<ul class="left-menu">
		<li>
			<a href="securityJsp/bill/list.html" target="mainFrame">
				<img src="images/btn_bill.gif" /> 
			</a>
		</li>
		<!--判断用户权限-->
		<c:if test="${user.role=='300'}">
			<li>
				<a href="supplier_list.action" target="mainFrame">
					<img src="images/btn_suppliers.gif" /> 
				</a>
			</li>
		</c:if>
		<li>
			<a href="user_list.action" target="mainFrame">
				<img src="images/btn_users.gif" />
			</a>
		</li>
		<li>
			<a href="javascript:void(0);" onclick="exitsys()">
				<img src="images/btn_exit.gif" /> 
			</a>
		</li>
	</ul>
</body>
</html>
