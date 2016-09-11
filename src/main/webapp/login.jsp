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
<title>系统登录 - 超市账单管理系统</title>
<link type="text/css" rel="stylesheet" href="css/style.css" />
<script type="text/javascript">
	function checkName() {
		var username = document.getElementById("username").value;
		var span1 = document.getElementById("name");
		var reg = /^[a-zA-z0-9]{5,16}$/;
		if (username == null || username == "") {
			span1.innerHTML = "用户名不能为空";
			return false;
		} else if (reg.test(username) == false) {
			span1.innerHTML = "输入字符无效";
			return false;
		} else {
			span1.innerHTML = "";
			return true;
		}
	}
	function checkPassword() {
		var password = document.getElementById("password").value;
		var span1 = document.getElementById("pass");
		var reg = /^[a-zA-z0-9]{5,20}$/;
		if (password == null || password == "") {
			span1.innerHTML = "密码不能为空";
			return false;
		} else if (reg.test(password) == false) {
			span1.innerHTML = "输入字符无效";
			return false;
		} else {
			span1.innerHTML = "";
			return true;
		}
	}
	function checkCode() {
		var ck = document.getElementById("code").value;
		var span1 = document.getElementById("val");
		var reg = /^[a-zA-z0-9]{4}$/;
		if (ck == null || ck == "") {
			span1.innerHTML = "验证码不能为空";
			return false;
		} else if (reg.test(ck) == false) {
			span1.innerHTML = "输入字符无效";
			return false;
		} else {
			span1.innerHTML = "";
			return true;
		}
	}
	function changeValidateCode(obj) {
		/***
		  *   获取当前的时间作为参数，无具体意义   
		  *   每次请求需要一个不同的参数，否则可能会返回同样的验证码    
		  *   这和浏览器的缓存机制有关系，也可以把页面设置为不缓存，这样就不用这个参数了。  
		  */
		var timenow = new Date().getTime();
		obj.src = "random_code.action?" + timenow;
	}
	/**
	 * 验证方式一
	 */
	/* function checklogin() {
		if (checkPassword() && checkName() && checkCode()) {
			return true;
		} else {
			return false;
		}
	} */
	/**
	 * 验证方式二
	 */
	function checkdata() {
		var regName = /^[a-zA-z0-9]{3,16}$/;
		var regPwd = /^[a-zA-z0-9]{3,20}$/;
		var username = document.getElementById("username").value;
		var password = document.getElementById("password").value;
		if (form.username.value == "") {
			document.getElementById("name").innerHTML = "用户名为空";
			form.username.focus();
			return false;
		} else if (regName.test(username) == false) {
			document.getElementById("name").innerHTML = "用户名长度为5-16";
			form.username.focus();
			return false;
		} else {
			document.getElementById("name").innerHTML = "";
		}
		if (form.password.value == "") {
			document.getElementById("pass").innerHTML = "密码为空";
			form.password.focus();
			return false;
		} else if (regPwd.test(password) == false) {
			document.getElementById("pass").innerHTML = "密码长度为5-20";
			form.password.focus();
			return false;
		} else {
			document.getElementById("pass").innerHTML = "";
		}
		if (form.code.value == "") {
			document.getElementById("val").innerHTML = "验证码为空";
			form.code.focus();
			return false;
		} else if (form.code.value.length != 4) {
			document.getElementById("val").innerHTML = "验证码长度为4";
			form.code.focus();
			return false;
		} else {
			document.getElementById("val").innerHTML = "";
		}
	}
	//处理注销页面跳转(方式一)
	// 	if (top.location !== self.location) {
	// 		top.location = self.location;
	// 	}

	//处理注销页面跳转方式二
	if (window != top) {
		top.location.href = location.href;
	}
</script>

</head>
<body class="blue-style">
	<div id="login">
		<div class="icon"></div>
		<div class="login-box">
			<form name="form" method="post"
				action="user_login.action"
				onsubmit="return checkdata()">
				<dl>
					<dt>用户名：</dt>
					<dd>
						<input id="username" type="text" name="username"
							onblur="return checkName()" class="input-text" /> <span
							id="name" style="color: red"></span>
					</dd>
					<dt>密 码：</dt>
					<dd>
						<input id="password" type="password" name="password"
							onblur="return checkPassword()" class="input-text" /> <span
							id="pass" style="color: red"></span>
					</dd>
					<dt>验证码</dt>
					<dd>
						<input id="code" type="text" name="code" onblur="return checkCode()" class="input-text" style="width: 30%;" /> <img src="random_code.action"
							onclick="changeValidateCode(this)" title="点击图片刷新验证码" /> </br> <span
							id="val" style="color: red"></span>
					</dd>
				</dl>
				<div class="buttons">
					<input type="submit" value="登录系统" class="input-button" /> 
					<input type="reset" value="重　　填" class="input-button" /> </br> 
					<span id="show" style="color: red">${requestScope.info}</span>
				</div>
			</form>
		</div>
	</div>
</body>
<script type="text/javascript">
	<c:choose>
	<c:when test="${param.type==1}">
	alert("用户名或密码错误,请重新登录！");
	</c:when>
	<c:when test="${param.type==2}">
	alert("注销成功,请重新登录！");
	</c:when>
	<c:when test="${param.type==3}">
	alert("用户已超时,请重新登录！");
	</c:when>
	<c:when test="${param.type==4}">
	alert("密码修改成功,请重新登录！");
	</c:when>
	</c:choose>
</script>
</html>
