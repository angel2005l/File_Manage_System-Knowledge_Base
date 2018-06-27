<%@ include file="/view/base/base.jsp" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="zh-cmn-Hans">
	<head>
		<base href="<%=basePath %>">
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">

		<title>知识库（新）</title>
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
        <a class="header-team-name js-show-focus-driver" href="javascript:;">新海科技集团</a>
    </h1>

					<ul class="nav">
						<li class="" id="nav-project">
							<a href="" data-stack="" data-stack-root="">项目</a>
						</li>
						<li class="" id="nav-me">
							<a href="" data-stack="" data-stack-root="">我自己</a>
						</li>

						<li id="nav-upgrade">
						</li>

					</ul>

					<div class="command-bar">
						<div class="search-wrap">
							<a href="javascript:;" class="link-search" title="搜索"><i class="twr twr-search"></i></a>
							<form id="form-search" class="form" method="get">
								<input id="txt-search" type="text" class="keyword no-border" name="keyword" placeholder="搜索" autocomplete="off">
							</form>
						</div>

						<div class="notification-info">
							<a href="javascript:;" id="notification-count" class="label" title="新的通知" data-unread-count="0" data-url="/teams/66fb82b17f5341cbb0f5f39a1064c013/notifications/unread_counts">
								<span class="twr twr-bell-o bell"></span>
								<span class="num">0</span>
							</a>
							<div class="noti-pop">
								<div class="noti-pop-hd">
									<b class="title">通知</b>
									<a class="mark-as-read" id="noti-mark-read" data-loading="true" data-remote="true" rel="nofollow" data-method="post" href="">
										<span class="twr twr twr-check"></span> 全部标记为已读
									</a>
								</div>
								<div class="noti-pop-list-wrap">
									<div class="noti-pop-list notification-list">

									</div>
								</div>
								<div class="noti-pop-empty">- 没有新通知 -</div>
								<div class="noti-pop-footer">
									<a class="noti-settings" data-stack="true" data-stack-root="true" href="">
										<i class="twr twr-cog"></i> 通知设置
									</a>
									<a class="noti-all-link" data-stack="true" data-stack-root="true" href="">查看全部通知</a>
								</div>
							</div>
						</div>
						<div class="account-info">
							<div class="member-settings">
								<a class="link-member-menu" href="javascript:;" data-new-feature="false">
									<span class="twr twr-caret-down"></span>
								</a>
							</div>
						</div>
					</div>
				</div>
			</div>

		<!--  	<script id="tpl-header-menu" type="text/html">
				<div class="header-menu">
					<header class="header-menu-head">
						<div class="header-menu-member">
							<h2>
          黄官易
          <small>
            <i class="twr twr-team"></i>
            新海科技集团
          </small>
        </h2>
							<p>hgy@x.xinhai.com</p>
						</div>
					</header>
					<div class="header-menu-group-primary">
						<div class="header-menu-item">
							<a data-stack="true" data-stack-root="true" href="/members/3b3a614642fc499bbce9a48e5c7672aa/settings">
								<b>个人设置</b>
								<small>头像、名字、密码、通知等</small>
							</a>
						</div>
					</div>
					<div class="header-menu-group-secondary">
						<div class="header-menu-group">
							<div class="header-menu-item">
								<a rel="nofollow" data-method="DELETE" href="/users/sign_out">退出登录</a>
							</div>
						</div>
					</div>
			</script>-->

			<div class="container workspace simple-stack simple-stack-transition">
				<div class="page page-root simple-pjax page-behind" style="">
					<a class="link-page-behind" data-stack="" href="" data-stack-fluid="">面包屑</a>
				</div>
				<div class="page page-1 simple-pjax">
				<div class="page-inner" id="page-todolist">
				<div class="todos-all">
				<div class="todolists-wrap">
				<div class="todolists">
				<div class="todolist">
				<div class="title">
				<div class="todolist-actions actions">
				<div class="inr">
				<a class="archive" title="归档（请确认清单内任务都已完成）" data-method="put" data-remote="true" data-loading="true" href="">归档</a>
				<a href="" class="edit" data-remote="true" data-loading="true" data-mothod="get" title="编辑">编辑</a>
			</div>
		</div>
	<h4>
    <span class="name-non-linkable">
        <span class="todolist-rest" >admin1</span>
        <span style="display:none"><input type="text" value="P201806251124300102" name="project_parent_code" /></span><!-- 父类的projectCode -->
        <span style="display:none"><input type="text" value="0" name="project_level" /></span><!-- 父类的projectLevel -->
    </span>
    
    <!-- 功能暂不明确 -->
    <span class="name">
            <span class="todolist-todos-count"></span>
        <a class="todolist-rest" data-stack="true" href=""></a>
    </span>
    <!-- 进度条 -->
   	<div class="progress-wrap">
    	<span class="progress-pie" title="75%" data-pie="75"></span>
        <span class="progress-text">3/4</span>
    </div>


    <a href="javascript:;" class="fold">
        <i class="twr twr-angle-up"></i>
    </a>
	</h4>
				</div>

				<ul class="todos todos-uncompleted ui-sortable">

				<li class="todo running" data-guid="62a2464f74964a1a8581bcccad958b89" data-sort="0.0" data-sequence-mine="0.0" data-sort-url="" data-project-guid="8f766bfdbe614633b7170027e8165a55" data-project-name="(新)-智能工厂" data-check-items-size="10" data-completed-check-items-size="6">
						<!-- 功能暂不明确 -->
