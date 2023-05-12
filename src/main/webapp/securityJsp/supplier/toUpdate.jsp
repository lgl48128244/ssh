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
        function checksname() {
            var sname = document.getElementById("sname").value;
            var span2 = document.getElementById("ssname");
            if (null == sname || "" == sname) {
                span2.innerHTML = "供应商名称不能为空";
                return false;
            } else {
                span2.innerHTML = "";
                return true;
            }
        }

        function checkform() {
            if (checksname()) {
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
          action="supplier_update.action"
          onsubmit="return checkform();">
        <div class="content">
            <font color="red"></font> <input name="flag" value="doAdd"
                                             type="hidden"/>
            <table class="box">

                <tbody>
                <tr>
                    <td class="field">供应商名称：</td>
                    <td>
						<input name="sid" type="hidden" value="${supplier.sid}"/>
						<input name="createTime" type="hidden" value="${supplier.createTime}"/>
						<input name="sname" id="sname" value="${supplier.sname}" class="text" type="text" onblur="return checksname();"/>
                        <span id="ssname" style="color: red; font-size: 12px"></span>
					</td>
                </tr>
                <tr>
                    <td class="field">供应商描述：</td>
                    <td><textarea name="description" id="description" cols="45"
                                  rows="5">${supplier.description}</textarea></td>
                </tr>
                <tr>
                    <td class="field">供应商联系：</td>

                    <td><input name="contacter" id="contacter"
                               value="${supplier.contacter}" class="text"
                               type="text"/></td>
                </tr>
                <tr>
                    <td class="field">供应商电话：</td>
                    <td><input name="phone" id="phone"
                               value="${supplier.phone}" class="text" type="text"/>
                    </td>
                </tr>
                <tr>
                    <td class="field">供应商传真：</td>

                    <td><input name="fax" id="fax"
                               value="${supplier.fax}" class="text" type="text"/>
                    </td>
                </tr>
                <tr>
                    <td class="field">供应商地址：</td>
                    <td><input name="address" id="address"
                               value="${supplier.address}" class="text"
                               type="text"/></td>
                </tr>
                </tbody>
            </table>
        </div>

        <div class="buttons">
            <input type="submit" value="提交" class="input-button"/> <input
                type="button" name="button" value="返回" class="input-button"
                onclick="history.back();"/>
        </div>
    </form>
</div>
</body>
</html>