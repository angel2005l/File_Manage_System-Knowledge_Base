<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <title>文件上传</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
  </head>
  
  <body >
  	<form action="disFile/upFile.do" method="post" enctype="multipart/form-data" role="form">
    	<input type="file" name="fileName" id="file"/>
    	<input type="submit" value="提交">
  	</form>
  	<br>
  	<br>
  	<br>
  	<form action="disFile/downloadFile.do" method="post" enctype="multipart/form-data" role="form">
  		<input type="text" name="path" id="path" value="D:/Program Files/Apache Software Foundation/Tomcat 8.0/webapps/aspose/upload/20180619.docx">
  		<input type="text" name="filename" id="filename" value="20180619.docx">
    	<input type="submit" value="download">
  	</form>
  	<div><span style="color:red">${info }</span></div>
  	<br>
  	<br>
  	<br>
  	<form action="build/generic/web/viewer.html" method="get" target="_blank">
   		PDF路径： <input type="text" name="file" value="/xh_bi_b_knowledge_base/disFile/disPdf.do?pathAddress=C:/Users/Administrator/Desktop/123.pdf" /><input type="submit" value="提交" />
    </form>   
  	
  </body>
</html>
