<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <base href="<%=basePath%>">
    <title>列表</title>
    <script type="text/javascript">
        function del(id) {
            location.href = "demo_delete.action?id=" + id;
        }

        function toUpdate(id) {
            location.href = "demo_toUpdate.action?id=" + id;
        }
    </script>
</head>
<body>
<table border="1" style="align-content: center;">
    <tr>
        <td>id</td>
        <td>name</td>
        <td>age</td>
        <td colspan="2">操作</td>
    </tr>
    <c:forEach items="${list }" var="demo">
        <tr>
            <td>${demo.id }</td>
            <td>${demo.name }</td>
            <td>${demo.age }</td>
            <td>
                <button onclick="del(${demo.id })">删除</button>
                <button onclick="toUpdate(${demo.id })">修改</button>
            </td>
        </tr>
    </c:forEach>
</table>
<a href="demo_toAdd.action">添加</a>
</body>
</html>