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
									<a class="mark-as-read" id="noti-mark-read" data-loading="true" data-remote="true" rel="nofollow" data-method="post" href="https://tower.im/teams/66fb82b17f5341cbb0f5f39a1064c013/notifications/read_all">
										<span class="twr twr twr-check"></span> 全部标记为已读
									</a>
								</div>
								<div class="noti-pop-list-wrap">
									<div class="noti-pop-list notification-list">

									</div>
								</div>
								<div class="noti-pop-empty">- 没有新通知 -</div>
								<div class="noti-pop-footer">
									<a class="noti-settings" data-stack="true" data-stack-root="true" href="https://tower.im/members/3b3a614642fc499bbce9a48e5c7672aa/notification_settings">
										<i class="twr twr-cog"></i> 通知设置
									</a>
									<a class="noti-all-link" data-stack="true" data-stack-root="true" href="https://tower.im/teams/66fb82b17f5341cbb0f5f39a1064c013/notifications">查看全部通知</a>
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

			<script id="tpl-header-menu" type="text/html">
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
			</script>

			<div class="container workspace simple-stack simple-stack-transition">
				<div class="page page-root simple-pjax page-behind" style="">
					<a class="link-page-behind" data-stack="" href="" data-stack-fluid="">面包屑</a>
				</div>
				<div class="page page-root simple-pjax page-behind" style="">
					<a class="link-page-behind" data-stack="" href="" data-stack-fluid="">面包屑</a>
				</div>
				<div class="page page-root simple-pjax page-behind" style="">
					<a class="link-page-behind" data-stack="" href="" data-stack-fluid="">面包屑</a>
				</div>
				<div class="page page-root simple-pjax page-behind" style="">
					<a class="link-page-behind" data-stack="" href="" data-stack-fluid="">面包屑</a>
				</div>
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
        <span class="todolist-rest">知识库（新）</span>
    </span>
    <span class="name">
            <span class="todolist-todos-count">1</span>
        <a class="todolist-rest" data-stack="true" href="">项目名称</a>
    </span>
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

												<div class="todo-actions actions">
													<div class="inr">
														<a class="run" title="标记成正在进行中" data-loading="true" data-remote="true" data-method="post" href="https://tower.im/projects/8f766bfdbe614633b7170027e8165a55/todos/62a2464f74964a1a8581bcccad958b89/running" style="display: none;">执行</a>
														<a class="pause" title="暂停" data-loading="true" data-remote="true" data-method="post" href="https://tower.im/projects/8f766bfdbe614633b7170027e8165a55/todos/62a2464f74964a1a8581bcccad958b89/pause" style="display: inline;">暂停</a>
														<a href="https://tower.im/projects/8f766bfdbe614633b7170027e8165a55/todos/62a2464f74964a1a8581bcccad958b89/edit" class="edit" data-request-members="8f766bfdbe614633b7170027e8165a55" title="编辑">编辑</a>

													</div>
												</div>

												<div class="todo-wrap">
													<div class="simple-checkbox" style="height: 18px; width: 18px;">
														<div class="checkbox-container" style="border: 1.8px solid;">
															<div class="checkbox-tick" style="border-right: 2.52px solid; border-bottom: 2.52px solid;">
															</div>
														</div>
														<input type="checkbox" name="todo-done" class="checkbox-input" style="display: none;"></div>
													<span class="todo-content">
     													 <span class="raw">知识库 新版本</span>
													<span class="content-non-linkable">
															<span class="todo-rest">知识库 新版本</span>
													</span>
													<span class="content-linkable">
        													<a class="todo-rest" data-stack="true" href="https://tower.im/projects/8f766bfdbe614633b7170027e8165a55/todos/62a2464f74964a1a8581bcccad958b89">知识库 新版本</a>
      													</span>
													<div class="progress-wrap">
														<span class="progress-pie" title="60%" data-pie="60"></span>
														<span class="todo-progress" title="总共有 10 个检查项，已完成 6 个">(6/10)</span>
													</div>
													</span>

													<span class="todo-detail">
         												<a class="label todo-assign-due" href="javascript:;" data-request-members="8f766bfdbe614633b7170027e8165a55">
              											<span class="assignee" data-guid="3b3a614642fc499bbce9a48e5c7672aa" data-gavatar="https://avatar.tower.im/1a4f53e4f9af48a6b20ae4a24cd6d5d9">项目创建人 </span>
													</a>
													<span class="label comments-count">12条评论</span>
													<a class="label comments-count" href="https://tower.im/projects/8f766bfdbe614633b7170027e8165a55/todos/62a2464f74964a1a8581bcccad958b89" data-stack="">
														12份文件
													</a>

													</span>

													<a class="label todo-proj" title="(新)-智能工厂 - 知识库（新）" data-stack="true" href="https://tower.im/projects/8f766bfdbe614633b7170027e8165a55/lists/fcf031d5c79a43768df1daacd52f00de/show">(新)-智能工厂 - 知识库（新）</a>
												</div>
											</li>

										</ul>

										<ul class="todo-new-wrap"></ul>
										<a href="javascript:;" class="btn-new-todo" data-url="/projects/8f766bfdbe614633b7170027e8165a55/lists/fcf031d5c79a43768df1daacd52f00de" data-request-members="8f766bfdbe614633b7170027e8165a55">添加新任务</a>

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
        <a class="todo-rest" data-stack="true" href="https://tower.im/projects/8f766bfdbe614633b7170027e8165a55/todos/5c8583122f27477f81c90f83c8484835">知识库审核</a>
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

													<a class="label todo-proj" title="(新)-智能工厂 - 知识库（新）" data-stack="true" href="https://tower.im/projects/8f766bfdbe614633b7170027e8165a55/lists/fcf031d5c79a43768df1daacd52f00de/show">(新)-智能工厂 - 知识库（新）</a>
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
        <a class="todo-rest" data-stack="true" href="https://tower.im/projects/8f766bfdbe614633b7170027e8165a55/todos/210bda932564477cbf8fdd6360b22cdc">新知识库原型</a>
      </span>
													</span>

													<span class="todo-detail">
        <span class="label completed-member">( 章晓莲 <span class="completed-time" data-readable-time="2018-06-01T08:38:22+08:00">6月1日</span> )</span>
													</span>

													<a class="label todo-proj" title="(新)-智能工厂 - 知识库（新）" data-stack="true" href="https://tower.im/projects/8f766bfdbe614633b7170027e8165a55/lists/fcf031d5c79a43768df1daacd52f00de/show">(新)-智能工厂 - 知识库（新）</a>
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

													<a class="label todo-proj" title="(新)-智能工厂 - 知识库（新）" data-stack="true" href="https://tower.im/projects/8f766bfdbe614633b7170027e8165a55/lists/fcf031d5c79a43768df1daacd52f00de/show">(新)-智能工厂 - 知识库（新）</a>
												</div>
											</li>

										</ul>

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
												<div class="images">
												</div>
												<div class="others">
													<div class="attachment">
														<div class="attachment-thumb">
															<img  src="assets/img/<tag:enum className="FileTypeImgEnum">${b.file_type }</tag:enum>">
														</div>
														<div class="attachment-info">
															<div class="name">
																<a class="link-download"></a><span class="-rest">${b.file_name }</span></a>
															</div>
															<div class="tags">

															</div>
															<div class="control-dir no-dir">
																<a class="link-change-dir" onclick="display(${b.file_code },${b.file_level })">预览</a>
																<c:if test="${b.file_permission eq 'download' }"><a onclick="download(${b.file_code },${b.file_level })" class="link-change-dir" >下载</a></c:if>
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
								<a class="detail-action detail-action-star" title="关注" data-remote="true" data-itemtype="Todolist" data-itemid="7552347" data-loading="true" rel="nofollow" data-method="post" href="https://tower.im/projects/8f766bfdbe614633b7170027e8165a55/lists/fcf031d5c79a43768df1daacd52f00de/star?muid=fcf031d5c79a43768df1daacd52f00de">关注</a>
							</div>

							<div class="item">
								<a class="detail-action detail-action-edit" href="javascript:;">编辑</a>
							</div>

							<div class="item">
								<a class="detail-action detail-action-archive" title="请确认清单内任务都已完成" data-method="put" data-remote="true" data-loading="true" href="https://tower.im/projects/8f766bfdbe614633b7170027e8165a55/lists/fcf031d5c79a43768df1daacd52f00de/toggle_archived">归档</a>
							</div>

						</div>

						<script type="text/html" id="tpl-todo-form">

							<li class="todo-form">
								<form class="form form-edit-todo" method="post" data-remote="true">
									<div class="form-body">
										<input type="checkbox" name="todo-done" disabled />
										<textarea name="todo_content" class="todo-content no-border" placeholder="新的任务" data-validate="custom" data-validate-msg=""></textarea>

										<div class="todo-toolbar">
											<a href="javascript:;" class="add-tag" title="点击添加标签">#</a>
											<a href="javascript:;" class="add-priority" title="点击添加优先级">!</a>
										</div>

										<a href="javascript:;" class="todo-label">
											<span class="assignee">未指派</span> ·
											<span class="due">没有截止时间</span>
										</a>
									</div>
									<div class="buttons create-buttons">
										<button type="submit" class="btn btn-primary btn-create-todo" data-disable-with="正在保存...">添加任务</button>
										<a href="javascript:;" class="btn-cancel-todo">取消</a>
									</div>
									<div class="buttons edit-buttons">
										<button type="submit" class="btn btn-primary btn-update-todo" data-disable-with="正在保存...">保存</button>
										<a href="javascript:;" class="btn-cancel-update-todo">取消</a>
									</div>

									<input type="hidden" name="assignee_guid" class="hidden-assignee" value="-1" />
									<input type="hidden" name="due_at" class="hidden-due-date" value="" />
								</form>
							</li>

						</script>
						<!--下拉菜单的指派-->
						<!--<script type="text/html" id="tpl-todo-popover">
							<div class="todo-popover">
								<div class="select-assignee">
									<h3>将任务指派给</h3>
									<div class="assignee-wrapper">
										<select id="txt-assignee"></select>
									</div>
								</div>

								<div class="select-due-date">
									<h3>任务截止时间</h3>
									<div class="due-date-wrapper">
										<input type="text" class="txt-due-date" placeholder="选择截止时间" />
										<a href="javascript:;" class="link-remove-due-date" title="取消截止时间"><span class='twr twr-times'></span></a>

										<div class="due-date-picker">
											<div class="cal-wrapper">
												<div class="cal-shortcuts">
													<a href="javascript:;" class="link-cal-shortcut today">[今天]</a>
													<a href="javascript:;" class="link-cal-shortcut tomorrow">[明天]</a>
													<a href="javascript:;" class="link-cal-shortcut this-friday">[本周]</a>
													<a href="javascript:;" class="link-cal-shortcut next-friday">[下周]</a>
												</div>
												<input type="date" class="hidden-due-date" />
											</div>
											<div class="shortcuts-wrapper">
												<a href="javascript:;" class="link-date-shortcut today" data-shortcut="today|td|jintian|jt|今天">今天</a>
												<a href="javascript:;" class="link-date-shortcut tomorrow" data-shortcut="tomorrow|tm|mingtian|mt|明天">明天</a>
												<a href="javascript:;" class="link-date-shortcut after-tomorrow" data-shortcut="houtian|ht|dat|后天">后天</a>
												<a href="javascript:;" class="link-date-shortcut monday" data-shortcut="monday|zhouyi|xingqiyi|zy|xqy|周一|星期一|1">周一</a>
												<a href="javascript:;" class="link-date-shortcut tuesday" data-shortcut="tuesday|zhouer|xingqier|ze|xqe|周二|星期二|2">周二</a>
												<a href="javascript:;" class="link-date-shortcut wednesday" data-shortcut="wednesday|zhousan|xingqisan|zs|xqs|周三|星期三|3">周三</a>
												<a href="javascript:;" class="link-date-shortcut thursday" data-shortcut="thursday|zhousi|xingqisi|zs|xqs|周四|星期四|4">周四</a>
												<a href="javascript:;" class="link-date-shortcut friday" data-shortcut="friday|zhouwu|xingqiwu|zw|xqw|周五|星期五|5">周五</a>
												<a href="javascript:;" class="link-date-shortcut saturday" data-shortcut="saturday|zhouliu|xingqiliu|zl|xql|周六|星期日|6">周六</a>
												<a href="javascript:;" class="link-date-shortcut sunday" data-shortcut="sunday|zhouri|xingqiri|zr|xqr|周日|星期日|7">周日</a>
											</div>
										</div>
									</div>
								</div>
							</div>

						</script>-->


						<script type="text/html" id="comments-liked-list">

						</script>
					</div>
			</div>
			<div class="footer">
				© 商务智能部
			</div>
			</div>
	</body>

</html>