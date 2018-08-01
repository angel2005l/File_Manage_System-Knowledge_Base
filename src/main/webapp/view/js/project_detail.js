$(function() {
	toload();
	changeFileEventType();
	changeFileEventLevel();
	changeResearchUserInfo();
})

$("#batch_download").on("click", function() {
	var bfcObj = $(".batch-file-check");
	var displayValue = bfcObj.css("display");
	if ("none" == displayValue) {
		bfcObj.css("display", "");
		$(this).css("display", "none");
		$("#batch_enter").css("display", "").attr("data-batch-method", "1");
		$("#batch_cancel").css("display", "");
	}
})

$("#batch_share").on("click", function() {
	var bfcObj = $(".batch-file-check");
	var displayValue = bfcObj.css("display");
	if ("none" == displayValue) {
		bfcObj.css("display", "");
		$(this).css("display", "none");
		$("#batch_enter").css("display", "").attr("data-batch-method", "2");
		$("#batch_cancel").css("display", "");
	}
})

$("#batch_enter").on("click", function() {
	var batchMethod = $(this).attr("data-batch-method");
	var fileCodes = "";
	$(".batch-file-check.checked").each(function() {
		fileCodes += $(this).attr("file_code") + ",";
	})
	if (fileCodes.length < 2) {
		layer.msg("文件未选择");
	} else {
		fileCodes = fileCodes.substr(0, fileCodes.length - 1);
	}
	switch (batchMethod) {
	case "1":
		// 批量下载
		downloadFiles(fileCodes, $('#hidden_project_level').val())
		break;
	case "2":
		// 批量分享
		$.ajax({
			url : "file/insShareFiles.do",
			type : 'post',
			data : {
				'project_code' : $('#hidden_project_code').val(),
				'project_level' : $('#hidden_project_level').val(),
				'file_codes' : fileCodes
			},
			dataType : 'json',
			success : function(result) {
				cancelBtnBatch();
				if (result.code == 0) {
					shareFiles(result.data)
				} else {
					layer.msg(result.msg)
				}
			},
			error : function() {
				layer.msg("服务器未响应")
			}
		});
		break;
	}
})

$(".batch-file-check").on("click", function() {
	if ($(this).hasClass("noChecked")) {
		$(this).removeClass("noChecked").addClass("checked");
	} else {
		$(this).removeClass("checked").addClass("noChecked");
	}
})

$("#batch_cancel").on("click", function() {
	cancelBtnBatch();
})

function cancelBtnBatch() {
	$(".batch-file-check").removeClass("checked").addClass("noChecked").css(
			"display", "none");
	$("#batch_enter").css("display", "none").attr("data-batch-method", "");
	$("#batch_cancel").css("display", "none").attr("data-batch-method", "")
	$("#batch_download,#batch_share").css("display", "");
}

$('.form_datetime').datetimepicker({
	language : 'zh-CN',
	weekStart : 1,// 星期几为周一
	autoclose : 1,
	startView : 4,
	minView : 2,
	maxView : 4,
	todayHighlight : true,
	todayBtn : true,
	format : "yyyy-mm-dd"
});

function formSubmit() {
	$('#search').submit();
}

$(".displayBtn").on("click", function() {
	var obj = $(this);
	var dataStr = obj.attr("data-info");
	var fileInfos = dataStr.split(",");
	display(fileInfos[0], fileInfos[1], fileInfos[2], obj);
})

function display(suffix, code, name, obj) {
	// 需要进行分类
	switch (suffix) {
	case ".xls":
	case ".xlsx":
	case ".doc":
	case ".docx":
	case ".ppt":
	case ".pptx":
	case ".pdf":
		var str = $("base").attr('href') + "file/disPdf.do?file_info=" + code
				+ "," + name;
		$("#displayValues").val(str);
		$("#displayForm").submit();
		break;
	case ".jpe":
	case ".jpeg":
	case ".jpg":
	case ".png":
		var imgObj = obj.parent().parent().parent().find(".tempImg");
		var file_info = code + "," + name
		$.ajax({
			url : "file/disImg.do",
			data : {
				"file_info" : file_info
			},
			type : "post",
			dataType : "json",
			success : function(result) {
				if (result.code == 0) {
					imgObj.attr("data-src", result.data);
					imgObj.viewer({
						url : 'data-src',
						hide : function(e) {
							$.ajax({
								url : 'file/delTempImg.do',
								type : 'post',
								data : {
									"delete_file_path" : result.data
								},
								dataType : "json",
								success : function() {
									return;
								}
							})
							imgObj.viewer('destroy')
						},
						navbar : false,
						title : false,
						keyboard : false,
					}).viewer("view");
				} else {
					layer.msg(result.msg);
				}
			},
			error : function() {
				layer.msg("服务未响应")
			}
		})
		break;
	default:
		layer.msg("不支持的文件格式")
		break;
	}
}

