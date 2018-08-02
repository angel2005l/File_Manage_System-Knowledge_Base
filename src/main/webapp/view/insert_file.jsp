<%@ include file="/view/base/base.jsp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="zh-cmn-Hans" class="translated-ltr">
<head>
<base href="<%=basePath%>">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">

<title>知识库-上传文件</title>
<meta name="renderer" content="webkit">
<link rel="stylesheet" href="assets/css/bootstrap.min.css">
<link rel="stylesheet"
	href="assets/css/bootstrap/bootstrap-datetimepicker.min.css">
<link type="text/css" rel="stylesheet"
	href="assets/css/translateelement.css">
<link rel="stylesheet" href="assets/theme/default/layer.css" />
</head>
<body>
	<div class="wrapper">
		<div class="header">
			<div class="header-container">
				<h1 class="logo">
					<a class="header-team-name" data-stack="true"
						data-stack-root="true" href=""><font
						style="vertical-align: inherit;"><font
							style="vertical-align: inherit;">新海科技集团</font></font></a>
				</h1>
			</div>
		</div>
		<div class="container workspace simple-stack simple-stack-transition">
			<div class="page page-0 simple-pjax">
				<div class="page-inner" id="page-new-project">
					<h3 class="page-title">
						<font style="vertical-align: inherit;">上传文件</font>
					</h3>
					<form id="fileFrom" class="form" method="post"
						enctype="multipart/form-data">
						<div class="form-item">
							<div class="form-field">
								<input type="file" name="file_data">
							</div>
						</div>
						<div class="form-item">
							<div class="form-field">
								<textarea name="file_info" id="file_info"
									placeholder="简单描述文件信息，便于其他人理解（选填）" data-validate="length:0,255"
									data-validate-msg="文件描述最长255个字符"></textarea>
							</div>
						</div>
						<div class="form-item">
							<div style="float: left; display: inline-block; padding-right: 10%">
								<h4>
									<font style="vertical-align: inherit;">选择事件类型</font>
								</h4>
								<select id="file_event_type" class="xh_file_select" name="file_event_type">
								</select>
							</div>
							<div style="display: inline-block;">
								<h4>
									<font style="vertical-align: inherit;">选择事件级别</font>
								</h4>
								<select id="file_event_level" class="xh_file_select" name="file_event_level">
								</select>
							</div>
						</div>
						<div class="form-item">
							<div style="float: left; display: inline-block; padding-right: 10%">
								<h4>
									<font style="vertical-align: inherit;">选择调研人</font>
								</h4>
								<select id="file_research_user_code" class="xh_file_select" name="file_research_user_code">
								<c:forEach var="b" items="${userList }">
									<option value="${b.user_code }" <c:if test="${b.user_code eq sessionScope.user_code }" >selected</c:if> >${b.user_name }</option>
								</c:forEach>
								</select>
							</div>
							<div style="display: inline-block;">
								<h4>
									<font style="vertical-align: inherit;">选择调研时间</font>
								</h4>
								<input type="text" class="form_datetime" id="file_research_start_time"
									style="display: inline; width: 220px;" name="file_research_start_time"
									placeholder="请选择调研时间" autocomplete="off" readonly="readonly">
							</div>
						</div>
						<div class="setting-section">
							<h4>
								<font style="vertical-align: inherit;">选择文件可下载成员</font>
							</h4>
							<p class="desc">
								<font style="vertical-align: inherit;"> <font
									style="vertical-align: inherit;">管理员可以邀请和移除文件权限，只有被邀请的团队成员才能预览和下载该文件。</font>
								</font>
							</p>
							<div class="manage-members-tabs">

								<div class="tab active" data-tab="team">
									<div class="manage-members">
										<div class="add-member"></div>
										<div class="members member-checkbox-list">
												<label class="member"> <input type="checkbox" name="file_all" value="download" ><span
													class="name"><font style="vertical-align: inherit;"><font
															style="vertical-align: inherit;">全选</font></font></span>
												</label>
											<c:forEach var="b" items="${userList }">
												<label title="${b.user_name }" class="member"> <input
													type="checkbox" name="file_download"
													value="${b.user_code },${b.user_name }"> <span
													class="name"><font style="vertical-align: inherit;"><font
															style="vertical-align: inherit;">${b.user_name }</font></font></span>
												</label>
											</c:forEach>
										</div>
									</div>
								</div>
							</div>
						</div>
						<div class="setting-section">
							<h4>
								<font style="vertical-align: inherit;">选择文件只读成员</font>
							</h4>
							<p class="desc">
								<font style="vertical-align: inherit;"> <font
									style="vertical-align: inherit;">管理员可以邀请和移除文件权限，只有被邀请的团队成员才能预览该文件。</font>
								</font>
							</p>
							<div class="manage-members-tabs">
								<div class="tab active" data-tab="team">
									<div class="manage-members">
										<div class="add-member"></div>
										<div class="members member-checkbox-list">
											<label class="member"> <input type="checkbox" name="file_all" value="show" ><span
													class="name"><font style="vertical-align: inherit;"><font
															style="vertical-align: inherit;">全选</font></font></span>
												</label>
											<c:forEach var="b" items="${userList }">
												<label title="${b.user_name }" class="member"> <input
													type="checkbox" name="file_show"
													value="${b.user_code },${b.user_name }"> <span
													class="name"><font style="vertical-align: inherit;"><font
															style="vertical-align: inherit;">${b.user_name }</font></font></span>
												</label>
											</c:forEach>
										</div>
									</div>
								</div>
							</div>
						</div>
						<div>
							<button type="button" class="btn btn-primary" id="uploadBtn" 
								onclick="clickBtn()">
								<font style="vertical-align: inherit;"><font
									style="vertical-align: inherit;">上传文件</font></font>
							</button>
							<a href="javascript:" onclick="self.location=document.referrer;"
								class="btn btn-x"> <font style="vertical-align: inherit;">
									<font style="vertical-align: inherit;">取消</font>
							</font>
							</a>
						</div>
						<!-- 当前项目等级（父类） -->
						<input type="hidden" name="project_level" id="project_level"
							value="${projectLevel }">
						<!-- 当前项目编码（父类）-->
						<input type="hidden" name="project_code" id="project_code"
							value="${projectCode }">
					</form>
					<!-- 不需要提交的隐藏域 -->
					<input type="hidden" id="userInfo"
						value="${sessionScope.user_code },${sessionScope.user_name }" />
				</div>
			</div>
		</div>
		<div class="footer">
			<font style="vertical-align: inherit;"> <font
				style="vertical-align: inherit;"> © 商务智能部 </font>
			</font>
		</div>
	</div>
	<script type="text/javascript"
		src="assets/js/bootstrap-datetimepicker.min.js"></script>
	<script type="text/javascript"
		src="assets/js/bootstrap-datetimepicker.zh-CN.js"></script>
	<script type="text/javascript" src="assets/js/jquery.form.js"></script>
	<script type="text/javascript" src="assets/js/layer.js"></script>
	<script type="text/javascript" src="view/js/insert_file.js"></script>
</body>

</html>