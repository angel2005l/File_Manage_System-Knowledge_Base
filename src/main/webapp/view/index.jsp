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

<body onload="toload()">
	<div class="wrapper">
		<div class="header">
			<div class="header-container">
				<h1 class="logo">
					<a class="header-team-name" href="pro/index.do?method=participation"><font style="vertical-align: inherit;"><font
							style="vertical-align: inherit;">新海科技集团</font></font></a>
				</h1>
				<div class="command-bar">
				<div class="notification-info" id="both">
					<!-- 如果有未读的  显示label unread  否则显示label -->
			        <span id="notification-count" title="新的通知" onclick="intoclick()" class="label">
			          <span class="twr twr-bell-o bell"></span>
			          <span class="num" id="num"></span>
			        </span>
			        <div class="noti-pop" id="thediv" style="display:none;" onblur="losePoint()"><!-- tabindex="0" -->
			          <div class="noti-pop-hd">
			            <b class="title">通知</b>
			            <a class="mark-as-read" id="noti-mark-read" href="javascript:;" onclick="allread()">
			              <span class="twr twr twr-check"></span>全部标记为已读</a>          
			          </div>
			          <div class="noti-pop-list-wrap">
				            <div class="noti-pop-list notification-list" style="display: block;">
				            	<div class="notice unread"><a class="link" id="msg"></a></div>
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
			<div class="page page-root simple-pjax">

				<div class="page-inner" id="page-projects"
					data-page-name="新海科技集团的项目">
					<div class="projects-tools">
						<div class="project-groups">
							<a class="project-group" title="我的收藏" href="pro/index.do?method=collect">我的收藏 <i class="twr twr-star"></i></a> 
							<a class="project-group" title="我的项目" href="pro/index.do?method=self">我的项目</a> 
							<a class="project-group" title="参与项目" href="pro/index.do?method=participation">参与项目</a>
							<a class="project-group" title="参与项目" href="pro/index.do?method=participation">锁定项目</a>
							<input type="text" class="keyword" style="display:inline-block; width: 150px; margin-right: 10px;"
								 name="form_file_name" placeholder="请输入项目名称关键字" autocomplete="off">
							<button type="button" class="btn btn-primary"  onclick="formSubmit()"> 
								<font style="vertical-align: inline;">搜索</font>
							</button>
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
							<a class="project pin c2 i19"
								href="file/pfd.do?project_code=${b.project_main_code }&project_level=0">
								<span class="badge"></span> <span class="name"><font
									style="vertical-align: inherit;"><font
										style="vertical-align: inherit;">${b.project_main_name }</font></font></span>
								<span
								class="pin-icon<c:if test="${b.project_is_collect =='Y' }">-c</c:if>"
								title="星标" main_code="${b.project_main_code }"></span>
							</a>
						</c:forEach>
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
			className = className == 'pin-icon' ? "pin-icon-c" : "pin-icon"
			var isCollect = className == 'pin-icon-c' ? "Y" : "N"
			if (mainProjectCode != undefined && mainProjectCode != '') {
				$.ajax({
					url : 'pro/collect.do',
					type : 'post',
					data : {
						"is_collect" : isCollect,
						"project_main_code" : mainProjectCode
					},
					dateType : 'json',
					success : function(result) {
						collectObj.attr("class", className);
					},
					error : function() {
						alert("服务器未响应");
					}
				})
			}
			//阻止冒泡
			event.stopPropagation();
			event.preventDefault();
		})
		function intoclick(){
			$("#thediv").toggle();
		}
		function losePoint(){
			$("#thediv").hide();
		}
		function allread(){
			var list=[];
			for(var i=0;i<$(".adviceCode").length;i++){
				list.push($(".adviceCode")[i].innerHTML)
			};
			$.ajax({
				url:'pro/isRead.do',
				type:'post',
				data:"list="+JSON.stringify(list),
				dateType:'json',
				success:function(){
					toload();
				}
			})
		}
		function toload(){
			$.ajax({
				url:'index/getAllMsg.do',
				type:'post',
				data:"",//发送服务器的数据
    			dataType:"json",//返会值的类型
				success:function(data){
			        var str="";
					if(data.data.adNum!=0){
						$('#num').html(data.data.adNum);
						$("#notification-count").attr("class", "label unread");
						for(var i=0;i<data.data.adviceMsg.length;i++){
							str+="<span class='title'><span class='target'>"+data.data.adviceMsg[i].logMsg+"</span><span class='adviceCode' style='display: none'>"+data.data.adviceMsg[i].adviceCode+"</span></span><hr style='margin:0px 0px 3px 0px'>";
						};
						$("#msg").html(str);
					}else{
						$("#notification-count").attr("class", "label");
						$("#msg").html("<span style='width:100%;text-align:center;display:block;'>- 没有新通知 -</span>");
					}
				}
			})	
		}
		$(document).bind('click', function(e) {
				var e = e || window.event; //浏览器兼容性 
				var elem = e.target || e.srcElement;
				while (elem) { //循环判断至跟节点，防止点击的是div子元素 
					if (elem.id && elem.id == 'both') {
						return;
					}
					elem = elem.parentNode;
				}
				$('#thediv').css('display', 'none'); //点击的不是div或其子元素 
			});
		
	</script>
</body>
</html>