function downloadFile(code, level) {
	var data = "file_code=" + code + "&file_level=" + level;
	var result = ajaxPost('file/downloadCheck.do', data, false);
	if (null != result) {
		if (result.code === 0) {
			window.location.href = "file/downloadFile.do?file_name="
					+ result.data;
		} else {
			layer.msg(result.msg);
		}
	} else {
		layer.msg("服务器未响应");
	}
}

function downloadFileCheck(code, level) {
	var resultArray = new Array(2);
	resultArray[0] = -1;
	resultArray[1] = "";
	var data = "file_code=" + code + "&file_level=" + level;
	var result = ajaxPost('file/downloadCheck.do', data, false);
	if (null != result) {
		resultArray[0] = result.code;
		resultArray[1] = result.data;
	} else {
		layer.msg("服务器未响应")
	}
	return resultArray;
}

function downloadFiles(fileCodes, level) {
	var fileCodesArray = fileCodes.split(",");
	for (var index = 0; index < fileCodesArray.length; index++) {
		var vaule = fileCodesArray[index];
		var result = downloadFileCheck(vaule, level);
		console.log(result[1]);
		if (result[0] == 0) {
			if (index == (fileCodesArray.length - 1)) {
				window.open("file/downloadFile.do?file_name=" + result[1],
						"_self");
			} else {
				window.open("file/downloadFile.do?file_name=" + result[1]);
			}
		}
	}
	cancelBtnBatch();
}

function shareProject(projectCode, projectLevel, userCode) {
	var str = $("base").attr('href') + "pro/sp.do?project_code=" + projectCode
			+ "&project_level=" + projectLevel + "&user_code=" + userCode;
	$("#shareUrl").html(str)
	layer.open({
		type : 1,
		skin : 'demo-class', // 样式类名
		area : [ '450px', '150px' ],
		closeBtn : 1,
		anim : 0,
		shadeClose : true, // 开启遮罩关闭
		content : $("#share")
	})
}
function shareFile(fileCode, fileLevel, projectCode) {
	var str = $("base").attr('href') + "file/sf.do?file_code=" + fileCode
			+ "&file_level=" + fileLevel + "&project_code=" + projectCode;
	$("#shareUrl").html(str)
	layer.open({
		type : 1,
		skin : 'demo-class', // 样式类名
		area : [ '450px', '150px' ],
		closeBtn : 1,
		anim : 0,
		shadeClose : true, // 开启遮罩关闭
		content : $("#share")
	})
}

function shareFiles(shareCode) {
	var str = $("base").attr('href') + "file/shareFiles.do?share_code="
			+ shareCode;
	$("#shareUrl").html(str)
	layer.open({
		type : 1,
		skin : 'demo-class', // 样式类名
		area : [ '450px', '150px' ],
		closeBtn : 1,
		anim : 0,
		shadeClose : true, // 开启遮罩关闭
		content : $("#share")
	})
}

function intoclick() {
	$("#thediv").toggle();
}

function losePoint() {
	$("#thediv").hide();
}

function allread() {
	var list = [];
	for (var i = 0; i < $(".adviceCode").length; i++) {
		list.push($(".adviceCode")[i].innerHTML)
	}
	;
	$.ajax({
		url : 'pro/isRead.do',
		type : 'post',
		data : "list=" + JSON.stringify(list),
		dateType : 'json',
		success : function() {
			toload();
		}
	})
}

function toload() {
	$
			.ajax({
				url : 'index/getAllMsg.do',
				type : 'post',
				data : "",// 发送服务器的数据
				dataType : "json",// 返会值的类型
				success : function(data) {
					var str = "";
					if (data.data.adNum != 0) {
						$('#num').html(data.data.adNum);
						$("#notification-count").attr("class", "label unread");
						for (var i = 0; i < data.data.adviceMsg.length; i++) {
							str += "<span class='title'><span class='target'>"
									+ data.data.adviceMsg[i].logMsg
									+ "</span><span class='adviceCode' style='display: none'>"
									+ data.data.adviceMsg[i].adviceCode
									+ "</span></span><hr style='margin:0px 0px 3px 0px'>";
						}
						;
						$("#msg").html(str);
					} else {
						$("#notification-count").attr("class", "label");
						$("#msg")
								.html(
										"<span style='width:100%;text-align:center;display:block;'>- 没有新通知 -</span>");
					}
				}
			})
}

$(document).bind('click', function(e) {
	var e = e || window.event; // 浏览器兼容性
	var elem = e.target || e.srcElement;
	while (elem) { // 循环判断至跟节点，防止点击的是div子元素
		if (elem.id && elem.id == 'both') {
			return;
		}
		elem = elem.parentNode;
	}
	$('#thediv').css('display', 'none'); // 点击的不是div或其子元素
});

