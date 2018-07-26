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
function checkFile() {
	var fileUrl = $("#file_data").val();
	if (fileUrl == '') {
		layer.msg("请选择文件");
	} else if (fileUrl.indexOf(" ") >= 0) {
		layer.msg("文件名不能有空格");
		return false;
	}
}