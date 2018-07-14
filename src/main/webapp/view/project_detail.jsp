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
<link rel="stylesheet" href="assets/css/bootstrap.min.css">
<link rel="stylesheet"
	href="assets/css/bootstrap/bootstrap-datetimepicker.min.css">
<link rel="stylesheet" media="all" href="assets/css/xh_application.css">
<link rel="stylesheet" href="assets/theme/default/layer.css" />
<style type="text/css">
</style>
</head>

<body onload="toload()">
	<div class="wrapper">
		<div class="header">
			<div class="header-container">
				<h1 class="logo">
					<a class="header-team-name js-show-focus-driver"
						href="javascript:;"><font style="vertical-align: inherit;"><font
							style="vertical-align: inherit;">新海科技集团</font></font></a>
				</h1>
				<ul class="nav">
					<li class="" id="nav-project"><a href="" data-stack=""
						data-stack-root="">项目</a></li>
					<li class="" id="nav-me"><a href="" data-stack=""
						data-stack-root="">我自己</a></li>
					<li id="nav-upgrade"></li>
				</ul>
				<div class="command-bar">
				<div class="notification-info" id="both">
					<!-- 如果有未读的  显示label unread  否则显示label -->
			        <a id="notification-count" title="新的通知" onclick="intoclick()" href="javascript:;" class="label">
			          <span class="twr twr-bell-o bell"></span>
			          <span class="num" id="num"></span>
			        </a>
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
      			<div class="account-info"></div>
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
															<img
																src="assets/img/<tag:enum className="FileTypeImgEnum">${b.file_type }</tag:enum>" />
														</div>
														<div class="attachment-info">
															<div class="name">
																<a class="link-download"><span class="-rest">${b.file_name }</span></a>
																<p class=" detail">${b.file_info }</p>
																<%-- <p class="detail-p"><a class="link-download">${b.file_info }</a></p> --%>
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
								</c:forEach>
								<button id="batch_download" type="button" class="btn btn-reject"
									style="float: left;">
									<font style="vertical-align: inline;">批量下载</font>
								</button>
								<button id="batch_enter" type="button" class="btn btn-reject"
									style="float: left; display: none" batch_method="">
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
							</div>
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
				<c:if test="${'write' eq projectInfo.projectPermission }">
					<div class="item">
						<a class="detail-action detail-action-star" title="文件上传"
							href="file/insFileJsp.do?project_code=${projectInfo.projectCode }&project_level=${projectInfo.projectLevel }">文件上传</a>
					</div>
					<div class="item">
						<a class="detail-action detail-action-edit"
							href="pro/insProJsp.do?project_code=${projectInfo.projectCode }&project_level=${projectInfo.projectLevel }">新增项目</a>
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
	<script type="text/javascript"
		src="assets/js/bootstrap-datetimepicker.js"></script>
	<script type="text/javascript"
		src="assets/js/bootstrap-datetimepicker.zh-CN.js"></script>
	<script type="text/javascript">
		$("#batch_download").on("click",function(){
			var bfcObj = $(".batch-file-check");
			var displayValue = bfcObj.css("display");
			if("none" ==displayValue){
				bfcObj.css("display","");
				$(this).css("display","none");
				$("#batch_enter").css("display","").attr("batch_method","1");
				$("#batch_cancel").css("display","");
			}
		})
		
		$("#batch_share").on("click",function(){
			var bfcObj = $(".batch-file-check");
			var displayValue = bfcObj.css("display");
			if("none" ==displayValue){
				bfcObj.css("display","");
				$(this).css("display","none");
				$("#batch_enter").css("display","").attr("batch_method","2");
				$("#batch_cancel").css("display","");
			}
		})
		
		$("#batch_enter").on("click",function(){
			var batchMethod = $(this).attr("batch_method");
			var fileCodes = "";
			$(".batch-file-check.checked").each(function(){
				fileCodes  += $(this).attr("file_code")+",";
			})
			if(fileCodes.length <2){
				alert("文件未选择");
			}else {
				fileCodes = fileCodes.substr(0,fileCodes.length-1);
			}
			switch (batchMethod) {
			case "1":
				//批量下载
				downloadFiles(fileCodes,$('#hidden_project_level').val())
				break;
			case "2":
				//批量分享
				$.ajax({
					url:"file/insShareFiles.do",
					type:'post',
					data:{'project_code':$('#hidden_project_code').val(),'project_level':$('#hidden_project_level').val(),'file_codes':fileCodes},
					dataType:'json',
					success:function(result){
						if(result.code==0){
							shareFiles(result.data)
						}else{
							alert(result.msg);
						}
					},
					error:function(){
						alert("服务器未响应");
					}
				});
				break;
			}
		})
		
		$(".batch-file-check").on("click",function(){
			if($(this).hasClass("noChecked")){
				$(this).removeClass("noChecked").addClass("checked");
			}else{
				$(this).removeClass("checked").addClass("noChecked");
			}
		})
		
		$("#batch_cancel").on("click",function(){
			$(".batch-file-check").removeClass("checked").addClass("noChecked").css("display","none");
			$("#batch_enter").css("display","none").attr("batch_method","");
			$("#batch_cancel").css("display","none").attr("batch_method","")
			$("#batch_download,#batch_share").css("display","");
		})
		
		$('.form_datetime').datetimepicker({
			language : 'zh-CN',
			weekStart : 1,//星期几为周一
			autoclose : 1,
			startView : 4,
			minView : 2,
			maxView : 4,
			todayHighlight : true,
			todayBtn : true,
			format : "yyyy-mm-dd"
		});

		function checkDate() {

		}

		function formSubmit() {
			$('#search').submit();
		}

		$(".project-check").on('click', function() {
			var checkedObj = $(this);
			$.ajax({
				url : 'pro/priStatus.do',
				type : 'post',
				data : {
					'project_code' : checkedObj.attr("project_code"),
					'project_level' : checkedObj.attr("project_level")
				},
				dataType : 'json',
				success : function(result) {
					if (result.code == 0) {
						//checkedObj.addClass("checked");				
						location.reload();
					} else {
						alert(result.msg);
					}
				},
				error : function() {
					alert("服务器未响应")
				}

			})
		})

		function display(code, name) {
			var str = "${pageContext.request.contextPath }/file/disPdf.do?file_info="
					+ code + "," + name;
			$("#displayValues").val(str);
			$("#displayForm").submit();
		}

		function downloadFile(code, level) {
			$.ajax({
						url : "file/downloadCheck.do",
						type : "post",
						data : {
							"file_code" : code,
							"file_level" : level
						},
						dataType : 'json',
						success : function(data) {
							if (data.code === 0) {
								alert(data.data);
								window.location.href = "file/downloadFile.do?file_name="
										+ data.data;
							} else {
								alert(data.msg);
							}
						},
						error : function() {
							alert("服务器未响应")
						}
					})
		}
		
		function downloadFileCheck(code, level){
			var resultArray =new Array(2);
			resultArray[0] = -1;
			resultArray[1] = "";
			$.ajax({
				url : "file/downloadCheck.do",
				type : "post",
				async: false,
				data : {
					"file_code" : code,
					"file_level" : level
				},
				dataType : 'json',
				success :function(result){
					resultArray[0] = result.code;
					resultArray[1] = result.data;
					},
				error:function(){
					alert("服务器未响应");
					
				}
				})
				return resultArray;
		}
		
		function downloadFiles(fileCodes,level) {
			var fileCodesArray =  fileCodes.split(",");
			$.each(fileCodesArray,function(index,vaule){
				 var result = downloadFileCheck(vaule,level);
				if(result[0]==0){
					//window.location.href = "file/downloadFile.do?file_name="+ result[1];
					console.log("1");
					//window.open("file/downloadFile.do?file_name="+ result[1],"_parent");
					
					
					var newWin = window.open("file/downloadFile.do?file_name="+ result[1],"target");
					//window.open = "file/downloadFile.do?file_name="+ result[1];
					//window.open("file/downloadFile.do?file_name="+ result[1]);
				} 
			})			
		}
		
		
		function shareProject(projectCode, projectLevel, userCode) {
			var str = "${pageContext.request.scheme }://${pageContext.request.serverName }:${pageContext.request.serverPort }${pageContext.request.contextPath }/pro/sp.do?project_code="
					+ projectCode
					+ "&project_level="
					+ projectLevel
					+ "&user_code=" + userCode;
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
		function shareFile(fileCode, fileLevel, projectCode) {
			var str = "${pageContext.request.scheme }://${pageContext.request.serverName }:${pageContext.request.serverPort }${pageContext.request.contextPath }/file/sf.do?file_code="
					+ fileCode
					+ "&file_level="
					+ fileLevel
					+ "&project_code="
					+ projectCode;
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
		
		function shareFiles(shareCode) {
			var str = "${pageContext.request.scheme }://${pageContext.request.serverName }:${pageContext.request.serverPort }${pageContext.request.contextPath }/file/shareFiles.do?share_code="+ shareCode;
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
	<div id="share" style="display: none;">
		<span style="font-weight: bold; padding-right: 20px;">分享链接:</span>
		<textarea id="shareUrl"
			style="border: hidden; resize: none; overflow-y: hidden; width: 400px; height: 60px"
			disabled="disabled" rows="4"></textarea>
	</div>
</body>

</html>