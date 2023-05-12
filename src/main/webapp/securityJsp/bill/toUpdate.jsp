<%@ page language="java" pageEncoding="UTF-8" %>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <base href="<%=basePath%>">
    <link type="text/css" rel="stylesheet" href="css/style.css" />
    <script type="text/javascript">
        function checkmoney() {
            var moneys = document.getElementById("money").value;
            var span2 = document.getElementById("smoney");
            var reg1 = /^[1-9]\d*(\.\d+)*$/;
            if (null == moneys || "" == moneys) {
                span2.innerHTML = "交易金额不能为空";
                return false;
            } else if (reg1.test(moneys) == false) {
                span2.innerHTML = "交易金额不正确";
                return false;
            } else {
                span2.innerHTML = "";
                return true;
            }
        }

        function checkunit() {
            var units = document.getElementById("unit").value;
            var span3 = document.getElementById("sunit");
            if (null == units || "" == units) {
                span3.innerHTML = "商品单位不能为空";
                return false;
            } else {
                span3.innerHTML = "";
                return true;
            }
        }

        function checkamount() {
            var amount = document.getElementById("amount").value;
            var span4 = document.getElementById("samount");
            var reg2 = /^[1-9]\d*$/;
            if (null == amount || "" == amount) {
                span4.innerHTML = "商品数量不能为空";
                return false;
            } else if (reg2.test(amount) == false) {
                span4.innerHTML = "商品数量必须为数字，非0开头";
                return false;
            } else {
                span4.innerHTML = "";
                return true;
            }
        }

        function checkproductName() {
            var productName = document.getElementById("productName").value;
            var span5 = document.getElementById("sproductName");
            if (null == productName || "" == productName) {
                span5.innerHTML = "商品名称不能为空";
                return false;
            } else {
                span5.innerHTML = "";
                return true;
            }
        }

        function checkIsPay(isPay) {
            if (isPay == '1'){
                document.getElementById("isPay").disabled='disabled';
            }
        }

        function checksname() {
            var sname = document.getElementById("sname");
            var span6 = document.getElementById("ssname");
            var index = sname.selectedIndex; // 选中索引 
            var text = sname.options[index].text; // 选中文本
            var snvalue = sname.options[index].value; // 选中值
            if ("" == snvalue) {
                span6.innerHTML = "请选择供应商";
                return false;
            } else {
                span6.innerHTML = "";
                return true;
            }
        }

        function checkisPay() {
            var isPay = document.getElementById("isPay");
            var span7 = document.getElementById("iisPay");
            var index = isPay.selectedIndex; // 选中索引 
            var text = isPay.options[index].text; // 选中文本
            var payValue = isPay.options[index].value; // 选中值
            if ("" == payValue) {
                span7.innerHTML = "是否付款";
                form.isPay.focus();
                return false;
            } else {
                span7.innerHTML = "";
                return true;
            }
        }

        function checkform() {
            if (checksname() && checkproductName() && checkamount() && checkunit() && checkmoney() && checkisPay()) {
                return true;
            }
            return false;
        }

        function checkdata() {
            var moneys = document.getElementById("money").value;
            var reg1 = /^[1-9]\d*(\.\d+)*$/;
            if (form.money.value == "") {
                document.getElementById("smoney").innerHTML = "交易金额不能为空";
                form.money.focus();
                return false;
            } else if (reg1.test(moneys) == false) {
                document.getElementById("smoney").innerHTML = "交易金额不正确";
                form.money.focus();
                return false;
            } else {
                document.getElementById("smoney").innerHTML = "";
            }
            if (form.unit.value == "") {
                document.getElementById("sunit").innerHTML = "商品单位不能为空";
                form.unit.focus();
                return false;
            } else {
                document.getElementById("sunit").innerHTML = "";
            }
            var amount = document.getElementById("amount").value;
            var reg2 = /^[1-9]\d*$/;
            if (form.amount.value == "") {
                document.getElementById("samount").innerHTML = "商品数量不能为空";
                form.amount.focus();
                return false;
            } else if (reg2.test(amount) == false) {
                document.getElementById("samount").innerHTML = "商品数量必须为数字，非0开头";
                form.amount.focus();
                return false;
            } else {
                document.getElementById("samount").innerHTML = "";
            }
            if (form.productName.value == "") {
                document.getElementById("sproductName").innerHTML = "商品名称不能为空";
                form.productName.focus();
                return false;
            } else {
                document.getElementById("sproductName").innerHTML = "";
            }
            //var snvalue = sname.options[index].value; // 选中值
            var snvalue = $('#sname option:selected').val();//选中的值
            if (snvalue == "") {
                document.getElementById("ssname").innerHTML = "请选择供应商";
                return false;
            } else {
                document.getElementById("ssname").innerHTML = "";
                return true;
            }
            var sisPay = $('#isPay option:selected').val();//选中的值
            if (sisPay == "") {
                document.getElementById("iisPay").innerHTML = "是否付款";
                return false;
            } else {
                document.getElementById("iisPay").innerHTML = "";
                return true;
            }
        }

        function test() {
            //提交
            $.ajax({
                url: "bill_update.action",
                data: $('#myform').serialize(), //向后台发送的数据
                type: "post",
                dataType: "json",
                success: function (data) {
                    alert(data.result);
                    window.location.href = "bill_list.action";
                }
            });
        }
    </script>
