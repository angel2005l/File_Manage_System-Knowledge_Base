$(function() {
	// 上传文件者默认为项目参与者
	var createInfo = $("#userInfo").val();
	$("input[name='file_download'][value='" + createInfo + "']").prop(
			"checked", "checked").on("change", function() {
		$(this).prop("checked", "checked");
	})
	$("input[name='file_show'][value='" + createInfo + "']").prop("disabled",
			"disabled");
	// 控制权限唯一
	$("input[name='file_download']").on("change", function() {
		var dowloadVal = $(this).val();
		changeFileShow(dowloadVal);
	});
	$("input[name='file_show']").on("change", function() {
		var showVal = $(this).val();
		changeFileDownload(showVal);
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
	if (checkFile) {
		$("#fileFrom").ajaxSubmit({
			url : 'file/upFile.do',
			type : 'post',
			dataType : 'json',
			success : function(result) {
				layer.msg(result.msg);
				if (result.code == 0) {
					self.location = document.referrer;
				}
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

function changeFileEventLevel(){
	var result = ajaxGet("dic/getFel.do", false);
	var fileEventLevelSelectObj = $("#file_event_level");
	fileEventLevelSelectObj.empty();
	fileEventLevelSelectObj.append($("<option />").text("请选择事件级别").attr("value",
			"").attr("selected", "selected"));
	if (result.code == 0) {
		$(result.data).each(
				function() {
					fileEventLevelSelectObj.append($("<option />").text(
							this.dicName).attr("value", this.dicCode));
				})
	}
}

function checkFile() {
	var fileUrl = $("#file_data").val();
	if (fileUrl == '') {
		layer.msg("请选择文件");
	} else if (fileUrl.indexOf(" ") >= 0) {
		layer.msg("文件名不能有空格");
		return false;
	}
}
