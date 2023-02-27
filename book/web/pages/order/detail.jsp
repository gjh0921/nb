<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>订单详情</title>
    <base href="${pageContext.request.scheme}://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}/">
    <link type="text/css" rel="stylesheet" href="static/css/style.css" >
</head>
<body>

<div id="header">
    <img class="logo_img" alt="" src="static/img/logo.gif"  width="230px" height="80px">
    <span class="wel_word">订单详情</span>
    <div>
        <c:if test="${empty user}">
            <a href="pages/user/login.jsp">登录</a> |
            <a href="pages/user/regist.jsp">注册</a> &nbsp;&nbsp;
        </c:if>
        <c:if test="${not empty user}">
            <span>欢迎<span class="um_span">${user.username}</span>光临网上书城</span>
            <a href="pages/order/order.jsp">我的订单</a>
            <a href="UserServlet?action=logout">注销</a>
        </c:if>
        <a href="index.jsp">返回</a>
    </div>
</div>

<div id="main">

    <table>
        <tr>
            <td>商品名称</td>
            <td>数量</td>
            <td>单价</td>
            <td>金额</td>
        </tr>
        <c:forEach items="${requestScope.orderItem}" var="orderItem">
            <tr>
                <td>${orderItem.name}</td>
                <td>${orderItem.count}</td>
                <td>${orderItem.price}</td>
                <td>${orderItem.totalPrice}</td>
            </tr>
        </c:forEach>
    </table>
</div>

<%@include file="/pages/common/bottom.jsp"%>
</body>
</html>