<!-- 					<div class="todo-actions actions">
						<div class="inr">
								<a class="run" title="标记成正在进行中" data-loading="true" data-remote="true" data-method="post" href="" style="display: none;">执行</a>
								<a class="pause" title="暂停" data-loading="true" data-remote="true" data-method="post" href="" style="display: inline;">暂停</a>
								<a href="" class="edit" data-request-members="8f766bfdbe614633b7170027e8165a55" title="编辑">编辑</a>
						</div>
					</div> -->
												<div class="todo-wrap">
													<div class="simple-checkbox" style="height: 18px; width: 18px;">
														<div class="checkbox-container" style="border: 1.8px solid;">
															<div class="checkbox-tick" style="border-right: 2.52px solid; border-bottom: 2.52px solid;">
															</div>
														</div>
														<input type="checkbox" name="todo-done" class="checkbox-input" style="display: none;"></div>
													<span class="todo-content">
     													<!--  <span class="raw">知识库 新版本3</span>
													<span class="content-non-linkable">
															<span class="todo-rest">知识库 新版本2</span>
													</span> -->
													
													<span class="content-linkable">
        													<c:forEach var="projectName" items="${ a}"><a class="todo-rest" data-stack="true" href="" name="project_name">${projectName. }</a></c:forEach>
      												</span>
													<div class="progress-wrap">
														<span class="progress-pie" title="60%" data-pie="60"></span>
														<span class="todo-progress" title="总共有 10 个检查项，已完成 6 个">(6/10)</span>
													</div>
													</span>
													

													<span class="todo-detail">
         												<a class="label todo-assign-due" href="javascript:;" data-request-members="8f766bfdbe614633b7170027e8165a55">
              											<span class="assignee" data-guid="3b3a614642fc499bbce9a48e5c7672aa" data-gavatar="">项目创建人 </span>
													</a>
													<!-- <span class="label comments-count">12条评论</span>
													<a class="label comments-count" href="" data-stack="">
														12份文件
													</a> -->
													</span>
												</div>
											</li>
										</ul>
					<ul class="todo-new-wrap"></ul>
					<a href="javascript:;" class="btn-new-todo" data-url="/projects/8f766bfdbe614633b7170027e8165a55/lists/fcf031d5c79a43768df1daacd52f00de" data-request-members="8f766bfdbe614633b7170027e8165a55"></a>
					<ul class="todos todos-completed" data-length="3" data-url="/projects/8f766bfdbe614633b7170027e8165a55/lists/fcf031d5c79a43768df1daacd52f00de/completed">
					<li class="todo completed" data-guid="5c8583122f27477f81c90f83c8484835" data-sort="1024.0" data-sequence-mine="0.0" data-sort-url="/projects/8f766bfdbe614633b7170027e8165a55/todos/5c8583122f27477f81c90f83c8484835/reorder" data-project-guid="8f766bfdbe614633b7170027e8165a55" data-project-name="(新)-智能工厂" data-creator-guid="07bd49af0e1f4e67abeab4722791b3fd" data-assignee-guid="859f3810cf53463392c1edb48c5e43db" data-updated-at="1528522296" data-check-items-size="1" data-completed-check-items-size="1" data-closed-at="1528522283">
					<div class="todo-actions actions">
						<div class="inr" style="display: none;">
													</div>
												</div>

												<div class="todo-wrap">
													<div class="simple-checkbox checked" style="height: 18px; width: 18px;">
														<div class="checkbox-container" style="border: 1.8px solid;">
															<div class="checkbox-tick" style="border-right: 2.52px solid; border-bottom: 2.52px solid;">
															</div>
														</div>
														<input type="checkbox" name="todo-done" checked="" class="checkbox-input"></div>

													<span class="todo-content">
      <span class="raw">知识库审核</span>
													<span class="content-non-linkable">
        <span class="todo-rest">知识库审核</span>
													</span>
													<span class="content-linkable">
        <a class="todo-rest" data-stack="true" href="">知识库审核</a>
      </span>
													<div class="progress-wrap">
														<span class="progress-pie" title="100%" data-pie="100"></span>
														<span class="todo-progress" title="总共有 1 个检查项，已完成 1 个">(1/1)</span>
													</div>
													<i class="twr twr-description" title="任务描述"></i>
													</span>

													<span class="todo-detail">
        <span class="label completed-member">( 张铭一 <span class="completed-time" data-readable-time="2018-06-09T13:31:23+08:00">6月9日</span> )</span>
													</span>

													<a class="label todo-proj" title="(新)-智能工厂 - 知识库（新）" data-stack="true" href="">(新)-智能工厂 - 知识库（新）</a>
												</div>
											</li>

											<li class="todo completed" data-guid="210bda932564477cbf8fdd6360b22cdc" data-sort="1024.0" data-sequence-mine="0.0" data-sort-url="/projects/8f766bfdbe614633b7170027e8165a55/todos/210bda932564477cbf8fdd6360b22cdc/reorder" data-project-guid="8f766bfdbe614633b7170027e8165a55" data-project-name="(新)-智能工厂" data-creator-guid="859f3810cf53463392c1edb48c5e43db" data-assignee-guid="c3be8cca336d47a59fe6c0ce1ec1be19" data-updated-at="1527813502" data-check-items-size="0" data-completed-check-items-size="0" data-closed-at="1527813502">

												<div class="todo-actions actions">
													<div class="inr" style="display: none;">

													</div>
												</div>

												<div class="todo-wrap">
													<div class="simple-checkbox checked" style="height: 18px; width: 18px;">
														<div class="checkbox-container" style="border: 1.8px solid;">
															<div class="checkbox-tick" style="border-right: 2.52px solid; border-bottom: 2.52px solid;">
															</div>
														</div>
														<input type="checkbox" name="todo-done" checked="" class="checkbox-input" style="display: none;"></div>

													<span class="todo-content">
      <span class="raw">新知识库原型</span>
													<span class="content-non-linkable">
        <span class="todo-rest">新知识库原型</span>
													</span>
													<span class="content-linkable">
        <a class="todo-rest" data-stack="true" href="">新知识库原型</a>
      </span>
													</span>

													<span class="todo-detail">
        <span class="label completed-member">( 章晓莲 <span class="completed-time" data-readable-time="2018-06-01T08:38:22+08:00">6月1日</span> )</span>
													</span>

													<a class="label todo-proj" title="(新)-智能工厂 - 知识库（新）" data-stack="true" href="">(新)-智能工厂 - 知识库（新）</a>
												</div>
											</li>

											<li class="todo completed" data-guid="dc27a0e8a1c742589838399a67a86088" data-sort="2048.0" data-sequence-mine="0.0" data-sort-url="/projects/8f766bfdbe614633b7170027e8165a55/todos/dc27a0e8a1c742589838399a67a86088/reorder" data-project-guid="8f766bfdbe614633b7170027e8165a55" data-project-name="(新)-智能工厂" data-creator-guid="859f3810cf53463392c1edb48c5e43db" data-assignee-guid="bb61bd9f3d3d462da97b3a4476204034" data-updated-at="1528522260" data-check-items-size="4" data-completed-check-items-size="4" data-closed-at="1526342855">

												<div class="todo-actions actions">
													<div class="inr" style="display: none;">

													</div>
												</div>

												<div class="todo-wrap">
													<div class="simple-checkbox checked" style="height: 18px; width: 18px;">
														<div class="checkbox-container" style="border: 1.8px solid;">
															<div class="checkbox-tick" style="border-right: 2.52px solid; border-bottom: 2.52px solid;">
															</div>
														</div>
														<input type="checkbox" name="todo-done" checked="" class="checkbox-input" style="display: none;"></div>

													<span class="todo-content">
      <span class="raw">知识库系统</span>
													<span class="content-non-linkable">
        <span class="todo-rest">知识库系统</span>
													</span>
													<span class="content-linkable">
        <a class="todo-rest" data-stack="true" href="https://tower.im/projects/8f766bfdbe614633b7170027e8165a55/todos/dc27a0e8a1c742589838399a67a86088">知识库系统</a>
      </span>
													<div class="progress-wrap">
														<span class="progress-pie" title="100%" data-pie="100"></span>
														<span class="todo-progress" title="总共有 4 个检查项，已完成 4 个">(4/4)</span>
													</div>
													</span>

													<span class="todo-detail">
        <span class="label completed-member">( 凌小平 <span class="completed-time" data-readable-time="2018-05-15T08:07:35+08:00">5月15日</span> )</span>
													</span>

													<a class="label todo-proj" title="(新)-智能工厂 - 知识库（新）" data-stack="true" href="">(新)-智能工厂 - 知识库（新）</a>
												</div>
											</li>

										</ul>

									</div>

								</div>
							</div>

								</div>


						<div class="comments streams">
							<c:forEach var="b" items="${requestScope.files }">
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
															<a class="link-download"></a><span class="-rest">${b.file_name }</span></a>
														</div>
														<div class="tags"></div>
														<div class="control-dir no-dir">
															<a class="link-change-dir"
																onclick="display('${b.file_code }','${b.file_name }')" target="_blank">预览</a>
															<c:if test="${b.file_permission eq 'download' }">
																<a onclick="downloadFile('${b.file_code }','${b.file_level }')" class="link-change-dir">下载</a>
															</c:if>
														</div>
													</div>
												</div>
											</div>
										</div>
									</div>
								</div>
							</c:forEach>
							<div class="tpl-comment-actions-menu" style="display: none;">
							</div>
						</div>
					</div>

				</div>
				<div class="detail-actions">
							<div class="item detail-star-action">
								<a class="detail-action detail-action-star" title="关注" data-remote="true" data-itemtype="Todolist" data-itemid="7552347" data-loading="true" rel="nofollow" data-method="post" href="">返回上一层</a>
							</div>

							<div class="item">
								<a class="detail-action detail-action-edit" href="javascript:;">添加子项目</a>
							</div>

							<div class="item">
								<a class="detail-action detail-action-archive" title="请确认清单内任务都已完成" data-method="put" data-remote="true" data-loading="true" href="">分享</a>
							</div>

						</div>
			</div>
			<div class="footer">© 商务智能部</div>
		</div>
	<form id="displayForm" action="build/generic/web/viewer.html" method="get" target="_blank">
   		<input id="displayValues" type="hidden" name="file" value="/xh_bi_b_knowledge_base/file/disPdf.do?file_info=F201806221421466073,新海知识库数据库结构.xlsx"/>
    </form>   
	<script type="text/javascript" src="assets/js/jquery-3.3.1.min.js"></script>
	<script type="text/javascript">
				function display(code,name){
					var str = "/xh_bi_b_knowledge_base/file/disPdf.do?file_info="+code+","+name;
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
			</script>

</body>

</html>