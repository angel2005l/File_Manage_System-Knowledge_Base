<%@ include file="/view/base/base.jsp" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="zh-cmn-Hans" class="translated-ltr">
	<head>
	<base href="<%=basePath %>" >
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">

		<title>创建新项目- Tower</title>
		<meta name="renderer" content="webkit">
		<link type="text/css" rel="stylesheet" href="assets/css/translateelement.css">
	</head>
	<body >
		<div class="wrapper">
			<div class="header">
				<div class="header-container">
					<h1 class="logo">
        <a class="header-team-name" data-stack="true" data-stack-root="true" href=""><font style="vertical-align: inherit;"><font style="vertical-align: inherit;">新海科技集团</font></font></a>
    </h1>
					<ul class="nav">
						<li class="" id="nav-project">
							<a href="" data-stack="" data-stack-root="">
								<font style="vertical-align: inherit;">
									<font style="vertical-align: inherit;">项目</font>
								</font>
							</a>
						</li>
						<li class="" id="nav-me">
							<a href="" data-stack="" data-stack-root="">
								<font style="vertical-align: inherit;">
									<font style="vertical-align: inherit;">我自己</font>
								</font>
							</a>
						</li>
						<li id="nav-upgrade">
						</li>
					</ul>
					<div class="command-bar">
						<div class="search-wrap">
							<a href="javascript:;" class="link-search" title="搜索"><i class="twr twr-search"></i></a>
							<form id="form-search" class="form" method="get" action="">
								<input id="txt-search" type="text" class="keyword no-border" name="keyword" placeholder="搜索" autocomplete="off">
							</form>
						</div>
						<div class="notification-info">
							<a href="javascript:;" id="notification-count" class="label" title="新的通知" data-unread-count="0" data-url="/teams/66fb82b17f5341cbb0f5f39a1064c013/notifications/unread_counts">
								<span class="twr twr-bell-o bell"></span>
								<span class="num"><font style="vertical-align: inherit;"><font style="vertical-align: inherit;">0</font></font></span>
							</a>
							<div class="noti-pop">
								<div class="noti-pop-hd">
									<b class="title"><font style="vertical-align: inherit;"><font style="vertical-align: inherit;">通知</font></font></b>
									<a class="mark-as-read" id="noti-mark-read" data-loading="true" data-remote="true" rel="nofollow" data-method="post" href="https://tower.im/teams/66fb82b17f5341cbb0f5f39a1064c013/notifications/read_all">
										<span class="twr twr twr-check"></span>
										<font style="vertical-align: inherit;">
											<font style="vertical-align: inherit;">
												全部标记为已读
											</font>
										</font>
									</a>
								</div>
								<div class="noti-pop-list-wrap">
									<div class="noti-pop-list notification-list">

									</div>
								</div>
								<div class="noti-pop-empty">
									<font style="vertical-align: inherit;">
										<font style="vertical-align: inherit;">- 没有新通知 -</font>
									</font>
								</div>
								<div class="noti-pop-footer">
									<a class="noti-settings" data-stack="true" data-stack-root="true" href="">
										<i class="twr twr-cog"></i>
										<font style="vertical-align: inherit;">
											<font style="vertical-align: inherit;">通知设置
											</font>
										</font>
									</a>
									<a class="noti-all-link" data-stack="true" data-stack-root="true" href="">
										<font style="vertical-align: inherit;">
											<font style="vertical-align: inherit;">查看全部通知</font>
										</font>
									</a>
								</div>
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
					<div class="header-menu-group">
						<div class="header-menu-item">
							<a href="/launchpad?skip=1">+创建新团队</a>
						</div>
					</div>
					<div class="header-menu-group-secondary">
						<div class="header-menu-item">
							<a target="_blank" href="/guides">
								<i class="header-menu-icon header-menu-icon-guides"></i> 新手入门
							</a>
						</div>
						<div class="header-menu-item">
							<a target="_blank" href="/help">
								<i class="header-menu-icon header-menu-icon-help"></i> 帮助中心
							</a>
						</div>
						<div class="header-menu-item">
							<a target="_blank" href="/teams/66fb82b17f5341cbb0f5f39a1064c013/upgrade">
								<i class="header-menu-icon header-menu-icon-pro"></i> 了解 Pro 版
							</a>
						</div>
						<div class="header-menu-item">
							<a target="_blank" href="/downloads">
								<i class="header-menu-icon header-menu-icon-download"></i> 下载客户端
							</a>
						</div>
						<div class="header-menu-item">
							<a id="link-roadmap" target="_blank" href="/roadmap">
								<i class="header-menu-icon header-menu-icon-roadmap"></i> Tower 新功能
							</a>
						</div>
						<div class="header-menu-item">
							<a target="_blank" href="/choices">
								<i class="header-menu-icon header-menu-icon-choices"></i> 企业服务精选
							</a>
						</div>
					</div>
					<div class="header-menu-group">
						<div class="header-menu-item">
							<a rel="nofollow" data-method="DELETE" href="/users/sign_out">退出登录</a>
						</div>
					</div>
				</div>
			</script>

			<div class="container workspace simple-stack simple-stack-transition">
				<div class="page page-1 simple-pjax">
					<div class="page-inner" id="page-new-project" data-page-name="创建新项目">
						<h3 class="page-title"><font style="vertical-align: inherit;"><font style="vertical-align: inherit;">创建新项目</font></font></h3>

						<form class="form form-invite" action="" method="post">
							<div class="form-item">
								<div class="form-field">
									<input type="text" name="project_name" id="project-name" placeholder="项目名称" autofocus="" data-validate="required;length:1,32" data-validate-msg="请填写项目名称;项目名称最长32个字符">
								</div>
							</div>

							<div class="form-item">
								<div class="form-field">
									<textarea name="project_desc" id="project-desc" placeholder="简单描述项目，便于其他人理解（选填）" data-validate="length:0,255" data-validate-msg="项目描述最长255个字符"></textarea>
								</div>
							</div>
							<div class="setting-section select-section">
								<h4><font style="vertical-align: inherit;"><font style="vertical-align: inherit;">项目类型</font></font></h4>
								<label class="project-radio"><input type="radio" name="project_type" value="0" checked="">
									<font style="vertical-align: inherit;">部门项目</font>
									<small><font style="vertical-align: inherit;">更好地组织、细分和管理任务，适用于一般项目管理</font></small>
        						</label>
								<label class="project-radio">
          							<input type="radio" name="project_type" value="1">
          							<font style="vertical-align: inherit;">个人项目</font>
          							<small><font style="vertical-align: inherit;">试用版本，暂不支持个人项目</font></small>
       							 </label>
							</div>

							<div class="setting-section">
								<h4><font style="vertical-align: inherit;">选择项目参与成员</font></h4>
								<p class="desc">
									<font style="vertical-align: inherit;">
										<font style="vertical-align: inherit;">管理员可以邀请和移除项目参与成员，只有被邀请的团队成员才能访问该项目的信息。</font>
									</font>
								</p>
								<div class="manage-members-tabs">

									<div class="tab active" data-tab="team">
										<div class="manage-members">
											<div class="add-member">
											</div>

											<div class="members member-checkbox-list">
												<c:forEach var="${userList }" items="b">
												<label title="包雪丹" class="member">
                              				  		<input type="checkbox" name="" id="" value="${b.userCode }">
                                					<span class="name"><font style="vertical-align: inherit;"><font style="vertical-align: inherit;">包雪丹</font></font></span>
                       					 		</label>
												</c:forEach>
											</div>
										</div>
									</div>

								</div>
							</div>
							<div class="setting-section">
								<h4><font style="vertical-align: inherit;">选择项目只读成员</font></h4>
								<p class="desc">
									<font style="vertical-align: inherit;">
										<font style="vertical-align: inherit;">管理员可以邀请和移除项目只读成员，只有被邀请的团队成员才能访问该项目的信息。</font>
									</font>
								</p>
								<div class="manage-members-tabs">
									<div class="tab active" data-tab="team">
										<div class="manage-members">
											<div class="add-member">
											</div>
											<div class="members member-checkbox-list">
											<c:forEach var="${userList }" items="b">
												<label title="包雪丹" class="member">
                              				  		<input type="checkbox" name="" id="" value="044914a80b7940839268b6e2bc70cfb5">
                                					<span class="name"><font style="vertical-align: inherit;"><font style="vertical-align: inherit;">包雪丹</font></font></span>
                       					 		</label>
											</c:forEach>
											</div>
										</div>
									</div>

								</div>
							</div>

							<div class="form-buttons">
								<button type="submit" class="btn btn-primary" id="btn-create-project" data-disable-with="正在创建..." data-success-text="创建成功"><font style="vertical-align: inherit;"><font style="vertical-align: inherit;">创建项目</font></font></button>
								<a href="退回主页" class="btn btn-x" data-stack="" data-stack-root="">
									<font style="vertical-align: inherit;">
										<font style="vertical-align: inherit;">取消</font>
									</font>
								</a>
							</div>
							<input type="hidden" name="project_level" value="${projectLevel }">
							<input type="hidden" name="project_parent_code" value="${projectParentCode }">
							
						</form>
					</div>
				</div>
			</div>
			<div class="footer">
				<font style="vertical-align: inherit;">
					<font style="vertical-align: inherit;">
						© 商务智能部
					</font>
				</font>
			</div>
		</div>
	</body>

</html>