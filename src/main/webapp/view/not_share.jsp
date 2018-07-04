<%@ include file="/view/base/base.jsp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title></title>
    
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=0">
	<link href="assets/css/404.css" rel="stylesheet">
  </head>
      <script>
	    window.cow = {}
	    window.invokeInfo={
	        type: '11',
	         currentFile: {
	             type: '0'
	         }
	    }
    </script>
  <body class="no-permission">
    <div class="error-box error-403-box">
        <div class="no-permission"></div>
        <div class="info-word">
                    <p>没有权限访问这个文档</p>
                <div class="back-link-container">
                  <a href="javascript:;" class="back-link" onclick="goback()">返回桌面</a>
                </div>
        </div>
        <div class="error-illustration error-403-illustration"></div>
    </div>
    
    <script type="text/javascript">
        function goback(){
        	window.location.href="pro/index.do";
        }
        
    </script>
    <script src="assets/js/vendor.js"></script>
    <script src="assets/js/open_app.js"></script>
    
    <script src="assets/js/sensors.js"></script>
  </body>
</html>
