$(function() {
	// 创建人默认为项目参与者
	var createInfo = $("#userInfo").val();
	$("input[name='project_edit'][value='" + createInfo + "']").prop("checked",
			"checked").on("change", function() {
		$(this).prop("checked", "checked");
	})
	$("input[name='project_read'][value='" + createInfo + "']").prop(
			"disabled", "disabled");

	// 控制权限唯一
	$("input[name='project_edit']").on("change", function() {
		var editVal = $(this).val();
		changeFileShow(editVal);
	});
	$("input[name='project_read']").on("change", function() {
		var readVal = $(this).val();
		changeFileDownload(readVal);
	})
});
function changeFileShow(editVal) {
	var obj = $("input[name='project_read'][value='" + editVal + "']");
	if (obj.is(":checked")) {
		obj.prop("checked", false);
	}
}
function changeFileDownload(readVal) {
	var obj = $("input[name='project_edit'][value='" + readVal + "']");
	if (obj.is(":checked")) {
		obj.prop("checked", false);
	}
}

function clickBtn() {
	$("#projectFrom").ajaxSubmit({
		url : 'pro/insPro.do',
		type : 'post',
		dataType : 'json',
		success : function(result) {
			layer.msg(result.msg);
			if (result.code == 0) {
				self.location = document.referrer;
			}
		},
		error : function(result) {
			layer.msg("服务未响应");
		}
	});
}