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