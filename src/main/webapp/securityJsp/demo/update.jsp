<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>更新</title>
</head>
<body>
<form action="demo_update.action" method="post">
    <input type="hidden" name="id" value="${demo.id }">
    姓名：<input type="text" value="${demo.name }" name="name"><br>
    年龄：<input type="text" value="${demo.age }" name="age"><br>
    <input type="submit" value="提交">
</form>
</body>
</html>