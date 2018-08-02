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
	
//多部门协同
	var cudc = $("#create_user_dept_code").val();
	changeEmpHidenAndShow("join_work",cudc,true);
	changeEmpHidenAndShow("read_work",cudc,true);
	onlyDeptHidenAndShow('join');
	onlyDeptHidenAndShow('read');
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
	var createProjectBtnObj = $("#createProjectBtn");
	if(checkLabel()){
		createProjectBtnObj.attr("disabled","disabled");
		$("#projectFrom").ajaxSubmit({
			url : 'pro/insPro.do',
			type : 'post',
			dataType : 'json',
			success : function(result) {
				layer.msg(result.msg);
				if (result.code == 0) {
					self.location = document.referrer;
				}else if(result.code ==4200){
					window.location.href='user/logout.do'
				}
			createProjectBtnObj.removeAttr("disabled");
		},
		error : function(result) {
			layer.msg("服务未响应");
		}
	});
	}
}
	function onlyDeptHidenAndShow(elName){
		var spanName = elName+"_dept";
		var labelName = elName+"_work";
		$("."+spanName).click(function(){
			var joinDeptSpanObj = $(this);
			var deptCode = joinDeptSpanObj.attr('data-dept_code');
			//是否有selected
			if(joinDeptSpanObj.hasClass('selected')){
				joinDeptSpanObj.removeClass("selected");
				changeEmpHidenAndShow(labelName,deptCode,false);
			}else{
				joinDeptSpanObj.addClass("selected");
				changeEmpHidenAndShow(labelName,deptCode,true);
			}
		})
		
		$(".all_"+spanName).click(function(){
			var joinDeptSpanObj = $(this);
			//是否有selected
			if(joinDeptSpanObj.hasClass('selected')){//取消
				var deptCode = joinDeptSpanObj.attr("data-dept_code");
				joinDeptSpanObj.removeClass("selected");
				$("."+spanName).removeClass("selected");
				$("."+spanName+"[data-dept_code ='"+deptCode+"']").addClass("selected");
				changeEmpHidenAndShow(labelName,'all',false);
				changeEmpHidenAndShow(labelName,deptCode,true);
			}else{//添加
				$("."+spanName).addClass("selected")
				joinDeptSpanObj.addClass("selected");
				changeEmpHidenAndShow(labelName,'all',true);
			}
		})
	}
	
	function changeEmpHidenAndShow(elName,deptCode,isShow){
		var displayStr = isShow ? 'inline-block':'none';
		if('all'==deptCode){
			$("."+elName).css('display',displayStr);
		}else{
			$("."+elName+"[data-dept_code='"+deptCode+"']").css('display',displayStr)
		}
	}
	
	function checkLabel(){
		var projectName = $("#project_name").val();
		if(projectName==''){
			layer.msg("项目名称不能为空");
			return false;
		}else if(projectName.length >32){
			layer.msg("项目名称不能大于32位,请修改文件名称");
			return false;
		}
		return true;
	}
