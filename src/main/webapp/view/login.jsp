<%@ include file="/view/base/base.jsp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html class="login-bg">

<head>
<title>新海集团_知识库_登录</title>
<base href="<%=basePath%>">
<meta name="viewport" content="width=device-width, initial-scale=1.0" />

<link href="assets/css/bootstrap/bootstrap.css" rel="stylesheet" />
<link href="assets/css/bootstrap/bootstrap-responsive.css"
	rel="stylesheet" />
<link href="assets/css/bootstrap/bootstrap-overrides.css"
	type="text/css" rel="stylesheet" />

<link rel="stylesheet" type="text/css" href="assets/css/layout.css" />
<link rel="stylesheet" type="text/css" href="assets/css/elements.css" />
<link rel="stylesheet" type="text/css" href="assets/css/icons.css" />

<link href="assets/css/font-awesome.css" type="text/css"
	rel="stylesheet" />

<script src="assets/js/jquery-latest.js"></script>
<script src="assets/js/bootstrap.min.js"></script>
<script src="assets/js/theme.js"></script>

<!-- this page specific styles -->
<link rel="stylesheet" href="assets/css/signin.css" type="text/css"
	media="screen" />

<!--[if lt IE 9]>
      <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
    <![endif]-->
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
</head>
<body>
	<!-- background switcher -->
	<div class="row-fluid login-wrapper">
		<img class="logo" src="assets/img/logo_simple.png"  style="display: inline-block;" />
		<div class="span4 box">
			<div class="content-wrap">
				<img class="logo" src="assets/img/login_xh_kb.png"
					style="margin-bottom: 20px;" /> <input class="span12" type="text"
					id="userName" placeholder="请输入账号" /> <input class="span12"
					type="password" id="password" placeholder="请输入密码" />
				<!--<a href="#" class="forgot">Forgot password?</a>-->
				<!--<div class="remember">
						<input id="remember-me" type="checkbox" />
						<label for="remember-me">Remember me</label>
					</div>-->
				<a class="btn-glow primary login" onclick="login()">登录</a>
			</div>
		</div>
		<!--<div class="span4 no-account">
				<p>Don't have an account?</p>
				<a href="signup.html">Sign up</a>
			</div>-->
	</div>

	<!-- pre load bg imgs -->
	<script type="text/javascript" src="view/js/login.js"></script>
</body>

</html>