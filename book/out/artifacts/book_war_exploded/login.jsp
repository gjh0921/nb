<%@ page contentType="text/html;charset=UTF-8" language="java"  %>
<!DOCTYPE html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />

<link type="text/css" rel="stylesheet" href="static/css/bootstrap.css">
<link type="text/css" rel="stylesheet" href="static/css/misc-pages.css">
	<base href="${pageContext.request.scheme}://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}/">
<title>图书管理系统</title>

</head>
<body class="simple-page">


<div class="simple-page-wrap">

	<div class="simple-page-logo ">
		<a href="">
			<span></span>
			<span>图书管理系统</span>
		</a>
	</div><!-- logo -->
	
	<div class="simple-page-form" id="login-form">
		<h4 class="form-title m-b-xl text-center">请输入用户名和密码</h4>
		<form action="AdminServlet?action=login" method="post">
			<div class="form-group">
				<input type="text" name="username" class="form-control" placeholder="用户名" value="">
			</div>
		
			<div class="form-group">
				<input type="password" name="password" class="form-control" placeholder="密码">
			</div>
		
			<div class="form-group m-b-xl">
			
				<input name="remember" value="remember" type="checkbox" id="keep_me_logged_in"  style="height:auto;margin:0 0 0 10px;" />
				<label for="keep_me_logged_in">记住我</label>
			
			</div>
			
			<input type="submit" class="btn btn-primary" value="登录">
		</form>
	</div>

</div>

</body>
</html>