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
  	<form action="file/upFile.do" method="post" enctype="multipart/form-data" role="form">
    	<input type="file" name="fileName" id="file"/>
    	<input type="submit" value="提交">
  	</form>
  	<br>
  	<br>
  	<br>
  	<form action="file/downloadFile.do" method="post" enctype="multipart/form-data" role="form">
  		<input type="text" name="filename" id="filename" value="致各位代理们.docx">
    	<input type="submit" value="download">
  	</form>
  	<div><span style="color:red">${info }</span></div>
  	<br>
  	<br>
  	<br>
  	<form action="build/generic/web/viewer.html" method="get" target="_blank">在新的窗口打开
   		PDF路径： <input type="text" name="file" value="/xh_bi_b_knowledge_base/file/disPdf.do?file_info=F201806221421466073,新海知识库数据库结构.xlsx"/>
   		<input type="submit" value="提交" />
    </form>   
  	<hr>
  	<br>
  	<br>
  	<br>
  	<form action="pro/newPro" method="get">
		<div>项目名称：<input type="text" placeholder="请输入项目名称" name="project_name" /></div>
		<div style="display: block;">项目类型：<select name="project_type">     
			  <option value="0">-请选择-</option>     
			  <option value="pub">公有</option>     
			  <option value="own">私有</option>  
			 </select> 
		</div>
		<div>项目创建目的：<textarea name="project_info" placeholder="请输入项目创建目的"></textarea></div>
		<div>项目备注:<input type="text" name="project_remark" placeholder="请输入项目备注" /></div>
		<div>项目所属父项目的编码(下拉选择框):<select name="project_parent_code">     
			  <option value="-1">-请选择-</option>     
			  <option value="0">12313</option>     
			  <option value="1">12313</option>  
			  <option value="2">12313</option>  
			 </select></div>
		<div>项目级别:<select name="project_level">     
			  <option value="-1">-请选择-</option>     
			  <option value="0">一级项目</option>     
			  <option value="1">二级项目</option>  
			  <option value="2">三级项目</option>  
			</select> </div>
		<div>项目参与人员(只读)：
			<input type="button" name="selAll_read" id="selAll_read" value="全选/反选" onclick="into_read()" />
			1<input type="checkbox" name="pt_user_read" value="黄XX1" />
			2<input type="checkbox" name="pt_user_read" value="黄XX2" />
			3<input type="checkbox" name="pt_user_read" value="黄XX3" />
			4<input type="checkbox" name="pt_user_read" value="黄XX4" />
		</div>
		<div>项目参与人员(可编辑)：
			<input type="button" name="selAll_write" id="selAll_write" value="全选/反选" onclick="into_write()" />
			1<input type="checkbox" name="pt_user_write" value="黄XX1" />
			2<input type="checkbox" name="pt_user_write" value="黄XX2" />
			3<input type="checkbox" name="pt_user_write" value="黄XX3" />
			4<input type="checkbox" name="pt_user_write" value="黄XX4" />
		</div>
  	</form>
  	
  <script type="text/javascript" src="js/jquery-1.8.1.min.js"></script>
	<script>
		function into_read(){
			var allsel=document.getElementsByName("pt_user_read");
			for(var i=0;i<allsel.length;i++){
				allsel[i].checked=!allsel[i].checked;
			}
		}
		function into_write(){
			var allsel=document.getElementsByName("pt_user_read");
			for(var i=0;i<allsel.length;i++){
				allsel[i].checked=!allsel[i].checked;
			}
		}
	</script>
  </body>
</html>
