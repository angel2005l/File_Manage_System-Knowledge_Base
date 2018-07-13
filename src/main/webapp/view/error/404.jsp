<%@ include file="/view/base/base.jsp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
	<base href="<%=basePath %>">
		<meta charset="UTF-8">
		<title></title>
		<link rel="stylesheet" href="assets/css/H-ui.min.css" />
		<link rel="stylesheet" href="assets/css/iconfont/iconfont.min.css" />
	</head>
	<body>
		<article class="page-404 minWP text-c">
	<p class="error-title"><i class="Hui-iconfont va-m">&#xe688;</i>
		<span class="va-m"> 404</span>
	</p>
	<p class="error-description">不好意思，您访问的页面不存在~</p>
	<p class="error-info">您可以：
		<a href="javascript:;" onclick="history.go(-1)" class="c-primary">&lt; 返回上一页</a>
		<span class="ml-20">|</span>
		<a href="pro/index.do" class="c-primary ml-20">去首页 &gt;</a>
	</p>
</article>
	</body>
</html>