<%@ include file="/view/base/base.jsp"%>
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
<link rel="stylesheet" href="assets/theme/default/layer.css" />
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

				<ul class="nav">
					<li class="" id="nav-project"><a href="" data-stack=""
						data-stack-root="">项目</a></li>
					<li class="" id="nav-me"><a href="" data-stack=""
						data-stack-root="">我自己</a></li>

					<li id="nav-upgrade"></li>

				</ul>

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
							title="新的通知" >
							<span class="twr twr-bell-o bell"></span> <span class="num">0</span>
						</a>
						<div class="noti-pop">
							<div class="noti-pop-hd">
								<b class="title">通知</b> <a class="mark-as-read"
									id="noti-mark-read"> <span
									class="twr twr twr-check"></span> 全部标记为已读
								</a>
							</div>
							<div class="noti-pop-list-wrap">
								<div class="noti-pop-list notification-list"></div>
							</div>
							<div class="noti-pop-empty">- 没有新通知 -</div>
							<div class="noti-pop-footer">
								<a class="noti-settings" data-stack="true"
									data-stack-root="true" href=""> <i class="twr twr-cog"></i>
									通知设置
								</a> <a class="noti-all-link" data-stack="true"
									data-stack-root="true" href="">查看全部通知</a>
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
			<!-- 			<div class="page page-root simple-pjax page-behind" style="">
				<a class="link-page-behind" data-stack="" href=""
					data-stack-fluid="">面包屑</a>
			</div> -->
			<div class="page page-1 simple-pjax">
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

											<!-- 功能暂不明确 -->
											<span class="name"> <span class="todolist-todos-count"></span>
												<a class="todolist-rest" data-stack="true" href=""></a>
											</span>
											<!-- 进度条 -->
												<span class="progress-pie" title="${per }%"
													data-pie="${per }"></span> <span class="progress-text">${ratio }</span>

											<a href="javascript:;" class="fold"> <i
												class="twr twr-angle-up"></i>
											</a>
										</h4>
									</div>

									<ul class="todos todos-uncompleted ui-sortable">
										<c:forEach var="projects" items="${projectSonInfos }">
											<c:if test="${projects.projectStatus=='progress' }">
												<li class="todo">
													<div class="todo-wrap">
														<div class="simple-checkbox"
															style="height: 18px; width: 18px;">
															<div class="checkbox-container"
																style="border: 1.8px solid;">
																<div class="checkbox-tick"
																	style="border-right: 2.52px solid; border-bottom: 2.52px solid;"></div>
															</div>
															<input type="checkbox" name="todo-done"
																class="checkbox-input" style="display: none;">
														</div>
														<span class="todo-content"> <span class="raw">${projects.projectName }</span>
															<span class="content-non-linkable"> <span
																class="todo-rest">${projects.projectName }</span>
														</span> <span class="content-linkable"> <a
																class="todo-rest" data-stack="true"
																href="file/pfd.do?project_code=${projects.projectCode }&project_level=${projects.projectLevel}&root_code=${rootCode }">${projects.projectName }</a>
														</span>
														</span> <span class="todo-detail"> <a
															class="label todo-assign-due">${projects.createUserCode }
														</a></span>

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
														<input type="checkbox" name="todo-done" checked="checked"
															class="checkbox-input">
													</div>

													<span class="todo-content"> <span class="raw">${projects.projectName }</span>
														<span class="content-non-linkable"> <span
															class="todo-rest">${projects.projectName }</span>
													</span> <span class="content-linkable"> <a
															class="todo-rest" data-stack="true" href="">${projects.projectName }</a>
													</span> <!-- <div class="progress-wrap">
							<span class="progress-pie" title="100%" data-pie="100"></span>
							<span class="todo-progress" title="总共有 1 个检查项，已完成 1 个">(1/1)</span>
						</div> --> <!-- <i class="twr twr-description" title="任务描述"></i> -->
													</span> <span class="todo-detail"> <span
														class="label completed-member">${projects.createUserCode }</span>
													</span>
												</div>
											</li>
										</c:if>
									</c:forEach>
									</ul>
								</div>
							</div>
						</div>
						<div class="comment-actions">
							<c:forEach var="b" items="${files }">
								<div class="comment">
								<div class="comment-main">
									<div class="comment-content editor-style">
											<p>${b.file_info }</p>
										</div>
										<div class="attachments-preview gallery-wrap">
										<div class="attachment-list">
											<div class="images"></div>
											<div class="others">
												<div class="attachment">
													<div class="attachment-thumb">
															<img
																src="assets/img/<tag:enum className="FileTypeImgEnum">${b.file_type }</tag:enum>">
														</div>
														<div class="attachment-info">
														<div class="name">
															<a class="link-download"><span class="-rest">${b.file_name }</span></a>
														</div>
														<div class="tags"></div>
														<div class="control-dir no-dir">
																<a class="link-change-dir"
																	onclick="display('${b.file_code }','${b.file_name }')">预览</a>
																<c:if test="${b.file_permission eq 'download' }">
																	<a
																		onclick="downloadFile('${b.file_code }','${b.file_level }')"
																		class="link-change-dir">下载</a>
																</c:if>
																<a class="link-change-dir"
																	onclick="shareFile('${b.file_code }','${b.file_level }','${projectInfo.projectCode }')">分享</a>
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
			<div class="detail-actions">
				<c:if test="${!(rootCode eq projectInfo.projectCode) }">
					<div class="item">
						<a class="detail-action detail-action-star" title="返回上一层"
							href="file/pfdb.do?project_code=${projectInfo.projectCode }&project_level=${projectInfo.projectLevel }&root_code=${rootCode }">返回上一层</a>
					</div>
				</c:if>
				<div class="item">
					<a class="detail-action detail-action-star" title="返回首页"
						href="pro/index.do">返回首页</a>
				</div>
				<div class="item">
					<a class="detail-action detail-action-star" title="文件上传"
						href="file/insFileJsp.do?project_code=${projectInfo.projectCode }&project_level=${projectInfo.projectLevel }">文件上传</a>
				</div>
				<div class="item">
					<a class="detail-action detail-action-edit"
						href="pro/insProJsp.do?project_code=${projectInfo.projectCode }&project_level=${projectInfo.projectLevel }">新增项目</a>
				</div>

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

	<script type="text/javascript">
				function display(code,name){
					var str = "${pageContext.request.contextPath }/file/disPdf.do?file_info="+code+","+name;
					$("#displayValues").val(str);
					$("#displayForm").submit();
				}
				
				function downloadFile(code,level){
					$.ajax({
						url:"file/downloadCheck.do",
						type:"post",
						data:{"file_code":code,"file_level":level},
						dataType:'json',
						success:function(data){
							if(data.code ===0){
								window.location.href="file/downloadFile.do?file_name="+data.data;
							}else{
								alert(data.msg);
							}
						},
						error:function(){
							alert("error");
						}
					})	
				}
				function shareProject(projectCode,projectLevel,userCode){
					var str = "${pageContext.request.scheme }://${pageContext.request.serverName }:${pageContext.request.serverPort }${pageContext.request.contextPath }/pro/sp.do?project_code="+projectCode+"&project_level="+projectLevel+"&user_code="+userCode;
				$("#shareUrl").html(str)
					layer.open({
					  type: 1,
					  skin: 'demo-class', //样式类名
					  area: ['450px','150px'],
					  closeBtn: 1,
					  anim: 0,
					  shadeClose: true, //开启遮罩关闭
					  content:$("#share")
					}) 
				}
				function shareFile(fileCode,fileLevel,projectCode){
					var str = "${pageContext.request.scheme }://${pageContext.request.serverName }:${pageContext.request.serverPort }${pageContext.request.contextPath }/file/sf.do?file_code=" + fileCode+ "&file_level=" + fileLevel + "&project_code="+ projectCode;
			$("#shareUrl").html(str)
			layer.open({
				type : 1,
				skin : 'demo-class', //样式类名
				area : [ '450px', '150px' ],
				closeBtn : 1,
				anim : 0,
				shadeClose : true, //开启遮罩关闭
				content : $("#share")
			})
		}
	</script>
	<div id="share" style="display: none;">
		<span style="font-weight: bold; padding-right: 20px;">分享链接:</span>
		<textarea id="shareUrl"
			style="border: hidden; resize: none; overflow-y: hidden; width: 400px; height: 60px"
			disabled="disabled" rows="4"></textarea>
	</div>
</body>

</html>