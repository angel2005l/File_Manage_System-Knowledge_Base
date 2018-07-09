<%@ include file="/view/base/base.jsp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="zh-cmn-Hans" class="translated-ltr">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<title>知识库-主页</title>
<meta name="renderer" content="webkit">
<script type="text/javascript" src="assets/js/analytics.js"></script>
<link type="text/css" rel="stylesheet"
	href="assets/css/translateelement.css">
</head>

<body>
	<div class="wrapper">
		<div class="header">
			<div class="header-container">
				<h1 class="logo">
					<a class="header-team-name" data-stack="true"
						data-stack-root="true"><font style="vertical-align: inherit;"><font
							style="vertical-align: inherit;">新海科技集团</font></font></a>
				</h1>
				<ul class="nav">
					<li id="nav-project"><a href=""> <font
							style="vertical-align: inherit;"> <font
								style="vertical-align: inherit;">项目</font>
						</font>
					</a></li>
					<li class="" id="nav-me"><a href=""> <font
							style="vertical-align: inherit;"> <font
								style="vertical-align: inherit;">我自己</font>
						</font>
					</a></li>

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
				</div>
			</div>
		</div>
		<div class="container workspace simple-stack simple-stack-transition">
			<div class="page page-root simple-pjax">

				<div class="page-inner" id="page-projects"
					data-page-name="新海科技集团的项目">
					<div class="projects-tools">
						<div class="project-groups">
							<a class="project-group" title="我的收藏"
								href="pro/index.do?method=collect">我的收藏 <i
								class="twr twr-star"></i></a> <a class="project-group" title="我的项目"
								href="pro/index.do?method=self">我的项目</a> <a
								class="project-group" title="参与项目"
								href="pro/index.do?method=participation">参与项目</a>
							<div class="project-tools-right">
								<a class="create-project" href="pro/insProJsp.do"> <font
									style="vertical-align: inherit;"> <font
										style="vertical-align: inherit;"> 新建项目 </font>
								</font>
								</a>

							</div>
						</div>

					</div>

					<div class="projects grid-view ui-sortable">
						<c:forEach var="b" items="${projectList }">
							<a class="project pin c2 i19" href="file/pfd.do?project_code=${b.project_main_code }"> <span class="badge"></span>
								<span class="name"><font style="vertical-align: inherit;"><font
										style="vertical-align: inherit;">${b.project_main_name }</font></font></span>
								<span
								class="pin-icon<c:if test="${b.project_is_collect =='Y' }">-c</c:if>"
								title="星标" main_code="${b.project_main_code }"></span> <!-- onclick="isCollect('${b.project_main_code }')" -->
							</a>
						</c:forEach>
					</div>
					<div class="projects-footer">
						<!--<a href="https://tower.im/teams/66fb82b17f5341cbb0f5f39a1064c013/project_templates" data-stack="" data-visible-to="member">
								<font style="vertical-align: inherit;">
									<font style="vertical-align: inherit;">管理项目模板</font>
								</font>
							</a>

							<a href="https://tower.im/teams/66fb82b17f5341cbb0f5f39a1064c013/archived_projects" data-stack="">
								<font style="vertical-align: inherit;">
									<font style="vertical-align: inherit;">管理已归档项目</font>
								</font>
							</a>-->

					</div>

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
		$(".pin-icon,.pin-icon-c").click(function(event) {
			
			var collectObj = $(this);
			var mainProjectCode = collectObj.attr("main_code");
			
			var className = collectObj.attr("class");
			className =  className == 'pin-icon' ? "pin-icon-c":"pin-icon"
			var isCollect = className == 'pin-icon-c' ? "Y":"N"
			if (mainProjectCode != undefined && mainProjectCode != '') {
				$.ajax({
					url:'pro/collect.do',
					type:'post',
					data:{"is_collect":isCollect,"project_main_code":mainProjectCode},
					dateType:'json',
					success:function(result){
						collectObj.attr("class",className);
					},
					error:function(){
						alert("服务器未响应");
					}
				})
			}
			//阻止冒泡
			event.stopPropagation(); 
			event.preventDefault();
		})
	</script>
</body>
</html>