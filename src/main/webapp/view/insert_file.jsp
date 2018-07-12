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
											<c:forEach var="b" items="${userList }">
												<label title="${b.userName }" class="member"> <input
													type="checkbox" name="file_download" value="${b.userCode },${b.userName }">
													<span class="name"><font
														style="vertical-align: inherit;"><font
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
											<c:forEach var="b" items="${userList }" >
												<label title="${b.userName }" class="member"> <input
													type="checkbox" name="file_show" value="${b.userCode },${b.userName }">
													<span class="name"><font
														style="vertical-align: inherit;"><font
															style="vertical-align: inherit;">${b.userName }</font></font></span>
												</label>
											</c:forEach>
										</div>
									</div>
								</div>

							</div>
						</div>

						<div class="">
							<button type="button" class="btn btn-primary"
								onclick="clickBtn()">
								<font style="vertical-align: inherit;"><font
									style="vertical-align: inherit;">上传文件</font></font>
							</button>

							<a href="javascript:" onclick="self.location=document.referrer;" class="btn btn-x"> <font
								style="vertical-align: inherit;"> <font
									style="vertical-align: inherit;">取消</font>
							</font>
							</a>
						</div>
						<!-- 当前项目等级（父类） -->
						<input type="hidden" name="project_level" id="project_level" value="${projectLevel }">
						<!-- 当前项目编码（父类）-->
						<input type="hidden" name="project_code" id="project_code" value="${projectCode }">
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
			//上传文件者默认为项目参与者
			var createInfo = $("#userInfo").val();
			$("input[name='file_download'][value='"+createInfo+"']").prop("checked","checked").on("change",function(){
				$(this).prop("checked","checked");
			})
			$("input[name='file_show'][value='"+createInfo+"']").prop("disabled","disabled");
			
			//控制权限唯一
			$("input[name='file_download']").on("change", function() {
				var dowloadVal = $(this).val();
				changeFileShow(dowloadVal);
			});
			$("input[name='file_show']").on("change", function() {
				var showVal = $(this).val();
				changeFileDownload(showVal);
			})
		})
		
		function changeFileShow(dowloadVal) {
			var obj = $("input[name='file_show'][value='" + dowloadVal + "']");
			if (obj.is(":checked")) {
				obj.prop("checked", false);
			}
		}
		function changeFileDownload(showVal) {
			var obj = $("input[name='file_download'][value='" + showVal + "']");
			if (obj.is(":checked")) {
				obj.prop("checked", false);
			}
		}
		function clickBtn() {
			var projectCode = $("#project_code").val();
			var projectLevel = $("#project_level").val();
			if (checkFile) {
				$("#fileFrom").ajaxSubmit({
					url : 'file/upFile.do',
					type : 'post',
					dataType : 'json',
					success : function(result) {
							alert(result.msg)
						if (result.code == 0) {
							self.location=document.referrer;
						}
							},
					error : function() {
						alert("服务未响应");
					}
				});
			}
		}
		function checkFile() {
			var fileUrl = $("#file_data").val();
			if (fileUrl == '') {
				alert("请选择文件")
			} else if (fileUrl.indexOf(" ") >= 0) {
				alert("文件名不能有空格");
				return false;
			}
		}
	</script>

</body>

</html>