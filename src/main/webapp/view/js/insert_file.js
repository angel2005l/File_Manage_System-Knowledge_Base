$(function() {
	initJoinUser();
	// 控制权限唯一
	$("input[name='file_download']").on("change", function() {
		var dowloadVal = $(this).val();
		changeFileShow(dowloadVal);
	});
	$("input[name='file_show']").on("change", function() {
		var showVal = $(this).val();
		changeFileDownload(showVal);
	})
	
	$("input[name='file_all']").on('click',function(){
		var thisObj = $(this);
		if(thisObj.is(":checked")){
			switch (thisObj.val()) {
				case "download":
					$("input[name='file_all'][value='show']").prop("checked", false);
					$("input[name='file_download']").prop("checked", "checked");
					$("input[name='file_show']").prop("checked", false);			
					break;
				case "show":
					$("input[name='file_all'][value='download']").prop("checked", false);
					$("input[name='file_show']").prop("checked", "checked");
					$("input[name='file_download']").prop("checked", false);
					break;
			}
			thisObj.prop("checked", "checked");
		}else{
			switch (thisObj.val()) {
			case "download":
			$("input[name='file_all'][value='download']").prop("checked", false);
			$("input[name='file_download']").prop("checked", false);
			break;
		case "show":
			$("input[name='file_all'][value='show']").prop("checked", false);
			$("input[name='file_show']").prop("checked", false);
			break;
		}
		thisObj.prop("checked", false);
	}
		initJoinUser();
	})
	changeFileEventType();
	changeFileEventLevel();
	$('.form_datetime').datetimepicker({
		language : 'zh-CN',
		weekStart : 1,// 星期几为周一
		autoclose : 1,
		startView : 4,
		minView : 2,
		maxView : 4,
		endDate : new Date(),
		todayHighlight : true,
		todayBtn : true,
		format : "yyyy-mm-dd"
	});
})

function initJoinUser(){
	// 上传文件者默认为项目参与者
	var createInfo = $("#userInfo").val();
	$("input[name='file_download'][value='" + createInfo + "']").prop(
			"checked", "checked").on("change", function() {
		$(this).prop("checked", "checked");
	})
	$("input[name='file_show'][value='" + createInfo + "']").prop("disabled",
			"disabled").prop("checked", false);
}

function changeFileShow(dowloadVal) {
	var obj = $("input[name='file_show'][value='" + dowloadVal + "']");
	if (obj.is(":checked")) {
		obj.prop("checked", false);
	}
}
function changeFileDownload(showVal) {
	var obj = $("input[name='file_download'][value='" + showVal + "']");
	if (obj.is(":checked")) {
		obj.prop("checked", false);
	}
}
function clickBtn() {
	var projectCode = $("#project_code").val();
	var projectLevel = $("#project_level").val();
	var uploadbtnObj = $("#uploadBtn");
	if (checkLabel()) {
		uploadbtnObj.attr("disabled","disabled");
		$("#fileFrom").ajaxSubmit({
			url : 'file/upFile.do',
			type : 'post',
			dataType : 'json',
			success : function(result) {
				layer.msg(result.msg);
				if (result.code == 0) {
					self.location = document.referrer;
				}
				uploadbtnObj.removeAttr("disabled");
			},
			error : function() {
				layer.msg("服务未响应");
			}
		});
	}
}

function changeFileEventType() {
	var result = ajaxGet("dic/getFet.do", false);
	var fileEventTypeSelectObj = $("#file_event_type");
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
	var fileEventLevelSelectObj = $("#file_event_level");
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

function checkLabel() {
	var fileUrl = $("input[name='file_data']").val();
	var fileEventType = $("#file_event_type").val();
	var fileEventLevel = $("#file_event_level").val();
	var fileResearchUserCode = $("#file_research_user_code").val();
	var fileResearchStartTime = $("#file_research_start_time").val();
	if (fileUrl == '') {
		layer.msg("请选择文件");
		return false;
	} else if ((fileUrl.indexOf(" ") >= 0)) {
		layer.msg("文件名不能有空格");
		return false;
	} else if (fileEventType == '') {
		layer.msg("请选择事件类型");
		return false;
	} else if (fileEventLevel == '') {
		layer.msg("请选择事件级别");
		return false;
	} else if (fileResearchUserCode == '') {
		layer.msg("请选择调研人");
		return false;
	} else if (fileResearchStartTime == '') {
		layer.msg("请选择调研时间");
		return false;
	}
	return true;
}
