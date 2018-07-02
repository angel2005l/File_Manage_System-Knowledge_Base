<%@ include file="/view/base/base.jsp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<!-- saved from url=(0103)https://tower.im/projects/8f766bfdbe614633b7170027e8165a55/lists/fcf031d5c79a43768df1daacd52f00de/show/ -->
<html lang="zh-cmn-Hans">

<head>
<base href="<%=basePath%>">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">

<title>知识库-项目分享</title>
<meta name="renderer" content="webkit">
<link rel="stylesheet" media="all" href="assets/css/xh_application.css">
<link rel="stylesheet" href="assets/css/xh.css" />
<script src="assets/js/analytics.js"></script>

<script src="assets/js/xh_application.js"></script>
</head>

<body class="" style="cursor: auto;">

	<div class="wrapper">

		<div class="header">
			<div class="header-container">
				<h1 class="logo">
					<a class="header-team-name js-show-focus-driver"
						href="javascript:;">新海科技集团</a>
				</h1>

				<div class="command-bar">
					<div class="search-wrap">
						<a href="javascript:;" class="link-search" title="搜索"><i
							class="twr twr-search"></i></a>
						<form id="form-search" class="form" method="get">
							<input id="txt-search" type="text" class="keyword no-border"
								name="keyword" placeholder="搜索" autocomplete="off">
						</form>
					</div>

					<div class="notification-info">
						<a href="javascript:;" id="notification-count" class="label"
							title="新的通知" data-unread-count="0"
							data-url="/teams/66fb82b17f5341cbb0f5f39a1064c013/notifications/unread_counts">
							<span class="twr twr-bell-o bell"></span> <span class="num">0</span>
						</a>
						<div class="noti-pop">
							<div class="noti-pop-hd">
								<b class="title">通知</b> <a class="mark-as-read"
									id="noti-mark-read" data-loading="true" data-remote="true"
									rel="nofollow" data-method="post"
									href="https://tower.im/teams/66fb82b17f5341cbb0f5f39a1064c013/notifications/read_all">
									<span class="twr twr twr-check"></span> 全部标记为已读
								</a>
							</div>
							<div class="noti-pop-list-wrap">
								<div class="noti-pop-list notification-list"></div>
							</div>
							<div class="noti-pop-empty">- 没有新通知 -</div>
							<div class="noti-pop-footer">
								<a class="noti-settings" data-stack="true"
									data-stack-root="true"
									href="https://tower.im/members/3b3a614642fc499bbce9a48e5c7672aa/notification_settings">
									<i class="twr twr-cog"></i> 通知设置
								</a> <a class="noti-all-link" data-stack="true"
									data-stack-root="true"
									href="https://tower.im/teams/66fb82b17f5341cbb0f5f39a1064c013/notifications">查看全部通知</a>
							</div>
						</div>
					</div>
					<div class="account-info">
						<div class="member-settings">
							<a class="link-member-menu" href="javascript:;"
								data-new-feature="false"> <span class="twr twr-caret-down"></span>
							</a>
						</div>
					</div>
				</div>
			</div>
		</div>

		<div class="container workspace simple-stack simple-stack-transition">
			<div class="page page-1 simple-pjax">
				<div class="page-inner" id="page-todolist">
					<div class="todos-all">
						<div class="todolists-wrap">
							<div class="todolists">
								<div class="todolist">
									<ul class="todos todos-uncompleted ui-sortable">
										<li class="todo running">
											<div class="todo-wrap">
												<h3>
													<span class="todo-content"> <span
														class="content-linkable"> <a class="todo-rest">分享项目：【${shareProject.projectName }】</a>
													</span>
													</span>
												</h3>
											</div>
										</li>

									</ul>

								</div>

							</div>
						</div>
						<div class="comment-actions ">
						<c:forEach  var="shareFile" items="${shareFiles }">
							<div class="comment">
								<div class="comment-main">
									<div class="comment-content editor-style">
										<p>文件描述：${shareFile.file_info }</p>
									</div>
									<div class="attachments-preview gallery-wrap">
										<div class="attachment-list">
											<div class="images"></div>
											<div class="others">
												<div class="attachment">
													<div class="attachment-thumb">
														<img
															src="assets/img/<tag:enum className="FileTypeImgEnum">${shareFile.file_type }</tag:enum>">
													</div>
													<div class="attachment-info">
														<div class="name">
															<a class="link-download"><span class="-rest">${shareFile.file_name }</span></a>
														</div>
														<div class="tags"></div>
														<div class="control-dir no-dir">
															<a class="link-change-dir"
																onclick="display('${shareFile.file_code }','${shareFile.file_name }')"
																target="_blank">预览</a>
															<%-- 	<c:if test="${shareFile.file_permission eq 'download' }">
															<a
																onclick="downloadFile('${shareFile.file_code }','${shareFile.file_level }')"
																class="link-change-dir">下载</a>
														</c:if> --%>
														</div>
													</div>
												</div>
											</div>
										</div>
									</div>
								</div>
							</div>
							</c:forEach>
						</div>
					</div>

				</div>

			</div>
		</div>
	</div>
	<div class="footer">© 商务智能部</div>
</body>

</html>