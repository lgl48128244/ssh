<%@ page language="java" pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <base href="<%=basePath%>">
    <link type="text/css" rel="stylesheet" href="css/style.css"/>
    <script type="text/javascript">
        function checkoldpassword() {
            var oldpassword = document.getElementById("oldpassword").value;
            var pwd = document.getElementById("pwd").value;
            var span8 = document.getElementById("soldpassword");
            var reg1 = /^[a-zA-z0-9]{5,20}$/;
            if (oldpassword != pwd) {
                span8.innerHTML = "密码错误";
                return false;
            } else if (oldpassword == null || oldpassword == "") {
                span8.innerHTML = "密码不能为空";
                return false;
            } else if (reg1.test(oldpassword) == false) {
                span8.innerHTML = "输入的字符不合法";
                return false;
            } else {
                span8.innerHTML = "";
                return true;
            }
        }

        function checkpassword() {
            var password = document.getElementById("password").value;
            var span9 = document.getElementById("spassword");
            var reg3 = /^[a-zA-z0-9]{5,20}$/;
            if (password == null || password == "") {
                span9.innerHTML = "密码不能为空";
                return false;
            } else if (reg3.test(password) == false) {
                span9.innerHTML = "输入的字符不合法";
                return false;
            } else {
                span9.innerHTML = "";
                return true;
            }
        }

        function checkrepassword() {
            var password = document.getElementById("password").value;
            var repassword = document.getElementById("repassword").value;
            var span10 = document.getElementById("srepassword");
            var reg4 = /^[a-zA-z0-9]{5,20}$/;
            if (password != repassword) {
                span10.innerHTML = "两次密码不一样";
                return false;
            } else if (repassword == null || repassword == "") {
                span10.innerHTML = "密码不能为空";
                return false;
            } else if (reg4.test(repassword) == false) {
                span10.innerHTML = "输入的字符不合法";
                return false;
            } else {
                span10.innerHTML = "";
                return true;
            }
        }

        function checkform() {
            if (checkoldpassword() && checkpassword() && checkrepassword()) {
                return true;
            }
            return false;
        }
    </script>
</head>
<body>
<div class="main">
    <div class="optitle clearfix">
        <div class="title">用户管理&gt;&gt;</div>
    </div>
    <form id="form1" name="form1" method="post"
          action="login_updatePwd.action"
          onsubmit="return checkform();">
        <div class="content">
            <table class="box">
                <tr>
                    <td class="field">旧的密码：</td>
                    <td><input type="hidden" id="pwd" value="${user.password}"/>
                        <input type="hidden" name="upk" value="${user.upk}"/>
                        <input type="password" name="oldpassword" class="text" id="oldpassword"
                               onblur="return checkoldpassword();"/>
                        <span id="soldpassword" style="color: red; font-size: 12px"></span></td>
                </tr>
                <tr>
                    <td class="field">新的密码：</td>
                    <td><input type="password" name="password" class="text" id="password"
                               onblur="return checkpassword();"/>
                        <span id="spassword" style="color: red; font-size: 12px"></span>
                    </td>
                </tr>
                <tr>
                    <td class="field">确认密码：</td>
                    <td>
                        <input type="password" name="repassword" class="text" id="repassword"
                               onblur="return checkrepassword();"/>
                        <span id="srepassword" style="color: red; font-size: 12px"></span>
                    </td>
                </tr>
            </table>
        </div>
        <div class="buttons">
            <input type="button" name="button" value="返回" class="input-button" onclick="history.back();"/>
            <input type="submit" value="修改密码" class="input-button"/>
        </div>
    </form>
</div>
</body>
</html>