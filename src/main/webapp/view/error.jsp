<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'not_share.jsp' starting page</title>
    
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=0">
	<meta name="description" content="">
	<meta name="keywords" content="">
	<meta name="google" value="notranslate">
	<meta name="apple-itunes-app" content="app-id=1013727678">
	<meta name="version" content="4.2.3">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<link href="assets/css/404.css" rel="stylesheet">
    <script>
	    window.cow = window.cow || {}
	    window.invokeInfo={
	        needBnt: false,
	        type: '界面错误',
	         currentFile: {
	             guid: 'Nbjj8YfIsrswACw4',
	             type: '-3'
	         }
	    }
    </script>
	
  </head>
  
  <body class="no-permission">
      
    
    
    <div class="error-box error-403-box">
        <div class="no-permission"></div>
        <div class="info-word">
                    <p>界面错误</p>
                <div class="back-link-container">
                  <a href="javascript:;" class="back-link" onclick="goback()">返回桌面</a>
                </div>
        </div>
        <div class="error-illustration error-403-illustration"></div>
    </div>
    
    <script type="text/javascript">
        var switchAccount = document.getElementById('switch-account');

        if (switchAccount) {
            var pathname = window.location.pathname;
            var url = '/logout?next=/login?redirect_url=' + pathname;
            switchAccount.setAttribute('href', url);
        }
        
        function goback(){
        	window.location.href="pro/index.do";
        }
        
    </script>
    <script src="assets/js/vendor.js"></script>
    <script src="assets/js/open_app.js"></script>
    
    <script>
        var isLogin = true;
        var userId = 9064539;
    </script>
    <script src="assets/js/sensors.js"></script>
  </body>
</html>
