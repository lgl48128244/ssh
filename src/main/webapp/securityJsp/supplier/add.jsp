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
	function checkSname() {
		var sname = document.getElementById("sname").value;
		var span2 = document.getElementById("span1");
		if (null == sname || "" == sname) {
			span2.innerHTML = "供应商名称不能为空";
			form1.sname.focus();
			return false;
		} else {
			span2.innerHTML = "";
			return true;
		}
	}
	function checkDecription() {
		var decription = document.getElementById("decription").value;
		var span2 = document.getElementById("span2");
		if (null == decription || "" == decription) {
			span2.innerHTML = "供应商描述不能为空";
			form1.decription.focus();
			return false;
		} else {
			span2.innerHTML = "";
			return true;
		}
	}
	function checkContacter() {
		var contacter = document.getElementById("contacter").value;
		var span2 = document.getElementById("span3");
		if (null == contacter || "" == contacter) {
			span2.innerHTML = "供应商联系不能为空";
			form1.contacter.focus();
			return false;
		} else {
			span2.innerHTML = "";
			return true;
		}
	}
	function checkPhone() {
		var phone = document.getElementById("phone").value;
		var span2 = document.getElementById("span4");
		if (null == phone || "" == phone) {
			span2.innerHTML = "供应商电话不能为空";
			form1.phone.focus();
			return false;
		} else {
			span2.innerHTML = "";
			return true;
		}
	}
	function checkFax() {
		var fax = document.getElementById("fax").value;
		var span2 = document.getElementById("span5");
		if (null == fax || "" == fax) {
			span2.innerHTML = "供应商传真不能为空";
			form1.fax.focus();
			return false;
		} else {
			span2.innerHTML = "";
			return true;
		}
	}
	function checksAddress() {
		var address = document.getElementById("address").value;
		var span2 = document.getElementById("span6");
		if (null == address || "" == address) {
			span2.innerHTML = "供应商地址不能为空";
			form1.address.focus();
			return false;
		} else {
			span2.innerHTML = "";
			return true;
		}
	}
	function checkform() {
		if (checkSname() && checkDecription() && checkContacter() && checkPhone() && checkFax() && checksAddress()) {
			return true;
		}
		return false;
	}
</script>
</head>
<body>
	<div class="main">
		<div class="optitle clearfix">
			<div class="title">供应商管理&gt;&gt;</div>
		</div>
		<form id="form1" name="form1" method="post"
			action="supplier_add.action"
			onsubmit="return checkform();">
			<div class="content">
				<font color="red"></font> <input name="flag" value="doAdd"
					type="hidden" />
				<table class="box">

					<tbody>
						<tr>
							<td class="field">供应商名称：</td>
							<td><input name="sname" id="sname" class="text" type="text"
								onblur="return checksname();" /> <span id="span1"
								style="color: red; font-size: 12px"></span></td>
						</tr>
						<tr>
							<td class="field">供应商描述：</td>
							<td><textarea name="decription" id="decription" cols="45"
									rows="5"></textarea> <span id="span2"
								style="color: red; font-size: 12px"></span></td>
						</tr>
						<tr>
							<td class="field">供应商联系：</td>

							<td><input name="contacter" id="contacter" class="text"
								type="text" /> <span id="span3"
								style="color: red; font-size: 12px"></span></td>
						</tr>
						<tr>
							<td class="field">供应商电话：</td>
							<td><input name="phone" id="phone" class="text" type="text" />
								<span id="span4" style="color: red; font-size: 12px"></span></td>
						</tr>
						<tr>
							<td class="field">供应商传真：</td>

							<td><input name="fax" id="fax" class="text" type="text" />
								<span id="span5" style="color: red; font-size: 12px"></span></td>
						</tr>
						<tr>
							<td class="field">供应商地址：</td>
							<td><input name="address" id="address" class="text"
								type="text" /> <span id="span6"
								style="color: red; font-size: 12px"></span></td>
						</tr>
					</tbody>
				</table>
			</div>

			<div class="buttons">
				<input type="submit" value="提交" class="input-button" /> <input
					type="button" name="button" value="返回" class="input-button"
					onclick="history.back();" />
			</div>
		</form>
	</div>
</body>
</html>