</head>
<body>
<div class="main">
    <div class="optitle clearfix">
        <div class="title">账单管理&gt;&gt;</div>
    </div>
    <form method="post" id="myform" name="form"
          action="bill_update.action"
          onsubmit="return checkform();">
        <div class="content">
            <table class="box">
                <tr>
                    <td class="field">交易金额：</td>
                    <td>
                        <input type="hidden" name="bid" id="bid" value="${bill.bid}"/>
                        <input type="hidden" name="tradeTime" id="tradeTime" value="${bill.tradeTime}"/>
                        <input type="text" name="money" id="money" class="text" value="${bill.money}"
                               onblur="return checkmoney();"/>
                        <span id="smoney" style="color: red; font-size: 12px"></span>
                    </td>
                </tr>
                <tr>
                    <td class="field">交易单位：</td>
                    <td><input type="text" name="unit" id="unit" class="text"
                               value="${bill.unit}" onblur="return checkunit();"/>
                        <span id="sunit" style="color: red; font-size: 12px"></span></td>
                </tr>
                <tr>
                    <td class="field">交易数量：</td>
                    <td><input type="text" name="amount" id="amount" class="text"
                               value="${bill.amount}"
                               onblur="return checkamount();"/> <span id="samount"
                                                                      style="color: red; font-size: 12px"></span></td>
                </tr>
                <tr>
                    <td class="field">商品名称：</td>
                    <td><input type="text" name="productName" id="productName"
                               class="text" value="${bill.productName}"
                               onblur="return checkproductName();"/> <span id="sproductName"
                                                                           style="color: red; font-size: 12px"></span>
                    </td>
                </tr>
                <tr>
                    <td class="field">商品描述：</td>
                    <td><textarea name="description">${bill.description}</textarea></td>
                </tr>
                <tr>
                    <td class="field">所属供应商：</td>
                    <td><select id="sname" name="sname"
                                onblur="return checksname();">
                        <option value="${bill.sname}" selected="selected">${bill.sname}</option>
                        <c:forEach var="supply" items="${supplierList}">
                            <option value="${supply.sname}">${supply.sname}</option>
                        </c:forEach>
                    </select> <span id="ssname" style="color: red; font-size: 12px"></span></td>
                </tr>
                <tr>
                    <td class="field">是否付款：</td>
                    <td>
                        <select name="isPay" id="isPay" onfocus="return checkIsPay(${bill.isPay});">
                            <option value="">请选择</option>
                            <option value="0" <c:if test="${bill.isPay==0}"> selected="selected"</c:if>>未付款</option>
                            <option value="1" <c:if test="${bill.isPay==1}"> selected="selected"</c:if>>已付款</option>
                        </select>
                        <span id="iisPay" style="color: red; font-size: 12px"></span>
                    </td>
                </tr>
            </table>
        </div>
        <div class="buttons">
            <input type="submit" value="确认" class="input-button"/> <input
                type="button" name="button" value="返回" class="input-button"
                onclick="history.back();"/>
        </div>
    </form>
</div>
</body>
</html>
