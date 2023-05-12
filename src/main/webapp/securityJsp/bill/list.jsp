<%@ page language="java" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix='fmt' uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <base href="<%=basePath%>">
    <title>账单管理</title>
    <link type="text/css" rel="stylesheet" href="css/style.css"/>
</head>
<body>
<div class="menu">
    <form method="post" action="bill_list.action">
        商品名称： <input type="text" name="productName" placeholder="请输入商品名称" class="input-text"/>
        &nbsp;&nbsp;&nbsp;&nbsp;
        是否付款：
        <select name="isPay">
            <option value="">请选择</option>
            <option value="1">已付款</option>
            <option value="0">未付款</option>
        </select> &nbsp;&nbsp;&nbsp;&nbsp;
        <input type="submit" value="组合查询" class="button"/>
    </form>
</div>
<div class="main">
    <div class="optitle clearfix">
        <c:if test="${sessionScope.user.role=='300'}">
            <em> <input type="button" name="button" value="添加数据" class="input-button"
                        onclick="window.location.href='bill_toAdd.action'"/>
            </em>
        </c:if>
        <div class="title">
            账单管理&gt;&gt;
            <s:actionmessage/>
        </div>
    </div>
    <div class="content">
        <table class="list">
            <tr>
                <td>序号</td>
                <td>商品名称</td>
                <td>商品数量</td>
                <td>交易金额</td>
                <td>是否付款</td>
                <td>供应商名称</td>
                <td>商品描述</td>
                <td>账单时间</td>
                <c:if test="${sessionScope.user.role=='300'}">
                    <td>操作</td>
                </c:if>
            </tr>
            <c:forEach var="bill" items="${billList.records}" varStatus="vars">
                <tr>
                    <td align="center">${pager.offset + vars.count}</td>
                    <td align="center">${bill.productName}</td>
                    <td align="center">${bill.amount}</td>
                    <td align="center">${bill.money}</td>
                    <td align="center">
                        <c:choose>
                            <c:when test="${bill.isPay==0}">未付款</c:when>
                            <c:otherwise>已付款</c:otherwise>
                        </c:choose>
                    </td>
                    <td align="center">${bill.sname}</td>
                    <td align="center">${bill.description}</td>
                    <td align="center">
                        <fmt:formatDate value="${bill.tradeTime}" pattern="yyyy-MM-dd HH:mm:ss" var="trade_time"/>
                            ${trade_time}
                    </td>
                    <c:if test="${sessionScope.user.role=='300'}">
                        <td align="center">
                            <button onclick="window.location.href='bill_toUpdate.action?id=${bill.bid}'">
                                修改
                            </button>
                            <button onclick="if(window.confirm( '确定要删除[${bill.productName}]吗?')==true){window.location.href='bill_delete.action?id=${bill.bid}'}">
                                删除
                            </button>
                        </td>
                    </c:if>
                </tr>
            </c:forEach>
            <tr>
                <td colspan="9">
                    <jsp:include page="../inc/pager.jsp">
                        <jsp:param value="bill_list.action" name="url"/>
                        <jsp:param value="${billList.total }" name="items"/>
                        <jsp:param value="productName" name="param1"/>
                        <jsp:param value="isPay" name="param2"/>
                    </jsp:include>
                </td>
            </tr>
        </table>
    </div>
</div>
</body>
<script type="text/javascript">
    <c:choose>
    <c:when test="${param.type==1}">
    alert("添加成功");
    </c:when>
    <c:when test="${param.type==2}">
    alert("删除成功");
    </c:when>
    <c:when test="${param.type==3}">
    alert("更新成功");
    </c:when>
    </c:choose>
</script>
</html>