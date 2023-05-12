<%@ page language="java" pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <base href="<%=basePath%>">
    <link type="text/css" rel="stylesheet" href="css/style.css"/>
</head>
<body>
<div class="menu">
    <table>
        <tbody>
        <tr>
            <td>
                <form method="post" action="user_list.action">
                    用户名称：<input name="username" placeholder="请输入用户名称" class="input-text" type="text"/>
                    <input value="查 询" type="submit" class="button"/>
                </form>
            </td>
        </tr>
        </tbody>
    </table>
</div>
<div class="main">
    <div class="optitle clearfix">
        <c:if test="${sessionScope.user.role=='300'}">
            <em> <input type="button" value="添加数据" class="input-button"
                        onclick="window.location.href='securityJsp/user/add.jsp'"/>
                <input type="button" value="修改密码" class="input-button"
                       onclick="window.location.href='securityJsp/user/updatePwd.jsp'"/>
            </em>
        </c:if>
        <div class="title">
            用户管理&gt;&gt;
            <s:actionmessage/>
        </div>
    </div>
    <div class="content">
        <div class="box span10 oh">
            <table class="list">
                <tbody>
                <tr>
                    <td width="70" height="29">
                        <div class="STYLE1" align="center">序号</div>
                    </td>
                    <td>
                        <div class="STYLE1" align="center">用户名称</div>
                    </td>
                    <td>
                        <div class="STYLE1" align="center">性别</div>
                    </td>
                    <td>
                        <div class="STYLE1" align="center">年龄</div>
                    </td>
                    <td>
                        <div class="STYLE1" align="center">电话</div>
                    </td>
                    <td>
                        <div class="STYLE1" align="center">地址</div>
                    </td>
                    <td>
                        <div class="STYLE1" align="center">权限</div>
                    </td>
                    <c:if test="${sessionScope.user.role=='300'}">
                        <td>
                            <div class="STYLE1" align="center">操作</div>
                        </td>
                    </c:if>
                </tr>
                <c:forEach var="users" items="${userList.records}" varStatus="vars">
                    <tr>
                        <td><span class="STYLE1">${pager.offset + vars.count} </span></td>
                        <td><span class="STYLE1">${users.username}</span></td>
                        <td>
							<span class="STYLE1">
								<c:if test="${users.gender==1}">
                                    男
                                </c:if>
								<c:if test="${users.gender==0}">
                                    女
                                </c:if>
							</span>
                        </td>
                        <td><span class="STYLE1">${users.age}</span></td>
                        <td><span class="STYLE1">${users.phone}</span></td>
                        <td><span class="STYLE1">${users.address}</span></td>
                        <td><span class="STYLE1">${users.role}</span></td>
                        <c:if test="${sessionScope.user.role=='300'}">
                            <td>
								<span class="STYLE1">
									<button onclick="window.location.href='user_toUpdate.action?id=${users.upk}'">修改</button>
									<button onclick="if(window.confirm( '确定要删除[${users.username}]吗?')==true){window.location.href='user_delete.action?id=${users.upk}'}">删除</button>
								</span>
                            </td>
                        </c:if>
                    </tr>
                </c:forEach>
                </tbody>
                <tr>
                    <td colspan="8">
                        <jsp:include page="../inc/pager.jsp">
                            <jsp:param value="user_list.action" name="url"/>
                            <jsp:param value="${userList.total }" name="items"/>
                            <jsp:param value="username" name="param1"/>
                        </jsp:include>
                    </td>
                </tr>
            </table>
        </div>
    </div>
</div>
</body>
</html>