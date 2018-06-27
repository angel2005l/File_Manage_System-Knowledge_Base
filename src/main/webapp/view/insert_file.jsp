<%@ include file="/view/base/base.jsp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="zh-cmn-Hans" class="translated-ltr">
<head>
<base href="<%=basePath%>">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">

<title></title>
<meta name="renderer" content="webkit">
<link type="text/css" rel="stylesheet"
	href="assets/css/translateelement.css">
</head>

<body class="">

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
				<div class="command-bar">
					<div class="search-wrap">
						<a href="javascript:;" class="link-search" title="搜索"><i
							class="twr twr-search"></i></a>
						<form id="form-search" class="form" method="get" action="">
							<input id="txt-search" type="text" class="keyword no-border"
								name="keyword" placeholder="搜索" autocomplete="off">
						</form>
					</div>
					<div class="notification-info">
						<a href="javascript:;" id="notification-count" class="label"
							title="新的通知" data-unread-count="0"
							data-url="/teams/66fb82b17f5341cbb0f5f39a1064c013/notifications/unread_counts">
							<span class="twr twr-bell-o bell"></span> <span class="num"><font
								style="vertical-align: inherit;"><font
									style="vertical-align: inherit;">0</font></font></span>
						</a>
						<div class="noti-pop">
							<div class="noti-pop-hd">
								<b class="title"><font style="vertical-align: inherit;"><font
										style="vertical-align: inherit;">通知</font></font></b> <a
									class="mark-as-read" id="noti-mark-read" data-loading="true"
									data-remote="true" rel="nofollow" data-method="post"
									href="https://tower.im/teams/66fb82b17f5341cbb0f5f39a1064c013/notifications/read_all">
									<span class="twr twr twr-check"></span> <font
									style="vertical-align: inherit;"> <font
										style="vertical-align: inherit;"> 全部标记为已读 </font>
								</font>
								</a>
							</div>
							<div class="noti-pop-list-wrap">
								<div class="noti-pop-list notification-list"></div>
							</div>
							<div class="noti-pop-empty">
								<font style="vertical-align: inherit;"> <font
									style="vertical-align: inherit;">- 没有新通知 -</font>
								</font>
							</div>
							<div class="noti-pop-footer">
								<a class="noti-settings" data-stack="true"
									data-stack-root="true" href=""> <i class="twr twr-cog"></i>
									<font style="vertical-align: inherit;"> <font
										style="vertical-align: inherit;">通知设置 </font>
								</font>
								</a> <a class="noti-all-link" data-stack="true"
									data-stack-root="true" href=""> <font
									style="vertical-align: inherit;"> <font
										style="vertical-align: inherit;">查看全部通知</font>
								</font>
								</a>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>

		<div class="container workspace simple-stack simple-stack-transition">
			<div class="page page-1 simple-pjax">
				<div class="page-inner" id="page-new-project">
					<h3 class="page-title">
						<font style="vertical-align: inherit;">上传文件</font>
					</h3>

					<form class="form form-invite" action="" method="post">
						<div class="form-item">
							<div class="form-field">
								<input type="file" name="file_info">
							</div>
						</div>

						<div class="form-item">
							<div class="form-field">
								<textarea name="project_desc" id="project-desc"
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
													type="checkbox" name="file_download" value="${b.userCode }">
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
											<c:forEach var="b" items="${userList }">
												<label title="${b.userName }" class="member"> <input
													type="checkbox" name="file_show" value="${b.userCode }">
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

						<div class="form-buttons">
							<button type="submit" class="btn btn-primary"
								id="btn-create-project" data-disable-with="正在上传..."
								data-success-text="上传成功">
								<font style="vertical-align: inherit;"><font
									style="vertical-align: inherit;">上传文件</font></font>
							</button>
							<a href="退回主页" class="btn btn-x" data-stack="" data-stack-root="">
								<font style="vertical-align: inherit;"> <font
									style="vertical-align: inherit;">取消</font>
							</font>
							</a>
						</div>
						<input type="hidden" name="project_level" value="${projectLevel }">
						<input type="hidden" name="project_parent_code"
							value="${projectCode }">
					</form>

				</div>
			</div>
		</div>
		<div class="footer">
			<font style="vertical-align: inherit;"> <font
				style="vertical-align: inherit;"> © 商务智能部 </font>
			</font>
		</div>
	</div>
	<script type="text/javascript">
		$(function() {
			$("input[name='file_download']").on("change", function() {
				var dowloadVal = $(this).val();
				alert(dowloadVal)
				changeFileShow(dowloadVal);
			})

			function changeFileShow(dowloadVal) {
				var obj = $("input[name='file_show'][value=" + dowloadVal + "]");
				console.log(obj);
				obj.prop("checked",false);
			}

		})
	</script>

</body>

</html>