$(".project-check").on(
		'click',
		function() {
			var checkedObj = $(this);
			var data = "project_code=" + checkedObj.attr('project_code')
					+ "&project_level=" + checkedObj.attr('project_level');
			layer.msg('确认后无法更改项目内容,是否完成任务', {
				time : 0,
				btn : [ '确定', '取消' ],
				yes : function(index) {
					layer.close(index);
					var result = ajaxPost('pro/proStatus.do', data, false);
					if (null != result) {
						if (result.code == 0) {
							location.reload();
						} else {
							layer.msg(result.msg);
						}
					} else {
						layer.msg('服务未响应');
					}
				}
			})
		})

/*
 * function deleteProject(code,level){ var data =
 * "project_code="+code+"&project_level="+ level; layer.msg('是否删除项目',{ time:0,
 * btn:['确定','取消'], yes:function(index){ layer.close(index); var result =
 * ajaxPost('pro/delPro.do',data,false); if(null != result ){ if(result.code ==0 ){
 * if(level==0){ $("#back_index").click(); }else{ $("#back_page").click(); }
 * }else if(result.code ==4200){ window.location.href='user/logout.do' }else{
 * layer.msg(result.msg); } }else{ layer.msg('服务未响应'); } } }) }
 */
/*
 * function lockProject(code,level){ var data =
 * "project_code="+code+"&project_level="+ level; layer.msg('是否锁定项目',{ time:0,
 * btn:['确定','取消'], yes:function(index){ layer.close(index); var result =
 * ajaxPost('pro/lockPro.do',data,false); if(null != result ){ if(result.code
 * ==0 ){ if(level==0){ $("#back_index").click(); }else{
 * $("#back_page").click(); } }else if(result.code ==4200){
 * window.location.href='user/logout.do' }else{ layer.msg(result.msg); } }else{
 * layer.msg('服务未响应'); } } }) }
 */
function lockFile(code, level) {
	var data = "file_code=" + code + "&file_level=" + level;
	layer.msg('是否锁定文件', {
		time : 0,
		btn : [ '确定', '取消' ],
		yes : function(index) {
			layer.close(index);
			var result = ajaxPost('file/lockFile.do', data, false);
			if (null != result) {
				layer.msg(result.msg);
				if (result.code == 0) {
					location.reload();
				} else if (result.code == 4200) {
					window.location.href = 'user/logout.do'
				}
			} else {
				layer.msg('服务未响应');
			}
		}
	})
}

function delFile(code, level) {
	var data = "file_code=" + code + "&file_level=" + level;
	layer.msg('是否解锁文件', {
		time : 0,
		btn : [ '确定', '取消' ],
		yes : function(index) {
			layer.close(index);
			var result = ajaxPost('file/unLockFile.do', data, false);
			if (null != result) {
				layer.msg(result.msg);
				if (result.code == 0) {
					location.reload();
				} else if (result.code == 4200) {
					window.location.href = 'user/logout.do'
				}
			} else {
				layer.msg('服务未响应');
			}
		}
	})
}

function unLockFile(code, level) {
	var data = "file_code=" + code + "&file_level=" + level;
	layer.msg('是否锁定文件', {
		time : 0,
		btn : [ '确定', '取消' ],
		yes : function(index) {
			layer.close(index);
			var result = ajaxPost('file/lockFile.do', data, false);
			if (null != result) {
				layer.msg(result.msg);
				if (result.code == 0) {
					location.reload();
				} else if (result.code == 4200) {
					window.location.href = 'user/logout.do'
				}
			} else {
				layer.msg('服务未响应');
			}
		}
	})
}

function changeFileEventType() {
	var result = ajaxGet("dic/getFet.do", false);
	var fileEventTypeSelectObj = $("#xh_type_select");
	fileEventTypeSelectObj.empty();
	fileEventTypeSelectObj.append($("<option />").text("请选择事件类型").attr("value",
			"").attr("selected", "selected"));
	if (result.code == 0) {
		$(result.data).each(
				function() {
					fileEventTypeSelectObj.append($("<option />").text(
							this.dicName).attr("value", this.dicCode));
				})
	}
}

function changeFileEventLevel() {
	var result = ajaxGet("dic/getFel.do", false);
	var fileEventLevelSelectObj = $("#xh_level_select");
	fileEventLevelSelectObj.empty();
	fileEventLevelSelectObj.append($("<option />").text("请选择事件级别").attr(
			"value", "").attr("selected", "selected"));
	if (result.code == 0) {
		$(result.data).each(
				function() {
					fileEventLevelSelectObj.append($("<option />").text(
							this.dicName).attr("value", this.dicCode));
				})
	}
}

function changeResearchUserInfo() {
	var projectCode = $("#project_code").val();
	var result = ajaxGet("user/researchUserKv.do?project_code=" + projectCode,
			false);
	var fileEventLevelSelectObj = $("#xh_research_user_select");
	fileEventLevelSelectObj.empty();
	fileEventLevelSelectObj.append($("<option />").text("请选择调研人").attr("value",
			"").attr("selected", "selected"));
	if (result.code == 0) {
		$(result.data).each(
				function() {
					fileEventLevelSelectObj.append($("<option />").text(
							this.userName).attr("value", this.userCode));
				})
	}

}