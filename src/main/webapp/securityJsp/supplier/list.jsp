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
</head>
<body>
	<div class="menu">
		<table>
			<tbody>
				<tr>
					<td>
						<form method="post" action="supplier_list.action">
							供应商名称： <input name="sname" placeholder="请输入供应商名称" class="input-text" type="text" /> &nbsp;&nbsp;&nbsp;&nbsp;供应商描述： <input
								name="description" placeholder="请输入供应商描述" class="input-text" type="text" /> &nbsp;&nbsp;&nbsp;&nbsp; <input value="组 合 查 询" type="submit" />
						</form>
					</td>
				</tr>
			</tbody>
		</table>
	</div>
	<div class="main">
		<div class="optitle clearfix">
			<em> <input value="添加数据" class="input-button" onclick="window.location='add.jsp'" type="button" />
			</em>
			<div class="title">供应商管理&gt;&gt;</div>
		</div>
		<div class="content">
			<table class="list">
				<tbody>
					<tr>
						<td width="70" height="29">
							<div class="STYLE1" align="center">序号</div>
						</td>
						<td width="80">
							<div class="STYLE1" align="center">供应商名称</div>
						</td>
						<td width="100">
							<div class="STYLE1" align="center">供应商描述</div>
						</td>
						<td width="100">
							<div class="STYLE1" align="center">联系人</div>
						</td>
						<td width="100">
							<div class="STYLE1" align="center">电话</div>
						</td>
						<td width="100">
							<div class="STYLE1" align="center">地址</div>
						</td>
						<td width="100">
							<div class="STYLE1" align="center">操作</div>
						</td>
					</tr>
					<c:forEach var="suppliers" items="${supplierList.datas}" varStatus="vars">
						<tr>
							<td width="70" height="29">
								<div class="STYLE1" align="center">${vars.count}</div>
							</td>
							<td width="80">
								<div class="STYLE1" align="center">${suppliers.sname}</div>
							</td>
							<td width="100">
								<div class="STYLE1" align="center">${suppliers.description}</div>
							</td>
							<td width="100">
								<div class="STYLE1" align="center">${suppliers.contacter}</div>
							</td>
							<td width="100">
								<div class="STYLE1" align="center">${suppliers.phone}</div>
							</td>
							<td width="100">
								<div class="STYLE1" align="center">${suppliers.address}</div>
							</td>
							<td width="100">
								<div class="STYLE1" align="center">
									<input type="button" value="修改" onclick="window.location.href='supplier_updateParam.action?id=${suppliers.sid}'" />
									<input type="button" value="删除"
										onclick="if(window.confirm( '确定要删除[${suppliers.sname}]吗?')==true){
												window.location.href='supplier_delete.action?id=${suppliers.sid}'}">
								</div>
							</td>
						</tr>
					</c:forEach>
				</tbody>
				<tr>
					<td colspan="7">
						<jsp:include page="../inc/pager.jsp">
							<jsp:param value="supplier_list.action" name="url" />
							<jsp:param value="${supplierList.totalRecord }" name="items" />
							<jsp:param value="sname" name="param1" />
							<jsp:param value="description" name="param2" />
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