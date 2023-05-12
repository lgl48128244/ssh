<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>添加</title>
</head>
<body>
<form action="demo_add.action" method="post">
    <input type="hidden" name="demo.id">
    姓名：<input type="text" value="" name="name"><br>
    年龄：<input type="text" value="" name="age"><br>
    <input type="submit" value="提交">
</form>
</body>
</html>