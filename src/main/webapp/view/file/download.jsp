<%@ include file="../base/base.jsp" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base href="<%=basePath %>">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form id= "downloadaa" method="post">
		<table>
			<tr>
				<td>文件编码</td>
				<td><input type="text" name="file_code"/></td>
			</tr>
			<tr>
				<td>文件等级</td>
				<td><input type="text" name="file_level"/></td>
			</tr>
			<tr>
				<td>提交</td>
				<td><input type="submit" value="sumbit" ></td>
			</tr>
		</table>
	</form>
	<button id="download" >按钮</button>
	<script type="text/javascript" src="assets/js/jquery-3.3.1.min.js"></script>
	<script type="text/javascript">
		$("#download").on("click",function(){
			$.ajax({
				url:"file/downloadCheck.do",
				type:"post",
				data:{"file_code":'F2018062214300242',"file_level":0},
				dataType:'json',
				success:function(data){
					if(data.code ===0){
						console.log("file/downloadFile.do?file_name="+data.data);
						//window.open("file/downloadFile.do?file_name="+data.data);
						window.location.href="file/downloadFile.do?file_name="+data.data;
					}else{
						alert(data.msg);
					}
				},
				error:function(){
					alert("error");
				}
			})
		})
	</script>
</body>
</html>