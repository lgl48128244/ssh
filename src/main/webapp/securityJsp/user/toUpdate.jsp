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
        function checkusername() {
            var username = document.getElementById("username").value;
            var span2 = document.getElementById("susername");
            //中文验证
            // var reg = /^[\u4e00-\u9fa5]{2,4}$/;
            //字母数字验证
            var reg = /^[a-zA-z0-9]{2,16}$/;
            //var reg = /^[\u4e00-\u9fa5]{2,4}$/;
            if (null == username || "" == username) {
                span2.innerHTML = "用户名不能为空";
                form1.username.focus();
                return false;
            } else if (reg.test(username) == false) {
                // span2.innerHTML = "用户名为汉字，最多四个字";
                span2.innerHTML = "用户名长度为5-16";
                form1.username.focus();
                return false;
            } else {
                span2.innerHTML = "";
                return true;
            }
        }

        function checkage() {
            var age = document.getElementById("age").value;
            var span3 = document.getElementById("sage");
            var reg5 = /^(1[89]|[2-5]\d|60)$/;
            if (null == age || "" == age) {
                span3.innerHTML = "年龄不能为空";
                form1.age.focus();
                return false;
            } else if (reg5.test(age) == false) {
                span3.innerHTML = "年龄范围18-60";
                form1.age.focus();
                return false;
            } else {
                span3.innerHTML = "";
                return true;
            }
        }

        function checkphone() {
            var phone = document.getElementById("phone").value;
            var span5 = document.getElementById("sphone");
            var reg = /^(13|15|18)\d{9}$/;
            if (null == phone || "" == phone) {
                span5.innerHTML = "电话不能为空";
                form1.phone.focus();
                return false;
            } else if (reg.test(phone) == false) {
                span5.innerHTML = "手机验证不通过";
                form1.phone.focus();
                return false;
            } else {
                span5.innerHTML = "";
                return true;
            }
        }

        function checkform() {
            if (checkusername() && checkage() && checkphone()) {
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
    <form id="form1" name="form1" method="post" action="user_update.action" onsubmit="return checkform();">
        <div class="content">
            <table class="box">
                <tr>
                    <td class="field">用户名称：</td>
                    <td>
                        <input type="hidden" name="upk" value="${user.upk}"/>
                        <input type="hidden" name="createTime" value="${user.createTime}"/>
                        <input type="text" name="username" class="text" id="username" onblur="return checkusername();"
                               value="${user.username}"/>
                        <span id="susername" style="color: red; font-size: 12px"></span></td>
                </tr>
                <tr>
                    <td class="field">用户性别：</td>
                    <td><select name="gender" id="gender">
                        <option value="${user.gender}" selected="selected">
                            <c:choose>
                                <c:when test="${user.gender==1}">
                                    男
                                </c:when>
                                <c:otherwise>
                                    女
                                </c:otherwise>
                            </c:choose>
                        </option>
                        <option value="0">女</option>
                        <option value="1">男</option>
                    </select></td>
                </tr>
                <tr>
                    <td class="field">用户年龄：</td>
                    <td><input type="text" name="age" class="text" id="age" onblur="return checkage();"
                               value="${user.age}"/>
                        <span id="sage" style="color: red; font-size: 12px"></span></td>
                </tr>
                <tr>
                    <td class="field">用户电话：</td>
                    <td><input type="text" name="phone" class="text" id="phone" onblur="return checkphone();"
                               value="${user.phone}"/>
                        <span id="sphone" style="color: red; font-size: 12px"></span>
                    </td>
                </tr>
                <tr>
                    <td class="field">用户地址：</td>
                    <td><textarea name="address" id="address" class="text" cols="45" rows="5">${user.address}</textarea>
                    </td>
                </tr>
                <c:if test="${user.role=='300'}">
                    <tr>
                        <td class="field">用户权限：</td>
                        <td><c:if test="${user.role=='300'}">
                            <input type="radio" name="role" id="role1" value="100"/>普通用户
                            <input type="radio" name="role" id="role2" value="300" checked="checked"/>经理
                        </c:if> <c:if test="${user.role!='300'}">
                            <input type="radio" name="role" id="role1" value="100" checked="checked"/>普通用户
                            <input type="radio" name="role" id="role2" value="300"/>经理
                        </c:if></td>
                    </tr>
                </c:if>
                <c:if test="${user.role!='300'}">
                    <input type="hidden" name="role" id="role1" value="100"/>
                </c:if>
            </table>
        </div>
        <div class="buttons">
            <input type="submit" value="数据提交" class="input-button"/>
            <input type="button" name="button" value="返回" class="input-button" onclick="history.back();"/>
        </div>
    </form>
</div>
</body>
</html>