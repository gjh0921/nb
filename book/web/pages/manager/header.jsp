<%@ page contentType="text/html;charset=UTF-8" language="java"  %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%
  request.setCharacterEncoding("UTF-8");
%>
<div id="header">
  <img class="logo_img" alt="" src="static/img/logo.gif"  width="230px" height="80px">
  <span class="wel_word">${param.msg}</span>
  <div>
    <a href="BookServlet?action=page">图书管理</a>
    <a href="pages/manager/order_manager.jsp">订单管理</a>
    <a href="index.jsp">返回商城</a>
  </div>
</div>
</body>
</html>
