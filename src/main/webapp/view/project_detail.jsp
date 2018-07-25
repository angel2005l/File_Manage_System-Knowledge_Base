<%@ include file="/view/base/base.jsp"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="zh-cmn-Hans">
<head>
<base href="<%=basePath%>">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">

<title>知识库-项目详情</title>
<meta name="renderer" content="webkit">
<link rel="stylesheet" href="assets/css/bootstrap.min.css">
<link rel="stylesheet"
	href="assets/css/bootstrap/bootstrap-datetimepicker.min.css">
<link rel="stylesheet" media="all" href="assets/css/xh_application.css">
<link rel="stylesheet" href="assets/theme/default/layer.css" />
<link rel="stylesheet" href="assets/css/viewer.css" />
<style type="text/css">
</style>
</head>

<body>
	<div class="wrapper">
		<div class="header">
			<div class="header-container">
				<h1 class="logo">
					<a class="header-team-name js-show-focus-driver"
						href="pro/index.do?method=participation"><font
						style="vertical-align: inherit;"><font
							style="vertical-align: inherit;">新海科技集团</font></font></a>
				</h1>
				<ul class="nav">
					<li class="" id="nav-project"><a
						href="pro/index.do?method=participation">项目</a></li>
					<li class="" id="nav-me"><a href="pro/index.do?method=self">我自己</a></li>
					<li id="nav-upgrade"></li>
				</ul>
				<div class="command-bar">
					<div class="notification-info" id="both">
						<!-- 如果有未读的  显示label unread  否则显示label -->
						<span id="notification-count" title="新的通知" onclick="intoclick()"
							class="label"> <span class="twr twr-bell-o bell"></span> <span
							class="num" id="num"></span>
						</span>
						<div class="noti-pop" id="thediv" style="display: none;"
							onblur="losePoint()">
							<div class="noti-pop-hd">
								<b class="title">通知</b> <a class="mark-as-read"
									id="noti-mark-read" href="javascript:;" onclick="allread()">
									<span class="twr twr twr-check"></span>全部标记为已读
								</a>
							</div>
							<div class="noti-pop-list-wrap">
								<div class="noti-pop-list notification-list"
									style="display: block;">
									<div class="notice unread">
										<span class="link" id="msg"></span>
									</div>
								</div>
							</div>
						</div>
					</div>
					<div class="account-info">
						<a class="detail-action" href="user/logout.do">登出</a>
					</div>
				</div>
			</div>
		</div>
		<div class="container workspace simple-stack simple-stack-transition">
			<div class="page page-0 simple-pjax">
				<div class="page-inner" id="page-todolist">
					<div class="todos-all">
						<div class="todolists-wrap">
							<div class="todolists">
								<div class="todolist">
									<div class="title">
										<div class="todolist-actions actions"></div>
										<h4>
											<span class="name-non-linkable"> <span
												class="todolist-rest" id="project_name">${projectInfo.projectName }</span>
												<span style="display: none"><input type="text"
													value="${projectInfo.projectCode }" id="project_code" /></span> <!-- 父类的projectCode -->
												<span style="display: none"><input type="text"
													value="${projectInfo.projectLevel }" id="project_level" /></span>
												<!-- 父类的projectLevel -->
											</span>

											<!-- 进度条 -->
											<span class="progress-pie" title="${per }%"
												data-pie="${per }"></span> <span class="progress-text">${ratio }</span>
										</h4>
									</div>

									<ul class="todos todos-uncompleted ui-sortable">
										<c:forEach var="projects" items="${projectSonInfos }">
											<c:if test="${projects.projectStatus=='progress' }">
												<li class="todo">
													<div class="todo-wrap">
														<div class="simple-checkbox noChecked project-check"
															project_code='${projects.projectCode }'
															project_level='${projects.projectLevel}'
															style="height: 18px; width: 18px;">
															<div class="checkbox-container"
																style="border: 1.8px solid;">
																<div class="checkbox-tick"
																	style="border-right: 2.52px solid; border-bottom: 2.52px solid;"></div>
															</div>
														</div>
														<span class="todo-content"> <span
															class="content-linkable"> <a class="todo-rest"
																data-stack="true"
																href="file/pfd.do?project_code=${projects.projectCode }&project_level=${projects.projectLevel}&root_code=${rootCode }">${projects.projectName }</a>
														</span>
														</span> <span class="todo-detail"> <a
															class="label todo-assign-due">${projects.createUserName }
														</a><a class="label todo-assign-due"><fmt:parseDate
																	value="${projects.createTime }" var="parsedEmpDate" />
																<fmt:formatDate value="${parsedEmpDate }"
																	pattern="yyyy-MM-dd" /> </a></span>
													</div>
												</li>
											</c:if>
										</c:forEach>
									</ul>
									<ul class="todo-new-wrap">
										<c:forEach var="projects" items="${projectSonInfos }">
											<c:if test="${projects.projectStatus=='completed' }">
												<li class="todo completed">
													<div class="todo-actions actions">
														<div class="inr" style="display: none;"></div>
													</div>
													<div class="todo-wrap">
														<div class="simple-checkbox checked"
															style="height: 18px; width: 18px;">
															<div class="checkbox-container"
																style="border: 1.8px solid;">
																<div class="checkbox-tick"
																	style="border-right: 2.52px solid; border-bottom: 2.52px solid;"></div>
															</div>
														</div>
														<span class="todo-content"> <span
															class="content-linkable"> <a class="todo-rest"
																href="file/pfd.do?project_code=${projects.projectCode }&project_level=${projects.projectLevel}&root_code=${rootCode }">${projects.projectName }</a>
														</span>
														</span> <span class="todo-detail"> <a
															class="label todo-assign-due">${projects.updateUserName }
														</a><a class="label todo-assign-due"><fmt:parseDate
																	value="${projects.updateTime }" var="parsedEmpDate" />
																<fmt:formatDate value="${parsedEmpDate }"
																	pattern="yyyy-MM-dd" /> </a></span>
													</div>
												</li>
											</c:if>
										</c:forEach>
									</ul>
								</div>
							</div>
						</div>
						<div class="comment-actions">
							<div class="comment">
								<div class="search-wrap">
									<form id="search" method="post" action="file/pfd.do">
										<input type="text" class="keyword"
											style="display: inline; width: 150px; margin-right: 20px;"
											name="form_file_name" placeholder="请输入文件关键字名"
											autocomplete="off"> <input type="text"
											class="keyword form_datetime"
											style="display: inline; width: 100px; margin-right: 20px;"
											name="form_start_date" placeholder="起始时间" autocomplete="off"
											readonly="readonly"> <input type="text"
											class="keyword form_datetime"
											style="display: inline; width: 100px; margin-right: 20px;"
											name="form_end_date" placeholder="终止时间" autocomplete="off"
											readonly="readonly"> <input id="hidden_project_code"
											type="hidden" name="project_code"
											value="${projectInfo.projectCode }"> <input
											id="hidden_project_level" type="hidden" name="project_level"
											value="${projectInfo.projectLevel}"> <input
											type="hidden" name="root_code" value="${rootCode }">
										<button type="button" class="btn btn-primary"
											style="float: right;" onclick="formSubmit()">
											<font style="vertical-align: inline;">筛选</font>
										</button>
									</form>
								</div>
								<c:forEach var="b" items="${files }">
									<div class="comment-main">
										<div class="attachments-preview gallery-wrap">
											<div class="attachment-list">
												<div class="others">
													<div class="attachment">
														<div class="attachment-thumb" style="width: 70px">
															<div class="simple-checkbox noChecked batch-file-check"
																style="margin-bottom: 50px; display: none;"
																file_code="${b.file_code }">
																<div class="checkbox-container"
																	style="border: 1.8px solid;">
																	<div class="checkbox-tick"
																		style="border-right: 2.52px solid; border-bottom: 2.52px solid;"></div>
																</div>
															</div>
															<img class = "tempImg" data-src
																src="assets/img/<tag:enum className="FileTypeImgEnum">${b.file_type }</tag:enum>" />
														</div>
														<div class="attachment-info">
															<div class="name">
																<a class="link-download"><span class="-rest">${b.file_name }</span>
																</a> <span style="font-size: 10px; padding-left: 40px">${b.user_name }</span>
																<span style="font-size: 8px; padding-left: 10px">${b.create_time }</span>
																<p class=" detail">${b.file_info }</p>
																<%-- <p class="detail-p"><a class="link-download">${b.file_info }</a></p> --%>
															</div>
															<div class="tags"></div>
															<div class="control-dir no-dir">
																<!-- record -->
																	<!-- onlyread -->
																		<!-- 预览、分享 -->
																	<!-- download -->
																		<!-- 预览、分享、锁定、删除 -->
																<!-- lock -->
																	<!-- download -->
																		<!-- 解锁、删除 -->
																	<c:choose>
																		<c:when test="${b.file_status eq 'record' }">
																			<a class="link-change-dir displayBtn"  data-info ="${b.file_type },${b.file_code },${b.file_name }" >预览</a>
																			<c:if test="${b.file_permission eq 'download' }">
																			<a class="link-change-dir" onclick="downloadFile('${b.file_code }','${b.file_level }')">下载</a>
																			</c:if>
																			<a class="link-change-dir" onclick="shareFile('${b.file_code }','${b.file_level }','${projectInfo.projectCode }')">分享</a>
																			<c:if test="${b.file_permission eq 'download' }">
																			<a class="link-change-dir" onclick="lockFile('${b.file_code }','${b.file_level }')">锁定</a>
																			<a class="link-change-dir" onclick="delFile('${b.file_code }','${b.file_level }')">删除</a>
																			</c:if>
																		</c:when>
																		<c:when test="${b.file_status eq 'locked' }">
																			<c:if test="${b.file_permission eq 'download' }">
																				<a class="link-change-dir"
																					onclick="shareFile('${b.file_code }','${b.file_level }','${projectInfo.projectCode }')">解锁</a>
																				<a class="link-change-dir"
																					onclick="shareFile('${b.file_code }','${b.file_level }','${projectInfo.projectCode }')">删除</a>
																			</c:if>
																		</c:when>
																	</c:choose>
															</div>
														</div>
													</div>
												</div>
											</div>
										</div>
									</div>
								</c:forEach>
								<c:if test="${fn:length(files)>0 }">
									<button id="batch_download" type="button"
										class="btn btn-reject" style="float: left;">
										<font style="vertical-align: inline;">批量下载</font>
									</button>
									<button id="batch_enter" type="button" class="btn btn-reject"
										style="float: left; display: none" data-batch-method="">
										<font style="vertical-align: inline;">确定</font>
									</button>
									<button id="batch_cancel" type="button" class="btn btn-reject"
										style="float: left; display: none">
										<font style="vertical-align: inline;">取消</font>
									</button>
									<button id="batch_share" type="button" class="btn btn-reject"
										style="float: left;">
										<font style="vertical-align: inline;">批量分享</font>
									</button>
								</c:if>
							</div>
						</div>
					</div>
				</div>

			</div>
			<div class="detail-actions">
				<c:if test="${!(rootCode eq projectInfo.projectCode) }">
					<div class="item">
						<a id="back_page" class="detail-action detail-action-star " title="返回上一层"
							href="file/pfdb.do?project_code=${projectInfo.projectCode }&project_level=${projectInfo.projectLevel }&root_code=${rootCode }">返回上一层</a>
					</div>
				</c:if>
				<div class="item">
					<a id="back_index" class="detail-action detail-action-star" title="返回首页"
						href="pro/index.do">返回首页</a>
				</div>
				<c:if test="${'write' eq projectInfo.projectPermission }">
					<div class="item">
						<a class="detail-action detail-action-star" title="文件上传"
							href="file/insFileJsp.do?project_code=${projectInfo.projectCode }&project_level=${projectInfo.projectLevel }">文件上传</a>
					</div>
					<div class="item">
						<a class="detail-action detail-action-star"
							href="pro/insProJsp.do?project_code=${projectInfo.projectCode }&project_level=${projectInfo.projectLevel }">新增项目</a>
					</div>
					<div class="item">
						<a class="detail-action detail-action-star" onclick="lockProject('${projectInfo.projectCode }','${projectInfo.projectLevel }')">锁定项目</a>
					</div>
					<div class="item">
						<a class="detail-action detail-action-star" onclick="deleteProject('${projectInfo.projectCode }','${projectInfo.projectLevel }')">删除项目</a>
					</div>
				</c:if>
				<div class="item">
					<!-- 分享项目需要 -->
					<a class="detail-action detail-action-archive"
						onclick="shareProject('${projectInfo.projectCode}','${projectInfo.projectLevel }','${sessionScope.user_code }')">分享</a>
				</div>
			</div>
		</div>
		<div class="footer">© 商务智能部</div>
	</div>
	<form id="displayForm" action="build/generic/web/viewer.html"
		method="get" target="_blank">
		<input id="displayValues" type="hidden" name="file" />
	</form>
	<script type="text/javascript" src="assets/js/layer.js"></script>
	<script type="text/javascript" src="assets/js/viewer.js"></script>
	<script type="text/javascript"
		src="assets/js/bootstrap-datetimepicker.js"></script>
	<script type="text/javascript"
		src="assets/js/bootstrap-datetimepicker.zh-CN.js"></script>
	<script type="text/javascript" src="view/js/project_detail.js" ></script>
	<div id="share" style="display: none;">
		<span style="font-weight: bold; padding-right: 20px;">分享链接:</span>
		<textarea id="shareUrl"
			style="border: hidden; resize: none; overflow-y: hidden; width: 400px; height: 60px"
			disabled="disabled" rows="4"></textarea>
	</div>
</body>

</html>