<%@ include file="/view/base/base.jsp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="zh-cmn-Hans" class="translated-ltr">
<head>
<base href="<%=basePath%>">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">

<title>知识库-新增项目</title>
<meta name="renderer" content="webkit">
<link type="text/css" rel="stylesheet"
	href="assets/css/translateelement.css">
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
				<ul class="nav">
					<li class="" id="nav-project"><a href="" data-stack=""
						data-stack-root=""> <font style="vertical-align: inherit;">
								<font style="vertical-align: inherit;">项目</font>
						</font>
					</a></li>
					<li class="" id="nav-me"><a href="" data-stack=""
						data-stack-root=""> <font style="vertical-align: inherit;">
								<font style="vertical-align: inherit;">我自己</font>
						</font>
					</a></li>
					<li id="nav-upgrade"></li>
				</ul>
			</div>
		</div>


		<div class="container workspace simple-stack simple-stack-transition">
			<div class="page page-0 simple-pjax">
				<div class="page-inner" id="page-new-project" data-page-name="创建新项目">
					<h3 class="page-title">
						<font style="vertical-align: inherit;"><font
							style="vertical-align: inherit;">创建新项目</font></font>
					</h3>

					<form id="projectFrom" class="form form-invite"
						enctype="multipart/form-data">
						<div class="form-item">
							<div class="form-field">
								<input type="text" name="project_name" placeholder="项目名称"
									autofocus="" data-validate="required;length:1,32"
									data-validate-msg="请填写项目名称;项目名称最长32个字符">
							</div>
						</div>

						<div class="form-item">
							<div class="form-field">
								<textarea name="project_info" id="project_info"
									placeholder="简单描述项目，便于其他人理解（选填）" data-validate="length:0,255"
									data-validate-msg="项目描述最长255个字符"></textarea>
							</div>
						</div>
						<div class="setting-section select-section">
							<h4>
								<font style="vertical-align: inherit;"><font
									style="vertical-align: inherit;">项目类型</font></font>
							</h4>
							<label class="project-radio"><input type="radio"
								name="project_type" value="pub" checked=""> <font
								style="vertical-align: inherit;">部门项目</font> <small><font
									style="vertical-align: inherit;">更好地组织、细分和管理任务，适用于一般项目管理</font></small>
							</label> <label class="project-radio"> <input type="radio"
								name="project_type" value="own"> <font
								style="vertical-align: inherit;">个人项目</font> <small><font
									style="vertical-align: inherit;">试用版本，暂不支持个人项目</font></small>
							</label>
						</div>

						<div class="setting-section">
							<h4>
								<font style="vertical-align: inherit;">选择项目参与成员</font>
							</h4>
							<p class="desc">
								<font style="vertical-align: inherit;"> <font
									style="vertical-align: inherit;">管理员可以邀请和移除项目参与成员，只有被邀请的团队成员才能访问该项目的信息。</font>
								</font>
							</p>
							<div class="manage-members-tabs">

								<div class="tab active" data-tab="team">
									<div class="manage-members">
										<div class="add-member"></div>

										<div class="members member-checkbox-list">
											<c:forEach var="b" items="${userList }">
												<label title="${b.userName }" class="member"> <input
													type="checkbox" name="project_edit"
													value="${b.userCode },${b.userName }"> <span
													class="name"><font style="vertical-align: inherit;"><font
															style="vertical-align: inherit;">${b.userName }</font></font></span>
												</label>
											</c:forEach>
										</div>
									</div>
								</div>

							</div>
						</div>
						<div class="setting-section">
							<h4>
								<font style="vertical-align: inherit;">选择项目只读成员</font>
							</h4>
							<p class="desc">
								<font style="vertical-align: inherit;"> <font
									style="vertical-align: inherit;">管理员可以邀请和移除项目只读成员，只有被邀请的团队成员才能访问该项目的信息。</font>
								</font>
							</p>
							<div class="manage-members-tabs">
								<div class="tab active" data-tab="team">
									<div class="manage-members">
										<div class="add-member"></div>
										<div class="members member-checkbox-list">
											<c:forEach var="b" items="${userList }">
												<label title="${b.userName }" class="member"> <input
													type="checkbox" name="project_read"
													value="${b.userCode },${b.userName }"> <span
													class="name"><font style="vertical-align: inherit;"><font
															style="vertical-align: inherit;">${b.userName }</font></font></span>
												</label>
											</c:forEach>
										</div>
									</div>
								</div>

							</div>
						</div>

						<div class="form-buttons">
							<button type="button" class="btn btn-primary"
								onclick="clickBtn()">
								<font style="vertical-align: inherit;"><font
									style="vertical-align: inherit;">创建项目</font></font>
							</button>
							<a href="javascript:" onclick="self.location=document.referrer;" class="btn btn-x" >
								<font style="vertical-align: inherit;"> <font
									style="vertical-align: inherit;">取消</font>
							</font>
							</a>
						</div>
						<input type="hidden" name="project_level" value="${projectLevel }">
						<input type="hidden" name="project_parent_code"
							value="${projectParentCode }">
						<input type="hidden" name="project_code"
							value="${projectCode }">
					</form>
					<!-- 不需要提交的隐藏域 -->
					<input type="hidden" id="userInfo" value="${sessionScope.user_code },${sessionScope.user_name }" />
				</div>
			</div>
		</div>
		<div class="footer">
			<font style="vertical-align: inherit;"> <font
				style="vertical-align: inherit;"> © 商务智能部 </font>
			</font>
		</div>
	</div>
	<script type="text/javascript" src="assets/js/jquery.form.js"></script>
	<script type="text/javascript">
		$(function() {
			//创建人默认为项目参与者
			var createInfo = $("#userInfo").val();
			$("input[name='project_edit'][value='"+createInfo+"']").prop("checked","checked").on("change",function(){
				$(this).prop("checked","checked");
			})
			$("input[name='project_read'][value='"+createInfo+"']").prop("disabled","disabled");
			
			//控制权限唯一
			$("input[name='project_edit']").on("change", function() {
				var editVal = $(this).val();
				changeFileShow(editVal);
			});
			$("input[name='project_read']").on("change", function() {
				var readVal = $(this).val();
				changeFileDownload(readVal);
			})
		});
		function changeFileShow(editVal) {
			var obj = $("input[name='project_read'][value='" + editVal + "']");
			if (obj.is(":checked")) {
				obj.prop("checked", false);
			}
		}
		function changeFileDownload(readVal) {
			var obj = $("input[name='project_edit'][value='" + readVal + "']");
			if (obj.is(":checked")) {
				obj.prop("checked", false);
			}
		}

		function clickBtn() {
			$("#projectFrom").ajaxSubmit({
				url : 'pro/insPro.do',
				type : 'post',
				dataType : 'json',
				success : function(result) {
					alert(result.msg);
					if (result.code == 0) {
						self.location=document.referrer;
					}
				},
				error : function(result) {
					alert("服务未响应");
				}
			});
		}
	</script>
</body>

</html>